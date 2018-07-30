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
 * Model class for TlPjUserRelation
 */
@Entity
@Table(name = "tl_pj_user_relation")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TlPjUserRelationModel extends BaseModel implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "TlPjUserRelation";

	public static final class FieldNames {
		/**
		 * 系统ID
		 */
		public static final String purId = "purId";
		/**
		 * 项目ID
		 */
		public static final String pjId = "pjId";
		/**
		 * 人员
		 */
		public static final String userId = "userId";
		/**
		 * 关系
		 */
		public static final String relationship = "relationship";
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

	//系统ID
	private String purId;
	//项目ID
	private String pjId;
	//人员
	private String userId;
	//关系
	private String relationship;
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
	 * Get 系统ID
	 */
	@Column(name = "PUR_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getPurId() {
		return purId;
	}

	/**
	 * Set 系统ID
	 */
	public void setPurId(String purId) {
		this.purId = purId;
		addValidField(FieldNames.purId);
	}

	/**
	 * Get 项目ID
	 */
	@Column(name = "PJ_ID")
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
	 * Get 人员
	 */
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	/**
	 * Set 人员
	 */
	public void setUserId(String userId) {
		this.userId = userId;
		addValidField(FieldNames.userId);
	}

	/**
	 * Get 关系
	 * 0：项目成员；1：项目经理
	 */
	@Column(name = "RELATIONSHIP")
	public String getRelationship() {
		return relationship;
	}

	/**
	 * Set 关系
	 * 0：项目成员；1：项目经理
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
		addValidField(FieldNames.relationship);
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
