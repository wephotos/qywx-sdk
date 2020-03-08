package com.github.wephotos.sdk.qywx.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.wephotos.sdk.qywx.WeChatRES;

/**
 * 管理组须拥有agent的使用权限；agentid必须和跳转链接时所在的企业应用ID相同。
 * 根据企业Code获取的人员信息结构
 * @author Aaron.tian
 *
 */
public class OAuth2Response extends WeChatRES {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 成员UserID
	 */
	@JsonProperty(value="UserId")
	private String userId;
	/**
	 * 手机设备号(由微信在安装时随机生成，删除重装会改变，升级不受影响) 
	 */
	@JsonProperty(value="DeviceId")
	private String deviceId;
	/**
	 * 非企业成员的标识，对当前企业唯一
	 */
	@JsonProperty(value="OpenId")
	private String openId;
	/**
	 * 成员票据，最大为512字节。<br>
	 * scope为snsapi_userinfo或snsapi_privateinfo，且用户在应用可见范围之内时返回此参数。<br>
	 * 后续利用该参数可以获取用户信息或敏感信息。
	 */
	private String user_ticket;
	/**
	 * 成员UserID
	 * @return UserID
	 */
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 手机设备号(由微信在安装时随机生成，删除重装会改变，升级不受影响) 
	 * @return 手机设备号
	 */
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 非企业成员验证时候返回
	 * @return 非企业成员的标识，对当前企业唯一
	 */
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 成员票据，最大为512字节。
	 * @return 成员票据
	 */
	public String getUser_ticket() {
		return user_ticket;
	}
	public void setUser_ticket(String user_ticket) {
		this.user_ticket = user_ticket;
	}
	
	@Override
	public String toString() {
		return "OAuth2Response [userId=" + userId + ", deviceId=" + deviceId
				+ ", openId=" + openId + ", user_ticket=" + user_ticket + "]";
	}
	
}
