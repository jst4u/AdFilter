package com.loit.apps.project.palace.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.loit.core.hibernate.model.BaseModel;

/**
 * Model class for AppSendRecord
 */
@Entity
@Table(name = "app_send_record")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class AppSendRecordModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "AppSendRecord";

	public static final class FieldNames {
		/**
		 * ID
		 */
		public static final String id = "id";
		/**
		 * 发送类型
		 */
		public static final String sendType = "sendType";
		/**
		 * 设备编号
		 */
		public static final String equCode = "equCode";
		/**
		 * 数据1
		 */
		public static final String data1 = "data1";
		/**
		 * 数据2
		 */
		public static final String data2 = "data2";
		/**
		 * 数据3
		 */
		public static final String data3 = "data3";
		/**
		 * 发送时间
		 */
		public static final String sendTime = "sendTime";
		/**
		 * 发送结果
		 */
		public static final String res = "res";
		/**
		 * 备注1
		 */
		public static final String ext1 = "ext1";
		/**
		 * 备注2
		 */
		public static final String ext2 = "ext2";
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

	//ID
	private String id;
	//发送类型
	private String sendType;
	//设备编号
	private String equCode;
	//数据1
	private String data1;
	//数据2
	private String data2;
	//数据3
	private String data3;
	//发送时间
	private String sendTime;
	//发送结果
	private String res;
	//备注1
	private String ext1;
	//备注2
	private String ext2;
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
	 * Get ID
	 */
	@Column(name = "ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name="UUIDGenerator", strategy="com.loit.core.hibernate.UUIDGenerator")
	public String getId() {
		return id;
	}

	/**
	 * Set ID
	 */
	public void setId(String id) {
		this.id = id;
		addValidField(FieldNames.id);
	}

	/**
	 * Get 发送类型
	 */
	@Column(name = "SEND_TYPE")
	public String getSendType() {
		return sendType;
	}

	/**
	 * Set 发送类型
	 */
	public void setSendType(String sendType) {
		this.sendType = sendType;
		addValidField(FieldNames.sendType);
	}

	/**
	 * Get 设备编号
	 */
	@Column(name = "EQU_CODE")
	public String getEquCode() {
		return equCode;
	}

	/**
	 * Set 设备编号
	 */
	public void setEquCode(String equCode) {
		this.equCode = equCode;
		addValidField(FieldNames.equCode);
	}

	/**
	 * Get 数据1
	 */
	@Column(name = "DATA1")
	public String getData1() {
		return data1;
	}

	/**
	 * Set 数据1
	 */
	public void setData1(String data1) {
		this.data1 = data1;
		addValidField(FieldNames.data1);
	}

	/**
	 * Get 数据2
	 */
	@Column(name = "DATA2")
	public String getData2() {
		return data2;
	}

	/**
	 * Set 数据2
	 */
	public void setData2(String data2) {
		this.data2 = data2;
		addValidField(FieldNames.data2);
	}

	/**
	 * Get 数据3
	 */
	@Column(name = "DATA3")
	public String getData3() {
		return data3;
	}

	/**
	 * Set 数据3
	 */
	public void setData3(String data3) {
		this.data3 = data3;
		addValidField(FieldNames.data3);
	}

	/**
	 * Get 发送时间
	 */
	@Column(name = "SEND_TIME")
	public String getSendTime() {
		return sendTime;
	}

	/**
	 * Set 发送时间
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
		addValidField(FieldNames.sendTime);
	}

	/**
	 * Get 发送结果
	 */
	@Column(name = "RES")
	public String getRes() {
		return res;
	}

	/**
	 * Set 发送结果
	 */
	public void setRes(String res) {
		this.res = res;
		addValidField(FieldNames.res);
	}

	/**
	 * Get 备注1
	 */
	@Column(name = "EXT1")
	public String getExt1() {
		return ext1;
	}

	/**
	 * Set 备注1
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
		addValidField(FieldNames.ext1);
	}

	/**
	 * Get 备注2
	 */
	@Column(name = "EXT2")
	public String getExt2() {
		return ext2;
	}

	/**
	 * Set 备注2
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
		addValidField(FieldNames.ext2);
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
