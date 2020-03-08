package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 链接消息
 * @author Administrator
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NLinkMessage extends RMessage {
	/**
	 * 标题 
	 */
	private String Title;
	/**
	 * 描述
	 */
	private String Description;
	/**
	 *  封面缩略图的url  
	 */
	private String PicUrl;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	@Override
	public String toString() {
		return "RLinkMessage [Title=" + Title + ", Description=" + Description
				+ ", PicUrl=" + PicUrl + "]";
	}
	
}
