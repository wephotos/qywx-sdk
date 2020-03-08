package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 删除成员事件
 * @author Administrator
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDeleteUser extends EventContactMessage {

	/**
	 * 变更信息的成员UserID
	 */
	private String UserID;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	@Override
	public String toString() {
		return "EventDeleteUser [UserID=" + UserID + ", toString()="
				+ super.toString() + "]";
	}
	
}
