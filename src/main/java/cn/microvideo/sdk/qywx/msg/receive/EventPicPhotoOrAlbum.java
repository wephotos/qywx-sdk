package cn.microvideo.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 弹出拍照或者相册发图的事件推送
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventPicPhotoOrAlbum extends EventKeyMessage {
	/**
	 * 发送的图片信息 
	 */
	private EventSendPicsInfo SendPicsInfo = new EventSendPicsInfo();
	
	public EventPicPhotoOrAlbum() {
		this.setEvent(PIC_PHOTO_OR_ALBUM);
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
