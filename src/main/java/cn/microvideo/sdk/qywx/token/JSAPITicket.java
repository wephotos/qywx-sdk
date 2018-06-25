package cn.microvideo.sdk.qywx.token;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import cn.microvideo.sdk.qywx.config.DomainUtils;
import cn.microvideo.sdk.qywx.exception.ErrcodeException;
import cn.microvideo.sdk.qywx.utils.WXUtils;

/**
 * jsapi_ticket是企业号号用于调用微信JS接口的临时票据
 * @author Aaron.tian
 *
 */
public final class JSAPITicket {
	/**
	 * 日志
	 */
	private static final Logger logger = Logger.getLogger(JSAPITicket.class);
	/**
	 * 采用http GET方式请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）
	 */
	private static final String TICKET_URL = DomainUtils.getQywxDomain()+"/cgi-bin/get_jsapi_ticket";
	
	/**
	 * jsapi_ticket 缓存，有效期7200秒
	 */
	private final static Map<String, Token> JATCache = new ConcurrentHashMap<String, Token>();

	/**
	 * 采用http GET方式请求获得jsapi_ticket
	 * （有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）
	 * @return jsapi_ticket
	 * @throws ErrcodeException 
	 * @throws WechatException 
	 */
	public static String getJSAPITicket(String secret) throws ErrcodeException {
		Token token = JATCache.get(secret);
		if(token != null && token.isValid()){
			return token.getTicket();
		}
		token = WXUtils.APIGet(TICKET_URL, Token.class, secret);
		JATCache.put(secret, token);
		return token.getTicket();
	}
	
	/**
	 * 获取签名信息
	 * @param jsapi_ticket JS票证
	 * @param url 签名URL
	 * @return 签名字段 {@link HashMap} 集合
	 */
	public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }
	/**
	 * byte转16进制串
	 * @param hash byte数组
	 * @return 字符串集合
	 */
    protected static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    /**
     * 随机字符串
     * @return 字符串
     */
    protected static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
    /**
     * 当前时间戳 S
     * @return 当前时间秒
     */
    protected static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
