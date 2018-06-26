package cn.interesting.sdk.qywx;

import cn.interesting.sdk.qywx.exception.AccessTokenInvalidException;
import cn.interesting.sdk.qywx.exception.ErrcodeException;

/**
 * 微信响应信息，包含错误代码和错误消息描述，包含在所有的返回结果中
 * @author Aaron.tian
 *
 */
public class WeChatRES {
	/**
	 * 访问凭证超时
	 */
	public final static int ACCESS_TOKEN_TIMEOUT = 42001;
	/**
	 * 访问凭证无效
	 */
	public final static int ACCESS_TOKEN_INVALID = 40001;
	/**
	 * 不合法的访问凭证
	 */
	public final static int ACCESS_TOKEN_ILLEGAL = 40014;
	/**
	 * 错误代码
	 */
	private int errcode;
	/**
	 * 错误消息
	 */
	private String errmsg;
	
	/**
	 * 获取响应消息的错误代码；查阅微信官方文档对照表
	 * <br>
	 * https://work.weixin.qq.com/api/doc#10649
	 * @return 错误代码
	 */
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	/**
	 * 获取错误的消息描述
	 * @return 错误消息
	 */
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	/**
	 * 检查错误代码；抛出异常
	 * @return
	 * @throws AccessTokenInvalidException 
	 */
	@SuppressWarnings("unchecked")
	public <T> T checkErrorCode() throws AccessTokenInvalidException, ErrcodeException {
		if(this.errcode == 0){
			return (T)this;
		}
		if(this.errcode == ACCESS_TOKEN_TIMEOUT 
			|| this.errcode == ACCESS_TOKEN_INVALID 
			|| this.errcode == ACCESS_TOKEN_ILLEGAL){
			throw new AccessTokenInvalidException(this.errcode, this.errmsg);
		}
		throw new ErrcodeException(this.errcode, this.errmsg);
	}
	@Override
	public String toString() {
		return "WechatResponse [errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}
	
}
