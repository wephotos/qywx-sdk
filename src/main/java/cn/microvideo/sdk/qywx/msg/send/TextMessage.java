package cn.microvideo.sdk.qywx.msg.send;


/**
 * 要发送的文本消息
 * @author Aaron.tian
 *
 */
public class TextMessage extends SMessage {
	
	/**
	 * 文本消息
	 */
	private Text text = new Text();
	
	/**
	 * 获取文本消息
	 * @return 文件消息
	 */
	public Text getText() {
		return text;
	}
	/**
	 * 设定文本消息
	 * @param text 文本消息
	 */
	public void setText(Text text) {
		if(text != null){
			this.text = text;
		}
	}
	
	/**
	 * 设定文本消息
	 * @param text 文本消息字符串
	 */
	public void setText2(String text) {
		this.text.setContent(text);
	}

	@Override
	public String getMsgtype() {
		return "text";
	}

	/**
	 * 要发送的消息内容
	 * @author Administrator
	 *
	 */
	public static class Text{
		/**
		 * 文本消息内容
		 */
		private String content;
		/**
		 * 获取文件消息内容
		 * @return 消息内容
		 */
		public String getContent() {
			return content;
		}
		/**
		 * 设定文本消息内容
		 * @param content 消息内容
		 */
		public void setContent(String content) {
			this.content = content;
		}
		
	}
}
