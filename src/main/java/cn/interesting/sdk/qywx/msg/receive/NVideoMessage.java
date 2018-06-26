package cn.interesting.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 视频消息
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NVideoMessage extends RMessage {
	
	/**
	 * 视频媒体文件id，可以调用获取媒体文件接口拉取数据
	 */
	private String MediaId;
	/**
	 * 视频消息缩略图的媒体id，可以调用获取媒体文件接口拉取数据 
	 */
	private String ThumbMediaId;

	/**
	 * 视频媒体文件id，可以调用获取媒体文件接口拉取数据
	 * @return 视频媒体文件id
	 */
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
	}
	/**
	 * 视频消息缩略图的媒体id，可以调用获取媒体文件接口拉取数据 
	 * @return 视频消息缩略图的媒体id
	 */
	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.ThumbMediaId = thumbMediaId;
	}

	@Override
	public String toString() {
		return "VideoMessage [mediaId=" + MediaId + ", thumbMediaId="
				+ ThumbMediaId + ", toString()=" + super.toString() + "]";
	}

}
