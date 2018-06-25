package cn.microvideo.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 更新部门事件
 * @author Administrator
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventUpdateParty extends EventContactMessage {
	/**
	 * 部门Id
	 */
	private long Id;
	/**
	 * 部门名称
	 */
	private String Name;
	/**
	 * 父部门id
	 */
	private String ParentId;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getParentId() {
		return ParentId;
	}
	public void setParentId(String parentId) {
		ParentId = parentId;
	}
	@Override
	public String toString() {
		return "EventUpdateParty [Id=" + Id + ", Name=" + Name + ", ParentId="
				+ ParentId + ", toString()=" + super.toString() + "]";
	}
	
}
