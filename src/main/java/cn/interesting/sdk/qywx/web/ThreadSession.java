package cn.interesting.sdk.qywx.web;

import javax.servlet.http.HttpSession;

/**
 * 
 * @ClassName: ThreadSession
 * @Description: 将会话绑定到当前线程上
 * @author Aaron.tian
 * @date 2016年8月24日
 *
 */
public final class ThreadSession {

	/**
	 * 与当前用户线程关联，实现会话保持
	 */
	private final static ThreadLocal<HttpSession> TS = new ThreadLocal<HttpSession>();
	
	/**
	 * 存入当前session
	 * @param session
	 */
	public static void set(HttpSession session) {
		TS.set(session);
	}

	/**
	 * 取出当前session
	 * @return
	 */
	public static HttpSession get() {
		return TS.get();
	}

	/**
	 * 移除当前session
	 */
	public static void remove(){
		TS.remove();
	}

	/**
	 * 获取当前会话中的属性值
	 * @param name 属性名称
	 * @return 值
	 */
	public static String getAttribute(String name){
		HttpSession session = get();
		if(session != null){
			return (String)session.getAttribute(name);
		}
		return null;
	}
	
	/**
	 * 在当前会话中设置属性
	 * @param name 属性
	 * @param value 值
	 */
	public static void setAttribute(String name, String value){
		HttpSession session = get();
		if(session != null){
			session.setAttribute(name, value);
		}
	}
	
	/**
	 * 移除当前会话中设置属性
	 * @param name 属性名
	 */
	public static void removeAttribute(String name){
		HttpSession session = get();
		if(session != null){
			session.removeAttribute(name);
		}
	}
}
