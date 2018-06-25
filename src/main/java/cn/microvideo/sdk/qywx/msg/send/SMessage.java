package cn.microvideo.sdk.qywx.msg.send;

/**
 * 要发送的消息
 * @author Aaron.tian
 *
 */
public abstract class SMessage {

	/**
	 * 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。
	 * 特殊情况：指定为@all，则向关注该企业应用的全部成员发送 
	 */
	private String touser;
	
	/**
	 * 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数 
	 */
	private String toparty;
	
	/**
	 * 标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数 
	 */
	private String totag;
	
	/**
	 * 企业应用的id，整型。可在应用的设置页面查看 
	 */
	private int agentid;
	
	/**
	 * 表示是否是保密消息，0表示否，1表示是，默认0 
	 */
	private String safe = "0";
	/**
	 * 获取消息类型
	 * @return 消息类型
	 */
	public abstract String getMsgtype();
	/**
	 * 设置消息类型，解决JSON序列化的冲突，无实际意义
	 * @param msgtype 消息类型 
	 */
	public void setMsgtype(String msgtype){
		
	}
	/**
	 * 获取成员ID列表。
	 * 特殊情况：指定为@all，则向关注该企业应用的全部成员发送 
	 * @return 成员ID列表
	 */
	public String getTouser() {
		return touser;
	}
	/**
	 * 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。
	 * 特殊情况：指定为@all，则向关注该企业应用的全部成员发送 
	 * @param touser 成员ID列表
	 */
	public void setTouser(String touser) {
		this.touser = touser;
	}
	/**
	 * 获取部门ID列表，多个接收者用‘|’分隔
	 * @return 部门ID列表
	 */
	public String getToparty() {
		return toparty;
	}
	/**
	 * 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数 
	 * @param toparty 部门ID列表
	 */
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	
	/**
	 * 获取标签ID列表
	 * @return 标签ID列表
	 */
	public String getTotag() {
		return totag;
	}
	/**
	 * 标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数 
	 * @param totag 标签ID列表
	 */
	public void setTotag(String totag) {
		this.totag = totag;
	}
	/**
	 * 获取消息所属应用
	 * @return 应用ID
	 */
	public int getAgentid() {
		return agentid;
	}
	
	/**
	 * 设定消息所属应用
	 * @param agentid 应用ID
	 */
	public void setAgentid(int agentid) {
		this.agentid = agentid;
	}
	
	/**
	 * 是否是保密消息
	 * @return 0表示否，1表示是
	 */
	public String getSafe() {
		return safe;
	}
	/**
	 * 表示是否是保密消息，0表示否，1表示是，默认0 
	 * @param safe 0表示否，1表示是，默认0 
	 */
	public void setSafe(String safe) {
		this.safe = safe;
	}
	
}
