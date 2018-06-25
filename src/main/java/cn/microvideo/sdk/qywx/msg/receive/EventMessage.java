package cn.microvideo.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 事件消息
 * 
 * @author Aaron.tian
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EventMessage extends RMessage {
	
	/**
	 * 订阅
	 */
	public static final String SUBSCRIBE = "subscribe";
	/**
	 * 取消订阅
	 */
	public static final String UNSUBSCRIBE = "unsubscribe";
	/**
	 * 上报地理位置事件
	 */
	public static final String LOCATION = "location";
	/**
	 * 点击菜单拉取消息的事件推送
	 */
	public static final String CLICK = "click";
	/**
	 * 点击菜单跳转链接的事件推送
	 */
	public static final String VIEW = "view";
	/**
	 * 扫码推事件的事件推送
	 */
	public static final String SCANCODE_PUSH  = "scancode_push";
	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件推送
	 */
	public static final String SCANCODE_WAITMSG  = "scancode_waitmsg";
	/**
	 * 弹出系统拍照发图的事件推送
	 */
	public static final String PIC_SYSPHOTO = "pic_sysphoto";
	/**
	 * 弹出拍照或者相册发图的事件推送
	 */
	public static final String PIC_PHOTO_OR_ALBUM  = "pic_photo_or_album";
	/**
	 * 弹出微信相册发图器的事件推送
	 */
	public static final String PIC_WEIXIN = "pic_weixin";
	/**
	 * 弹出地理位置选择器的事件推送
	 */
	public static final String LOCATION_SELECT = "location_select";
	/**
	 * 弹出地理位置选择器的事件推送
	 */
	public static final String ENTER_AGENT = "enter_agent";
	/**
	 * 异步任务完成事件推送
	 */
	public static final String BATCH_JOB_RESULT = "batch_job_result";
	/**
	 * 通讯录变更事件
	 */
	public static final String CHANGE_CONTACT = "change_contact";
	/**
	 * 事件类型
	 */
	private String Event;
	
	public EventMessage() {
		this.setMsgType(EVENT);
	}
	/**
	 * 事件类型
	 * @return 事件类型字符串
	 */
	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	@Override
	public String toString() {
		return "EventMessage [Event=" + Event + ", toString()="
				+ super.toString() + "]";
	}
	
}
