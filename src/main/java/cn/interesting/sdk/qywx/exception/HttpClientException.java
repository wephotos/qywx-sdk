package cn.interesting.sdk.qywx.exception;

/**
 * HttpClient触发的相关异常
 * @author Administrator
 *
 */
public class HttpClientException extends WXUnCheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HttpClientException(String message) {
		super(message);
	}
	
	public HttpClientException(String message, Throwable cause) {
		super(message, cause);
	}
}
