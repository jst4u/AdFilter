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

/**
 * Model class for SysQuery
 */
@Entity
@Table(name = "sys_query")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysQueryModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysQuery";

	public static final class FieldNames {
		/**
		 * 查询ID
		 */
		public static final String queryId = "queryId";
		/**
		 * 查询编码ID
		 */
		public static final String queryCode = "queryCode";
		/**
		 * 查询名称
		 */
		public static final String queryName = "queryName";
		/**
		 * 查询脚本
		 */
		public static final String queryScript = "queryScript";
		/**
		 * 查询脚本类型hql/sql
		 */
		public static final String queryScripttype = "queryScripttype";
		/**
		 * 备注
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

	//查询ID
	private String queryId;
	//查询编码ID
	private String queryCode;
	//查询名称
	private String queryName;
	//查询脚本
	private String queryScript;
	//查询脚本类型hql/sql
	private String queryScripttype;
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
	private int recVer;

	/**
	 * Get 查询ID
	 */
	@Column(name = "QUERY_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getQueryId() {
		return queryId;
	}

	/**
	 * Set 查询ID
	 */
	public void setQueryId(String queryId) {
		this.queryId = queryId;
		addValidField(FieldNames.queryId);
	}

	/**
	 * Get 查询编码ID
	 */
	@Column(name = "QUERY_CODE")
	public String getQueryCode() {
		return queryCode;
	}

	/**
	 * Set 查询编码ID
	 */
	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
		addValidField(FieldNames.queryCode);
	}

	/**
	 * Get 查询名称
	 */
	@Column(name = "QUERY_NAME")
	public String getQueryName() {
		return queryName;
	}

	/**
	 * Set 查询名称
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
		addValidField(FieldNames.queryName);
	}

	/**
	 * Get 查询脚本
	 */
	@Column(name = "QUERY_SCRIPT")
	public String getQueryScript() {
		return queryScript;
	}

	/**
	 * Set 查询脚本
	 */
	public void setQueryScript(String queryScript) {
		this.queryScript = queryScript;
		addValidField(FieldNames.queryScript);
	}

	/**
	 * Get 查询脚本类型hql/sql
	 */
	@Column(name = "QUERY_SCRIPTTYPE")
	public String getQueryScripttype() {
		return queryScripttype;
	}

	/**
	 * Set 查询脚本类型hql/sql
	 */
	public void setQueryScripttype(String queryScripttype) {
		this.queryScripttype = queryScripttype;
		addValidField(FieldNames.queryScripttype);
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
	public int getRecVer() {
		return recVer;
	}

	/**
	 * Set recVer
	 */
	public void setRecVer(int recVer) {
		this.recVer = recVer;
		addValidField(FieldNames.recVer);
	}

}
