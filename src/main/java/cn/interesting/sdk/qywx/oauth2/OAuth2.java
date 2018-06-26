package cn.interesting.sdk.qywx.oauth2;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.interesting.sdk.qywx.agent.Agent;
import cn.interesting.sdk.qywx.agent.AgentFactory;
import cn.interesting.sdk.qywx.config.DomainUtils;
import cn.interesting.sdk.qywx.config.EnvConfig;
import cn.interesting.sdk.qywx.config.WXConfigUtils;
import cn.interesting.sdk.qywx.exception.ErrcodeException;
import cn.interesting.sdk.qywx.exception.WXUnCheckedException;
import cn.interesting.sdk.qywx.utils.WXUtils;

/**
 * 企业应用中的URL链接（包括自定义菜单或者消息中的链接），可以通过OAuth2.0验证接口来获取成员的身份信息。
 * 通过此接口获取成员身份会有一定的时间开销。对于频繁获取成员身份的场景，建议采用如下方案：
 * 1、企业应用中的URL链接直接填写企业自己的页面地址
 * 2、成员跳转到企业页面时，企业校验是否有代表成员身份的cookie，此cookie由企业生成
 * 3、如果没有获取到cookie，重定向到OAuth验证链接，获取成员身份后，由企业生成代表成员身份的cookie
 * 4、根据cookie获取成员身份，进入相应的页面
 * 注意，此URL的域名，必须完全匹配企业应用设置项中的
 * '可信域名'（如果你的redirect_uri有端口号，那'可信域名'也必须加上端口号），否则获取成员信息时会返回50001错误码。
 * 
 * @author Aaron.tian
 *
 */
public final class OAuth2 {

	/**
	 * 保存信息到Cookie中
	 */
	public static final String  OAUTH2_COOKIE= "OAUTH2_COOKIE";

	/**
	 * 企业获取code URL
	 * ?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	 */
	public static final String AUTHORIZE_URL = DomainUtils.getOAuth2Domain() + "/connect/oauth2/authorize";
	/**
	 * 根据企业CODE获取用户信息
	 * ?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENTID
	 */
	public static final String GETUSERINFO_URL = DomainUtils.getQywxDomain() + "/cgi-bin/user/getuserinfo";
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(OAuth2.class);
	/**
	 * oauth2 验证接口；获取账户，如果获取到缓存到Cookie中；
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 人员标识
	 */
	public static String oauth2(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String oauth2 = null;
		if(cookies != null){
			for(Cookie cookie:cookies){
				String cookieName = cookie.getName();
				if(OAUTH2_COOKIE.equals(cookieName)){
					oauth2 = cookie.getValue();
				}
			}
		}
		if(StringUtils.isNotEmpty(oauth2)){
			return oauth2;
		}
		String code = request.getParameter("code");
		if(StringUtils.isEmpty(code)){
			sendRedirect(response, getRedirectURL(request), "auto");
			return null;
		}else{
			OAuth2Response oAuth2Response = getUserInfo(code, getUsableSecret(request));
			String userId = oAuth2Response.getUserId();
			Cookie cookie = new Cookie(OAUTH2_COOKIE, userId);
			//last forever
			cookie.setMaxAge(Integer.MAX_VALUE);
			cookie.setPath("/");
			response.addCookie(cookie);
			return userId;
		}
	}
	
	/**
	 * 重定向到微信OAUTH2验证链接获取企业 CODE
	 * @param response
	 * @param redirectURI
	 * @param state 重定向后带上的参数
	 * @throws WechatException 
	 */
	public static void sendRedirect(HttpServletResponse response, String redirectURI, String state) {
		try {
			StringBuilder redirectURL = new StringBuilder(AUTHORIZE_URL);
			redirectURL.append("?appid="+WXConfigUtils.getCorpid());
			redirectURL.append("&redirect_uri="+URLEncoder.encode(redirectURI, EnvConfig.charset));
			redirectURL.append("&response_type=code");
			redirectURL.append("&scope=snsapi_base");
			redirectURL.append("&state="+state);
			redirectURL.append("#wechat_redirect");
			response.sendRedirect(redirectURL.toString());
		} catch (IOException e) {
			throw new RuntimeException("HTTP转发异常！",e);
		}
	}
	
	/**
	 * 根据企业code和应用密钥获取用户信息
	 * @param code 通过成员授权获取到的code
	 * @param secret 应用密钥
	 * @return 响应信息
	 * @throws WechatException 
	 */
	public static OAuth2Response getUserInfo(String code, String secret) {
		try {
			logger.debug("code:" + code + ",secret:" + secret);
			StringBuilder url = new StringBuilder(GETUSERINFO_URL);
			url = url.append("?code="+code);
			return WXUtils.APIGet(url.toString(), OAuth2Response.class, secret);
		} catch (ErrcodeException e) {
			throw new WXUnCheckedException(e.getMessage());
		}
	}
	
	/**
	 * 获取本次请求的重定向地址
	 * @param request
	 * @return
	 */
	public static String getRedirectURL(HttpServletRequest request){
		StringBuilder redirectURL = new StringBuilder(50);
		//由于代理服务器的原因，不能使用 HttpServletRequest#getScheme()进行判断
		if(WXConfigUtils.isEnableSSL()){
			redirectURL.append("https://");
		}else{
			redirectURL.append("http://");
		}
		redirectURL.append(getTrustedDomain(request));
		redirectURL.append(request.getRequestURI());
		String queryString = request.getQueryString();
		if(StringUtils.isNotEmpty(queryString)){
			redirectURL.append("?" + queryString);
		}
		logger.debug("redirectURL:" + redirectURL);
		return redirectURL.toString();
	}
	
	/**
	 * 获取可信域名，应与企业微信应用中配置的相同
	 * @return 可信域名
	 */
	private static String getTrustedDomain(HttpServletRequest request){
		StringBuilder trustedDomain = new StringBuilder();
		trustedDomain.append(request.getServerName());
		int port = request.getServerPort();
		if(port != 80 && port != 443){
			trustedDomain.append(":");
			trustedDomain.append(port);
		}
		return trustedDomain.toString();
	}
	
	/**
	 * 由于获取用户的access_token对应应用的可信域名需要与跳转的域名相同，所以需要获取一个可用的密钥
	 * @param request
	 * @return
	 */
	private static String getUsableSecret(HttpServletRequest request){
		String trustedDomain = getTrustedDomain(request);
		for(Agent agent : AgentFactory.AGENTS){
			if(agent.getAgentId() > 0 && trustedDomain.equals(agent.getTrustedDomain())){
				return agent.getSecret();
			}
		}
		for(Agent agent : AgentFactory.AGENTS){
			if(agent.getTrustedDomain() == null){
				return agent.getSecret();
			}
		}
		throw new WXUnCheckedException("在应用中找不到匹配的可信域名");
	}
}
