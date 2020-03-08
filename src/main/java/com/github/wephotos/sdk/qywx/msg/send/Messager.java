package com.github.wephotos.sdk.qywx.msg.send;

import com.github.wephotos.sdk.qywx.config.DomainUtils;
import com.github.wephotos.sdk.qywx.config.WXConfigUtils;
import com.github.wephotos.sdk.qywx.exception.ErrcodeException;
import com.github.wephotos.sdk.qywx.utils.JSONUtils;
import com.github.wephotos.sdk.qywx.utils.WXUtils;

/**
 * 发送消息
 * @author Aaron.tian
 *
 */
public final class Messager {


	/**
	 * 发送消息URL
	 */
	public static final String SEND_URL = DomainUtils.getQywxDomain()+"/cgi-bin/message/send";
	
	/**
	 * 发送消息
	 * @param message 消息对象
	 * @return 发送消息后的响应消息 {@link MessageResponse}
	 * @throws ErrcodeException 
	 * @throws HttpConnectionException 
	 */
	public static SResponse send(SMessage message) throws ErrcodeException {
		if(message.getAgentid() == 0){
			throw new IllegalArgumentException("应用ID不能为空.agentId=>0");
		}
		String secret = WXConfigUtils.getAgentSecret(message.getAgentid());
		String body = JSONUtils.object2JSON(message);
		return WXUtils.APIPost(SEND_URL, body, SResponse.class, secret);
	}
}
