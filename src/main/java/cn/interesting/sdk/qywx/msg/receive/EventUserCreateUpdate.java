package cn.interesting.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 新增或更新成员事件
 * @author Administrator
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventUserCreateUpdate extends EventContactMessage {
	/**
	 * 成员UserID 
	 */
	private String UserID;
	/**
	 * 成员名称
	 */
	private String Name;
	/**
	 * 成员部门列表，逗号分隔
	 */
	private String Department;
	/**
	 * 职位信息。长度为0~64个字节
	 */
	private String Position;
	/**
	 * 手机号码
	 */
	private String Mobile;
	/**
	 * 性别，1表示男性，2表示女性
	 */
	private int Gender;
	/**
	 * 邮箱
	 */
	private String Email;
	/**
	 * 激活状态：1=已激活 2=已禁用
	 */
	private int Status;
	/**
	 * 头像链接
	 */
	private String Avatar;
	/**
	 * 英文名
	 */
	private String EnglishName;
	/**
	 * 上级字段，标识是否为上级。0表示普通成员，1表示上级 
	 */
	private int IsLeader;
	/**
	 * 座机
	 */
	private String Telephone;
	/**
	 * 扩展属性
	 */
	@XmlElement(name="ExtAttr")
	private ExtAttr extAttr;
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public int getGender() {
		return Gender;
	}
	public void setGender(int gender) {
		Gender = gender;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getAvatar() {
		return Avatar;
	}
	public void setAvatar(String avatar) {
		Avatar = avatar;
	}
	public String getEnglishName() {
		return EnglishName;
	}
	public void setEnglishName(String englishName) {
		EnglishName = englishName;
	}
	public int getIsLeader() {
		return IsLeader;
	}
	public void setIsLeader(int isLeader) {
		IsLeader = isLeader;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public ExtAttr getExtAttr() {
		return extAttr;
	}
	public void setExtAttr(ExtAttr extAttr) {
		this.extAttr = extAttr;
	}
	/**
	 * 扩展属性
	 * @author Administrator
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ExtAttr {
		/**
		 * 扩展项
		 */
		@XmlElement
		private ExtAttrItem[] Item;

		public ExtAttrItem[] getItem() {
			return Item;
		}

		public void setItem(ExtAttrItem[] item) {
			Item = item;
		}
	}
	/**
	 * 扩展属性
	 * @author Administrator
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ExtAttrItem {
		/**
		 * 扩展属性名
		 */
		private String Name = "";
		/**
		 * 扩展属性值
		 */
		private String Value = "";
		
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getValue() {
			return Value;
		}
		public void setValue(String value) {
			Value = value;
		}
		@Override
		public String toString() {
			return "ExtAttr [Name=" + Name + ", Value=" + Value + "]";
		}
		
	}

}
