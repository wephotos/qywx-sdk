package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 成员点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。
 * 另外，扫码、拍照及地理位置的菜单事件，
 * 仅支持微信iPhone5.4.1/Android5.4以上版本，
 * 旧版本微信成员点击后将没有回应，开发者也不能正常接收到事件推送。 
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventMenuClick extends EventKeyMessage{
	
	public EventMenuClick() {
		this.setEvent(CLICK);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
