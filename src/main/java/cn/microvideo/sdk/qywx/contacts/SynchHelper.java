package cn.microvideo.sdk.qywx.contacts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.microvideo.sdk.qywx.exception.ErrcodeException;
import cn.microvideo.sdk.qywx.exception.WXUnCheckedException;

/**
 * 通讯录同步助手，将人员同步至企业微信通讯录
 * @author Administrator
 *
 */
public final class SynchHelper {
	/**
	 * 同步接口
	 */
	private volatile static Synchronize synchronize;
	/**
	 * 是否调试,调试状态不会同步到微信服务器
	 */
	private volatile static boolean DEBUG = true;
	/**
	 * 日志
	 */
	private final static Logger logger = Logger.getLogger(SynchHelper.class);

	public static void setSynchronize(Synchronize synchronize) {
		SynchHelper.synchronize = synchronize;
	}
	/**
	 * 是否调试状态
	 * @param debug
	 */
	public static void setDebug(boolean debug) {
		DEBUG = debug;
	}

	/**
	 * 递归同步子部门
	 * @param children 子部门
	 * @param errList
	 */
	private static void depts(List<Department> children, List<String> errList) {
		for(Department dept:children){
			try {
				if(DEBUG){
					logger.debug("dept=>" + dept);
				}else{
					DeptMgmt.create(dept);
				}
			} catch (ErrcodeException e) {
				if(60008 == e.getErrcode()){
					try {
						DeptMgmt.update(dept);
					} catch (ErrcodeException e1) {
						errList.add("部门:" + dept.getName()+",错误:"+e.getMessage());
					}
				}else{
					errList.add("部门:" + dept.getName()+",错误:"+e.getMessage());
				}
			}
			users(dept.getId(), errList);
			children = synchronize.getDepts(dept.getId());
			if(children.size() > 0){
				depts(children, errList);
			}
		}
	}
	
	/**
	 * 同步部门人员
	 * @param deptid
	 * @param errList
	 */
	private static void users(long deptid, List<String> errList) {
		List<User> userList = synchronize.getUsers(deptid);
		for(User user:userList){
			try {
				if(DEBUG){
					logger.debug("user=>" + user);
				}else{
					UserMgmt.create(user);
				}
			} catch (ErrcodeException e) {
				if(e.getErrcode() == 60102){
					try {
						UserMgmt.update(user);
					} catch (ErrcodeException e1) {
						errList.add("人员:" + user.getName()+",错误:"+e.getMessage());
					}
				}else{
					errList.add("人员:" + user.getName()+",错误:"+e.getMessage());
				}
			}
			
		}
	}
	
	/**
	 * 开始同步
	 */
	public static List<String> synch() {
		if(synchronize == null){
			throw new IllegalStateException("同步接口未设置.synchronize=null");
		}
		Department root = synchronize.getRootDept();
		long rootId = root.getId();
		root.setId(1L);
		root.setParentid(1L);
		try {
			if(DEBUG){
				logger.debug("root=>" + root);
			}else{
				DeptMgmt.create(root);
			}
		} catch (ErrcodeException e) {
			if(60008 == e.getErrcode()){
				try {
					DeptMgmt.update(root);
				} catch (ErrcodeException e1) {
					throw new WXUnCheckedException(e.getMessage());
				}
			}
		}
		List<String> errList = new ArrayList<String>();
		depts(synchronize.getDepts(rootId), errList);
		return errList;
	}
}
