package cn.microvideo.sdk.qywx.msg.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 要回复的文本消息
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextMessage extends Message {

	/**
	 * 文本内容
	 */
	private String Content;
	
	public TextMessage() {
		this.setMsgType(TEXT);
	}
	/**
	 * 获取消息内容
	 * @return 消息内容
	 */
	public String getContent() {
		return Content;
	}
	/**
	 * 设定消息内容
	 * @param content 消息内容
	 */
	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String toString() {
		return "TextMessage [Content=" + Content + ", toString()="
				+ super.toString() + "]";
	}

}
