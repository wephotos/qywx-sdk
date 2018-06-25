package cn.microvideo.sdk.qywx.msg.response;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang.StringUtils;

import cn.microvideo.sdk.qywx.config.WXConfigUtils;

/**
 * 企业响应给用户的消息基本字段
 * @author Aaron.tian
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Message {
	
	/**
	 * 文本
	 */
	public static final String TEXT = "text";
	
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
	 * 新闻 (图文链接)
	 */
	public static final String NEWS = "news";
	/**
	 * 成员UserID 
	 */
	private String ToUserName = "";
	/**
	 * 企业号CorpID 
	 */
	private String FromUserName = "";
	/**
	 * 消息创建时间（整型） 
	 */
	private String CreateTime = "";
	/**
	 * 消息类型
	 */
	private String MsgType = "";
	
	/**
	 * 无参构造；设定了发送ID，即企业号ID；消息的创建时间（整型）
	 */
	public Message() {
		this.FromUserName = WXConfigUtils.getCorpid();
		this.CreateTime = String.valueOf(new Date().getTime());
	}
	
	/**
	 * 接收成员UserID 
	 * @return 成员UserID 
	 */
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	
	/**
	 * 企业号CorpID 
	 * @return 企业号CorpID 
	 */
	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	/**
	 * 消息创建时间（整型） 
	 * @return 消息创建时间
	 */
	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public void setMsgType(String msgType) {
		if(isLegal4Type(msgType)){
			this.MsgType = msgType;
		}else{
			throw new IllegalArgumentException("消息类型不合法！");
		}
	}

	/**
	 * 获取消息类型
	 * @return 消息类型
	 */
	public String getMsgType(){
		return this.MsgType;
	}
	
	/**
	 * 判定消息类型是否合法
	 * @param type 消息类型，参见本类常量
	 * @return true or false
	 */
	private boolean isLegal4Type(String type){
		if(StringUtils.isEmpty(type)){
			return false;
		}else{
			return type.equals(TEXT)
					|| type.equals(IMAGE)
					|| type.equals(VOICE)
					|| type.equals(VIDEO)
					|| type.equals(NEWS);
		}
	}
}
