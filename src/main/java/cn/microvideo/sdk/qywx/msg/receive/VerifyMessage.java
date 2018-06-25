package cn.microvideo.sdk.qywx.msg.receive;

/**
 * 接收消息URL中的验证消息
 * @author Aaron.tian
 *
 */
public final class VerifyMessage {

	/**
	 * 微信加密签名，msg_signature结合了企业填写的token、请求中的timestamp、nonce参数、加密的消息体 
	 */
	private String msg_signature;
	/**
	 * 时间戳
	 */
	private String timestamp;
	/**
	 * 随机数 
	 */
	private String nonce;
	/**
	 * 加密的随机字符串，以msg_encrypt格式提供。
	 * 需要解密并返回echostr明文，解密后有random、msg_len、msg、$CorpID四个字段，
	 * 其中msg即为echostr明文 
	 */
	private String echostr;
	
	/**
	 * 微信加密签名，msg_signature结合了企业填写的token、请求中的timestamp、nonce参数、加密的消息体 
	 * @return 微信加密签名
	 */
	public String getMsg_signature() {
		return msg_signature;
	}
	public void setMsg_signature(String msg_signature) {
		this.msg_signature = msg_signature;
	}
	/**
	 * 时间戳
	 * @return 时间戳
	 */
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 *  随机数 
	 * @return  随机数 
	 */
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	/**
	 * 加密的随机字符串，以msg_encrypt格式提供。
	 * 需要解密并返回echostr明文，解密后有random、msg_len、msg、$CorpID四个字段，
	 * 其中msg即为echostr明文 
	 * @return 加密串
	 */
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	@Override
	public String toString() {
		return "TokenMessage [msg_signature=" + msg_signature + ", timestamp="
				+ timestamp + ", nonce=" + nonce + ", echostr=" + echostr + "]";
	}
	
}
