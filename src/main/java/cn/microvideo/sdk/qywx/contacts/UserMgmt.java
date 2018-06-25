package cn.microvideo.sdk.qywx.contacts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.microvideo.sdk.qywx.WeChatRES;
import cn.microvideo.sdk.qywx.config.DomainUtils;
import cn.microvideo.sdk.qywx.config.WXConfigUtils;
import cn.microvideo.sdk.qywx.exception.ErrcodeException;
import cn.microvideo.sdk.qywx.utils.JSONUtils;
import cn.microvideo.sdk.qywx.utils.WXUtils;

/**
 * 通讯录管理
 * @author Administrator
 *
 */
public final class UserMgmt {
	/**
	 * 创建成员
	 */
	public final static String CREATE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/user/create";
	/**
	 * 更新成员
	 */
	public final static String UPDATE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/user/update";
	/**
	 * 删除成员
	 */
	public final static String DELETE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/user/delete";
	/**
	 * 获取成员
	 */
	public final static String GET_URL = DomainUtils.getQywxDomain()+"/cgi-bin/user/get";
	/**
	 * 批量删除用户
	 */
	public final static String BATCHDELETE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/user/batchdelete";
	/**
	 * 获取部门成员
	 */
	public final static String SIMPLELIST_URL = DomainUtils.getQywxDomain()+"/cgi-bin/user/simplelist";
	/**
	 * 获取部门成员详情
	 */
	public final static String LIST_URL = DomainUtils.getQywxDomain()+"/cgi-bin/user/list";
	/**
	 * userid转换成openid接口
	 */
	public final static String CONVERT_TO_OPENID_URL = DomainUtils.getQywxDomain()+"/cgi-bin/user/convert_to_openid";
	/**
	 * openid转换成userid接口
	 */
	public final static String CONVERT_TO_USERID_URL = DomainUtils.getQywxDomain()+"/cgi-bin/user/convert_to_userid";
	/**
	 * 通讯录管理密钥
	 */
	public final static String CONTACTS_SECRECT = WXConfigUtils.getContactsSecrect();
	/**
	 * 获取用户
	 * @param id
	 * @return
	 * @throws ErrcodeException
	 */
	public static User get(String id) throws ErrcodeException{
		return WXUtils.APIGet(GET_URL +"?userid="+id, User.class, CONTACTS_SECRECT);
	}
	/**
	 * 创建用户
	 * @param user
	 * @return
	 * @throws ErrcodeException
	 */
	public static WeChatRES create(User user) throws ErrcodeException{
		String body = JSONUtils.object2JSON(user);
		return WXUtils.APIPost(CREATE_URL, body, WeChatRES.class, CONTACTS_SECRECT);
	}
	/**
	 * 更新用户
	 * @param user
	 * @return
	 * @throws ErrcodeException
	 */
	public static WeChatRES update(User user) throws ErrcodeException{
		String body = JSONUtils.object2JSON(user);
		return WXUtils.APIPost(UPDATE_URL, body, WeChatRES.class, CONTACTS_SECRECT);
	}
	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws ErrcodeException
	 */
	public static WeChatRES delete(String id) throws ErrcodeException{
		return WXUtils.APIGet(DELETE_URL+"?userid="+id, WeChatRES.class, CONTACTS_SECRECT);
	}
	
	/**
	 * 批量删除用户
	 * @param useridlist
	 * @return
	 * @throws ErrcodeException 
	 */
	public static WeChatRES batchdelete(List<String> useridlist) throws ErrcodeException{
		Map<String, List<String>> batchDelete = new HashMap<String, List<String>>();
		batchDelete.put("useridlist", useridlist);
		String body = JSONUtils.object2JSON(batchDelete);
		return WXUtils.APIPost(BATCHDELETE_URL, body, WeChatRES.class, CONTACTS_SECRECT);
	}
	
	/**
	 * 获取部门成员
	 * @param deptId 获取的部门id
	 * @param fetch_child 1/0：是否递归获取子部门下面的成员
	 * @return 
	 * @throws ErrcodeException 
	 */
	public static SimplelistRES simplelist(long deptId, int fetch_child) throws ErrcodeException{
		String url = SIMPLELIST_URL + "?department_id=" + deptId + "&fetch_child="+fetch_child;
		return WXUtils.APIGet(url, SimplelistRES.class, CONTACTS_SECRECT);
	}
	/**
	 * 获取部门成员详情
	 * @param deptId 获取的部门id
	 * @param fetch_child 1/0：是否递归获取子部门下面的成员
	 * @return 
	 * @throws ErrcodeException 
	 */
	public static SimplelistRES list(long deptId, int fetch_child) throws ErrcodeException{
		String url = LIST_URL + "?department_id=" + deptId + "&fetch_child="+fetch_child;
		return WXUtils.APIGet(url, SimplelistRES.class, CONTACTS_SECRECT);
	}
	
	/**
	 * userid转换成openid接口
	 * @param userid
	 * @param agentid 整型，需要发送红包的应用ID，若只是使用微信支付和企业转账，则无需该参数，传入0
	 * @return
	 * @throws ErrcodeException
	 */
	public static UserOpenRES convert2Openid(String userid, int agentid) throws ErrcodeException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		if(agentid > 0){
			map.put("agentid", String.valueOf(agentid));
		}
		String body = JSONUtils.object2JSON(map);
		return WXUtils.APIPost(CONVERT_TO_OPENID_URL, body, UserOpenRES.class, CONTACTS_SECRECT);
	}
	/**
	 * openid转换成userid接口
	 * @param openid
	 * @return
	 * @throws ErrcodeException
	 */
	public static UserOpenRES convert2Userid(String openid) throws ErrcodeException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("openid", openid);
		String body = JSONUtils.object2JSON(map);
		return WXUtils.APIPost(CONVERT_TO_USERID_URL, body, UserOpenRES.class, CONTACTS_SECRECT);
	}
	
	/**
	 * 获取部门成员响应结果
	 * @author Administrator
	 *
	 */
	public static class SimplelistRES extends WeChatRES{
		private List<User> userlist;

		public List<User> getUserlist() {
			return userlist;
		}

		public void setUserlist(List<User> userlist) {
			this.userlist = userlist;
		}
	}
	/**
	 * Userid与Openid互换 响应结果
	 * @author Administrator
	 *
	 */
	public static class UserOpenRES extends WeChatRES{
		/**
		 * 
		 */
		private String openid;
		private String appid;
		private String userid;
		
		public String getOpenid() {
			return openid;
		}
		public void setOpenid(String openid) {
			this.openid = openid;
		}
		public String getAppid() {
			return appid;
		}
		public void setAppid(String appid) {
			this.appid = appid;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
	}
}
