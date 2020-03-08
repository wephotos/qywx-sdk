package com.github.wephotos.sdk.qywx.msg.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 图片消息
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImageMessage extends Message {
	
	/**
	 * 图片
	 */
	@XmlElement
	private Image Image = new Image();
	
	/**
	 * 设置消息类型为图片
	 */
	public ImageMessage() {
		super.setMsgType(IMAGE);
	}
	
	/**
	 * 获取图片对象
	 * @return 图片对象
	 */
	public Image getImage() {
		return Image;
	}
	public void setImage(Image image) {
		Image = image;
	}
	/**
	 * 设置图片对象
	 * @param media_id 图片文件ID
	 */
	public void setImage2(String media_id) {
		this.Image.setMediaId(media_id);
	}

	/**
	 * 图片文件
	 * @author Administrator
	 *
	 */
	@XmlRootElement(name="Image")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Image{
		/**
		 * 图片文件id，可以调用上传媒体文件接口获取 
		 */
		private String MediaId = "";
		/**
		 * 图片文件id
		 * @return 图片文件id
		 */
		public String getMediaId() {
			return MediaId;
		}
		
		/**
		 * 图片文件id，可以调用上传媒体文件接口获取 
		 * @param mediaId 图片文件id
		 */
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		
	}
}
