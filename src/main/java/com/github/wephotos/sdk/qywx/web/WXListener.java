package com.github.wephotos.sdk.qywx.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.github.wephotos.sdk.qywx.utils.WXUtils;

/**
 * 系统监听
 * @author Aaron.tian
 *
 */
public class WXListener implements ServletContextListener{
	/**
	 * 配置文件路径名称
	 */
	public final static String CONFIG_LOCATION_NAME = "weixinConfigLocation";
	
	public final static String LOG4J_CONFIG_LOCATION = "log4jConfigLocation";
	
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServletContext servletContext= contextEvent.getServletContext();
		servletContext.setAttribute("path", servletContext.getContextPath());
		String classpath = servletContext.getInitParameter(CONFIG_LOCATION_NAME);
		WXUtils.initConfig(classpath);
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}