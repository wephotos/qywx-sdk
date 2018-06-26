package cn.interesting.sdk.qywx.msg.send;

import java.util.Objects;

/**
 * 文本卡片消息
 * @author Administrator
 *
 */
public class TextCardMessage extends SMessage {
	
	/**
	 * 文本卡片消息内容
	 */
	private TextCard textcard = new TextCard();

	@Override
	public String getMsgtype() {
		return "textcard";
	}

	public TextCard getTextcard() {
		return textcard;
	}

	public void setTextcard(TextCard textcard) {
		this.textcard = Objects.requireNonNull(textcard);
	}
	/**
	 * 直接设置文本卡片内容
	 * @param title
	 * @param desc
	 * @param url
	 */
	public void setTextCard2(String title, String desc, String url){
		this.textcard.setUrl(url);
		this.textcard.setTitle(title);
		this.textcard.setDescription(desc);
	}
	/**
	 * 文本卡片消息内容描述
	 * @author Administrator
	 *
	 */
	public static class TextCard {
		/**
		 * 标题，不超过128个字节，超过会自动截断
		 */
		private String title;
		/**
		 * 描述，不超过512个字节，超过会自动截断
		 */
		private String description;
		/**
		 * 点击后跳转的链接。
		 */
		private String url;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		@Override
		public String toString() {
			return "TextCard [title=" + title + ", description=" + description
					+ ", url=" + url + "]";
		}
		
	}
}
