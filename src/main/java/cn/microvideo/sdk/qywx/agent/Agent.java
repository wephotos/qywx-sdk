package cn.microvideo.sdk.qywx.agent;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import cn.microvideo.sdk.qywx.config.WXConfigUtils;
import cn.microvideo.sdk.qywx.exception.ErrcodeException;
import cn.microvideo.sdk.qywx.media.MediaFile;
import cn.microvideo.sdk.qywx.media.MediaManager;
import cn.microvideo.sdk.qywx.media.UploadResponse;
import cn.microvideo.sdk.qywx.msg.receive.RMessage;
import cn.microvideo.sdk.qywx.msg.response.Message;
import cn.microvideo.sdk.qywx.msg.send.Messager;
import cn.microvideo.sdk.qywx.msg.send.SMessage;
import cn.microvideo.sdk.qywx.msg.send.SResponse;
import cn.microvideo.sdk.qywx.token.JSAPITicket;

/**
 * 应用服务接口
 * 各应用提供实现,子类必须提供无参构造
 * @author Aaron.tian
 *
 */
@ATAgent
public abstract class Agent {
	
	/**
	 * 应用密钥
	 */
	private String secret;
	/**
	 * 应用ID
	 * @return 应用ID
	 */
	public abstract int getAgentId();
	/**
	 * 接收消息
	 * @param message
	 * @return
	 */
	protected abstract Message doReceiving(RMessage message);
	/**
	 * 获取应用密钥
	 * @return 密钥
	 */
	public String getSecret(){
		if(StringUtils.isBlank(secret)){
			this.secret = WXConfigUtils.getAgentSecret(getAgentId());
		}
		return this.secret;
	}
	/**
	 * 应用接收HTTP请求入口
	 * @param request
	 * @param response
	 * @return
	 */
	public Object doService(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	/**
	 * 发送消息
	 * @param message
	 * @return
	 */
	public SResponse doSending(SMessage message) throws ErrcodeException {
		message.setAgentid(getAgentId());
		return Messager.send(message);
	}
	
	/**
	 * 获取应用的可信域名，如果可信域名与其它应用的可信域名不同需要重写返回
	 * @return 可信域名
	 */
	public String getTrustedDomain(){
		return null;
	}
	
	/**
	 * 是否是用于处理错误信息的应用;
	 * <br>
	 * 如果是该应用将接收到程序的未处理的异常信息供管理人员查看
	 * @return true or false
	 */
	public boolean isUncaughtExceptionHandler(){
		return false;
	}
	
	/**
	 * 下载临时素材文件
	 * @param media_id
	 * @return
	 * @throws ErrcodeException
	 */
	public MediaFile getMediaFile(String media_id) throws ErrcodeException{
		return MediaManager.get(media_id, getSecret());
	}
	/**
	 * 上传临时素材文件
	 * @param mediaFile
	 * @return
	 * @throws ErrcodeException
	 */
	public UploadResponse uploadMediaFile(MediaFile mediaFile) throws ErrcodeException{
		return MediaManager.upload(mediaFile, getSecret());
	}
	
	/**
	 * 获取应用JS-SDK签名
	 * @param request
	 * @return 签名map
	 * @throws ErrcodeException
	 */
	public final Map<String,String> getAPISignMap(HttpServletRequest request) throws ErrcodeException {
		String jsapi_ticket = JSAPITicket.getJSAPITicket(getSecret());
		String _url = request.getRequestURL().toString();
		String QString = request.getQueryString();
		if(QString !=null && !"null".equals(QString)){
			QString = "?"+QString;
		}else{
			QString = "";
		}
		Map<String,String> map = JSAPITicket.sign(jsapi_ticket, _url+QString);
		map.put("corpId", WXConfigUtils.getCorpid());
		return map;
	}
}
