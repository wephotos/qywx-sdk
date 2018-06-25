package cn.microvideo.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 通讯录变更事件
 * @author Administrator
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EventContactMessage extends EventMessage {
	/**
	 * 新增
	 */
	public static final String CREATE_USER = "create_user";
	/**
	 * 更新
	 */
	public static final String UPDATE_USER = "update_user";
	/**
	 * 删除部门
	 */
	public static final String DELETE_USER = "delete_user";
	/**
	 * 新增部门
	 */
	public static final String CREATE_PARTY = "create_party";
	/**
	 * 更新部门
	 */
	public static final String UPDATE_PARTY = "update_party";
	/**
	 * 删除部门
	 */
	public static final String DELETE_PARTY = "delete_party";
	/**
	 * 标签变更事件
	 */
	public static final String UPDATE_TAG = "update_tag";
	/**
	 * 变更类型
	 */
	private String ChangeType;

	public String getChangeType() {
		return ChangeType;
	}

	public void setChangeType(String changeType) {
		ChangeType = changeType;
	}

	@Override
	public String toString() {
		return "EventContactMessage [ChangeType=" + ChangeType
				+ ", toString()=" + super.toString() + "]";
	}
	
}
