package com.github.wephotos.sdk.qywx.msg.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 音频消息
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class VoiceMessage extends Message {
	/**
	 * 音频
	 */
	@XmlElement
	private Voice voice = new Voice();
	
	public VoiceMessage() {
		this.setMsgType(VOICE);
	}
	/**
	 * 获取音频消息
	 * @return 音频消息
	 */
	public Voice getVoice() {
		return voice;
	}
	/**
	 * 设定音频消息；音频文件id，可以调用上传媒体文件接口获取 
	 * @param media_id 音频文件ID
	 */
	public void setVoice(String media_id) {
		this.voice.setMediaId(media_id);
	}

	/**
	 * 语音文件
	 * @author Administrator
	 *
	 */
	@XmlRootElement(name="Voice")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Voice {
		/**
		 * 音频文件id，可以调用上传媒体文件接口获取 
		 */
		private String MediaId = "";
		
		/**
		 * 获取音频文件ID
		 * @return 音频ID
		 */
		public String getMediaId() {
			return MediaId;
		}
		/**
		 * 设定音频文件ID
		 * @param mediaId 音频ID
		 */
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		
	}
}
