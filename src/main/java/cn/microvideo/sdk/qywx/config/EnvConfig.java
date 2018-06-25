package cn.microvideo.sdk.qywx.config;

/**
 * 运行环境相关配置
 * @author Administrator
 *
 */
public final class EnvConfig {
	/**
	 * 字符集
	 */
	public static String charset = "UTF-8";

	public static String getCharset() {
		return charset;
	}

	public static void setCharset(String charset) {
		EnvConfig.charset = charset;
	}

}
