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
 * Model class for AppNodeState
 */
@Entity
@Table(name = "app_node_state")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class AppNodeStateModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "AppNodeState";

	public static final class FieldNames {
		/**
		 * ID
		 */
		public static final String id = "id";
		/**
		 * 节点ID
		 */
		public static final String nodeId = "nodeId";
		/**
		 * 电流值
		 */
		public static final String currentValue = "currentValue";
		/**
		 * 电压值
		 */
		public static final String voltageValue = "voltageValue";
		/**
		 * 发送时间
		 */
		public static final String sendTime = "sendTime";
		/**
		 * 是否重发
		 */
		public static final String isRepeat = "isRepeat";
		/**
		 * 重发次数
		 */
		public static final String repeatTimes = "repeatTimes";
		/**
		 * 重发时间
		 */
		public static final String repeatTime = "repeatTime";
		/**
		 * 系统时间
		 */
		public static final String systemTime = "systemTime";
		/**
		 * 备注1
		 */
		public static final String remarks1 = "remarks1";
		/**
		 * 备注2
		 */
		public static final String remarks2 = "remarks2";
		/**
		 * 备注3
		 */
		public static final String remarks3 = "remarks3";
		/**
		 * 版本号
		 */
		public static final String recVer = "recVer";
	}

	//ID
	private String id;
	//节点ID
	private String nodeId;
	//电流值
	private String currentValue;
	//电压值
	private String voltageValue;
	//发送时间
	private String sendTime;
	//是否重发
	private String isRepeat;
	//重发次数
	private String repeatTimes;
	//重发时间
	private String repeatTime;
	//系统时间
	private Date systemTime;
	//备注1
	private String remarks1;
	//备注2
	private String remarks2;
	//备注3
	private String remarks3;
	//版本号
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
	 * Get 节点ID
	 */
	@Column(name = "NODE_ID")
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * Set 节点ID
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
		addValidField(FieldNames.nodeId);
	}

	/**
	 * Get 电流值
	 */
	@Column(name = "CURRENT_VALUE")
	public String getCurrentValue() {
		return currentValue;
	}

	/**
	 * Set 电流值
	 */
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
		addValidField(FieldNames.currentValue);
	}

	/**
	 * Get 电压值
	 */
	@Column(name = "VOLTAGE_VALUE")
	public String getVoltageValue() {
		return voltageValue;
	}

	/**
	 * Set 电压值
	 */
	public void setVoltageValue(String voltageValue) {
		this.voltageValue = voltageValue;
		addValidField(FieldNames.voltageValue);
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
	 * Get 是否重发
	 */
	@Column(name = "IS_REPEAT")
	public String getIsRepeat() {
		return isRepeat;
	}

	/**
	 * Set 是否重发
	 */
	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
		addValidField(FieldNames.isRepeat);
	}

	/**
	 * Get 重发次数
	 */
	@Column(name = "REPEAT_TIMES")
	public String getRepeatTimes() {
		return repeatTimes;
	}

	/**
	 * Set 重发次数
	 */
	public void setRepeatTimes(String repeatTimes) {
		this.repeatTimes = repeatTimes;
		addValidField(FieldNames.repeatTimes);
	}

	/**
	 * Get 重发时间
	 */
	@Column(name = "REPEAT_TIME")
	public String getRepeatTime() {
		return repeatTime;
	}

	/**
	 * Set 重发时间
	 */
	public void setRepeatTime(String repeatTime) {
		this.repeatTime = repeatTime;
		addValidField(FieldNames.repeatTime);
	}

	/**
	 * Get 系统时间
	 */
	@Column(name = "SYSTEM_TIME")
	public Date getSystemTime() {
		return systemTime;
	}

	/**
	 * Set 系统时间
	 */
	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
		addValidField(FieldNames.systemTime);
	}

	/**
	 * Get 备注1
	 */
	@Column(name = "REMARKS1")
	public String getRemarks1() {
		return remarks1;
	}

	/**
	 * Set 备注1
	 */
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
		addValidField(FieldNames.remarks1);
	}

	/**
	 * Get 备注2
	 */
	@Column(name = "REMARKS2")
	public String getRemarks2() {
		return remarks2;
	}

	/**
	 * Set 备注2
	 */
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
		addValidField(FieldNames.remarks2);
	}

	/**
	 * Get 备注3
	 */
	@Column(name = "REMARKS3")
	public String getRemarks3() {
		return remarks3;
	}

	/**
	 * Set 备注3
	 */
	public void setRemarks3(String remarks3) {
		this.remarks3 = remarks3;
		addValidField(FieldNames.remarks3);
	}

	/**
	 * Get 版本号
	 */
	@Column(name = "REC_VER")
	@Version
	public Integer getRecVer() {
		return recVer;
	}

	/**
	 * Set 版本号
	 */
	public void setRecVer(Integer recVer) {
		this.recVer = recVer;
		addValidField(FieldNames.recVer);
	}

}
