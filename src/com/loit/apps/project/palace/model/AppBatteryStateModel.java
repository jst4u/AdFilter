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
 * Model class for AppBatteryState
 */
@Entity
@Table(name = "app_battery_state")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class AppBatteryStateModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "AppBatteryState";

	public static final class FieldNames {
		/**
		 * ID
		 */
		public static final String id = "id";
		/**
		 * 电池ID
		 */
		public static final String batteryId = "batteryId";
		/**
		 * 电池电压
		 */
		public static final String batteryVoltage = "batteryVoltage";
		/**
		 * 电池温度
		 */
		public static final String batteryTemperature = "batteryTemperature";
		/**
		 * 电池剩余容量
		 */
		public static final String residualCapacity = "residualCapacity";
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
	//电池ID
	private String batteryId;
	//电池电压
	private String batteryVoltage;
	//电池温度
	private String batteryTemperature;
	//电池剩余容量
	private String residualCapacity;
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
	 * Get 电池ID
	 */
	@Column(name = "BATTERY_ID")
	public String getBatteryId() {
		return batteryId;
	}

	/**
	 * Set 电池ID
	 */
	public void setBatteryId(String batteryId) {
		this.batteryId = batteryId;
		addValidField(FieldNames.batteryId);
	}

	/**
	 * Get 电池电压
	 */
	@Column(name = "BATTERY_VOLTAGE")
	public String getBatteryVoltage() {
		return batteryVoltage;
	}

	/**
	 * Set 电池电压
	 */
	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
		addValidField(FieldNames.batteryVoltage);
	}

	/**
	 * Get 电池温度
	 */
	@Column(name = "BATTERY_TEMPERATURE")
	public String getBatteryTemperature() {
		return batteryTemperature;
	}

	/**
	 * Set 电池温度
	 */
	public void setBatteryTemperature(String batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
		addValidField(FieldNames.batteryTemperature);
	}

	/**
	 * Get 电池剩余容量
	 */
	@Column(name = "RESIDUAL_CAPACITY")
	public String getResidualCapacity() {
		return residualCapacity;
	}

	/**
	 * Set 电池剩余容量
	 */
	public void setResidualCapacity(String residualCapacity) {
		this.residualCapacity = residualCapacity;
		addValidField(FieldNames.residualCapacity);
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
