package cn.interesting.sdk.qywx.web;

import javax.servlet.http.HttpServletRequest;

/**
 * <b>描述:</b>
 *
 * <p>获取可信域名接口</p>
 *
 * @version 1.0
 * @author Aaron.tian
 * @Date 2018年12月6日下午3:19:40
 * @since JDK1.8
 */
public interface HttpTrustedDomain {

	/**
	 * 从当前请求中获取可信域名
	 * @param request
	 * @return
	 */
	default String getTrustedDomain(HttpServletRequest request) {
		StringBuilder trusted = new StringBuilder();
		trusted.append(request.getServerName());
		int port = request.getServerPort();
		if(port != 80 && port != 443){
			trusted.append(":");
			trusted.append(port);
		}
		return trusted.toString();
	}
}
