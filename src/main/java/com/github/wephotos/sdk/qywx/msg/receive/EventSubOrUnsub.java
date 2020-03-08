package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 成员关注、取消关注企业号的事件，会推送到每个应用在管理端设置的URL；
 * 特别的，默认企业小助手可以用于获取整个企业号的关注状况
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventSubOrUnsub extends EventMessage {

	@Override
	public String toString() {
		return super.toString();
	}

}
