package com.github.wephotos.sdk.qywx.msg.send;

/**
 * 语音消息
 * @author Aaron.tian
 *
 */
public class VoiceMessage extends SMessage{
	
	/**
	 * 语音文件
	 */
	private Voice voice = new Voice();

	@Override
	public String getMsgtype() {
		return "voice";
	}
	/**
	 * 获取语音文件
	 * @return 语音文件
	 */
	public Voice getVoice() {
		return voice;
	}
	/**
	 * 设定语音文件id
	 * @param media_id 语音文件id
	 */
	public void setVoice(String media_id) {
		this.voice.setMedia_id(media_id);
	}

	static class Voice{
		/**
		 * 语音文件id，可以调用上传媒体文件接口获取 
		 */
		private String media_id;
		/**
		 * 获取语音文件ID
		 * @return 语音文件id
		 */
		public String getMedia_id() {
			return media_id;
		}
		/**
		 * 设定语音文件ID
		 * @param media_id 语音文件id
		 */
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
	}
}
