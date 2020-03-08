package com.github.wephotos.sdk.qywx.utils;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.wephotos.sdk.qywx.config.EnvConfig;

/**
 * WEB工具类
 * @author Administrator
 *
 */
public final class WEBUtils {
	
	/**
	 * 企业微信用户ID在Session中的名称
	 */
	public static final String SESSION_USERID_NAME = "qywx:userid:name";

	/**
	 * 读取请求流中的内容
	 * @param request
	 * @return
	 */
	public static String toString(HttpServletRequest request){
		try (InputStream input = request.getInputStream()){
			String encoding = request.getCharacterEncoding();
			if(StringUtils.isEmpty(encoding)){
				encoding = EnvConfig.charset;
			}
			return IOUtils.toString(input, encoding);
		}catch (Exception e) {
			throw new RuntimeException("读取HttpServletRequest请求内容失败！", e);
		}
	}
	/**
	 * 获取session用户
	 * @param session
	 * @return
	 */
	public static String getSessionUserId(HttpSession session){
		return (String)session.getAttribute(SESSION_USERID_NAME);
	}
	/**
	 * 设置session用户
	 * @param session
	 * @param value
	 */
	public static void setSessionUserId(HttpSession session, String value){
		session.setAttribute(SESSION_USERID_NAME, value);
	}
}
