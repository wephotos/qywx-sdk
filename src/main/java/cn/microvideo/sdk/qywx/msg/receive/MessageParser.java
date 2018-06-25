package cn.microvideo.sdk.qywx.msg.receive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import cn.microvideo.sdk.qywx.msg.response.Message;
import cn.microvideo.sdk.qywx.utils.XMLUtils;

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
	/**
	 * 将数据转换为消息对象
	 * @param xml 消息对象XML描述
	 * @return 消息对象 {@link Message}的子类
	 * @throws WechatException 找不到消息或事件类型；消息转换失败抛出异常
	 */
	@Nullable
	public static RMessage parse(String xml) {
		String msgType = null;
		Matcher matcher = MSG_TYPE_PATTERN.matcher(xml);
		if(matcher.find()){
			msgType = matcher.group(1);
		}
		Class<? extends RMessage> clazz;
		if(msgType.equals(RMessage.TEXT)){
			clazz = NTextMessage.class;
		}else if(msgType.equals(RMessage.IMAGE)){
			clazz = NImageMessage.class;
		}else if(msgType.equals(RMessage.VOICE)){
			clazz = NVoiceMessage.class;
		}else if(msgType.equals(RMessage.VIDEO)){
			clazz = NVideoMessage.class;
		}else if(msgType.equals(RMessage.LOCATION)){
			clazz = NLocationMessage.class;
		}else if(msgType.equals(RMessage.SHORTVIDEO)){
			clazz = NShortVideoMessage.class;
		}else if(msgType.equals(RMessage.LINK)){
			clazz = NLinkMessage.class;
		}else if(msgType.equals(RMessage.EVENT)){
			String event = null;
			matcher = EVENT_PATTERN.matcher(xml);
			if(matcher.find()){
				event = matcher.group(1);
			}
			if(EventMessage.BATCH_JOB_RESULT.equals(event)){
				clazz = EventBatchJobResult.class;
			}else if(EventMessage.CLICK.equals(event)){
				clazz = EventMenuClick.class;
			}else if(EventMessage.ENTER_AGENT.equals(event)){
				clazz = EventEnterAgent.class;
			}else if(EventMessage.LOCATION.equals(event)){
				clazz = EventLocation.class;
			}else if(EventMessage.LOCATION_SELECT.equals(event)){
				clazz = EventLocationSelect.class;
			}else if(EventMessage.PIC_PHOTO_OR_ALBUM.equals(event)){
				clazz = EventPicPhotoOrAlbum.class;
			}else if(EventMessage.PIC_SYSPHOTO.equals(event)){
				clazz = EventPicSysPhoto.class;
			}else if(EventMessage.PIC_WEIXIN.equals(event)){
				clazz = EventPicWeixin.class;
			}else if(EventMessage.SCANCODE_PUSH.equals(event)){
				clazz = EventScanCodePush.class;
			}else if(EventMessage.SCANCODE_WAITMSG.equals(event)){
				clazz = EventScanCodeWaitmsg.class;
			}else if(EventMessage.SUBSCRIBE.equals(event)){
				clazz = EventSubOrUnsub.class;
			}else if(EventMessage.UNSUBSCRIBE.equals(event)){
				clazz = EventSubOrUnsub.class;
			}else if(EventMessage.VIEW.equals(event)){
				clazz = EventMenuView.class;
			}else if(EventMessage.CHANGE_CONTACT.equals(event)){
				String changeType = null;
				matcher = CONTACT_CHANGE_TYPE_PATTERN.matcher(xml);
				if(matcher.find()){
					changeType = matcher.group(1);
				}
				if(EventContactMessage.CREATE_USER.equals(changeType)){
					clazz = EventUserCreateUpdate.class;
				}else if(EventContactMessage.UPDATE_USER.equals(changeType)){
					clazz = EventUserCreateUpdate.class;
				}else if(EventContactMessage.DELETE_USER.equals(changeType)){
					clazz = EventDeleteUser.class;
				}else if(EventContactMessage.CREATE_PARTY.equals(changeType)){
					clazz = EventCreateParty.class;
				}else if(EventContactMessage.UPDATE_PARTY.equals(changeType)){
					clazz = EventUpdateParty.class;
				}else if(EventContactMessage.DELETE_PARTY.equals(changeType)){
					clazz = EventDeleteParty.class;
				}else if(EventContactMessage.UPDATE_TAG.equals(changeType)){
					clazz = EventUpdateTag.class;
				}else{
					clazz = null;
				}
			}else{
				clazz = null;
			}
		}else{
			clazz = null;
		}
		if(clazz == null){
			return null;
		}else{
			return XMLUtils.xml2Object(xml, clazz);
		}
	}
}
