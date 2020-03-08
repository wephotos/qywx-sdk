package com.github.wephotos.sdk.qywx.contacts;

import java.util.List;

/**
 * 通讯录同步接口
 * @author Administrator
 *
 */
public interface Synchronize {
	/**
	 * 获取根部门
	 * @return
	 */
	Department getRootDept();
	/**
	 * 获取子部门
	 * @param deptId
	 * @return
	 */
	List<Department> getDepts(long deptId);
	/**
	 * 
	 * @param deptId
	 * @return
	 */
	List<User> getUsers(long deptId);
}
