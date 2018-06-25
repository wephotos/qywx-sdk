package cn.microvideo.sdk.qywx.msg.send;

/**
 * 图片消息
 * @author Aaron.tian
 *
 */
public class ImageMessage extends SMessage {
	
	/**
	 * 图片
	 */
	private Image image = new Image();

	@Override
	public String getMsgtype() {
		return "image";
	}
	
	/**
	 * 获取图片
	 * @return 图片对象
	 */
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	/**
	 * 设置图片文件ID
	 * @param media_id 文件ID
	 */
	public void setImage2(String media_id) {
		this.image.setMedia_id(media_id);
	}

	/**
	 * 图片消息
	 * @author Administrator
	 *
	 */
	public static class Image{
		/**
		 * 图片媒体文件id，可以调用上传媒体文件接口获取 
		 */
		private String media_id;
		
		/**
		 * 获取图片文件ID
		 * @return 图片ID
		 */
		public String getMedia_id() {
			return media_id;
		}
		/**
		 * 设定图片文件ID
		 * @param media_id 图片文件ID
		 */
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
		
	}
}
