package cn.interesting.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 图片消息
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NImageMessage extends RMessage{
	
	/**
	 * 图片链接 
	 */
	private String PicUrl;
	/**
	 * 图片媒体文件id，可以调用获取媒体文件接口拉取数据 
	 */
	private String MediaId;
	/**
	 * 图片链接 
	 * @return 图片链接 
	 */
	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}
	/**
	 * 图片媒体文件id，可以调用获取媒体文件接口拉取数据 
	 * @return 图片媒体文件id
	 */
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
	}

	@Override
	public String toString() {
		return "ImageMessage [picUrl=" + PicUrl + ", mediaId=" + MediaId
				+ ", toString()=" + super.toString() + "]";
	}

}
