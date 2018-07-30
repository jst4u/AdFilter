package com.loit.apps.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.loit.core.hibernate.model.BaseModel;

/**
 * Model class for sql日志
 */
@Entity
@Table(name = "SYS_SQL_LOG")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysSqlLogModel extends BaseModel {

	//主键
	private String sqlLogId;
	//用户
	private String userId;
	//sql语句
	private String sqlStatement;
	//访问时间
	private Date logTime;
	//用时（毫秒）
	private Integer timeUsed;
	//服务访问标识
	private String serviceAccessIndex;

	/**
	 * Get 主键
	 */
	@Column(name = "SQL_LOG_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getSqlLogId() {
		return sqlLogId;
	}

	/**
	 * Set 主键
	 */
	public void setSqlLogId(String sqlLogId) {
		this.sqlLogId = sqlLogId;
		addValidField("sqlLogId");
	}

	/**
	 * Get 用户
	 */
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	/**
	 * Set 用户
	 */
	public void setUserId(String userId) {
		this.userId = userId;
		addValidField("userId");
	}

	/**
	 * Get sql语句
	 */
	@Column(name = "SQL_STATEMENT")
	public String getSqlStatement() {
		return sqlStatement;
	}

	/**
	 * Set sql语句
	 */
	public void setSqlStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
		addValidField("sqlStatement");
	}

	/**
	 * Get 访问时间
	 */
	@Column(name = "LOG_TIME")
	public Date getLogTime() {
		return logTime;
	}

	/**
	 * Set 访问时间
	 */
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
		addValidField("logTime");
	}

	/**
	 * Get 用时（毫秒）
	 */
	@Column(name = "TIME_USED")
	public Integer getTimeUsed() {
		return timeUsed;
	}

	/**
	 * Set 用时（毫秒）
	 */
	public void setTimeUsed(Integer timeUsed) {
		this.timeUsed = timeUsed;
		addValidField("timeUsed");
	}

	/**
	 * Get 服务访问标识
	 */
	@Column(name = "SERVICE_ACCESS_INDEX")
	public String getServiceAccessIndex() {
		return serviceAccessIndex;
	}

	/**
	 * Set 服务访问标识
	 */
	public void setServiceAccessIndex(String serviceAccessIndex) {
		this.serviceAccessIndex = serviceAccessIndex;
		addValidField("serviceAccessIndex");
	}

}
