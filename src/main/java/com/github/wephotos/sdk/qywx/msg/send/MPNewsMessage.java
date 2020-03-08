package com.github.wephotos.sdk.qywx.msg.send;

import java.util.ArrayList;
import java.util.List;

/**
 * 注：mpnews消息与news消息类似，不同的是图文消息内容存储在微信后台，并且支持保密选项。<br>
 * 每个应用每天最多可以发送100次。
 * @author Aaron.tian
 *
 */
public class MPNewsMessage extends SMessage {
	
	/**
	 * 图文消息
	 */
	private MPNews mpnews = new MPNews();

	@Override
	public String getMsgtype() {
		return "mpnews";
	}
	/**
	 * 图文消息
	 * @return 图文消息
	 */
	public MPNews getMpnews() {
		return mpnews;
	}
	/**
	 * 设定图文消息
	 * @param mpnews 图文消息
	 */
	public void setMpnews(MPNews mpnews) {
		this.mpnews = mpnews;
	}
	/**
	 * 添加一条图文信息
	 * @param article
	 */
	public void add(Article article){
		this.mpnews.addArticle(article);
	}
	/**
	 * 图文 消息
	 * @author Administrator
	 *
	 */
	public static class MPNews{
		/**
		 * 图文消息，一个图文消息支持1到8条图文 
		 */
		private List<Article> articles = new ArrayList<Article>();
		
		/**
		 * 图文消息列表
		 * @return 消息列表
		 */
		public List<Article> getArticles() {
			return articles;
		}
		
		public void setArticles(List<Article> articles) {
			this.articles = articles;
		}
		/**
		 * 添加一条图文，不需要外部调用
		 * @param article
		 */
		public void addArticle(Article article){
			if(this.articles.size() == 8){
				return;
			}
			this.articles.add(article);
		}
	}
	/**
	 * 单条消息结构
	 * @author Administrator
	 *
	 */
	public static class Article{
		/**
		 * 图文消息的标题 
		 */
		private String title;
		/**
		 * 图文消息缩略图的media_id, 可以在上传多媒体文件接口中获得。
		 * 此处thumb_media_id即上传接口返回的media_id
		 */
		private String thumb_media_id;
		/**
		 * 图文消息的作者 
		 */
		private String author;
		/**
		 * 图文消息点击“阅读原文”之后的页面链接 
		 */
		private String content_source_url;
		/**
		 * 图文消息的内容，支持html标签 
		 */
		private String content;
		/**
		 * 图文消息的描述 
		 */
		private String digest;
		/**
		 * 图文消息的标题 
		 * @return 标题 
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * 图文消息的标题 
		 * @param title 消息标题
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		/**
		 * 图文消息缩略图的media_id
		 * @return media_id
		 */
		public String getThumb_media_id() {
			return thumb_media_id;
		}
		/**
		 * 图文消息缩略图的media_id, 可以在上传多媒体文件接口中获得。
		 * 此处thumb_media_id即上传接口返回的media_id
		 * @param thumb_media_id 图文消息缩略图的media_id
		 */
		public void setThumb_media_id(String thumb_media_id) {
			this.thumb_media_id = thumb_media_id;
		}
		/**
		 * 图文消息的作者 
		 * @return 作者 
		 */
		public String getAuthor() {
			return author;
		}
		/**
		 * 图文消息的作者 
		 * @param author 作者 
		 */
		public void setAuthor(String author) {
			this.author = author;
		}
		/**
		 * 图文消息点击“阅读原文”之后的页面链接 
		 * @return 页面链接 
		 */
		public String getContent_source_url() {
			return content_source_url;
		}
		/**
		 * 图文消息点击“阅读原文”之后的页面链接 
		 * @param content_source_url 原文页面链接
		 */
		public void setContent_source_url(String content_source_url) {
			this.content_source_url = content_source_url;
		}
		/**
		 * 图文消息的内容，支持html标签 
		 * @return 消息的内容
		 */
		public String getContent() {
			return content;
		}
		/**
		 * 图文消息的内容，支持html标签 
		 * @param content 消息的内容
		 */
		public void setContent(String content) {
			this.content = content;
		}
		/**
		 * 图文消息的描述 
		 * @return 图文消息的描述 
		 */
		public String getDigest() {
			return digest;
		}
		/**
		 * 图文消息的描述 
		 * @param digest 图文消息的描述 
		 */
		public void setDigest(String digest) {
			this.digest = digest;
		}
		
	}
}
