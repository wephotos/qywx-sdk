package com.github.wephotos.sdk.qywx.exception;

/**
 * 配置文件异常
 * @author Administrator
 *
 */
public class ConfigException extends WXUnCheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigException(String message) {
		super(message);
	}
	
	public ConfigException(String message, Throwable cause) {
		super(message, cause);
	}
}
