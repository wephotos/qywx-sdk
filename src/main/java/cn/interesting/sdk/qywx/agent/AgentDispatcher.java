package cn.interesting.sdk.qywx.agent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import cn.interesting.sdk.qywx.aes.AesException;
import cn.interesting.sdk.qywx.aes.WXBizMsgCrypt;
import cn.interesting.sdk.qywx.config.WXConfigUtils;
import cn.interesting.sdk.qywx.contacts.ContactsListener;
import cn.interesting.sdk.qywx.exception.ErrcodeException;
import cn.interesting.sdk.qywx.exception.WXUnCheckedException;
import cn.interesting.sdk.qywx.exception.WeChatUncaughtExceptionHandler;
import cn.interesting.sdk.qywx.msg.receive.EventContactMessage;
import cn.interesting.sdk.qywx.msg.receive.MessageParser;
import cn.interesting.sdk.qywx.msg.receive.RMessage;
import cn.interesting.sdk.qywx.msg.receive.VerifyMessage;
import cn.interesting.sdk.qywx.msg.send.SMessage;
import cn.interesting.sdk.qywx.msg.send.SResponse;
import cn.interesting.sdk.qywx.utils.WEBUtils;

/**
 * 微信通用服务接口
 * @author Aaron.tian
 *
 */
public final class AgentDispatcher {
	/**
	 * 线程池
	 */
	public static final ExecutorService CachedThreadPool = Executors.newCachedThreadPool();
	/**
	 * 提供接收和推送给公众平台消息的加解密接口(UTF8编码的字符串). 
	 * 在不同的应用中可填写相同的 Token EncodingAESKey
	 */
	private WXBizMsgCrypt wxcpt = null;
	/**
	 * 通讯录监听
	 */
	private ContactsListener contactsListener = new ContactsListener() {
		@Override
		public void change_contact(EventContactMessage ecm) {
			
		}
	};
	/**
	 * 私有默认构造
	 */
	private AgentDispatcher(){
		this(WXConfigUtils.getAgentToken(), WXConfigUtils.getAgentEncodingAesKey());
	}
	
	/**
	 * 使用指定的Token和密钥创建一个应用转发对象；指定的Token和密钥用于解析具有不同的令牌和密钥的应用消息；
	 * @param sToken 令牌
	 * @param sEncodingAESKey 密钥
	 */
	public AgentDispatcher(String sToken, String sEncodingAESKey){
		try {
			String sCorpID = WXConfigUtils.getCorpid();
			wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
			loadContactsListener();
		} catch (AesException e) {
			throw new WXUnCheckedException(e.getMessage());
		}
	}
	/**
	 * 加载消息监听
	 */
	private void loadContactsListener(){
		String contactsListenerClassName = WXConfigUtils.getContactsListener();
		if(StringUtils.isNotBlank(contactsListenerClassName)){
			try {
				contactsListener = (ContactsListener) Class.forName(contactsListenerClassName).newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				System.err.println("不能加载消息监听："+e.getMessage());
			}
		}
	}

	/**
	 * 提供当前类实例的内部类
	 * @author Administrator
	 *
	 */
	static class InstanceProvider{
		static AgentDispatcher INSTANCE = new AgentDispatcher();
	}
	
	/**
	 * 获取当前类实例
	 * @return 类实例
	 */
	public static AgentDispatcher getInstance(){
		return InstanceProvider.INSTANCE;
	}
	
	/**
	 * 执行应用的方法 {@link Agent#doService(AgentRequest, HttpServletRequest)}
	 * @param aRequest 应用参数 {@link AgentRequest}
	 * @return Object
	 */
	public Object doService(int agentId, HttpServletRequest request, HttpServletResponse response) {
		for(Agent agent:AgentFactory.AGENTS){
			if(agent.getAgentId() == agentId){
				return agent.doService(request, response);
			}
		}
		throw new RuntimeException("找不到对应的应用实例;agentId=>" + agentId);
	}
	
	/**
	 * 接收应用发送过来的消息
	 * @param request
	 * @param verifyMessage
	 */
	public String receiving(HttpServletRequest request, VerifyMessage verifyMessage) {
		String method = request.getMethod();
		String sVerifyMsgSig = verifyMessage.getMsg_signature();
		String sVerifyTimeStamp = verifyMessage.getTimestamp();
		String sVerifyNonce = verifyMessage.getNonce();
		//GET 验证
		if("GET".equalsIgnoreCase(method)){
			String sVerifyEchoStr = verifyMessage.getEchostr();
			String sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
			return sEchoStr;
		}
		
		String bodyData = WEBUtils.toString(request);
		final String xml = wxcpt.DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, bodyData);
		Thread.setDefaultUncaughtExceptionHandler(new WeChatUncaughtExceptionHandler());
		//回复5S超时,进行异步回复
		//将接收的消息分发到不同的应用模块
		CachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				RMessage message = MessageParser.parse(xml);
				if(message == null){
					return;
				}
				//通讯录变更事件
				if(message instanceof EventContactMessage){
					contactsListener.change_contact((EventContactMessage) message);
					return;
				}
				int agentId = message.getAgentID();
				for(final Agent agent:AgentFactory.AGENTS){
					if(agentId == agent.getAgentId()){
						agent.doReceiving(message);
						return;
					}
				}
			}
		});
		//String sEncryptMsg = wxcpt.EncryptMsg(sRespData, sVerifyTimeStamp, sVerifyNonce);
		return "";
	}
	
	/**
	 * 推送消息到微信终端
	 * {@link Agent#doSending(SMessage)}
	 * @param message 发送的消息
	 * @throws ErrcodeException 
	 */
	public SResponse doSending(SMessage message) throws ErrcodeException {
		for(Agent agent:AgentFactory.AGENTS){
			if(agent.getAgentId() == message.getAgentid()){
				return agent.doSending(message);
			}
		}
		throw new WXUnCheckedException("找不到应用实例.agentId->"+message.getAgentid());
	}

}
