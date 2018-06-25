package cn.microvideo.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 删除部门事件
 * @author Administrator
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDeleteParty extends EventContactMessage {

	/**
	 * 部门Id
	 */
	private long Id;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "EventDeleteParty [Id=" + Id + ", toString()="
				+ super.toString() + "]";
	}
	
}
