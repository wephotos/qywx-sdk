package com.github.wephotos.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.github.wephotos.sdk.qywx.WeChatRES;

/**
 * 本事件是成员在使用异步任务接口时，用于接收任务执行完毕的结果通知
 * @author Aaron.tian
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventBatchJobResult extends EventMessage{
	/**
	 * 用于接收任务执行完毕的结果通知
	 */
	private BatchJob BatchJob;
	
	public EventBatchJobResult() {
		this.setEvent(BATCH_JOB_RESULT);
	}
	/**
	 * 获取异步任务消息
	 * @return 异步任务消息
	 */
	public BatchJob getBatchJob() {
		return BatchJob;
	}
	/**
	 * 设置异步任务消息，不需要手工调用
	 * @param batchJob
	 */
	public void setBatchJob(BatchJob batchJob) {
		BatchJob = batchJob;
	}

	@Override
	public String toString() {
		return "EventEnterAgent [BatchJob=" + BatchJob + ", toString()="
				+ super.toString() + "]";
	}

	/**
	 * 异步任务完成事件推送消息体
	 * @author Aaron.tian
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	static class BatchJob extends WeChatRES{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 异步任务id，最大长度为64字符
		 */
		private String JobId;
		/**
		 * 操作类型，字符串，目前分别有： 
		 * 1. sync_user(增量更新成员) 
		 * 2. replace_user(全量覆盖成员) 
		 * 3. invite_user(邀请成员关注) 
		 * 4. replace_party(全量覆盖部门)
		 */
		private String JobType;
		/**
		 * 获取异步任务ID
		 * @return 任务ID
		 */
		public String getJobId() {
			return JobId;
		}
		/**
		 * 设置异步任务ID
		 * @param jobId 任务ID
		 */
		public void setJobId(String jobId) {
			JobId = jobId;
		}
		/**
		 * 获取
		 * 操作类型，字符串，目前分别有： 
		 * 1. sync_user(增量更新成员) 
		 * 2. replace_user(全量覆盖成员) 
		 * 3. invite_user(邀请成员关注) 
		 * 4. replace_party(全量覆盖部门)
		 * @return 任务类型
		 */
		public String getJobType() {
			return JobType;
		}
		/**
		 * 设置任务调用，接收返回消息用，不需要调用
		 * @param jobType 任务类型
		 */
		public void setJobType(String jobType) {
			JobType = jobType;
		}
		@Override
		public String toString() {
			return "BatchJob [JobId=" + JobId + ", JobType=" + JobType
					+ ", toString()=" + super.toString() + "]";
		}
		
	}

}
