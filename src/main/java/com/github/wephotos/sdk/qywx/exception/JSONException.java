package com.github.wephotos.sdk.qywx.exception;

/**
 * JSON相关异常
 * @author Administrator
 *
 */
public class JSONException extends WXUnCheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JSONException(String message) {
		super(message);
	}
	
	public JSONException(String message, Throwable cause) {
		super(message, cause);
	}
}
