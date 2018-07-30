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
 * Model class for SysCode
 */
@Entity
@Table(name = "sys_code")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysCodeModel extends BaseModel implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysCode";

	public static final class FieldNames {
		/**
		 * 代码ID
		 */
		public static final String codeId = "codeId";
		/**
		 * 代码类型ID
		 */
		public static final String codeTypeId = "codeTypeId";
		/**
		 * 代码类型名称
		 */
		public static final String codeName = "codeName";
		/**
		 * 编码类型代号
		 */
		public static final String codeValue = "codeValue";
		/**
		 * 代码类型描述
		 */
		public static final String codeDesc = "codeDesc";
		/**
		 * codeOrder
		 */
		public static final String codeOrder = "codeOrder";
		/**
		 * 备注说明
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

	//代码ID
	private String codeId;
	//代码类型ID
	private String codeTypeId;
	//代码类型名称
	private String codeName;
	//编码类型代号
	private String codeValue;
	//代码类型描述
	private String codeDesc;
	//codeOrder
	private Integer codeOrder;
	//备注说明
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
	 * Get 代码ID
	 */
	@Column(name = "CODE_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getCodeId() {
		return codeId;
	}

	/**
	 * Set 代码ID
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
		addValidField(FieldNames.codeId);
	}

	/**
	 * Get 代码类型ID
	 */
	@Column(name = "CODE_TYPE_ID")
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
	@Column(name = "CODE_NAME")
	public String getCodeName() {
		return codeName;
	}

	/**
	 * Set 代码类型名称
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
		addValidField(FieldNames.codeName);
	}

	/**
	 * Get 编码类型代号
	 */
	@Column(name = "CODE_VALUE")
	public String getCodeValue() {
		return codeValue;
	}

	/**
	 * Set 编码类型代号
	 */
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
		addValidField(FieldNames.codeValue);
	}

	/**
	 * Get 代码类型描述
	 */
	@Column(name = "CODE_DESC")
	public String getCodeDesc() {
		return codeDesc;
	}

	/**
	 * Set 代码类型描述
	 */
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
		addValidField(FieldNames.codeDesc);
	}

	/**
	 * Get codeOrder
	 */
	@Column(name = "CODE_ORDER")
	public Integer getCodeOrder() {
		return codeOrder;
	}

	/**
	 * Set codeOrder
	 */
	public void setCodeOrder(Integer codeOrder) {
		this.codeOrder = codeOrder;
		addValidField(FieldNames.codeOrder);
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
