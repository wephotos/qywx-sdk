package cn.microvideo.sdk.qywx.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.nio.charset.StandardCharsets;

import cn.microvideo.sdk.qywx.agent.Agent;
import cn.microvideo.sdk.qywx.agent.AgentFactory;
import cn.microvideo.sdk.qywx.msg.send.Messager;
import cn.microvideo.sdk.qywx.msg.send.TextMessage;

/**
 * 处理全局异常/将错误信息发给我
 * 
 * @author 田奇
 */
public class WeChatUncaughtExceptionHandler implements UncaughtExceptionHandler {

	/**
	 * 重写接口中的方法，将异常信息组织后发送到我微信
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		int agentId = getUncaughtExceptionAgentId();
		if (agentId == 0) {
			return;
		}
		StringBuilder trace = new StringBuilder(100);
		trace.append(e.getClass().getName() + ":" + e.getMessage());
		byte[] bytes = trace.toString().getBytes(StandardCharsets.UTF_8);
		if (bytes.length > 2048) {
			trace = new StringBuilder(new String(bytes, 0, 2048, StandardCharsets.UTF_8));
		}
		TextMessage error = new TextMessage();
		error.setTouser("@all");
		error.setText2(trace.toString());
		error.setAgentid(agentId);
		try {
			Messager.send(error);
		} catch (ErrcodeException e1) {
			//
		}
	}

	/**
	 * 获取异常处理应用ID
	 * 
	 * @return 应用ID
	 */
	private int getUncaughtExceptionAgentId() {
		for (Agent agent : AgentFactory.AGENTS) {
			if (agent.isUncaughtExceptionHandler()) {
				return agent.getAgentId();
			}
		}
		return 0;
	}
}
