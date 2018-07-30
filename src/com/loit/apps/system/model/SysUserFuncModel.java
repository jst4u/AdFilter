package com.loit.apps.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

import com.loit.core.hibernate.model.BaseModel;
import com.loit.core.hibernate.model.OperationLog;

/**
 * Model class for SysUserFunc
 */
@Entity
@Table(name = "sys_user_func")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysUserFuncModel extends BaseModel implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysUserFunc";

	public static final class FieldNames {
		/**
		 * userFuncId
		 */
		public static final String userFuncId = "userFuncId";
		/**
		 * userId
		 */
		public static final String userId = "userId";
		/**
		 * funcId
		 */
		public static final String funcId = "funcId";
		/**
		 * state
		 */
		public static final String state = "state";
		/**
		 * remarks
		 */
		public static final String remarks = "remarks";
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
	}

	//userFuncId
	private String userFuncId;
	//userId
	private String userId;
	//funcId
	private String funcId;
	//state
	private String state;
	//remarks
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
	 * Get userFuncId
	 */
	@Column(name = "USER_FUNC_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getUserFuncId() {
		return userFuncId;
	}

	/**
	 * Set userFuncId
	 */
	public void setUserFuncId(String userFuncId) {
		this.userFuncId = userFuncId;
		addValidField(FieldNames.userFuncId);
	}

	/**
	 * Get userId
	 */
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	/**
	 * Set userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
		addValidField(FieldNames.userId);
	}

	/**
	 * Get funcId
	 */
	@Column(name = "FUNC_ID")
	public String getFuncId() {
		return funcId;
	}

	/**
	 * Set funcId
	 */
	public void setFuncId(String funcId) {
		this.funcId = funcId;
		addValidField(FieldNames.funcId);
	}

	/**
	 * Get state
	 */
	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	/**
	 * Set state
	 */
	public void setState(String state) {
		this.state = state;
		addValidField(FieldNames.state);
	}

	/**
	 * Get remarks
	 */
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Set remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
		addValidField(FieldNames.remarks);
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
	public Integer getRecVer() {
		return recVer;
	}

	/**
	 * Set recVer
	 */
	public void setRecVer(Integer recVer) {
		this.recVer = recVer;
		addValidField(FieldNames.recVer);
	}

}
