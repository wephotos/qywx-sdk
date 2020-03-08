package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 点击菜单跳转链接的事件推送
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventMenuView extends EventKeyMessage {

	public EventMenuView() {
		this.setEvent(VIEW);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
