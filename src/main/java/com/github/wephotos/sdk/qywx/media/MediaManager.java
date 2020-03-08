package com.github.wephotos.sdk.qywx.media;

import java.io.IOException;
import java.util.Date;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.util.EntityUtils;

import com.github.wephotos.sdk.qywx.WeChatRES;
import com.github.wephotos.sdk.qywx.config.DomainUtils;
import com.github.wephotos.sdk.qywx.exception.AccessTokenInvalidException;
import com.github.wephotos.sdk.qywx.exception.ErrcodeException;
import com.github.wephotos.sdk.qywx.exception.HttpSessionInvalidException;
import com.github.wephotos.sdk.qywx.exception.WXUnCheckedException;
import com.github.wephotos.sdk.qywx.token.AccessToken;
import com.github.wephotos.sdk.qywx.utils.HttpUtils;
import com.github.wephotos.sdk.qywx.utils.JSONUtils;

/**
 * 媒体文件管理，上传、下载 <br>
 * 上传的媒体文件限制 所有文件size必须大于5个字节 图片（image）:1MB，支持JPG,PNG格式<br>
 * 语音（voice）：2MB，播放长度不超过60s，支持AMR格式<br>
 * 视频（video）：10MB，支持MP4格式<br>
 * 普通文件（file）：10MB<br>
 * @author Aaron.tian
 *
 */
public final class MediaManager {

	/**
	 * 上传URL
	 */
	public static final String UPLOAD_URL = DomainUtils.getQywxDomain()+"/cgi-bin/media/upload";
	/**
	 * 获取文件URL
	 */
	public static final String GET_URL = DomainUtils.getQywxDomain()+"/cgi-bin/media/get";
	
	/**
	 * 上传媒体文件
	 * @param mediaFile 媒体文件
	 * @param secret
	 * @return 上传响应消息
	 * @throws ErrcodeException 
	 */
	public static UploadResponse upload(MediaFile mediaFile, String secret) throws ErrcodeException {
		try {
			String access_token = AccessToken.getAccessToken(secret);
			String url = UPLOAD_URL+"?access_token="+access_token+"&type="+mediaFile.getType();
			FileBody contentBody = new FileBody(mediaFile.getMedia());
			HttpEntity postEntity = MultipartEntityBuilder.create().addPart("media", contentBody).build();
			HttpPost post = new HttpPost(url);
			post.setEntity(postEntity);
			UploadResponse resp = JSONUtils.JSON2Object(HttpUtils.getHttpString(post), UploadResponse.class);
			return resp.checkErrorCode();
		}catch (AccessTokenInvalidException e){
			AccessToken.getNewAccessToken(secret);
			return upload(mediaFile, secret);
		}catch (HttpSessionInvalidException e) {
			throw new WXUnCheckedException(e.getMessage());
		}
	}
	
	/**
	 * 下载媒体文件
	 * @param media_id 媒体文件ID
	 * @param secret
	 * @return 媒体响应消息 {@link UploadResponse}
	 * @throws ErrcodeException 
	 */
	public static MediaFile get(String media_id, String secret) throws ErrcodeException {
		try{
			String access_token = AccessToken.getAccessToken(secret);
			String url = GET_URL+"?access_token="+access_token+"&media_id="+media_id;
			HttpGet get = new HttpGet(url);
			get.setHeader("Date", new Date().toString());
			get.setHeader("Cache-Control", "no-cache, must-revalidate");
			HttpResponse response = HttpUtils.getHttpResponse(get);
			HttpEntity entity = response.getEntity();
			Header disposition = response.getFirstHeader("Content-disposition");
			if(disposition != null){
				Header contentType = response.getFirstHeader("Content-Type");
				Header contentLength = response.getFirstHeader("Content-Length");
				MediaFile mediaFile = new MediaFile();
				mediaFile.setStream(entity.getContent());
				mediaFile.setContentType(contentType.getValue());
				mediaFile.setContentLength(contentLength.getValue());
				mediaFile.setContentDisposition(disposition.getValue());
				return mediaFile;
			}else{
				WeChatRES resp = JSONUtils.JSON2Object(EntityUtils.toString(entity), WeChatRES.class);
				resp.checkErrorCode();
				return null;
			}
		}catch (AccessTokenInvalidException e){
			AccessToken.getNewAccessToken(secret);
			return get(media_id, secret);
		}catch (IOException | HttpSessionInvalidException e){
			throw new WXUnCheckedException("下载文件失败",e);
		}
	}

}
