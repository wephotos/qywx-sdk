package cn.interesting.sdk.qywx.media;

import cn.interesting.sdk.qywx.WeChatRES;

/**
 * 上传媒体文件响应消息
 * @author Aaron.tian
 *
 */
public class UploadResponse extends WeChatRES{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）,普通文件(file) 
	 */
	private String type;
	
	/**
	 * 媒体文件上传后获取的唯一标识 
	 */
	private String media_id;
	
	/**
	 * 媒体文件上传时间戳 
	 */
	private long created_at;
	
	/**
	 * 文件类型；分别有图片（image）、语音（voice）、视频（video）,普通文件(file) 
	 * @return 文件类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设定文件类型
	 * @param type 文件类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取媒体文件ID
	 * @return 文件ID
	 */
	public String getMedia_id() {
		return media_id;
	}
	/**
	 * 设定媒体文件ID
	 * @param media_id 媒体文件ID
	 */
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	/**
	 * 媒体文件上传时间戳 
	 * @return 时间戳
	 */
	public long getCreated_at() {
		return created_at;
	}
	/**
	 * 媒体文件上传时间戳 
	 * @param created_at 时间戳
	 */
	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "MediaResponse [type=" + type + ", media_id=" + media_id
				+ ", created_at=" + created_at + ", getErrcode()=" + getErrcode() 
				+ ", getErrmsg()=" + getErrmsg() + "]";
	}

}
