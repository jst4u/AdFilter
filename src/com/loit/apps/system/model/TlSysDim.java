package com.loit.apps.system.model;

import java.util.Date;

/**
 * TlSysDim entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlSysDim implements java.io.Serializable {

	// Fields

	private Integer dimId;
	private String dimTitle;
	private String dimTablename;
	private String dimColCode;
	private String dimColCodeType;
	private String dimColValue;
	private String dimWhere;
	private String description;
	private String dimParentcolCode;
	private Date createDate;
	private String createLogonname;
	private String deleteFlag;

	// Constructors

	/** default constructor */
	public TlSysDim() {
	}

	/** minimal constructor */
	public TlSysDim(Integer dimId, String createLogonname, String deleteFlag) {
		this.dimId = dimId;
		this.createLogonname = createLogonname;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public TlSysDim(Integer dimId, String dimTitle, String dimTablename,
			String dimColCode, String dimColCodeType, String dimColValue,
			String dimWhere, String description, String dimParentcolCode,
			Date createDate, String createLogonname, String deleteFlag) {
		this.dimId = dimId;
		this.dimTitle = dimTitle;
		this.dimTablename = dimTablename;
		this.dimColCode = dimColCode;
		this.dimColCodeType = dimColCodeType;
		this.dimColValue = dimColValue;
		this.dimWhere = dimWhere;
		this.description = description;
		this.dimParentcolCode = dimParentcolCode;
		this.createDate = createDate;
		this.createLogonname = createLogonname;
		this.deleteFlag = deleteFlag;
	}

	// Property accessors

	public Integer getDimId() {
		return this.dimId;
	}

	public void setDimId(Integer dimId) {
		this.dimId = dimId;
	}

	public String getDimTitle() {
		return this.dimTitle;
	}

	public void setDimTitle(String dimTitle) {
		this.dimTitle = dimTitle;
	}

	public String getDimTablename() {
		return this.dimTablename;
	}

	public void setDimTablename(String dimTablename) {
		this.dimTablename = dimTablename;
	}

	public String getDimColCode() {
		return this.dimColCode;
	}

	public void setDimColCode(String dimColCode) {
		this.dimColCode = dimColCode;
	}

	public String getDimColCodeType() {
		return this.dimColCodeType;
	}

	public void setDimColCodeType(String dimColCodeType) {
		this.dimColCodeType = dimColCodeType;
	}

	public String getDimColValue() {
		return this.dimColValue;
	}

	public void setDimColValue(String dimColValue) {
		this.dimColValue = dimColValue;
	}

	public String getDimWhere() {
		return this.dimWhere;
	}

	public void setDimWhere(String dimWhere) {
		this.dimWhere = dimWhere;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDimParentcolCode() {
		return this.dimParentcolCode;
	}

	public void setDimParentcolCode(String dimParentcolCode) {
		this.dimParentcolCode = dimParentcolCode;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateLogonname() {
		return this.createLogonname;
	}

	public void setCreateLogonname(String createLogonname) {
		this.createLogonname = createLogonname;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}