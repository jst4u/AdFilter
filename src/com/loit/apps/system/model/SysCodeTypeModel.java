package com.loit.apps.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.loit.core.hibernate.model.BaseModel;
import com.loit.core.hibernate.model.OperationLog;

/**
 * Model class for SysCodeType
 */
@Entity
@Table(name = "sys_code_type")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysCodeTypeModel extends BaseModel implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysCodeType";

	public static final class FieldNames {
		/**
		 * 代码类型ID
		 */
		public static final String codeTypeId = "codeTypeId";
		/**
		 * 代码类型名称
		 */
		public static final String codeTypeName = "codeTypeName";
		/**
		 * 编码类型代号
		 */
		public static final String codeTypeCode = "codeTypeCode";
		/**
		 * 代码类型描述
		 */
		public static final String codeTypeDesc = "codeTypeDesc";
		/**
		 * 代码类型分类
		 */
		public static final String codeTypeType = "codeTypeType";
		/**
		 * 备注说明
		 */
		public static final String remarks = "remarks";
		/**
		 * 动态代码
		 */
		public static final String dynColValue = "dynColValue";
		/**
		 * 动态代码
		 */
		public static final String dynColName = "dynColName";
		/**
		 * 动态代码
		 */
		public static final String dynTablename = "dynTablename";
		/**
		 * 代码显示条件
		 */
		public static final String dynWhere = "dynWhere";
		/**
		 * 系统自带代码
		 */
		public static final String codeFrom = "codeFrom";
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

	//代码类型ID
	private String codeTypeId;
	//代码类型名称
	private String codeTypeName;
	//编码类型代号
	private String codeTypeCode;
	//代码类型描述
	private String codeTypeDesc;
	//代码类型分类
	private String codeTypeType;
	//备注说明
	private String remarks;
	//动态代码
	private String dynColValue;
	//动态代码
	private String dynColName;
	//动态代码
	private String dynTablename;
	//代码显示条件
	private String dynWhere;
	//系统自带代码
	private String codeFrom;
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
	 * Get 代码类型ID
	 */
	@Column(name = "CODE_TYPE_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getCodeTypeId() {
		return codeTypeId;
	}

	/**
	 * Set 代码类型ID
	 */
	public void setCodeTypeId(String codeTypeId) {
		this.codeTypeId = codeTypeId;
		addValidField(FieldNames.codeTypeId);
	}

	/**
	 * Get 代码类型名称
	 */
	@Column(name = "CODE_TYPE_NAME")
	public String getCodeTypeName() {
		return codeTypeName;
	}

	/**
	 * Set 代码类型名称
	 */
	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
		addValidField(FieldNames.codeTypeName);
	}

	/**
	 * Get 编码类型代号
	 */
	@Column(name = "CODE_TYPE_CODE")
	public String getCodeTypeCode() {
		return codeTypeCode;
	}

	/**
	 * Set 编码类型代号
	 */
	public void setCodeTypeCode(String codeTypeCode) {
		this.codeTypeCode = codeTypeCode;
		addValidField(FieldNames.codeTypeCode);
	}

	/**
	 * Get 代码类型描述
	 */
	@Column(name = "CODE_TYPE_DESC")
	public String getCodeTypeDesc() {
		return codeTypeDesc;
	}

	/**
	 * Set 代码类型描述
	 */
	public void setCodeTypeDesc(String codeTypeDesc) {
		this.codeTypeDesc = codeTypeDesc;
		addValidField(FieldNames.codeTypeDesc);
	}

	/**
	 * Get 代码类型分类
	 * ：静态、动态
	 */
	@Column(name = "CODE_TYPE_TYPE")
	public String getCodeTypeType() {
		return codeTypeType;
	}

	/**
	 * Set 代码类型分类
	 * ：静态、动态
	 */
	public void setCodeTypeType(String codeTypeType) {
		this.codeTypeType = codeTypeType;
		addValidField(FieldNames.codeTypeType);
	}

	/**
	 * Get 备注说明
	 */
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Set 备注说明
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
		addValidField(FieldNames.remarks);
	}

	/**
	 * Get 动态代码
	 * ，代码列
	 */
	@Column(name = "DYN_COL_VALUE")
	public String getDynColValue() {
		return dynColValue;
	}

	/**
	 * Set 动态代码
	 * ，代码列
	 */
	public void setDynColValue(String dynColValue) {
		this.dynColValue = dynColValue;
		addValidField(FieldNames.dynColValue);
	}

	/**
	 * Get 动态代码
	 * ，代码显示名称
	 */
	@Column(name = "DYN_COL_NAME")
	public String getDynColName() {
		return dynColName;
	}

	/**
	 * Set 动态代码
	 * ，代码显示名称
	 */
	public void setDynColName(String dynColName) {
		this.dynColName = dynColName;
		addValidField(FieldNames.dynColName);
	}

	/**
	 * Get 动态代码
	 * ，代码显示名称
	 */
	@Column(name = "DYN_TABLENAME")
	public String getDynTablename() {
		return dynTablename;
	}

	/**
	 * Set 动态代码
	 * ，代码显示名称
	 */
	public void setDynTablename(String dynTablename) {
		this.dynTablename = dynTablename;
		addValidField(FieldNames.dynTablename);
	}

	/**
	 * Get 代码显示条件
	 */
	@Column(name = "DYN_WHERE")
	public String getDynWhere() {
		return dynWhere;
	}

	/**
	 * Set 代码显示条件
	 */
	public void setDynWhere(String dynWhere) {
		this.dynWhere = dynWhere;
		addValidField(FieldNames.dynWhere);
	}

	/**
	 * Get 系统自带代码
	 * ，不能删除
	 */
	@Column(name = "CODE_FROM")
	public String getCodeFrom() {
		return codeFrom;
	}

	/**
	 * Set 系统自带代码
	 * ，不能删除
	 */
	public void setCodeFrom(String codeFrom) {
		this.codeFrom = codeFrom;
		addValidField(FieldNames.codeFrom);
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
