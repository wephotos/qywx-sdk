package cn.microvideo.sdk.qywx.msg.send;


/**
 * 文件消息
 * @author Aaron.tian
 *
 */
public class FileMessage extends SMessage {
	
	/**
	 * 媒体文件
	 */
	private File file = new File();

	@Override
	public String getMsgtype() {
		return "file";
	}
	/**
	 * 获取文件
	 * @return 文件对象
	 */
	public File getFile() {
		return file;
	}
	/**
	 * 设定文件
	 * @param file 文件对象
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 文件对象
	 * @author Administrator
	 *
	 */
	public static class File{
		/**
		 * 媒体文件id，可以调用上传媒体文件接口获取 
		 */
		private String media_id;
		/**
		 * 获取文件ID
		 * @return 文件ID
		 */
		public String getMedia_id() {
			return media_id;
		}
		/**
		 * 设定媒体文件ID；媒体文件id，可以调用上传媒体文件接口获取 
		 * @param media_id 媒体文件id
		 */
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
	}
}
