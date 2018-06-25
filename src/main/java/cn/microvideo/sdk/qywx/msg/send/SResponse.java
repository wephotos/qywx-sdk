package cn.microvideo.sdk.qywx.msg.send;

import cn.microvideo.sdk.qywx.WeChatRES;

/**
 * 发送消息后的返回结果<br>
 * •权限要求 <br>
 *  收件人必须处于应用的可见范围内，并且管理组对应用有使用权限、对收件人有查看权限，否则本次调用失败。<br>
 * •返回结果 <br>
 *  如果无权限，则本次发送失败；如果收件人不存在或未关注，发送仍然执行。两种情况下均返回无效的部分。
 * @author Aaron.tian
 *
 */
public class SResponse extends WeChatRES {

	/**
	 * 无效用户
	 */
	private String invaliduser;
	/**
	 * 无效部门
	 */
	private String invalidparty;
	/**
	 * 无效标签
	 */
	private String invalidtag;
	/**
	 * 获取无效用户
	 * @return 无效用户
	 */
	public String getInvaliduser() {
		return invaliduser;
	}
	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}
	/**
	 * 获取无效部门
	 * @return 无效部门
	 */
	public String getInvalidparty() {
		return invalidparty;
	}
	public void setInvalidparty(String invalidparty) {
		this.invalidparty = invalidparty;
	}
	/**
	 * 获取无效标签
	 * @return 无效标签
	 */
	public String getInvalidtag() {
		return invalidtag;
	}
	public void setInvalidtag(String invalidtag) {
		this.invalidtag = invalidtag;
	}
	@Override
	public String toString() {
		return "SResponse [invaliduser=" + invaliduser
				+ ", invalidparty=" + invalidparty + ", invalidtag="
				+ invalidtag + ", getErrcode()=" + getErrcode()
				+ ", getErrmsg()=" + getErrmsg() + "]";
	}

}
