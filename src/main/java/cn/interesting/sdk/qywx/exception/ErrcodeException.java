package cn.interesting.sdk.qywx.exception;

/**
 * 微信错误码异常
 * @author Administrator
 *
 */
public class ErrcodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 错误代码链接
	 */
	public final static String ERROR_CODE_URL = "https://work.weixin.qq.com/api/doc#10649";
	/**
	 * 错误代码
	 */
	private int errcode;
	/**
	 * 错误消息
	 */
	private String errmsg;
	/**
	 * 使用错误代码、错误消息进行构造
	 * @param errcode
	 * @param errmsg
	 */
	public ErrcodeException(int errcode, String errmsg){
		super(errcode + ":" + errmsg + '\n' + ERROR_CODE_URL);
		this.errcode = errcode;
		this.errmsg = errmsg;
	}
	
	public int getErrcode() {
		return errcode;
	}
	
	public String getErrmsg() {
		return errmsg;
	}
}
