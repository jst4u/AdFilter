package com.loit.apps.project.model;

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
 * Model class for TlPjInfo
 */
@Entity
@Table(name = "tl_pj_info")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TlPjInfoModel extends BaseModel implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "TlPjInfo";

	public static final class FieldNames {
		/**
		 * 项目ID
		 */
		public static final String pjId = "pjId";
		/**
		 * 项目编号
		 */
		public static final String pjCode = "pjCode";
		/**
		 * 项目名称
		 */
		public static final String pjName = "pjName";
		/**
		 * 项目经理
		 */
		public static final String pjManager = "pjManager";
		/**
		 * 项目描述
		 */
		public static final String pjDesc = "pjDesc";
		/**
		 * 项目状态
		 */
		public static final String pjStatus = "pjStatus";
		/**
		 * 项目预计资金大小
		 */
		public static final String pjCapital = "pjCapital";
		/**
		 * 项目甲方
		 */
		public static final String pjA = "pjA";
		/**
		 * 项目乙方
		 */
		public static final String pjB = "pjB";
		/**
		 * 项目丙方
		 */
		public static final String pjC = "pjC";
		/**
		 * 项目相关用户
		 */
		public static final String pjUsers = "pjUsers";
		/**
		 * 项目关系描述
		 */
		public static final String pjDescRelationship = "pjDescRelationship";
		/**
		 * 项目资金描述
		 */
		public static final String pjDescCapital = "pjDescCapital";
		/**
		 * 项目预计结束日期
		 */
		public static final String pjEstimateEndtime = "pjEstimateEndtime";
		/**
		 * 项目招标日期
		 */
		public static final String pjBiddingTime = "pjBiddingTime";
		/**
		 * 项目启动会正式开始日期
		 */
		public static final String pjStarttime = "pjStarttime";
		/**
		 * 项目终验通过日期
		 */
		public static final String pjEndtime = "pjEndtime";
		/**
		 * 项目自从跟踪开始的日期
		 */
		public static final String pjBeginTime = "pjBeginTime";
		/**
		 * 负责项目跟踪、联系等
		 */
		public static final String pjOwner = "pjOwner";
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

	//项目ID
	private String pjId;
	//项目编号
	private String pjCode;
	//项目名称
	private String pjName;
	//项目经理
	private String pjManager;
	//项目描述
	private String pjDesc;
	//项目状态
	private String pjStatus;
	//项目预计资金大小
	private Double pjCapital;
	//项目甲方
	private String pjA;
	//项目乙方
	private String pjB;
	//项目丙方
	private String pjC;
	//项目相关用户
	private String pjUsers;
	//项目关系描述
	private String pjDescRelationship;
	//项目资金描述
	private String pjDescCapital;
	//项目预计结束日期
	private String pjEstimateEndtime;
	//项目招标日期
	private String pjBiddingTime;
	//项目启动会正式开始日期
	private String pjStarttime;
	//项目终验通过日期
	private String pjEndtime;
	//项目自从跟踪开始的日期
	private String pjBeginTime;
	//负责项目跟踪、联系等
	private String pjOwner;
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
	 * Get 项目ID
	 */
	@Column(name = "PJ_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getPjId() {
		return pjId;
	}

	/**
	 * Set 项目ID
	 */
	public void setPjId(String pjId) {
		this.pjId = pjId;
		addValidField(FieldNames.pjId);
	}

	/**
	 * Get 项目编号
	 */
	@Column(name = "PJ_CODE")
	public String getPjCode() {
		return pjCode;
	}

	/**
	 * Set 项目编号
	 */
	public void setPjCode(String pjCode) {
		this.pjCode = pjCode;
		addValidField(FieldNames.pjCode);
	}

	/**
	 * Get 项目名称
	 */
	@Column(name = "PJ_NAME")
	public String getPjName() {
		return pjName;
	}

	/**
	 * Set 项目名称
	 */
	public void setPjName(String pjName) {
		this.pjName = pjName;
		addValidField(FieldNames.pjName);
	}

	/**
	 * Get 项目经理
	 */
	@Column(name = "PJ_MANAGER")
	public String getPjManager() {
		return pjManager;
	}

	/**
	 * Set 项目经理
	 */
	public void setPjManager(String pjManager) {
		this.pjManager = pjManager;
		addValidField(FieldNames.pjManager);
	}

	/**
	 * Get 项目描述
	 */
	@Column(name = "PJ_DESC")
	public String getPjDesc() {
		return pjDesc;
	}

	/**
	 * Set 项目描述
	 */
	public void setPjDesc(String pjDesc) {
		this.pjDesc = pjDesc;
		addValidField(FieldNames.pjDesc);
	}

	/**
	 * Get 项目状态
	 */
	@Column(name = "PJ_STATUS")
	public String getPjStatus() {
		return pjStatus;
	}

	/**
	 * Set 项目状态
	 */
	public void setPjStatus(String pjStatus) {
		this.pjStatus = pjStatus;
		addValidField(FieldNames.pjStatus);
	}

	/**
	 * Get 项目预计资金大小
	 */
	@Column(name = "PJ_CAPITAL")
	public Double getPjCapital() {
		return pjCapital;
	}

	/**
	 * Set 项目预计资金大小
	 */
	public void setPjCapital(Double pjCapital) {
		this.pjCapital = pjCapital;
		addValidField(FieldNames.pjCapital);
	}

	/**
	 * Get 项目甲方
	 */
	@Column(name = "PJ_A")
	public String getPjA() {
		return pjA;
	}

	/**
	 * Set 项目甲方
	 */
	public void setPjA(String pjA) {
		this.pjA = pjA;
		addValidField(FieldNames.pjA);
	}

	/**
	 * Get 项目乙方
	 */
	@Column(name = "PJ_B")
	public String getPjB() {
		return pjB;
	}

	/**
	 * Set 项目乙方
	 */
	public void setPjB(String pjB) {
		this.pjB = pjB;
		addValidField(FieldNames.pjB);
	}

	/**
	 * Get 项目丙方
	 */
	@Column(name = "PJ_C")
	public String getPjC() {
		return pjC;
	}

	/**
	 * Set 项目丙方
	 */
	public void setPjC(String pjC) {
		this.pjC = pjC;
		addValidField(FieldNames.pjC);
	}

	/**
	 * Get 项目相关用户
	 */
	@Column(name = "PJ_USERS")
	public String getPjUsers() {
		return pjUsers;
	}

	/**
	 * Set 项目相关用户
	 */
	public void setPjUsers(String pjUsers) {
		this.pjUsers = pjUsers;
		addValidField(FieldNames.pjUsers);
	}

	/**
	 * Get 项目关系描述
	 */
	@Column(name = "PJ_DESC_RELATIONSHIP")
	public String getPjDescRelationship() {
		return pjDescRelationship;
	}

	/**
	 * Set 项目关系描述
	 */
	public void setPjDescRelationship(String pjDescRelationship) {
		this.pjDescRelationship = pjDescRelationship;
		addValidField(FieldNames.pjDescRelationship);
	}

	/**
	 * Get 项目资金描述
	 */
	@Column(name = "PJ_DESC_CAPITAL")
	public String getPjDescCapital() {
		return pjDescCapital;
	}

	/**
	 * Set 项目资金描述
	 */
	public void setPjDescCapital(String pjDescCapital) {
		this.pjDescCapital = pjDescCapital;
		addValidField(FieldNames.pjDescCapital);
	}

	/**
	 * Get 项目预计结束日期
	 */
	@Column(name = "PJ_ESTIMATE_ENDTIME")
	public String getPjEstimateEndtime() {
		return pjEstimateEndtime;
	}

	/**
	 * Set 项目预计结束日期
	 */
	public void setPjEstimateEndtime(String pjEstimateEndtime) {
		this.pjEstimateEndtime = pjEstimateEndtime;
		addValidField(FieldNames.pjEstimateEndtime);
	}

	/**
	 * Get 项目招标日期
	 */
	@Column(name = "PJ_BIDDING_TIME")
	public String getPjBiddingTime() {
		return pjBiddingTime;
	}

	/**
	 * Set 项目招标日期
	 */
	public void setPjBiddingTime(String pjBiddingTime) {
		this.pjBiddingTime = pjBiddingTime;
		addValidField(FieldNames.pjBiddingTime);
	}

	/**
	 * Get 项目启动会正式开始日期
	 */
	@Column(name = "PJ_STARTTIME")
	public String getPjStarttime() {
		return pjStarttime;
	}

	/**
	 * Set 项目启动会正式开始日期
	 */
	public void setPjStarttime(String pjStarttime) {
		this.pjStarttime = pjStarttime;
		addValidField(FieldNames.pjStarttime);
	}

	/**
	 * Get 项目终验通过日期
	 */
	@Column(name = "PJ_ENDTIME")
	public String getPjEndtime() {
		return pjEndtime;
	}

	/**
	 * Set 项目终验通过日期
	 */
	public void setPjEndtime(String pjEndtime) {
		this.pjEndtime = pjEndtime;
		addValidField(FieldNames.pjEndtime);
	}

	/**
	 * Get 项目自从跟踪开始的日期
	 */
	@Column(name = "PJ_BEGIN_TIME")
	public String getPjBeginTime() {
		return pjBeginTime;
	}

	/**
	 * Set 项目自从跟踪开始的日期
	 */
	public void setPjBeginTime(String pjBeginTime) {
		this.pjBeginTime = pjBeginTime;
		addValidField(FieldNames.pjBeginTime);
	}

	/**
	 * Get 负责项目跟踪、联系等
	 * ；
	 */
	@Column(name = "PJ_OWNER")
	public String getPjOwner() {
		return pjOwner;
	}

	/**
	 * Set 负责项目跟踪、联系等
	 * ；
	 */
	public void setPjOwner(String pjOwner) {
		this.pjOwner = pjOwner;
		addValidField(FieldNames.pjOwner);
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
