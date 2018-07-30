/*
 * Generated by Walle Tools. Do NOT modify.
 */
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
 * Model class for 角色
 */
@Entity
@Table(name = "SYS_ROLE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysRoleModel extends BaseModel implements OperationLog {

	private String roleId;
	private String code;
	private String name;
	private String state;
	private String remarks;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;
	private Integer recVer;

	/**
	 * Get 角色内部编码
	 */
	@Column(name = "ROLE_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getRoleId() {
		return roleId;
	}

	/**
	 * Set 角色内部编码
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
		addValidField("roleId");
	}

	/**
	 * Get 角色代码
	 */
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	/**
	 * Set 角色代码
	 */
	public void setCode(String code) {
		this.code = code;
		addValidField("code");
	}

	/**
	 * Get 角色名称
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * Set 角色名称
	 */
	public void setName(String name) {
		this.name = name;
		addValidField("name");
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