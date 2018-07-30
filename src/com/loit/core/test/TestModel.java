package com.loit.core.test;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.loit.core.hibernate.model.BaseModel;

public class TestModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "EsmAlertingAccept";

	public static final class FieldNames {
		/**
		 * ID
		 */
		public static final String alertingAcceptId = "alertingAcceptId";
		/**
		 * 监测点代码
		 */
		public static final String pointCode = "pointCode";
		/**
		 * 发送方式（手机
		 */
		public static final String sendTypeDictid = "sendTypeDictid";
		/**
		 * 信息接收方
		 */
		public static final String acceptor = "acceptor";
		/**
		 * creator
		 */
		public static final String creator = "creator";
		/**
		 * createTime
		 */
		public static final String createTime = "createTime";
		/**
		 * modifier
		 */
		public static final String modifier = "modifier";
		/**
		 * modifyTime
		 */
		public static final String modifyTime = "modifyTime";
		/**
		 * recVer
		 */
		public static final String recVer = "recVer";
		
		@Override
		public String toString() {
			return "测试";
		}
	}

	//ID
	private String alertingAcceptId;
	//监测点代码
	private String pointCode;
	//发送方式（手机
	private String sendTypeDictid;
	//信息接收方
	private String acceptor;
	//creator
	private String creator;
	//createTime
	private Date createTime;
	//modifier
	private String modifier;
	//modifyTime
	private Date modifyTime;
	//recVer
	private Long recVer;

	/**
	 * Get ID
	 */
	@Column(name = "ALERTING_ACCEPT_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	
	public String getAlertingAcceptId() {
		return alertingAcceptId;
	}

	/**
	 * Set ID
	 */
	public void setAlertingAcceptId(String alertingAcceptId) {
		this.alertingAcceptId = alertingAcceptId;
		addValidField(FieldNames.alertingAcceptId);
	}

	/**
	 * Get 监测点代码
	 */
	@Column(name = "POINT_CODE")
	public String getPointCode() {
		return pointCode;
	}

	/**
	 * Set 监测点代码
	 */
	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
		addValidField(FieldNames.pointCode);
	}

	/**
	 * Get 发送方式（手机
	 * ,网络）
	 */
	@Column(name = "SEND_TYPE_DICTID")
	public String getSendTypeDictid() {
		return sendTypeDictid;
	}

	/**
	 * Set 发送方式（手机
	 * ,网络）
	 */
	public void setSendTypeDictid(String sendTypeDictid) {
		this.sendTypeDictid = sendTypeDictid;
		addValidField(FieldNames.sendTypeDictid);
	}

	/**
	 * Get 信息接收方
	 */
	@Column(name = "ACCEPTOR")
	public String getAcceptor() {
		return acceptor;
	}

	/**
	 * Set 信息接收方
	 */
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
		addValidField(FieldNames.acceptor);
	}

	/**
	 * Get creator
	 */
	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	/**
	 * Set creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
		addValidField(FieldNames.creator);
	}

	/**
	 * Get createTime
	 */
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Set createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField(FieldNames.createTime);
	}

	/**
	 * Get modifier
	 */
	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	/**
	 * Set modifier
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
		addValidField(FieldNames.modifier);
	}

	/**
	 * Get modifyTime
	 */
	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * Set modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		addValidField(FieldNames.modifyTime);
	}

	/**
	 * Get recVer
	 */
	@Column(name = "REC_VER")
	@Version
	public Long getRecVer() {
		return recVer;
	}

	/**
	 * Set recVer
	 */
	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField(FieldNames.recVer);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestModel tm = new TestModel();
		tm.setCreateTime(new Date());
		tm.setAcceptor("test acde");
		tm.setRecVer(new Long(111111));
		System.out.println("tm:"+tm);
	}

}
