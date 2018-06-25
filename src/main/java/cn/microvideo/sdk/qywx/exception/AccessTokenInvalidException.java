package cn.microvideo.sdk.qywx.exception;

/**
 * access_token过期，出现此错误请重新获取令牌再次访问
 * @author Administrator
 *
 */
public class AccessTokenInvalidException extends ErrcodeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessTokenInvalidException(int errcode, String errmsg) {
		super(errcode, errmsg);
	}
}
