package cn.microvideo.sdk.qywx.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.microvideo.sdk.qywx.config.DomainUtils;
import cn.microvideo.sdk.qywx.config.WXConfigUtils;
import cn.microvideo.sdk.qywx.exception.ErrcodeException;
import cn.microvideo.sdk.qywx.exception.HttpSessionInvalidException;
import cn.microvideo.sdk.qywx.utils.HttpUtils;
import cn.microvideo.sdk.qywx.utils.JSONUtils;

/**
 * 访问接口凭证
 * @author Aaron.tian
 *
 */
public final class AccessToken {
	/**
	 * GET 获取AccessToken的地址 
	 */
	public final static String TOKEN_URL = DomainUtils.getQywxDomain()+"/cgi-bin/gettoken";
	
	/**
	 * Token 缓存，有效期7200秒
	 */
	private final static Map<String, Token> ATCache = new ConcurrentHashMap<String, Token>();
	
	/**
	 * 获取管理组访问凭证
	 * @return 凭证
	 * @throws ErrcodeException 
	 * @throws WechatException 
	 * @throws Exception 
	 */
	public static String getAccessToken(String secret) throws ErrcodeException {
		Token token = ATCache.get(secret);
		if(token != null && token.isValid()){
			return token.getAccess_token();
		}else{
			return getNewAccessToken(secret);
		}
	}
	
	/**
	 * 获取管理组访问凭证；并更新刷新缓存中的凭证；
	 * @return 凭证
	 * @throws ErrcodeException 
	 */
	public static String getNewAccessToken(String secret) throws ErrcodeException {
		try{
			StringBuilder url = new StringBuilder(TOKEN_URL);
			url.append("?corpid=");
			url.append(WXConfigUtils.getCorpid());
			url.append("&corpsecret=");
			url.append(secret);
			String json = HttpUtils.get(url.toString());
			Token token = JSONUtils.JSON2Object(json, Token.class);
			token.checkErrorCode();
			ATCache.put(secret, token);
			return token.getAccess_token();
		}catch(HttpSessionInvalidException e){
			throw new RuntimeException(e);
		}
	}
	
}
