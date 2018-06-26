package cn.interesting.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 扫码推事件的事件推送
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventScanCodePush extends EventKeyMessage {
	
	/**
	 * 扫描信息 
	 */
	private EventScanCodeInfo ScanCodeInfo;
	
	
	public EventScanCodePush() {
		this.setEvent(SCANCODE_PUSH);
	}
	/**
	 * 获取扫码事件的扫描消息
	 * @return {@link EventScanCodeInfo}
	 */
	public EventScanCodeInfo getScanCodeInfo() {
		return ScanCodeInfo;
	}

	public void setScanCodeInfo(EventScanCodeInfo scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}

	@Override
	public String toString() {
		return "EventScanCodePush [ScanCodeInfo=" + ScanCodeInfo
				+ ", toString()=" + super.toString() + "]";
	}
}
