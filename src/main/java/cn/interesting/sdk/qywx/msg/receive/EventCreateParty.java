package cn.interesting.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 新增部门事件
 * @author Administrator
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventCreateParty extends EventContactMessage {
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
	/**
	 * 部门排序
	 */
	private int Order;
	
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
	public int getOrder() {
		return Order;
	}
	public void setOrder(int order) {
		Order = order;
	}
	@Override
	public String toString() {
		return "EventCreateParty [Id=" + Id + ", Name=" + Name + ", ParentId="
				+ ParentId + ", Order=" + Order + ", toString()="
				+ super.toString() + "]";
	}
	
}
