package cn.microvideo.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 位置消息
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NLocationMessage extends RMessage {
	
	/**
	 * 地理位置纬度 
	 */
	private String Location_X;
	/**
	 * 地理位置经度 
	 */
	private String Location_Y;
	/**
	 * 地图缩放大小 
	 */
	private String Scale;
	/**
	 * 地理位置信息
	 */
	private String Label;
	/**
	 * 地理位置纬度 
	 * @return 纬度
	 */
	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		this.Location_X = location_X;
	}
	/**
	 * 地理位置经度 
	 * @return 经度 
	 */
	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		this.Location_Y = location_Y;
	}
	/**
	 * 地图缩放大小
	 * @return 缩放大小
	 */
	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		this.Scale = scale;
	}
	/**
	 * 地理位置信息
	 * @return 地理位置
	 */
	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		this.Label = label;
	}

	@Override
	public String toString() {
		return "LocationMessage [location_X=" + Location_X + ", location_Y="
				+ Location_Y + ", scale=" + Scale + ", label=" + Label
				+ ", toString()=" + super.toString() + "]";
	}

}
