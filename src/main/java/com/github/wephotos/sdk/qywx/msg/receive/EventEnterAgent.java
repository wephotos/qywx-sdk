package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 本事件在成员进入企业号的应用时触发，如果企业需要接收此事件，请打开应用回调模式中的相应开关
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventEnterAgent extends EventKeyMessage {
	
	public EventEnterAgent() {
		this.setEvent(ENTER_AGENT);
	}
	
}
