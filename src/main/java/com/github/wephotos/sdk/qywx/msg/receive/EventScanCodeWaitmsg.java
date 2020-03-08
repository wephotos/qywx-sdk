package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 扫码推事件且弹出“消息接收中”提示框的事件推送
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventScanCodeWaitmsg extends EventKeyMessage {
	
	/**
	 * 扫描信息 
	 */
	private EventScanCodeInfo ScanCodeInfo;
	
	public EventScanCodeWaitmsg() {
		this.setEvent(SCANCODE_WAITMSG);
	}
	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件推送
	 * @return 扫码信息
	 */
	public EventScanCodeInfo getScanCodeInfo() {
		return ScanCodeInfo;
	}

	public void setScanCodeInfo(EventScanCodeInfo scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}

	@Override
	public String toString() {
		return "EventScanCodeWaitmsg [ScanCodeInfo=" + ScanCodeInfo
				+ ", toString()=" + super.toString() + "]";
	}

}
