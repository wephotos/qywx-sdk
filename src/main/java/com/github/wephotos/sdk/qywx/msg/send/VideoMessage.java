package com.github.wephotos.sdk.qywx.msg.send;

/**
 * 视频消息
 * @author Aaron.tian
 *
 */
public class VideoMessage extends SMessage {
	
	/**
	 * 视频描述
	 */
	private Video video = new Video();

	@Override
	public String getMsgtype() {
		return "video";
	}

	/**
	 * 获取视频消息
	 * @return 视频消息
	 */
	public Video getVideo() {
		return video;
	}
	
	public void setVideo(Video video) {
		this.video = video;
	}

	/**
	 * 设置视频消息内容
	 * @param media_id 媒体文件ID
	 * @param title 标题
	 * @param desc 描述
	 */
	public void setVideo2(String media_id, String title, String desc) {
		this.video.setMedia_id(media_id);
		this.video.setTitle(title);
		this.video.setDescription(desc);
	}

	/**
	 * 视频消息结构
	 * @author Administrator
	 *
	 */
	public static class Video {
		/**
		 * 视频文件id，可以调用上传媒体文件接口获取 
		 */
		private String media_id;
		/**
		 * 视频消息的标题 
		 */
		private String title;
		/**
		 * 视频消息的描述 
		 */
		private String description;
		/**
		 * 获取媒体文件ID
		 * @return 语音文件ID
		 */
		public String getMedia_id() {
			return media_id;
		}
		/**
		 * 视频文件id，可以调用上传媒体文件接口获取 
		 * @param media_id 视频文件id
		 */
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
		/**
		 * 视频消息的标题
		 * @return 消息标题
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * 设定视频消息的标题
		 * @param title 消息标题 
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		/**
		 * 获取视频消息的描述
		 * @return 描述文字
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * 设定视频消息的描述
		 * @param description 描述文字
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
	}
}
