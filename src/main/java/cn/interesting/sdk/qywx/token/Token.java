package cn.interesting.sdk.qywx.token;

import cn.interesting.sdk.qywx.WeChatRES;

/**
 * 获取Token的响应消息
 * @author Aaron.tian
 *
 */
public class Token extends WeChatRES {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 获取到的访问凭证
	 */
	private String access_token;
	/**
	 * jsapi_ticket是企业号号用于调用微信JS接口的临时票据。
	 * 正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取
	 */
	private String ticket;
	/**
	 * 有效时间  秒
	 */
	private int expires_in;
	
	/**
	 * Token创建时间
	 */
	private long timestamp;
	
	public Token() {
		this.timestamp = System.currentTimeMillis();
	}
	
	/**
	 * 判断Token是否有效
	 * 返回true有效，false无效
	 * @return true or false
	 */
	public boolean isValid() {
		long currstamp = System.currentTimeMillis();
		long timeout = currstamp - this.timestamp;
		if (timeout / 1000 > this.expires_in - 100) {
			return false;
		}
		return true;
	}
	/**
	 * 获取访问凭证
	 * @return 凭证
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * 设定访问凭证
	 * @param access_token 凭证
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/**
	 * jsapi_ticket是企业号号用于调用微信JS接口的临时票据。
	 * 正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取
	 * @return JS票证
	 */
	public String getTicket() {
		return ticket;
	}
	/**
	 * 设定JS票证
	 * @param ticket JS票证
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	/**
	 * 获取有效期7200S
	 * @return 有效期
	 */
	public int getExpires_in() {
		return expires_in;
	}
	/**
	 * 设定当前有效期
	 * @param expires_in 有效期
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	/**
	 * Token创建时间
	 * @return 创建时间
	 */
	public long getTimestamp() {
		return timestamp;
	}
	/**
	 * Token创建时间
	 * @param timestamp 创建时间
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
