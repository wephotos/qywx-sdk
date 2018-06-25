package cn.microvideo.sdk.qywx.config;

import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 配置文件属性类
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="weixin")
@XmlAccessorType(XmlAccessType.FIELD)
public final class WXConfig {

	@XmlElement(name="agent-scan")
	private AgentScan agentScan = new AgentScan();
	@XmlElement(name="config")
	private Config config = new Config();
	@XmlElement(name="exclude-mapping")
	private ExcludeMapping excludeMapping = new ExcludeMapping();
	
	public AgentScan getAgentScan() {
		return agentScan;
	}

	public void setAgentScan(AgentScan agentScan) {
		this.agentScan = agentScan;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
	public ExcludeMapping getExcludeMapping() {
		return excludeMapping;
	}
	public void setExcludeMapping(ExcludeMapping excludeMapping) {
		this.excludeMapping = excludeMapping;
	}

	@Override
	public String toString() {
		return "XMLConfig [agentScan=" + agentScan + ", config=" + config
				+ ", excludeMapping=" + excludeMapping + "]";
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class AgentScan{
		
		@XmlAttribute(name="base-package")
		private String basePackage = "cn.microvideo.sdk.qywx.agent";

		public String getBasePackage() {
			return basePackage;
		}

		public void setBasePackage(String basePackage) {
			this.basePackage = basePackage;
		}

		@Override
		public String toString() {
			return "AgentScan [basePackage=" + basePackage + "]";
		}
		
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Config{
		/**
		 * 企业号ID
		 */
		private String corpid = "";
		/**
		 * 是否启用SSL
		 */
		@XmlElement(name="enable-ssl")
		private boolean enableSSL = false;
		/**
		 * 用户管理组密钥
		 */
		@XmlElement(name="contacts-secret")
		private String contactsSecret = "";
		/**
		 * 通讯录监听类
		 */
		@XmlElement(name="contacts-listener")
		private String contactsListener = "";
		/**
		 * 微信应用相关参数配置
		 */
		@XmlElement
		private Agents agents = new Agents();
		
		public String getCorpid() {
			return corpid;
		}
		public void setCorpid(String corpid) {
			this.corpid = corpid;
		}
		
		public String getContactsSecret() {
			return contactsSecret;
		}
		public void setContactsSecret(String contactsSecret) {
			this.contactsSecret = contactsSecret;
		}
		
		public String getContactsListener() {
			return contactsListener;
		}
		public void setContactsListener(String contactsListener) {
			this.contactsListener = contactsListener;
		}
		public boolean getEnableSSL() {
			return enableSSL;
		}
		public void setEnableSSL(boolean enableSSL) {
			this.enableSSL = enableSSL;
		}
		public Agents getAgents() {
			return agents;
		}
		public void setAgents(Agents agents) {
			this.agents = agents;
		}
		@Override
		public String toString() {
			return "Config [corpid=" + corpid + ", contacts_secrect="
					+ contactsSecret + ", enable_ssl=" + enableSSL
					+ ", agents=" + agents + "]";
		}

	}
	/**
	 * 应用参数配置
	 * @author Administrator
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Agents{
		/**
		 * 应用停牌
		 */
		@XmlElement(name="token")
		private String token = "";
		/**
		 * 应用密钥
		 */
		@XmlElement(name="encodingaeskey")
		private String encodingaeskey = "";
		
		@XmlElement(name="agent")
		private Agent[] agent = new Agent[1];
		
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getEncodingaeskey() {
			return encodingaeskey;
		}
		public void setEncodingaeskey(String encodingaeskey) {
			this.encodingaeskey = encodingaeskey;
		}
		
		public Agent[] getAgent() {
			return agent;
		}
		public void setAgent(Agent[] agent) {
			this.agent = agent;
		}
		@Override
		public String toString() {
			return "Agents [token=" + token + ", encodingaeskey="
					+ encodingaeskey + ", agent=" + Arrays.toString(agent)
					+ "]";
		}
	}
	
	/**
	 * 应用配置
	 * @author Administrator
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Agent{
		/**
		 * 应用ID
		 */
		private int id = 0;
		/**
		 * 应用密钥
		 */
		private String secret = "";
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		@Override
		public String toString() {
			return "Agent [id=" + id + ", secret=" + secret + "]";
		}
		
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ExcludeMapping{
		
		@XmlElement
		private String[] path = new String[1];
		
		public ExcludeMapping() {
			
		}
		public String[] getPath() {
			return path;
		}

		public void setPath(String[] path) {
			this.path = path;
		}
		
		@Override
		public String toString() {
			return "paths=>" + Arrays.toString(path);
		}
	}
	
	public static void main(String[] args) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(WXConfig.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xm头声明信息
        StringWriter writer = new StringWriter();
		marshaller.marshal(new WXConfig(), writer);
		System.err.println(writer.toString());
	}
}
