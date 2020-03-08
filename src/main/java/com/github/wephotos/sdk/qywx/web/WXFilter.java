package com.github.wephotos.sdk.qywx.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.wephotos.sdk.qywx.config.WXConfigUtils;
import com.github.wephotos.sdk.qywx.oauth2.OAuth2;
import com.github.wephotos.sdk.qywx.utils.WEBUtils;
import com.github.wephotos.sdk.qywx.utils.WXUtils;

/**
 * 系统过滤器
 * 处理用户认证
 * @author Aaron.tian
 *
 */
public class WXFilter implements Filter {
	/**
	 * 当请求中包含此参数，使用此参数的值作为当前用户ID，不再进行重定向，可用于调试
	 */
	public static final String REQ_WX_USER_ID_NAME = "wx_user_id";
	// log4j
	public static final Logger logger = LoggerFactory.getLogger(WXFilter.class);
		
	private static Pattern regex_pattern = Pattern.compile("/.*");
	/**
	 * 不拦截的路径
	 */
	private static String[] excludeMapping;
	/**
	 * 拦截的URL
	 */
	private String pattern = "/*";
	
	/**
	 * 在默认构造中加载系统放行的URL路径包含字符
	 */
	public WXFilter() {
		
	}
	
	/**
	 * 使用url-pattern创建过滤器
	 * @param pattern
	 */
	public WXFilter(String pattern) {
		if (pattern != null) {
			this.pattern = pattern;
			compilePattern();
		}
	}
	
	public void setPattern(String pattern) {
		if (pattern != null) {
			this.pattern = pattern;
			compilePattern();
		}
	}

	/**
	 * 编译映射规则
	 */
	private void compilePattern() {
		String regex = pattern.replaceAll("\\.", "\\\\.").replaceAll("\\*", ".*");
		regex_pattern = Pattern.compile(regex);
	}
	
	/**
	 * 判断请求路径是否匹配
	 * 
	 * @param uri
	 * @return
	 */
	private boolean isMapping(String servletPath) {
		if ("/*".equals(pattern)) {
			return true;
		}
		if (!this.pattern.contains("*")) {
			return servletPath.contains(pattern);
		}
		return regex_pattern.matcher(servletPath).matches();
	}
	
	/**
	 * 是否例外路径
	 * 
	 * @param uri
	 * @return
	 */
	private boolean isExcludeMapping(String servletPath) {
		for (String path : excludeMapping) {
			if (servletPath.contains(path)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)sRequest;
		HttpServletResponse response = (HttpServletResponse)sResponse;
		HttpSession session = request.getSession();
		//绑定会话到当前线程
		ThreadSession.set(session);
		String servletPath = request.getServletPath();
		
		if (!isMapping(servletPath)) {
			chain.doFilter(request, response);
			return;
		}
		if (isExcludeMapping(servletPath)) {
			chain.doFilter(request, response);
			return;
		}
		
		String userId = request.getParameter(REQ_WX_USER_ID_NAME);
		if(StringUtils.isNotBlank(userId)){
			WEBUtils.setSessionUserId(session, userId);
			chain.doFilter(request, response);
			return;
		}
		userId = WEBUtils.getSessionUserId(session);
		if(StringUtils.isNotEmpty(userId)){
			chain.doFilter(request, response);
			return;
		}
		userId = OAuth2.oauth2(request, response);
		//系统重定向后直接返回
		if(StringUtils.isEmpty(userId)){
			return;
		}
		WEBUtils.setSessionUserId(session, userId);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		String classpath = context.getInitParameter(WXListener.CONFIG_LOCATION_NAME);
		WXUtils.initConfig(classpath);
		excludeMapping = WXConfigUtils.getExcludeMapping();
		logger.info("weixin exclude mapping ->" + Arrays.toString(excludeMapping));
	}
	
	@Override
	public void destroy() {
		
	}
}


