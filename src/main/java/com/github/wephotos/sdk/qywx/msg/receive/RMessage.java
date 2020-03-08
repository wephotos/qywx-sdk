package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 要接收的消息抽象共性描述
 * 
 * @author Aaron.tian
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class RMessage {
	
	/**
	 * 文本消息
	 */
	public static final String TEXT = "text";
	/**
	 * 图片消息
	 */
	public static final String IMAGE = "image";
	/**
	 * 语音消息
	 */
	public static final String VOICE = "voice";
	/**
	 * 视频消息
	 */
	public static final String VIDEO = "video";
	/**
	 * 位置信息
	 */
	public static final String LOCATION = "location";
	/**
	 * 位置信息
	 */
	public static final String EVENT = "event";
	
	/**
	 * 小视频
	 */
	public static final String SHORTVIDEO = "shortvideo";
	/**
	 * 链接
	 */
	public static final String LINK = "link";

	/**
	 * 企业号CorpID
	 */
	private String ToUserName;
	/**
	 * 成员UserID 
	 */
	private String FromUserName;
	/**
	 * 消息创建时间（整型） 
	 */
	private long CreateTime;
	/**
	 * 消息类型，此时固定为：text 
	 */
	private String MsgType;
	/**
	 * 消息id，64位整型 
	 */
	private long MsgId;
	/**
	 * 企业应用的id，整型。可在应用的设置页面查看
	 */
	private int AgentID;
	/**
	 * 此处为企业号CorpID
	 * @return CorpID
	 */
	public String getToUserName() {
		return ToUserName;
	}
	/**
	 * 此处为企业号CorpID
	 * @param toUserName CorpID
	 */
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	/**
	 * 获取发送消息成员ID
	 * @return 发送成员ID
	 */
	public String getFromUserName() {
		return FromUserName;
	}
	/**
	 * 获取消息发送成员ID
	 * @param fromUserName 成员ID
	 */
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	/**
	 * 获取消息创建时间（整型）
	 * @return 消息创建时间
	 */
	public long getCreateTime() {
		return CreateTime;
	}
	/**
	 * 消息创建时间
	 * @param createTime 消息创建时间
	 */
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	/**
	 * 获取消息类型
	 * @return 消息类型
	 */
	public String getMsgType() {
		return MsgType;
	}
	/**
	 * 设置消息类型
	 * @param msgType 消息类型
	 */
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	/**
	 * 获取消息ID
	 * @return 消息ID
	 */
	public long getMsgId() {
		return MsgId;
	}
	/**
	 * 设置消息ID
	 * @param msgId 消息ID
	 */
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
	/**
	 * 获取应用ID
	 * @return 应用ID
	 */
	public int getAgentID() {
		return AgentID;
	}
	/**
	 * 设置应用ID
	 * @param agentID 应用ID
	 */
	public void setAgentID(int agentID) {
		AgentID = agentID;
	}
	
	@Override
	public String toString() {
		return "Message [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + ", MsgId=" + MsgId + ", AgentID=" + AgentID + "]";
	}

}
