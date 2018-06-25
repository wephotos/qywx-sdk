package cn.microvideo.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 成员同意上报地理位置后，每次在进入应用会话时都会上报一次地理位置，或在进入应用会话后每5秒上报一次地理位置。
 * 企业可以在管理端修改应用的以上设置。
 * 上报地理位置时，微信会将此事件推送到企业应用在管理端设置的URL
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventLocation extends EventMessage {
	
	/**
	 * 地理位置纬度
	 */
	private String Latitude;
	/**
	 * 地理位置经度 
	 */
	private String Longitude;
	/**
	 * 地理位置精度 
	 */
	private String Precision;
	
	public EventLocation() {
		this.setEvent(LOCATION);
	}
	/**
	 * 获取地理位置纬度
	 * @return 纬度
	 */
	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	/**
	 * 获取地理位置经度 
	 * @return 经度
	 */
	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	/**
	 * 获取位置精度
	 * @return 精度
	 */
	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

	@Override
	public String toString() {
		return "EventLocation [Latitude=" + Latitude + ", Longitude="
				+ Longitude + ", Precision=" + Precision + ", toString()="
				+ super.toString() + "]";
	}

}
