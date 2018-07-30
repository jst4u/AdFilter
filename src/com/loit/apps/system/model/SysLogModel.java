package com.loit.apps.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.loit.core.hibernate.model.BaseModel;
import com.loit.core.hibernate.model.OperationLog;

/**
 * Model class for 系统日志表
 */
@Entity
@Table(name = "SYS_LOG")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysLogModel extends BaseModel implements OperationLog {

	//日志ID
	private String logId;
	//操作用户ID
	private String operUserId;
	//记录日期
	private Date logDate;
	//操作对象
	private String operOject;
	//操作动作
	private String operAction;
	//变更记录数
	private Integer modiRecords;
	//日志描述
	private String logDesc;
	//结果
	private Integer result;
	//状态
	private String state;
	//备注
	private String remarks;
	//creator
	private String creator;
	//createTime
	private Date createTime;
	//modifier
	private String modifier;
	//modifyTime
	private Date modifyTime;
	//recVer
	private Integer recVer;

	/**
	 * Get 日志ID
	 */
	@Column(name = "LOG_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getLogId() {
		return logId;
	}

	/**
	 * Set 日志ID
	 */
	public void setLogId(String logId) {
		this.logId = logId;
		addValidField("logId");
	}

	/**
	 * Get 操作用户ID
	 */
	@Column(name = "OPER_USER_ID")
	public String getOperUserId() {
		return operUserId;
	}

	/**
	 * Set 操作用户ID
	 */
	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
		addValidField("operUserId");
	}

	/**
	 * Get 记录日期
	 */
	@Column(name = "LOG_DATE")
	public Date getLogDate() {
		return logDate;
	}

	/**
	 * Set 记录日期
	 */
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
		addValidField("logDate");
	}

	/**
	 * Get 操作对象
	 */
	@Column(name = "OPER_OJECT")
	public String getOperOject() {
		return operOject;
	}

	/**
	 * Set 操作对象
	 */
	public void setOperOject(String operOject) {
		this.operOject = operOject;
		addValidField("operOject");
	}

	/**
	 * Get 操作动作
	 */
	@Column(name = "OPER_ACTION")
	public String getOperAction() {
		return operAction;
	}

	/**
	 * Set 操作动作
	 */
	public void setOperAction(String operAction) {
		this.operAction = operAction;
		addValidField("operAction");
	}

	/**
	 * Get 变更记录数
	 */
	@Column(name = "MODI_RECORDS")
	public Integer getModiRecords() {
		return modiRecords;
	}

	/**
	 * Set 变更记录数
	 */
	public void setModiRecords(Integer modiRecords) {
		this.modiRecords = modiRecords;
		addValidField("modiRecords");
	}

	/**
	 * Get 日志描述
	 */
	@Column(name = "LOG_DESC")
	public String getLogDesc() {
		return logDesc;
	}

	/**
	 * Set 日志描述
	 */
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
		addValidField("logDesc");
	}

	/**
	 * Get 结果
	 */
	@Column(name = "RESULT")
	public Integer getResult() {
		return result;
	}

	/**
	 * Set 结果
	 */
	public void setResult(Integer result) {
		this.result = result;
		addValidField("result");
	}

	/**
	 * Get 状态
	 */
	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	/**
	 * Set 状态
	 */
	public void setState(String state) {
		this.state = state;
		addValidField("state");
	}

	/**
	 * Get 备注
	 */
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Set 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
		addValidField("remarks");
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
		addValidField("creator");
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
		addValidField("createTime");
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
		addValidField("modifier");
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
		addValidField("modifyTime");
	}

	/**
	 * Get recVer
	 */
	@Column(name = "REC_VER")
	@Version
	public Integer getRecVer() {
		return recVer;
	}

	/**
	 * Set recVer
	 */
	public void setRecVer(Integer recVer) {
		this.recVer = recVer;
		addValidField("recVer");
	}

}
