package cn.interesting.sdk.qywx.contacts;

import java.util.List;

import cn.interesting.sdk.qywx.WeChatRES;
import cn.interesting.sdk.qywx.config.DomainUtils;
import cn.interesting.sdk.qywx.config.WXConfigUtils;
import cn.interesting.sdk.qywx.exception.ErrcodeException;
import cn.interesting.sdk.qywx.utils.JSONUtils;
import cn.interesting.sdk.qywx.utils.WXUtils;

/**
 * 部门管理
 * @author Administrator
 *
 */
public final class DeptMgmt {
	
	/**
	 * 创建部门
	 */
	public static final String CREATE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/department/create";
	/**
	 * 更新部门
	 */
	public static final String UPDATE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/department/update";
	/**
	 * 删除部门
	 */
	public static final String DELETE_URL = DomainUtils.getQywxDomain()+"/cgi-bin/department/delete";
	/**
	 * 获取部门列表
	 */
	public static final String LIST_URL = DomainUtils.getQywxDomain()+"/cgi-bin/department/list";
	/**
	 * 通讯录管理密钥
	 */
	public final static String CONTACTS_SECRECT = WXConfigUtils.getContactsSecrect();
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws ErrcodeException 
	 */
	public static WeChatRES delete(int id) throws ErrcodeException {
		return WXUtils.APIGet(DELETE_URL+"?id=" + id, WeChatRES.class, CONTACTS_SECRECT);
	}
	/**
	 * 更新
	 * @param depart
	 * @return
	 * @throws ErrcodeException 
	 */
	public static WeChatRES update(Department depart) throws ErrcodeException{
		String body = JSONUtils.object2JSON(depart);
		return WXUtils.APIPost(UPDATE_URL, body, WeChatRES.class, CONTACTS_SECRECT);
	}
	/**
	 * 创建
	 * @param depart
	 * @return
	 * @throws ErrcodeException 
	 */
	public static DeptCreateRES create(Department depart) throws ErrcodeException{
		String body = JSONUtils.object2JSON(depart);
		return WXUtils.APIPost(CREATE_URL, body, DeptCreateRES.class, CONTACTS_SECRECT);
	}
	
	/**
	 * 获取部门列表
	 * @param deptId 部门id。获取指定部门及其下的子部门。 如果不填，默认获取全量组织架构
	 * @return
	 * @throws ErrcodeException
	 */
	public static DeptListRES list(long deptId) throws ErrcodeException{
		return WXUtils.APIGet(LIST_URL+"?id="+deptId, DeptListRES.class, CONTACTS_SECRECT);
	}
	/**
	 * 获取部门列表的响应结果
	 * @author Administrator
	 *
	 */
	public static class DeptListRES extends WeChatRES {
		private List<Department> department;

		public List<Department> getDepartment() {
			return department;
		}

		public void setDepartment(List<Department> department) {
			this.department = department;
		}
	}
	/**
	 * 创建部门的返回结果
	 * @author Administrator
	 *
	 */
	public static class DeptCreateRES extends WeChatRES {
		private long id;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
	}
}
