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
 * Model class for 在线用户
 */
@Entity
@Table(name = "SYS_ONLINE_USER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysOnlineUserModel extends BaseModel {

	//主键
	private String onlineUserId;
	//用户
	private String userId;
	//session
	private String sessionId;
	//服务器地址
	private String serverName;
	//客户端地址
	private String remoteAddress;
	//客户端用户
	private String remoteUser;
	//登陆时间
	private Date loginTime;
	//最后访问时间
	private Date lastRequestTime;
	//expired
	private String expired;

	/**
	 * Get 主键
	 */
	@Column(name = "ONLINE_USER_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getOnlineUserId() {
		return onlineUserId;
	}

	/**
	 * Set 主键
	 */
	public void setOnlineUserId(String onlineUserId) {
		this.onlineUserId = onlineUserId;
		addValidField("onlineUserId");
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
	 * Get session
	 */
	@Column(name = "SESSION_ID")
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Set session
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
		addValidField("sessionId");
	}

	/**
	 * Get 服务器地址
	 */
	@Column(name = "SERVER_NAME")
	public String getServerName() {
		return serverName;
	}

	/**
	 * Set 服务器地址
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
		addValidField("serverName");
	}

	/**
	 * Get 客户端地址
	 */
	@Column(name = "REMOTE_ADDRESS")
	public String getRemoteAddress() {
		return remoteAddress;
	}

	/**
	 * Set 客户端地址
	 */
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
		addValidField("remoteAddress");
	}

	/**
	 * Get 客户端用户
	 */
	@Column(name = "REMOTE_USER")
	public String getRemoteUser() {
		return remoteUser;
	}

	/**
	 * Set 客户端用户
	 */
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
		addValidField("remoteUser");
	}

	/**
	 * Get 登陆时间
	 */
	@Column(name = "LOGIN_TIME")
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * Set 登陆时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
		addValidField("loginTime");
	}

	/**
	 * Get 最后访问时间
	 */
	@Column(name = "LAST_REQUEST_TIME")
	public Date getLastRequestTime() {
		return lastRequestTime;
	}

	/**
	 * Set 最后访问时间
	 */
	public void setLastRequestTime(Date lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
		addValidField("lastRequestTime");
	}

	/**
	 * Get expired
	 */
	@Column(name = "EXPIRED")
	public String getExpired() {
		return expired;
	}

	/**
	 * Set expired
	 */
	public void setExpired(String expired) {
		this.expired = expired;
		addValidField("expired");
	}

}
