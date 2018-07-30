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
 * Model class for 组织
 */
@Entity
@Table(name = "SYS_ORGANIZE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysOrganizeModel extends BaseModel implements OperationLog {

	//组织内部编码
	private String organizeId;
	//组织名称
	private String name;
	//所属行政区域
	private Integer districtId;
	//组织类型
	private String organizeTypeId;
	//上级组织
	private String parentOrganizeId;
	//负责人
	private String manage;
	//联系人名称
	private String contact;
	//联系人电话
	private String contactTel;
	//传真号码
	private String fax;
	//联系人EMAIL地址
	private String email;
	//组织级别
	private String leve;
	//扩充字段1
	private String ex1;
	//扩充字段2
	private String ex2;
	//扩充字段3
	private String ex3;
	//扩充字段4
	private String ex4;
	//扩充字段5
	private String ex5;
	//扩充字段6
	private String ex6;
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

	/**
	 * Get 组织内部编码
	 */
	@Column(name = "ORGANIZE_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getOrganizeId() {
		return organizeId;
	}

	/**
	 * Set 组织内部编码
	 */
	public void setOrganizeId(String organizeId) {
		this.organizeId = organizeId;
		addValidField("organizeId");
	}

	/**
	 * Get 组织名称
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * Set 组织名称
	 */
	public void setName(String name) {
		this.name = name;
		addValidField("name");
	}

	/**
	 * Get 所属行政区域
	 */
	@Column(name = "DISTRICT_ID")
	public Integer getDistrictId() {
		return districtId;
	}

	/**
	 * Set 所属行政区域
	 */
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
		addValidField("districtId");
	}

	/**
	 * Get 组织类型
	 */
	@Column(name = "ORGANIZE_TYPE_ID")
	public String getOrganizeTypeId() {
		return organizeTypeId;
	}

	/**
	 * Set 组织类型
	 */
	public void setOrganizeTypeId(String organizeTypeId) {
		this.organizeTypeId = organizeTypeId;
		addValidField("organizeTypeId");
	}

	/**
	 * Get 上级组织
	 */
	@Column(name = "PARENT_ORGANIZE_ID")
	public String getParentOrganizeId() {
		return parentOrganizeId;
	}

	/**
	 * Set 上级组织
	 */
	public void setParentOrganizeId(String parentOrganizeId) {
		this.parentOrganizeId = parentOrganizeId;
		addValidField("parentOrganizeId");
	}

	/**
	 * Get 负责人
	 */
	@Column(name = "MANAGE")
	public String getManage() {
		return manage;
	}

	/**
	 * Set 负责人
	 */
	public void setManage(String manage) {
		this.manage = manage;
		addValidField("manage");
	}

	/**
	 * Get 联系人名称
	 */
	@Column(name = "CONTACT")
	public String getContact() {
		return contact;
	}

	/**
	 * Set 联系人名称
	 */
	public void setContact(String contact) {
		this.contact = contact;
		addValidField("contact");
	}

	/**
	 * Get 联系人电话
	 */
	@Column(name = "CONTACT_TEL")
	public String getContactTel() {
		return contactTel;
	}

	/**
	 * Set 联系人电话
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
		addValidField("contactTel");
	}

	/**
	 * Get 传真号码
	 */
	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	/**
	 * Set 传真号码
	 */
	public void setFax(String fax) {
		this.fax = fax;
		addValidField("fax");
	}

	/**
	 * Get 联系人EMAIL地址
	 */
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	/**
	 * Set 联系人EMAIL地址
	 */
	public void setEmail(String email) {
		this.email = email;
		addValidField("email");
	}

	/**
	 * Get 组织级别
	 */
	@Column(name = "LEVE")
	public String getLeve() {
		return leve;
	}

	/**
	 * Set 组织级别
	 */
	public void setLeve(String leve) {
		this.leve = leve;
		addValidField("leve");
	}

	/**
	 * Get 扩充字段1
	 */
	@Column(name = "EX1")
	public String getEx1() {
		return ex1;
	}

	/**
	 * Set 扩充字段1
	 */
	public void setEx1(String ex1) {
		this.ex1 = ex1;
		addValidField("ex1");
	}

	/**
	 * Get 扩充字段2
	 */
	@Column(name = "EX2")
	public String getEx2() {
		return ex2;
	}

	/**
	 * Set 扩充字段2
	 */
	public void setEx2(String ex2) {
		this.ex2 = ex2;
		addValidField("ex2");
	}

	/**
	 * Get 扩充字段3
	 */
	@Column(name = "EX3")
	public String getEx3() {
		return ex3;
	}

	/**
	 * Set 扩充字段3
	 */
	public void setEx3(String ex3) {
		this.ex3 = ex3;
		addValidField("ex3");
	}

	/**
	 * Get 扩充字段4
	 */
	@Column(name = "EX4")
	public String getEx4() {
		return ex4;
	}

	/**
	 * Set 扩充字段4
	 */
	public void setEx4(String ex4) {
		this.ex4 = ex4;
		addValidField("ex4");
	}

	/**
	 * Get 扩充字段5
	 */
	@Column(name = "EX5")
	public String getEx5() {
		return ex5;
	}

	/**
	 * Set 扩充字段5
	 */
	public void setEx5(String ex5) {
		this.ex5 = ex5;
		addValidField("ex5");
	}

	/**
	 * Get 扩充字段6
	 */
	@Column(name = "EX6")
	public String getEx6() {
		return ex6;
	}

	/**
	 * Set 扩充字段6
	 */
	public void setEx6(String ex6) {
		this.ex6 = ex6;
		addValidField("ex6");
	}

	/**
	 * Get 状态
	 * selectCode.user_status
	 */
	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	/**
	 * Set 状态
	 * selectCode.user_status
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

}
