package cn.microvideo.sdk.qywx.media;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

/**
 * 媒体文件描述
 * @author Aaron.tian
 *
 */
public final class MediaFile {
	
	/**
	 * 图片
	 */
	public static final String IMAGE = "image";
	/**
	 * 音频 
	 */
	public static final String VOICE = "voice";
	/**
	 * 视频
	 */
	public static final String VIDEO = "video";
	/**
	 * 普通文件
	 */
	public static final String FILE = "file";

	/**
	 * 文件内容，用于上传
	 */
	private File media;
	
	/**
	 * 媒体文件类型
	 */
	private String type = FILE;
	/**
	 * 文件流，用于下载
	 */
	private transient InputStream stream;
	/**
	 * 内容类型
	 */
	private String contentType;
	/**
	 * 文件名称
	 */
	private String contentDisposition;
	/**
	 * 内容长度
	 */
	private String contentLength;
	/**
	 * 获取媒体文件
	 * @return 媒体文件
	 */
	public File getMedia() {
		return media;
	}
	/**
	 * 设定媒体文件
	 * @param file 媒体文件
	 */
	public void setMedia(File media) {
		this.media = media;
	}
	/**
	 * 获取当前媒体文件类型
	 * @return 文件类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置当前媒体文件类型
	 * @param type 文件类型
	 */
	public void setType(String type) {
		if(checkFileType(type)){
			this.type = type;
		}
	}
	/**
	 * 判定文件类型是否合法
	 * @param type
	 * @return true or false
	 */
	private static boolean checkFileType(String type){
		if(StringUtils.isEmpty(type)){
			return false;
		}else{
			return type.equals(IMAGE)
					|| type.equals(VOICE)
					|| type.equals(VIDEO)
					|| type.equals(FILE);
		}
	}
	
	public InputStream getStream() {
		return stream;
	}
	public void setStream(InputStream stream) {
		this.stream = stream;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	public String getContentLength() {
		return contentLength;
	}
	public void setContentLength(String contentLength) {
		this.contentLength = contentLength;
	}
	@Override
	public String toString() {
		return "MediaFile [media=" + media + ", type=" + type + ", stream="
				+ stream + ", contentType=" + contentType
				+ ", contentDisposition=" + contentDisposition
				+ ", contentLength=" + contentLength + "]";
	}
	
}
