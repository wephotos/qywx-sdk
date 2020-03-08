package com.github.wephotos.sdk.qywx.contacts;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.wephotos.sdk.qywx.WeChatRES;
import com.github.wephotos.sdk.qywx.config.DomainUtils;
import com.github.wephotos.sdk.qywx.exception.ErrcodeException;
import com.github.wephotos.sdk.qywx.utils.JSONUtils;
import com.github.wephotos.sdk.qywx.utils.WXUtils;

/**
 * 标签管理
 * 注意，标签总数不能超过3000个。
 * 创建的标签属于该应用，只有该应用才可以增删成员。
 * @author TQ
 *
 */
public final class TagMgmt {
	/**
	 * 创建标签链接
	 */
	public static final String CREATE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/tag/create";
	/**
	 * 更新标签
	 */
	public static final String UPDATE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/tag/update";
	/**
	 * 删除标签
	 */
	public static final String DELETE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/tag/delete";
	/**
	 * 获取标签成员
	 */
	public static final String GET_URL = DomainUtils.getQywxDomain()+"/cgi-bin/tag/get";
	/**
	 * 增加标签成员
	 */
	public static final String ADDTAGUSERS_URL = DomainUtils.getQywxDomain()+"/cgi-bin/tag/addtagusers";
	/**
	 * 删除标签成员
	 */
	public static final String DELTAGUSERS_URL = DomainUtils.getQywxDomain()+"/cgi-bin/tag/deltagusers";
	
	/**
	 * 获取标签列表
	 */
	public static final String LIST_URL = DomainUtils.getQywxDomain()+"/cgi-bin/tag/list";
	/**
	 * 创建标签
	 * @param tag 标签对象
	 * @param secrect 应用密钥
	 * @return
	 * @throws ErrcodeException
	 */
	public static Tag create(Tag tag, String secret) throws ErrcodeException{
		String body = JSONUtils.object2JSON(tag);
		return WXUtils.APIPost(CREATE_URL, body, Tag.class, secret);
	}
	
	/**
	 * 更新标签
	 * @param tag 标签对象
	 * @param secrect 应用密钥
	 * @return
	 * @throws ErrcodeException
	 */
	public static WeChatRES update(Tag tag, String secret) throws ErrcodeException{
		String body = JSONUtils.object2JSON(tag);
		return WXUtils.APIPost(CREATE_URL, body, WeChatRES.class, secret);
	}
	
	/**
	 * 删除标签
	 * @param tagid 标签ID
	 * @param secret 应用密钥
	 * @return
	 * @throws ErrcodeException
	 */
	public static WeChatRES delete(long tagid, String secret) throws ErrcodeException{
		return WXUtils.APIGet(DELETE_URL+"?tagid="+tagid, WeChatRES.class, secret);
	}
	
	/**
	 * 获取标签成员
	 * @param tagid 标签ID
	 * @param secret 应用密钥
	 * @return
	 * @throws ErrcodeException
	 */
	public static TagGetRES get(long tagid, String secret) throws ErrcodeException {
		return WXUtils.APIGet(GET_URL+"?tagid="+tagid, TagGetRES.class, secret);
	}
	
	/**
	 * 注意：userlist、partylist不能同时为空
	 * @param tagid 标签ID
	 * @param userlist 单次请求长度不超过1000
	 * @param partylist 单次请求长度不超过100
	 * @param secret 应用密钥
	 * @return
	 * @throws ErrcodeException
	 */
	public static TagAddDelTagUsersRES addTagUsers(long tagid, List<String> userlist, List<Long> partylist, String secret) throws ErrcodeException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tagid", tagid);
		params.put("userlist", userlist);
		params.put("partylist", partylist);
		String body = JSONUtils.object2JSON(params);
		return WXUtils.APIPost(ADDTAGUSERS_URL, body, TagAddDelTagUsersRES.class, secret);
	}
	
	/**
	 * 注意：userlist、partylist不能同时为空
	 * @param tagid 标签ID
	 * @param userlist 企业成员ID列表
	 * @param partylist 企业部门ID列表
	 * @param secret 应用密钥
	 * @return
	 * @throws ErrcodeException
	 */
	public static TagAddDelTagUsersRES delTagUsers(long tagid, List<String> userlist, List<Long> partylist, String secret) throws ErrcodeException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tagid", tagid);
		params.put("userlist", userlist);
		params.put("partylist", partylist);
		String body = JSONUtils.object2JSON(params);
		return WXUtils.APIPost(DELTAGUSERS_URL, body, TagAddDelTagUsersRES.class, secret);
	}
	
	/**
	 * 获取标签列表
	 * @param secret 应用密钥
	 * @return
	 * @throws ErrcodeException
	 */
	public static TagListRES list(String secret) throws ErrcodeException {
		return WXUtils.APIGet(LIST_URL, TagListRES.class, secret);
	}
	
	/**
	 * 标签列表返回数据
	 * @author TQ
	 *
	 */
	public static class TagListRES extends WeChatRES {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 标签列表
		 */
		private List<Tag> taglist = Collections.emptyList();
		
		public void setTaglist(List<Tag> taglist) {
			this.taglist = taglist;
		}
		
		public List<Tag> getTaglist() {
			return taglist;
		}
	}
	
	/**
	 * 添加标签成员返回数据
	 * @author TQ
	 *
	 */
	public static class TagAddDelTagUsersRES extends WeChatRES {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * 非法的成员帐号列表 "usr1|usr2|usr"
		 */
		private String invalidlist;
		/**
		 * 非法的部门id列表
		 */
		private List<Long> invalidparty = Collections.emptyList();
		
		public String getInvalidlist() {
			return invalidlist;
		}
		public void setInvalidlist(String invalidlist) {
			this.invalidlist = invalidlist;
		}
		public List<Long> getInvalidparty() {
			return invalidparty;
		}
		public void setInvalidparty(List<Long> invalidparty) {
			this.invalidparty = invalidparty;
		}
		
	}
	
	/**
	 * 获取标签成员返回数据
	 * @author Administrator
	 *
	 */
	public static class TagGetRES extends WeChatRES {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 标签名
		 */
		private String tagname;
		/**
		 * 标签中包含的成员列表
		 */
		private List<User> userlist = Collections.emptyList();
		/**
		 * 标签中包含的部门id列表
		 */
		private List<Long> partylist = Collections.emptyList();
		
		public String getTagname() {
			return tagname;
		}
		public void setTagname(String tagname) {
			this.tagname = tagname;
		}
		public List<User> getUserlist() {
			return userlist;
		}
		public void setUserlist(List<User> userlist) {
			this.userlist = userlist;
		}
		public List<Long> getPartylist() {
			return partylist;
		}
		public void setPartylist(List<Long> partylist) {
			this.partylist = partylist;
		}
		
	}
}
