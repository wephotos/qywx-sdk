package cn.microvideo.sdk.qywx.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import cn.microvideo.sdk.qywx.config.WXConfigUtils;
import cn.microvideo.sdk.qywx.oauth2.OAuth2;
import cn.microvideo.sdk.qywx.utils.WEBUtils;

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
	/**
	 * 不拦截的路径
	 */
	private static String[] excludeMapping;
	/**
	 * 在默认构造中加载系统放行的URL路径包含字符
	 */
	public WXFilter() {
		excludeMapping = WXConfigUtils.getExcludeMapping();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		//绑定会话到当前线程
		ThreadSession.set(session);
		String requestURI=httpRequest.getRequestURI();
		for(String uri : excludeMapping){
			if(requestURI.contains(uri)){
				chain.doFilter(request, response);
				return;
			}
		}
		String userId = request.getParameter(REQ_WX_USER_ID_NAME);
		if(StringUtils.isNotBlank(userId)){
			WEBUtils.setSessionUserId(session, userId);
			chain.doFilter(request, response);
			return;
		}
		userId = WEBUtils.getSessionUserId(session);
		if(StringUtils.isNotEmpty(userId)){
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		userId = OAuth2.oauth2(httpRequest, httpResponse);
		//系统重定向后直接返回
		if(StringUtils.isEmpty(userId)){
			return;
		}
		WEBUtils.setSessionUserId(session, userId);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}
}


