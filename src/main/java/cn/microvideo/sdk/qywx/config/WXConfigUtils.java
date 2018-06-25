package cn.microvideo.sdk.qywx.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import cn.microvideo.sdk.qywx.config.WXConfig.Agents;
import cn.microvideo.sdk.qywx.exception.ConfigException;
import cn.microvideo.sdk.qywx.utils.XMLUtils;

/**
 * XML配置文件工具类
 * @author Aaron.tian
 *
 */
public final class WXConfigUtils {

	/**
	 * 配置文件对象
	 */
	private static WXConfig WX_CONFIG = null;
	
	/**
	 * 从XML中加载配置文件<br>
	 * @param classpath syntax -> classpath:cn/microvideo/sdk/qywx/config/weixin-config.xml
	 */
	public static void load(String classpath){
		classpath = classpath.replaceFirst("^classpath[^a-zA-Z_]+", "");
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
		try (InputStream input = currentClassLoader.getResourceAsStream(classpath)) {
			String xml = IOUtils.toString(input, EnvConfig.charset);
			WX_CONFIG = XMLUtils.xml2Object(xml, WXConfig.class);
		}catch (IOException e) {
			throw new ConfigException("配置文件加载错误",e);
		}
	}
	/**
	 * 获取配置文件信息
	 * @return
	 */
	public static WXConfig getWX_CONFIG() {
		return WX_CONFIG;
	}
	
	/**
	 * 获取企业号ID
	 * @return 企业号ID
	 */
	public static String getCorpid(){
		return WX_CONFIG.getConfig().getCorpid();
	}
	/**
	 * 获取管理组密钥
	 * @return 密钥
	 */
	public static String getContactsSecrect(){
		return WX_CONFIG.getConfig().getContactsSecret();
	}
	/**
	 * 获取管理组密钥
	 * @return 密钥
	 */
	public static String getContactsListener(){
		return WX_CONFIG.getConfig().getContactsListener();
	}
	/**
	 * 是否启用SSL
	 * @return
	 */
	public static boolean isEnableSSL(){
		return WX_CONFIG.getConfig().getEnableSSL();
	}
	/**
	 * 获取应用回调令牌
	 * @return 回调令牌
	 */
	public static String getAgentToken(){
		return WX_CONFIG.getConfig().getAgents().getToken();
	}
	/**
	 * 获取应用回调密钥
	 * @return 回调密钥
	 */
	public static String getAgentEncodingAesKey(){
		return WX_CONFIG.getConfig().getAgents().getEncodingaeskey();
	}
	/**
	 * 不需要拦截的路径
	 * @return 路径数组
	 */
	public static String[] getExcludeMapping(){
		String[] mapping = WX_CONFIG.getExcludeMapping().getPath();
		if(mapping == null){
			mapping = new String[0];
		}
		return mapping;
	}
	/**
	 * 微信应用所在包
	 * @return
	 */
	public static String getAgentBasePackage(){
		return WX_CONFIG.getAgentScan().getBasePackage();
	}
	/**
	 * 应用配置
	 * @return
	 */
	public static Agents getAgents(){
		return WX_CONFIG.getConfig().getAgents();
	}
	
	/**
	 * 获取应用密钥
	 * @param agentId 应用ID
	 * @return 密钥
	 */
	public static String getAgentSecret(int agentId) {
		WXConfig.Agent[] configAgents = WXConfigUtils.getAgents().getAgent();
		for(WXConfig.Agent agent : configAgents){
			if(agentId == agent.getId()){
				return agent.getSecret();
			}
		}
		throw new ConfigException("配置文件错误，无法获取应用密钥");
	}
}
