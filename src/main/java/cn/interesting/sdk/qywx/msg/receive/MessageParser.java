package cn.interesting.sdk.qywx.msg.receive;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.interesting.sdk.qywx.msg.response.Message;
import cn.interesting.sdk.qywx.utils.XMLUtils;

/**
 * 解析微信服务器发送过来的消息
 * @author Administrator
 *
 */
public final class MessageParser {
	/**
	 * 匹配消息类型的正则对象
	 */
	private final static Pattern MSG_TYPE_PATTERN = Pattern.compile("<MsgType><!\\[CDATA\\[(.*)]]></MsgType>");
	/**
	 * 匹配消息事件类型的正则对象
	 */
	private final static Pattern EVENT_PATTERN = Pattern.compile("<Event><!\\[CDATA\\[(.*)]]></Event>");
	/**
	 * 匹配通讯录变更事件中变更类型的正则对象
	 */
	private final static Pattern CONTACT_CHANGE_TYPE_PATTERN = Pattern.compile("<ChangeType><!\\[CDATA\\[(.*)]]></ChangeType>");
	//消息类型映射
	private final static Map<String, Class<? extends RMessage>> MESSAGE_CLASS_MAPPING = new HashMap<>();
	private final static Map<String, Class<? extends EventMessage>> EVENT_CLASS_MAPPING = new HashMap<>();
	private final static Map<String, Class<? extends EventContactMessage>> CHANGE_CONTACT_CLASS_MAPPING = new HashMap<>();
	static {
		MESSAGE_CLASS_MAPPING.put(RMessage.TEXT, NTextMessage.class);
		MESSAGE_CLASS_MAPPING.put(RMessage.IMAGE, NImageMessage.class);
		MESSAGE_CLASS_MAPPING.put(RMessage.VOICE, NVoiceMessage.class);
		MESSAGE_CLASS_MAPPING.put(RMessage.VIDEO, NVideoMessage.class);
		MESSAGE_CLASS_MAPPING.put(RMessage.LOCATION, NLocationMessage.class);
		MESSAGE_CLASS_MAPPING.put(RMessage.SHORTVIDEO, NShortVideoMessage.class);
		MESSAGE_CLASS_MAPPING.put(RMessage.LINK, NLinkMessage.class);
		MESSAGE_CLASS_MAPPING.put(RMessage.EVENT, EventMessage.class);
		
		EVENT_CLASS_MAPPING.put(EventMessage.BATCH_JOB_RESULT, EventBatchJobResult.class);
		EVENT_CLASS_MAPPING.put(EventMessage.CLICK, EventMenuClick.class);
		EVENT_CLASS_MAPPING.put(EventMessage.ENTER_AGENT, EventEnterAgent.class);
		EVENT_CLASS_MAPPING.put(EventMessage.LOCATION, EventLocation.class);
		EVENT_CLASS_MAPPING.put(EventMessage.LOCATION_SELECT, EventLocationSelect.class);
		EVENT_CLASS_MAPPING.put(EventMessage.PIC_PHOTO_OR_ALBUM, EventPicPhotoOrAlbum.class);
		EVENT_CLASS_MAPPING.put(EventMessage.PIC_SYSPHOTO, EventPicSysPhoto.class);
		EVENT_CLASS_MAPPING.put(EventMessage.PIC_WEIXIN, EventPicWeixin.class);
		EVENT_CLASS_MAPPING.put(EventMessage.SCANCODE_PUSH, EventScanCodePush.class);
		EVENT_CLASS_MAPPING.put(EventMessage.SCANCODE_WAITMSG, EventScanCodeWaitmsg.class);
		EVENT_CLASS_MAPPING.put(EventMessage.SUBSCRIBE, EventSubOrUnsub.class);
		EVENT_CLASS_MAPPING.put(EventMessage.UNSUBSCRIBE, EventSubOrUnsub.class);
		EVENT_CLASS_MAPPING.put(EventMessage.VIEW, EventMenuView.class);
		EVENT_CLASS_MAPPING.put(EventMessage.CHANGE_CONTACT, EventContactMessage.class);
		
		CHANGE_CONTACT_CLASS_MAPPING.put(EventContactMessage.CREATE_USER, EventUserCreateUpdate.class);
		CHANGE_CONTACT_CLASS_MAPPING.put(EventContactMessage.UPDATE_USER, EventUserCreateUpdate.class);
		CHANGE_CONTACT_CLASS_MAPPING.put(EventContactMessage.DELETE_USER, EventDeleteUser.class);
		CHANGE_CONTACT_CLASS_MAPPING.put(EventContactMessage.CREATE_PARTY, EventCreateParty.class);
		CHANGE_CONTACT_CLASS_MAPPING.put(EventContactMessage.UPDATE_PARTY, EventUpdateParty.class);
		CHANGE_CONTACT_CLASS_MAPPING.put(EventContactMessage.DELETE_PARTY, EventDeleteParty.class);
		CHANGE_CONTACT_CLASS_MAPPING.put(EventContactMessage.UPDATE_TAG, EventUpdateTag.class);
	}
	/**
	 * 将数据转换为消息对象
	 * @param xml 消息对象XML描述
	 * @return 消息对象 {@link Message}的子类
	 * @throws WechatException 找不到消息或事件类型；消息转换失败抛出异常
	 */
	public static RMessage parse(String xml) {
		Objects.requireNonNull(xml, "接收到的消息为空");
		String msgType = null;
		Matcher matcher = MSG_TYPE_PATTERN.matcher(xml);
		if(matcher.find()){
			msgType = matcher.group(1);
		}
		Class<? extends RMessage> clazz = MESSAGE_CLASS_MAPPING.get(msgType);
		if(EventMessage.class == clazz) {
			String event = null;
			matcher = EVENT_PATTERN.matcher(xml);
			if(matcher.find()){
				event = matcher.group(1);
			}
			clazz = EVENT_CLASS_MAPPING.get(event);
			if(EventContactMessage.class == clazz) {
				String changeType = null;
				matcher = CONTACT_CHANGE_TYPE_PATTERN.matcher(xml);
				if(matcher.find()){
					changeType = matcher.group(1);
				}
				clazz = CHANGE_CONTACT_CLASS_MAPPING.get(changeType);
			}
		}
		if(clazz == null){
			throw new IllegalArgumentException("不能解析的消息类型.xml:" + xml);
		}
		return XMLUtils.xml2Object(xml, clazz);
	}
}
