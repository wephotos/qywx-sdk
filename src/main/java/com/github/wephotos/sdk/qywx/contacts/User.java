package com.github.wephotos.sdk.qywx.contacts;

import java.util.ArrayList;
import java.util.List;

import com.github.wephotos.sdk.qywx.WeChatRES;

/**
 * 用户管理Bean
 * @author Aaron.tian
 *
 */
public class User extends WeChatRES {

	/**
	 * 序列版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 男
	 */
	public final static String MAN = "1";
	/**
	 * 女
	 */
	public final static String LADY = "2";
	/**
	 * 启用
	 */
	public final static int ENABLE = 1;
	/**
	 * 禁用
	 */
	public final static int DISABLE = 0;
	/**
	 * 是领导
	 */
	public final static int LEADER = 1;
	/**
	 * 不是领导
	 */
	public final static int UNLEADER = 0;
	/**
	 * 成员UserID。对应管理端的帐号，企业内必须唯一。长度为1~64个字节
	 */
	private String userid;
	/**
	 * 成员名称。长度为1~64个字节 
	 */
	private String name;
	/**
	 * 英文名。长度为1-64个字节。
	 */
	private String english_name;
	/**
	 * 手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空 
	 */
	private String mobile;
	/**
	 * 成员所属部门id列表。注意，每个部门的直属成员上限为1000个 
	 */
	private List<Long> department = new ArrayList<Long>();
	/**
	 * 部门内的排序值，默认为0。数量必须和department一致，数值越大排序越前面。有效的值范围是[0, 2^32)
	 */
	private List<Long> order = new ArrayList<Long>();
	/**
	 * 职位信息。长度为0~64个字节 
	 */
	private String position;
	
	/**
	 * 性别。1表示男性，2表示女性 
	 */
	private String gender = MAN;
	/**
	 * 邮箱。长度为0~64个字节。企业内必须唯一 
	 */
	private String email;
	/**
	 * 座机。长度0-64个字节。
	 */
	private String telephone;
	/**
	 * 上级字段，标识是否为上级。
	 */
	private int isleader;
	/**
	 * 成员头像的mediaid，通过多媒体接口上传图片获得的mediaid
	 */
	private String avatar_mediaid;
	/**
	 * 启用/禁用成员。1表示启用成员，0表示禁用成员
	 */
	private int enable = ENABLE;
	/**
	 * 头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可 
	 */
	private String avatar;
	
	/**
	 * 关注状态: 1=已关注，2=已冻结，4=未关注 
	 */
	private String status = "4";
	/**
	 * 扩展属性。扩展属性需要在WEB管理端创建后才生效，否则忽略未知属性的赋值 
	 */
	private UserExtattr<String, String> extattr = new UserExtattr<String, String>();

	public User() {
		
	}
	/**
	 * 使用人员ID构造对象
	 * @param userid 人员id
	 */
	public User(String userid){
		this.userid = userid;
	}
	/**
	 * 获取成员ID
	 * @return 成员ID
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * 设置成员ID
	 * @param userid 成员ID
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * 获取成员姓名
	 * @return 成员姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置成员姓名
	 * @param name 成员姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取成员职位信息
	 * @return 职位信息
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设定成员职位信息
	 * @param position 职位信息
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取手机号
	 * @return 手机号码
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设定手机号码
	 * @param mobile 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取性别；1男，2女
	 * @return 性别
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设定性别；1男，2女
	 * @param gender 性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取邮箱
	 * @return 邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设定邮箱
	 * @param email 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 用户头像链接
	 * @return 头像链接
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * 设定用户头像链接
	 * @param avatar 链接
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取用户用户状态
	 * @return 用户状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设定用户状态
	 * @param status 用户状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取部门列表
	 * @return 部门列表
	 */
	public List<Long> getDepartment() {
		return department;
	}
	/**
	 * 添加部门ID
	 * @param deptId 部门ID
	 * @param order 人员在部门内的排序
	 */
	public void addDepartOrder(Long deptId, Long order) {
		this.order.add(order);
		this.department.add(deptId);
	}
	public List<Long> getOrder() {
		return order;
	}
	public String getEnglish_name() {
		return english_name;
	}
	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getIsleader() {
		return isleader;
	}
	public void setIsleader(int isleader) {
		this.isleader = isleader;
	}
	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}
	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	/**
	 * 获取用户扩展属性
	 * @return 扩展属性
	 */
	public UserExtattr<String, String> getExtattr() {
		return extattr;
	}
	/**
	 * 设置用户扩展属性列表
	 * @param extatr 属性列表
	 */
	public void setExtattr(UserExtattr<String, String> extatr) {
		this.extattr = extatr;
	}
	/**
	 * 设置一个扩展属性
	 * @param k 键
	 * @param v 值
	 */
	public void putAttr(String k, String v) {
		extattr.put(k, v);
	}
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", english_name="
				+ english_name + ", mobile=" + mobile + ", department="
				+ department + ", order=" + order + ", position=" + position
				+ ", gender=" + gender + ", email=" + email + ", telephone="
				+ telephone + ", isleader=" + isleader + ", avatar_mediaid="
				+ avatar_mediaid + ", enable=" + enable + ", avatar=" + avatar
				+ ", status=" + status + ", extattr=" + extattr + "]";
	}

}
