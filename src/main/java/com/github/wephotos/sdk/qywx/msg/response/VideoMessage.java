package com.github.wephotos.sdk.qywx.msg.response;

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
public class VideoMessage extends Message {

	/**
	 * 视频消息
	 */
	private Video video = new Video();
	
	public VideoMessage() {
		this.setMsgType(VIDEO);
	}
	
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	/**
	 * 视频消息
	 * @author Administrator
	 *
	 */
	@XmlRootElement(name="Video")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Video{
		/**
		 * 视频文件id，可以调用上传媒体文件接口获取 
		 */
		private String MediaId = "";
		/**
		 * 视频消息的标题
		 */
		private String Title = "";
		/**
		 * 视频消息的描述 
		 */
		private String Description = "";
		
		/**
		 * 获取视频文件ID
		 * @return 文件ID
		 */
		public String getMediaId() {
			return MediaId;
		}
		/**
		 * 视频文件id，可以调用上传媒体文件接口获取 
		 * @param mediaId 视频文件id
		 */
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		/**
		 * 获取标题
		 * @return 标题
		 */
		public String getTitle() {
			return Title;
		}
		/**
		 * 设定标题
		 * @param title 标题
		 */
		public void setTitle(String title) {
			Title = title;
		}
		/**
		 * 获取描述
		 * @return 描述
		 */
		public String getDescription() {
			return Description;
		}
		/**
		 * 设定描述
		 * @param description 描述
		 */
		public void setDescription(String description) {
			Description = description;
		}
		
	}
}
