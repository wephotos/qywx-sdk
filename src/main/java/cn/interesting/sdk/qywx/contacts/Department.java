package cn.interesting.sdk.qywx.contacts;

import java.io.Serializable;

import cn.interesting.sdk.qywx.WeChatRES;

/**
 * 部门管理Bean
 * @author Aaron.tian
 *
 */
public class Department extends WeChatRES implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 部门id，整型。指定时必须大于1，否则自动生成
	 */
	private long id;
	
	/**
	 * 父亲部门id。根部门id为1 
	 */
	private long parentid;
	
	/**
	 * 部门名称。长度限制为1~64个字节，字符不能包括\:?”<>｜ 
	 */
	private String name;
	
	/**
	 * 在父部门中的次序值。order值大的排序靠前。有效的值范围是[0, 2^32)
	 */
	private long order;
	
	/**
	 * 获取部门ID
	 * @return 部门ID
	 */
	public long getId() {
		return id;
	}
	/**
	 * 设定部门ID
	 * @param id 部门ID
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * 父部门ID
	 * @return 父部门ID
	 */
	public long getParentid() {
		return parentid;
	}
	/**
	 * 设定父部门ID
	 * @param parentid 父部门ID
	 */
	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	/**
	 * 获取部门名称
	 * @return 名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 部门名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取部门排序号
	 * @return 排序号
	 */
	public long getOrder() {
		return order;
	}
	/**
	 * 设定排序号
	 * @param order 排序号
	 */
	public void setOrder(long order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", parentid=" + parentid + ", name="
				+ name + ", order=" + order + "]";
	}

}
