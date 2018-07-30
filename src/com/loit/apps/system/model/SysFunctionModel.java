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
 * Model class for 系统功能
 */
@Entity
@Table(name = "SYS_FUNCTION")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysFunctionModel extends BaseModel implements OperationLog {

	//功能内部编码
	private String funcId;
	//功能编号
	private String funcCode;
	//功能名称
	private String name;
	//上级模块
	private String parentId;
	//模块等级
	private Integer funcLevel;
	//模块序号
	private Integer funSeq;
	//关联对象（页面）
	private String viewname;
	//DLL路径（客服）
	private String dllPath;
	//关联图片路径
	private String funcImg;
	//关联参数
	private String funcArg;
	//M-菜单
	private String funcType;
	//是否禁用
	private String disabled;
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
	private Integer recVer;
	//所属系统
	private String sys;

	/**
	 * Get 功能内部编码
	 */
	@Column(name = "FUNC_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getFuncId() {
		return funcId;
	}

	/**
	 * Set 功能内部编码
	 */
	public void setFuncId(String funcId) {
		this.funcId = funcId;
		addValidField("funcId");
	}

	/**
	 * Get 功能编号
	 */
	@Column(name = "FUNC_CODE")
	public String getFuncCode() {
		return funcCode;
	}

	/**
	 * Set 功能编号
	 */
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
		addValidField("funcCode");
	}

	/**
	 * Get 功能名称
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * Set 功能名称
	 */
	public void setName(String name) {
		this.name = name;
		addValidField("name");
	}

	/**
	 * Get 上级模块
	 * selectCode.sysfunction
	 */
	@Column(name = "PARENT_ID")
	public String getParentId() {
		return parentId;
	}

	/**
	 * Set 上级模块
	 * selectCode.sysfunction
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
		addValidField("parentId");
	}

	/**
	 * Get 模块等级
	 */
	@Column(name = "FUNC_LEVEL")
	public Integer getFuncLevel() {
		return funcLevel;
	}

	/**
	 * Set 模块等级
	 */
	public void setFuncLevel(Integer funcLevel) {
		this.funcLevel = funcLevel;
		addValidField("funcLevel");
	}

	/**
	 * Get 模块序号
	 */
	@Column(name = "FUN_SEQ")
	public Integer getFunSeq() {
		return funSeq;
	}

	/**
	 * Set 模块序号
	 */
	public void setFunSeq(Integer funSeq) {
		this.funSeq = funSeq;
		addValidField("funSeq");
	}

	/**
	 * Get 关联对象（页面）
	 */
	@Column(name = "VIEWNAME")
	public String getViewname() {
		return viewname;
	}

	/**
	 * Set 关联对象（页面）
	 */
	public void setViewname(String viewname) {
		this.viewname = viewname;
		addValidField("viewname");
	}

	/**
	 * Get DLL路径（客服）
	 */
	@Column(name = "DLL_PATH")
	public String getDllPath() {
		return dllPath;
	}

	/**
	 * Set DLL路径（客服）
	 */
	public void setDllPath(String dllPath) {
		this.dllPath = dllPath;
		addValidField("dllPath");
	}

	/**
	 * Get 关联图片路径
	 */
	@Column(name = "FUNC_IMG")
	public String getFuncImg() {
		return funcImg;
	}

	/**
	 * Set 关联图片路径
	 */
	public void setFuncImg(String funcImg) {
		this.funcImg = funcImg;
		addValidField("funcImg");
	}

	/**
	 * Get 关联参数
	 */
	@Column(name = "FUNC_ARG")
	public String getFuncArg() {
		return funcArg;
	}

	/**
	 * Set 关联参数
	 */
	public void setFuncArg(String funcArg) {
		this.funcArg = funcArg;
		addValidField("funcArg");
	}

	/**
	 * Get M-菜单
	 * B-按钮
	 */
	@Column(name = "FUNC_TYPE")
	public String getFuncType() {
		return funcType;
	}

	/**
	 * Set M-菜单
	 * B-按钮
	 */
	public void setFuncType(String funcType) {
		this.funcType = funcType;
		addValidField("funcType");
	}

	/**
	 * Get 是否禁用
	 * Y-是
	 * N-否
	 */
	@Column(name = "DISABLED")
	public String getDisabled() {
		return disabled;
	}

	/**
	 * Set 是否禁用
	 * Y-是
	 * N-否
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
		addValidField("disabled");
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

	/**
	 * Get 所属系统
	 */
	@Column(name = "SYS")
	public String getSys() {
		return sys;
	}

	/**
	 * Set 所属系统
	 */
	public void setSys(String sys) {
		this.sys = sys;
		addValidField("sys");
	}

}
