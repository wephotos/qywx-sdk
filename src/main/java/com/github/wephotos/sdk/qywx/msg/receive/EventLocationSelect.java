package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 弹出地理位置选择器的事件推送
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventLocationSelect extends EventKeyMessage {
	
	/**
	 * 位置信息
	 */
	private SendLocationInfo SendLocationInfo;
	
	public EventLocationSelect() {
		this.setEvent(LOCATION_SELECT);
	}
	/**
	 * 获取位置信息
	 * @return 位置信息
	 */
	public SendLocationInfo getSendLocationInfo() {
		return SendLocationInfo;
	}

	public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
		SendLocationInfo = sendLocationInfo;
	}

	/**
	 * 发送的位置信息 
	 * @author Administrator
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	static class SendLocationInfo{
		/**
		 * X坐标信息 
		 */
		private String Location_X;
		/**
		 * Y坐标信息 
		 */
		private String Location_Y;
		/**
		 * 精度，可理解为精度或者比例尺、越精细的话 scale越高 
		 */
		private String Scale;
		/**
		 * 地理位置的字符串信息 
		 */
		private String Label;
		/**
		 * 朋友圈POI的名字，可能为空 
		 */
		private String Poiname;
		/**
		 * X坐标信息 
		 * @return X坐标信息 
		 */
		public String getLocation_X() {
			return Location_X;
		}
		public void setLocation_X(String location_X) {
			Location_X = location_X;
		}
		/**
		 * Y坐标信息 
		 * @return Y坐标
		 */
		public String getLocation_Y() {
			return Location_Y;
		}
		public void setLocation_Y(String location_Y) {
			Location_Y = location_Y;
		}
		/**
		 * 精度，可理解为精度或者比例尺、越精细的话 scale越高 
		 * @return 精度
		 */
		public String getScale() {
			return Scale;
		}
		public void setScale(String scale) {
			Scale = scale;
		}
		/**
		 * 地理位置的字符串信息 
		 * @return 地址位置
		 */
		public String getLabel() {
			return Label;
		}
		public void setLabel(String label) {
			Label = label;
		}
		/**
		 * 朋友圈POI的名字，可能为空 
		 * @return 字符串
		 */
		public String getPoiname() {
			return Poiname;
		}
		public void setPoiname(String poiname) {
			Poiname = poiname;
		}
		
	}
}
