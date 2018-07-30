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
 * Model class for 用户登录日志
 */
@Entity
@Table(name = "SYS_USER_LOGIN_LOG")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysUserLoginLogModel extends BaseModel{

	//员工登录日志内部部门
	private String userLoginLogId;
	//用户
	private String userId;
	//登录时间
	private Date loginTime;
	//退出时间
	private Date logoutTime;
	//登录成功标志
	private Integer tryTimes;
	//登录IP
	private String userIp;
	//登录主机名
	private String hostName;

	/**
	 * Get 员工登录日志内部部门
	 */
	@Column(name = "USER_LOGIN_LOG_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getUserLoginLogId() {
		return userLoginLogId;
	}

	/**
	 * Set 员工登录日志内部部门
	 */
	public void setUserLoginLogId(String userLoginLogId) {
		this.userLoginLogId = userLoginLogId;
		addValidField("userLoginLogId");
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
	 * Get 登录时间
	 */
	@Column(name = "LOGIN_TIME")
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * Set 登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
		addValidField("loginTime");
	}

	/**
	 * Get 退出时间
	 */
	@Column(name = "LOGOUT_TIME")
	public Date getLogoutTime() {
		return logoutTime;
	}

	/**
	 * Set 退出时间
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
		addValidField("logoutTime");
	}

	/**
	 * Get 登录成功标志
	 */
	@Column(name = "TRY_TIMES")
	public Integer getTryTimes() {
		return tryTimes;
	}

	/**
	 * Set 登录成功标志
	 */
	public void setTryTimes(Integer tryTimes) {
		this.tryTimes = tryTimes;
		addValidField("tryTimes");
	}

	/**
	 * Get 登录IP
	 */
	@Column(name = "USER_IP")
	public String getUserIp() {
		return userIp;
	}

	/**
	 * Set 登录IP
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
		addValidField("userIp");
	}

	/**
	 * Get 登录主机名
	 */
	@Column(name = "HOST_NAME")
	public String getHostName() {
		return hostName;
	}

	/**
	 * Set 登录主机名
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
		addValidField("hostName");
	}

}
