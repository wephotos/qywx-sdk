package com.github.wephotos.sdk.qywx.web;

import javax.servlet.http.HttpServletRequest;

/**
 * <b>描述:</b>
 *
 * <p>可信域名接口代理</p>
 *
 * @version 1.0
 * @author Aaron.tian
 * @Date 2018年12月6日下午3:21:38
 * @since JDK1.8
 */
public final class HttpTrustedDomainProxy {
	
	private static HttpTrustedDomain httpTrustedDomain = new HttpTrustedDomain() {};
	
	/**
	 * @param httpTrustedDomain the httpTrustedDomain to set
	 */
	public static void setHttpTrustedDomain(HttpTrustedDomain httpTrustedDomain) {
		HttpTrustedDomainProxy.httpTrustedDomain = httpTrustedDomain;
	}
	
	/**
	 * 获取可信域名
	 * @param request
	 * @return
	 */
	public static  String getTrustedDomain(HttpServletRequest request) {
		return httpTrustedDomain.getTrustedDomain(request);
	}
}
