package cn.interesting.sdk.qywx.contacts;

import cn.interesting.sdk.qywx.msg.receive.EventContactMessage;

/**
 * 通讯录监听
 * @author Administrator
 *
 */
public interface ContactsListener {

	/**
	 * 通讯录变更事件监听方法
	 * @param ecm 变更消息
	 */
	void change_contact(EventContactMessage ecm);
}
