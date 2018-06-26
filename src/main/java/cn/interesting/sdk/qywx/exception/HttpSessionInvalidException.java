package cn.interesting.sdk.qywx.exception;

/**
 * 
 * @ClassName: HttpSessionInvalidException
 * @Description: HTTP请求会话验证失败
 * @author Aaron.tian
 * @date 2016年12月5日
 *
 */
public class HttpSessionInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public HttpSessionInvalidException() {
        super();
    }
    
    /** Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public HttpSessionInvalidException(String message) {
        super(message);
    }
}
