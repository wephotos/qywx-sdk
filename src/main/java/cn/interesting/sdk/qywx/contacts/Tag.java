package cn.interesting.sdk.qywx.contacts;

import cn.interesting.sdk.qywx.WeChatRES;

/**
 * 标签
 * @author TQ
 *
 */
public class Tag extends WeChatRES {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 标签id，非负整型，指定此参数时新增的标签会生成对应的标签id，不指定时则以目前最大的id自增。
	 */
	private long tagid;
	/**
	 * 标签名称，长度限制为32个字（汉字或英文字母），标签名不可与其他标签重名。
	 */
	private String tagname;

	public long getTagid() {
		return tagid;
	}

	public void setTagid(long tagid) {
		this.tagid = tagid;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	
}
