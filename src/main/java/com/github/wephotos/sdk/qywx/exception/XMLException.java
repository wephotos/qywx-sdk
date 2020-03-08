package com.github.wephotos.sdk.qywx.exception;

/**
 * XML相关异常
 * @author Administrator
 *
 */
public class XMLException extends WXUnCheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XMLException(String message) {
		super(message);
	}
	
	public XMLException(String message, Throwable cause) {
		super(message, cause);
	}
}
