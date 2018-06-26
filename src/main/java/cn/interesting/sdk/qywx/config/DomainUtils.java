package cn.interesting.sdk.qywx.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cn.interesting.sdk.qywx.exception.ConfigException;
import cn.interesting.sdk.qywx.oauth2.OAuth2;

/**
 * 获取微信域名
 * @author TQ
 *
 */
public final class DomainUtils {
	/**
	 * 企业微信API默认域名
	 */
	private final static String QYAPI_DOMAIN_DEFAULT = "https://qyapi.weixin.qq.com";
	private final static String OAUTH2_DOMAIN_DEFAULT = "https://open.weixin.qq.com";
	/**
	 * 属性文件
	 */
	private final static Properties properties = new Properties();
	//静态加载
	static{
		new DomainUtils();
	}
	/**
	 * 私有构造
	 */
	private DomainUtils(){
		InputStream input = null;
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			input = classLoader.getResourceAsStream("java/cn/microvideo/sdk/qywx/config/domain.properties");
			if(input != null){
				properties.load(input);
			}
		}catch (IOException e) {
			throw new ConfigException("读取微信域名失败",e);
		}finally{
			try {
	            if (input != null) {
	            	input.close();
	            }
	        } catch (IOException ioe) {
	            // ignore
	        }
		}
	}
	/**
	 * 获取企业微信API的域名
	 * @return 域名
	 */
	public static String getQywxDomain(){
		return properties.getProperty("qywx-domain", QYAPI_DOMAIN_DEFAULT);
	}

	/**
	 * 获取企业微信OAuth2.0的域名
	 * @return 域名
	 */
	public static String getOAuth2Domain(){
		return properties.getProperty("oauth2-domain", OAUTH2_DOMAIN_DEFAULT);
	}
	
	public static void main(String[] args) {
		System.out.println(OAuth2.AUTHORIZE_URL);
	}
}
