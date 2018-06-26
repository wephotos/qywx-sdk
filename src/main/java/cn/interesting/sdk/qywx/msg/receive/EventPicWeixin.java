package cn.interesting.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 弹出微信相册发图器的事件推送
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventPicWeixin extends EventKeyMessage {
	/**
	 * 图片信息
	 */
	private EventSendPicsInfo SendPicsInfo = new EventSendPicsInfo();

	public EventPicWeixin() {
		this.setEvent(PIC_WEIXIN);
	}
	/**
	 * 发送的图片信息
	 * @return 发送的图片信息
	 */
	public EventSendPicsInfo getSendPicsInfo() {
		return SendPicsInfo;
	}
	public void setSendPicsInfo(EventSendPicsInfo sendPicsInfo) {
		SendPicsInfo = sendPicsInfo;
	}
	
}
