package cn.interesting.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 小视频消息
 * @author Administrator
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NShortVideoMessage extends RMessage {

	/**
	 * 视频媒体文件id，可以调用获取媒体文件接口拉取数据 
	 */
	private String MediaId;
	/**
	 * 视频消息缩略图的媒体id，可以调用获取媒体文件接口拉取数据 
	 */
	private String ThumbMediaId;
	
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	@Override
	public String toString() {
		return "RShortVideoMessage [MediaId=" + MediaId + ", ThumbMediaId="
				+ ThumbMediaId + "]";
	}
	
}
