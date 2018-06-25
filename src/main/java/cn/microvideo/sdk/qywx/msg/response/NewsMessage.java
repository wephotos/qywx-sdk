package cn.microvideo.sdk.qywx.msg.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 图文链接消息
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsMessage extends Message {
	/**
	 * 最大图文数量
	 */
	public static final int MAX_COUNT = 8;
	/**
	 * 图文条数，默认第一条为大图。图文数不能超过8，否则将会无响应 
	 */
	private int ArticleCount;
	/**
	 * 图文消息集合
	 */
	private Articles Articles = new Articles();
	
	public NewsMessage() {
		this.setMsgType(NEWS);
	}
	/**
	 * 图文数量
	 * @return 图文数量
	 */
	public int getArticleCount() {
		return ArticleCount;
	}
	/**
	 * 设置图文数量 大于最大数量或小于1将被忽略
	 * @param articleCount 数量
	 */
	public void setArticleCount(int articleCount) {
		if(articleCount > MAX_COUNT){
			return;
		}
		if(articleCount < 1){
			return;
		}
		ArticleCount = articleCount;
	}
	/**
	 * 获取图文消息
	 * @return 图文消息对象
	 */
	public Articles getArticles() {
		return Articles;
	}
	/**
	 * 设置图文消息
	 * @param articles
	 */
	public void setArticles(Articles articles) {
		Articles = articles;
	}
	/**
	 * 添加一条图文消息
	 * @param article
	 */
	public void addArticle(Article article){
		this.Articles.add(article);
	}

	/**
	 * 多条图文消息
	 * @author Administrator
	 *
	 */
	public static class Articles{
		/**
		 * 图文列表项
		 */
		private List<Article> item = new ArrayList<NewsMessage.Article>();
		
		/**
		 * 获取图文消息列表
		 * @return 消息列表
		 */
		public List<Article> getItem() {
			return item;
		}
		/**
		 * 设定图文消息列表
		 * @param item 消息列表
		 */
		public void setItem(List<Article> item) {
			this.item = item;
		}
		/**
		 * 添加一条图文
		 * @param e
		 */
		public void add(Article e){
			if(this.item.size() == 10){
				return;
			}
			this.item.add(e);
		}
	}
	/**
	 * NEWS消息
	 * @author Administrator
	 *
	 */
	@XmlRootElement(name="item")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Article{
		/**
		 * 图文消息标题 
		 */
		private String Title;
		/**
		 * 图文消息描述 
		 */
		private String Description;
		/**
		 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200 
		 */
		private String PicUrl;
		/**
		 * 点击图文消息跳转链接 
		 */
		private String Url;
		
		/**
		 * 消息标题 
		 * @return 标题
		 */
		public String getTitle() {
			return Title;
		}
		/**
		 * 设定图文消息标题
		 * @param title 标题
		 */
		public void setTitle(String title) {
			Title = title;
		}
		/**
		 * 设定消息描述
		 * @return 消息描述
		 */
		public String getDescription() {
			return Description;
		}
		/**
		 * 设定消息描述
		 * @param description 消息描述
		 */
		public void setDescription(String description) {
			Description = description;
		}
		/**
		 * 获取图片路径
		 * @return 图片URL
		 */
		public String getPicUrl() {
			return PicUrl;
		}
		/**
		 * 设定图片URL
		 * @param picUrl
		 */
		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}
		/**
		 * 获取消息链接
		 * @return 链接URL
		 */
		public String getUrl() {
			return Url;
		}
		/**
		 * 设定消息链接
		 * @param url 链接
		 */
		public void setUrl(String url) {
			Url = url;
		}
		
	}
}
