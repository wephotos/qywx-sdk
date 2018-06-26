package cn.interesting.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 语音消息
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NVoiceMessage extends RMessage {
	
	/**
	 * 语音媒体文件id，可以调用获取媒体文件接口拉取数据 
	 */
	private String MediaId;
	/**
	 * 语音格式，如amr，speex等 
	 */
	private String Format;
	/**
	 * 语音媒体文件id，可以调用获取媒体文件接口拉取数据 
	 * @return 语音媒体文件id
	 */
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
	}
	/**
	 * 语音格式，如amr，speex等 
	 * @return 语音格式
	 */
	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		this.Format = format;
	}

	@Override
	public String toString() {
		return "VoiceMessage [mediaId=" + MediaId + ", format=" + Format
				+ ", toString()=" + super.toString() + "]";
	}

}
