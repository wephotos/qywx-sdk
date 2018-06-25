package cn.microvideo.sdk.qywx;

import cn.microvideo.sdk.qywx.agent.Agent;
import cn.microvideo.sdk.qywx.agent.AgentFactory;
import cn.microvideo.sdk.qywx.agent.JUnitAgent;
import cn.microvideo.sdk.qywx.exception.ErrcodeException;
import cn.microvideo.sdk.qywx.exception.HttpSessionInvalidException;
import cn.microvideo.sdk.qywx.msg.send.NewsMessage;
import cn.microvideo.sdk.qywx.msg.send.SResponse;
import cn.microvideo.sdk.qywx.utils.WXUtils;

/**
 * 测试
 * @author Administrator
 *
 */
public class WxUnit {

	public static void main(String[] args) throws ErrcodeException, HttpSessionInvalidException {
		WXUtils.initConfig("classpath:cn/microvideo/sdk/qywx/config/weixin-config.xml");
		Agent agent = AgentFactory.getAgent(JUnitAgent.class);
		NewsMessage news = new NewsMessage();
		news.setTouser("18709827703");
		news.add("百度", "百度一下，你就知道", "https://www.baidu.com", "https://www.baidu.com/img/bd_logo1.png");
		SResponse response = agent.doSending(news);
		System.out.println(response);
	}
}
