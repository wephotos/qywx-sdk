package cn.interesting.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 带有事件KEY值的事件消息
 * @author Aaron.tian
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EventKeyMessage extends EventMessage {

	/**
	 * 事件KEY值
	 */
	private String EventKey;
	/**
	 * 获取事件KEY值
	 * @return KEY值
	 */
	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	@Override
	public String toString() {
		return "EventKeyMessage [EventKey=" + EventKey + ", toString()="
				+ super.toString() + "]";
	}

}
