package com.github.wephotos.sdk.qywx.agent;

import java.util.ArrayList;
import java.util.List;

import com.github.wephotos.sdk.qywx.exception.ConfigException;
import com.github.wephotos.sdk.qywx.exception.WXUnCheckedException;

/**
 * 注册应用缓存
 * @author Aaron.tian
 *
 */
public final class AgentFactory {
	/**
	 * 应用集合缓存;注意请不要删除集合中的数据；也不要向集合中添加数据
	 */
	public final static List<Agent> AGENTS = new ArrayList<Agent>();

	/**
	 * 加载所有系统应用
	 * @param basePackage
	 */
	public static void loadAgent(String basePackage){
		if(AGENTS.size() > 0){
			return;
		}
		try {
			List<Class<Agent>> classes = AgentScanner.getAgents(basePackage);
			for(Class<Agent> clazz:classes){
				AGENTS.add(clazz.newInstance());
			}
		} catch (Throwable e) {
			throw new ConfigException("加载企业微信应用出错，可能由于配置文件错误引起", e);
		}
	}
	
	/**
	 * 获取指定类型的应用对象
	 * @param clazz 应用的类类型
	 * @return 应用对象
	 * @throws NotFoundExpcetion #{@link NotFoundExpcetion}
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Agent> T getAgent(Class<T> clazz) {
		for(Agent agent:AGENTS){
			if(agent.getClass() == clazz){
				return ((T)agent);
			}
		}
		throw new WXUnCheckedException("找不到类实例.className==>"+clazz.getName());
	}
	
	/**
	 * 根据应用ID获取应用实例
	 * @param agentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Agent> T getAgent(int agentId) {
		for(Agent agent:AGENTS){
			if(agent.getAgentId() == agentId){
				return ((T)agent);
			}
		}
		throw new WXUnCheckedException("找不到类实例.agentId==>"+agentId);
	}
}
