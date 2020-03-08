package com.github.wephotos.sdk.qywx.msg.receive;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 发送的图片信息 
 * @author Aaron.tian
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventSendPicsInfo {
	/**
	 * 图片数量
	 */
	private int Count;
	/**
	 * 图片信息
	 */
	private PicList PicList = new PicList();
	/**
	 * 获取图片数量
	 * @return 图片数量
	 */
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	/**
	 * 图片列表 
	 * @return 图片列表 
	 */
	public PicList getPicList() {
		return PicList;
	}
	public void setPicList(PicList picList) {
		PicList = picList;
	}
	/**
	 * 图片列表 
	 * @author Administrator
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	static class PicList{
		/**
		 * 图片列表项集合
		 */
		private List<PicMd5Sum> item = new ArrayList<PicMd5Sum>();
		/**
		 * 图片列表项集合
		 * @return 列表项集合
		 */
		public List<PicMd5Sum> getItem() {
			return item;
		}

		public void setItem(List<PicMd5Sum> item) {
			this.item = item;
		}
		/**
		 * 图片的MD5值，开发者若需要，可用于验证接收到图片 
		 * @author Administrator
		 *
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		static class PicMd5Sum{
			private String PicMd5Sum;
			/**
			 * 图片的MD5值，开发者若需要，可用于验证接收到图片 
			 * @return 图片的MD5值
			 */
			public String getPicMd5Sum() {
				return PicMd5Sum;
			}

			public void setPicMd5Sum(String picMd5Sum) {
				PicMd5Sum = picMd5Sum;
			}
			
		}
	}
}
