package cn.interesting.sdk.qywx.utils;

import java.util.Map;

import cn.interesting.sdk.qywx.WeChatRES;
import cn.interesting.sdk.qywx.agent.AgentFactory;
import cn.interesting.sdk.qywx.config.WXConfigUtils;
import cn.interesting.sdk.qywx.exception.AccessTokenInvalidException;
import cn.interesting.sdk.qywx.exception.ErrcodeException;
import cn.interesting.sdk.qywx.exception.HttpSessionInvalidException;
import cn.interesting.sdk.qywx.token.AccessToken;


/**
 * 企业微信相关工具类
 * @author Administrator
 *
 */
public final class WXUtils {
	//配置文件是否初始化
	private static volatile boolean initialized = false;

	/**
	 * 向微信服务器发起GET请求
	 * @param url
	 * @param clazz
	 * @param secret 用于换取本次请求的AccessToken的密钥
	 * @return
	 * @throws ErrcodeException 
	 * @throws AccessTokenInvalidException
	 */
	public static <T extends WeChatRES> T APIGet(String url, Class<T> clazz, String secret) throws ErrcodeException {
		try{
			String json = HttpUtils.get(appendAccessToken(url, secret));
			T instance = JSONUtils.JSON2Object(json, clazz);
			return instance.checkErrorCode();
		}catch(HttpSessionInvalidException e){
			throw new RuntimeException(e);
		}catch (AccessTokenInvalidException e) {
			AccessToken.getNewAccessToken(secret);
			return APIGet(url, clazz, secret);
		}
	}
	
	/**
	 * 向微信服务器发起POST请求
	 * @param url
	 * @param params
	 * @param clazz
	 * @param secret
	 * @return
	 * @throws ErrcodeException 
	 * @throws AccessTokenInvalidException
	 */
	public static <T extends WeChatRES> T APIPost(String url, Map<String, String> params, Class<T> clazz, String secret) throws ErrcodeException {
		try{
			String json = HttpUtils.post(appendAccessToken(url, secret), params);
			T instance = JSONUtils.JSON2Object(json, clazz);
			return instance.checkErrorCode();
		}catch(HttpSessionInvalidException e){
			throw new RuntimeException(e);
		}catch (AccessTokenInvalidException e) {
			AccessToken.getNewAccessToken(secret);
			return APIPost(url, params, clazz, secret);
		}
	}
	/**
	 * 向微信服务器发起POST请求
	 * @param url
	 * @param body
	 * @param clazz
	 * @param secret
	 * @return
	 * @throws ErrcodeException 
	 * @throws AccessTokenInvalidException
	 */
	public static <T extends WeChatRES> T APIPost(String url, String body, Class<T> clazz, String secret) throws ErrcodeException {
		try{
			String json = HttpUtils.post(appendAccessToken(url, secret), body);
			T instance = JSONUtils.JSON2Object(json, clazz);
			return instance.checkErrorCode();
		}catch(HttpSessionInvalidException e){
			throw new RuntimeException(e);
		}catch (AccessTokenInvalidException e) {
			AccessToken.getNewAccessToken(secret);
			return APIPost(url, body, clazz, secret);
		}
	}
	
	/**
	 * 追加调用接口凭证
	 * @param url
	 * @param secret 密钥
	 * @return
	 * @throws ErrcodeException 
	 */
	private static String appendAccessToken(String url, String secret) throws ErrcodeException{
		if(url.contains("access_token")){
			return url;
		}else{
			url = url.contains("?") ? url.concat("&") : url.concat("?");
			return url.concat("access_token=").concat(AccessToken.getAccessToken(secret));
		}
	}
	
	/**
	 * 初始化配置信息
	 * @param classpath
	 */
	public static void initConfig(String classpath){
		if(classpath == null){
			throw new IllegalArgumentException("配置文件路径不能为空.");
		}
		if(!initialized) {
			initialized = true;
			WXConfigUtils.load(classpath);
			AgentFactory.loadAgent(WXConfigUtils.getAgentBasePackage());
		}
	}
}
