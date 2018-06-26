package cn.interesting.sdk.qywx.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

import cn.interesting.sdk.qywx.utils.WXUtils;

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
		
		String log4jLocation = servletContext.getInitParameter(LOG4J_CONFIG_LOCATION);
		initLog4j(log4jLocation);
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		LogManager.shutdown();
	}

	/**
	 * 初始化日志
	 * @param location
	 */
	private void initLog4j(String location){
		location = location.replaceFirst("^classpath[^a-zA-Z_]+", "");
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
		try (InputStream inputStream = currentClassLoader.getResourceAsStream(location)) {
			PropertyConfigurator.configure(inputStream);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}