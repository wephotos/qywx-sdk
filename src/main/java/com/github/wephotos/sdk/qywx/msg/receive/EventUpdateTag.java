package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 标签变更事件
 * @author Administrator
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventUpdateTag extends EventContactMessage {
	/**
	 * 标签Id
	 */
	private int TagId;
	/**
	 * 标签中新增的成员userid列表，用逗号分隔
	 */
	private String AddUserItems;
	/**
	 * 标签中删除的成员userid列表，用逗号分隔
	 */
	private String DelUserItems;
	/**
	 * 标签中新增的部门id列表，用逗号分隔
	 */
	private String AddPartyItems;
	/**
	 * 标签中删除的部门id列表，用逗号分隔
	 */
	private String DelPartyItems;
	public int getTagId() {
		return TagId;
	}
	public void setTagId(int tagId) {
		TagId = tagId;
	}
	public String getAddUserItems() {
		return AddUserItems;
	}
	public void setAddUserItems(String addUserItems) {
		AddUserItems = addUserItems;
	}
	public String getDelUserItems() {
		return DelUserItems;
	}
	public void setDelUserItems(String delUserItems) {
		DelUserItems = delUserItems;
	}
	public String getAddPartyItems() {
		return AddPartyItems;
	}
	public void setAddPartyItems(String addPartyItems) {
		AddPartyItems = addPartyItems;
	}
	public String getDelPartyItems() {
		return DelPartyItems;
	}
	public void setDelPartyItems(String delPartyItems) {
		DelPartyItems = delPartyItems;
	}
	@Override
	public String toString() {
		return "EventUpdateTag [TagId=" + TagId + ", AddUserItems="
				+ AddUserItems + ", DelUserItems=" + DelUserItems
				+ ", AddPartyItems=" + AddPartyItems + ", DelPartyItems="
				+ DelPartyItems + ", toString()=" + super.toString() + "]";
	}
	
}
