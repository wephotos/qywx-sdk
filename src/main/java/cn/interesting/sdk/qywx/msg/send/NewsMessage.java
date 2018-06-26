package cn.interesting.sdk.qywx.msg.send;

import java.util.ArrayList;
import java.util.List;

/**
 * News 消息
 * @author Aaron.tian
 *
 */
public class NewsMessage extends SMessage{

	/**
	 * 图文消息
	 */
	private News news = new News();
	/**
	 * 按钮文字，仅在图文数为1条时才生效。 默认为“阅读全文”， 不超过4个文字，超过自动截断。
	 */
	private String btntxt;
	
	@Override
	public String getMsgtype() {
		return "news";
	}
	/**
	 * 获取图文消息
	 * @return 图文消息
	 */
	public News getNews() {
		return news;
	}
	/**
	 * 设定图文消息
	 * @param news 图文消息 
	 */
	public void setNews(News news) {
		this.news = news;
	}
	
	public String getBtntxt() {
		return btntxt;
	}
	public void setBtntxt(String btntxt) {
		this.btntxt = btntxt;
	}
	/**
	 * 添加一条图文消息；超出10条消息将被忽略
	 * @param title 标题
	 * @param desc 描述
	 * @param url 链接
	 * @param picurl 图片链接
	 */
	public void add(String title, String desc, String url, String picurl){
		Article article = new Article();
		article.setTitle(title);
		article.setDescription(desc);
		article.setUrl(url);
		article.setPicurl(picurl);
		this.news.addArticle(article);
	}
	/**
	 * 清空消息
	 */
	public void clear(){
		this.news.getArticles().clear();
	}
	/**
	 * 图文 消息
	 * @author Administrator
	 *
	 */
	public static class News{
		/**
		 * 图文消息，一个图文消息支持1到8条图文 
		 */
		private List<Article> articles = new ArrayList<NewsMessage.Article>();

		/**
		 * 获取图文消息列表
		 * @return 图文列表
		 */
		public List<Article> getArticles() {
			return articles;
		}
		/**
		 * 设定图文消息列表
		 * @param articles
		 */
		public void setArticles(List<Article> articles) {
			this.articles = articles;
		}
		/**
		 * 添加一条图文消息、超了10条将被忽略
		 * @param article 图文对象
		 */
		public void addArticle(Article article){
			if(this.articles.size() == 8){
				return;
			}
			this.articles.add(article);
		}
	}

	/**
	 * 单条图文消息描述
	 * @author Administrator
	 *
	 */
	public static class Article{
		/**
		 * 标题 
		 */
		private String title;
		/**
		 * 描述
		 */
        private String description;
        /**
         * 点击后跳转的链接
         */
        private String url;
        /**
         * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。
         * 如不填，在客户端不显示图片
         */
        private String picurl;
        
        /**
         * 获取消息标题
         * @return 标题
         */
		public String getTitle() {
			return title;
		}
		/**
		 * 设定消息标题
		 * @param title 标题
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		/**
		 * 获取消息描述文字
		 * @return 描述文字
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * 设定消息描述文字
		 * @param description 描述文字
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * 设定消息链接
		 * @return 消息链接
		 */
		public String getUrl() {
			return url;
		}
		/**
		 * 设定消息链接
		 * @param url 消息链接
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		/**
		 * 获取消息图片链接
		 * @return 链接
		 */
		public String getPicurl() {
			return picurl;
		}
		/**
		 * 设定图片链接
		 * @param picurl 链接
		 */
		public void setPicurl(String picurl) {
			this.picurl = picurl;
		}

	}
}
