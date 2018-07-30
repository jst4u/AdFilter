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
 * Model class for 系统参数表
 */
@Entity
@Table(name = "SYS_PARAM")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysParamModel extends BaseModel implements OperationLog {

	//参数ID
	private String paramId;
	//数据类型
	private String dateType;
	//参数名称
	private String paramName;
	//参数值
	private String paramValue;
	//参数描述
	private String paramDesc;
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
	private Long recVer;
	//system
	private String system;
	//参数值名称
	private String paramValueLabel;
	//转向页面
	private String editAction;

	/**
	 * Get 参数ID
	 */
	@Column(name = "PARAM_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getParamId() {
		return paramId;
	}

	/**
	 * Set 参数ID
	 */
	public void setParamId(String paramId) {
		this.paramId = paramId;
		addValidField("paramId");
	}

	/**
	 * Get 数据类型
	 */
	@Column(name = "DATE_TYPE")
	public String getDateType() {
		return dateType;
	}

	/**
	 * Set 数据类型
	 */
	public void setDateType(String dateType) {
		this.dateType = dateType;
		addValidField("dateType");
	}

	/**
	 * Get 参数名称
	 */
	@Column(name = "PARAM_NAME")
	public String getParamName() {
		return paramName;
	}

	/**
	 * Set 参数名称
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
		addValidField("paramName");
	}

	/**
	 * Get 参数值
	 */
	@Column(name = "PARAM_VALUE")
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * Set 参数值
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
		addValidField("paramValue");
	}

	/**
	 * Get 参数描述
	 */
	@Column(name = "PARAM_DESC")
	public String getParamDesc() {
		return paramDesc;
	}

	/**
	 * Set 参数描述
	 */
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
		addValidField("paramDesc");
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
	public Long getRecVer() {
		return recVer;
	}

	/**
	 * Set recVer
	 */
	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField("recVer");
	}

	/**
	 * Get system
	 */
	@Column(name = "SYSTEM")
	public String getSystem() {
		return system;
	}

	/**
	 * Set system
	 */
	public void setSystem(String system) {
		this.system = system;
		addValidField("system");
	}

	/**
	 * Get 参数值名称
	 */
	@Column(name = "PARAM_VALUE_LABEL")
	public String getParamValueLabel() {
		return paramValueLabel;
	}

	/**
	 * Set 参数值名称
	 */
	public void setParamValueLabel(String paramValueLabel) {
		this.paramValueLabel = paramValueLabel;
		addValidField("paramValueLabel");
	}

	/**
	 * Get 转向页面
	 */
	@Column(name = "EDIT_ACTION")
	public String getEditAction() {
		return editAction;
	}

	/**
	 * Set 转向页面
	 */
	public void setEditAction(String editAction) {
		this.editAction = editAction;
		addValidField("editAction");
	}

}
