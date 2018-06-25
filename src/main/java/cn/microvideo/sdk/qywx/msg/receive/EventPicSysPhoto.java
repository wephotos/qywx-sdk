package cn.microvideo.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 弹出系统拍照发图的事件推送
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventPicSysPhoto extends EventKeyMessage {
	/**
	 * 图片信息
	 */
	private EventSendPicsInfo SendPicsInfo = new EventSendPicsInfo();
	
	public EventPicSysPhoto() {
		this.setEvent(PIC_SYSPHOTO);
	}
	/**
	 * 发送的图片信息 
	 * @return 图片信息 
	 */
	public EventSendPicsInfo getSendPicsInfo() {
		return SendPicsInfo;
	}

	public void setSendPicsInfo(EventSendPicsInfo sendPicsInfo) {
		SendPicsInfo = sendPicsInfo;
	}
}
