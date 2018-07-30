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
 * Model class for AppGatewayBase
 */
@Entity
@Table(name = "app_gateway_base")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class AppGatewayBaseModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "AppGatewayBase";

	public static final class FieldNames {
		/**
		 * ID
		 */
		public static final String id = "id";
		/**
		 * 网关ID
		 */
		public static final String gatewayId = "gatewayId";
		/**
		 * 网关状态
		 */
		public static final String gatewayState = "gatewayState";
		/**
		 * 安装位置
		 */
		public static final String installationPosition = "installationPosition";
		/**
		 * 安装人员
		 */
		public static final String installationPersonnel = "installationPersonnel";
		/**
		 * 安装时间
		 */
		public static final String installationTime = "installationTime";
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
	//网关ID
	private String gatewayId;
	//网关状态
	private String gatewayState;
	//安装位置
	private String installationPosition;
	//安装人员
	private String installationPersonnel;
	//安装时间
	private Date installationTime;
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
	 * Get 网关ID
	 */
	@Column(name = "GATEWAY_ID")
	public String getGatewayId() {
		return gatewayId;
	}

	/**
	 * Set 网关ID
	 */
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
		addValidField(FieldNames.gatewayId);
	}

	/**
	 * Get 网关状态
	 */
	@Column(name = "GATEWAY_STATE")
	public String getGatewayState() {
		return gatewayState;
	}

	/**
	 * Set 网关状态
	 */
	public void setGatewayState(String gatewayState) {
		this.gatewayState = gatewayState;
		addValidField(FieldNames.gatewayState);
	}

	/**
	 * Get 安装位置
	 */
	@Column(name = "INSTALLATION_POSITION")
	public String getInstallationPosition() {
		return installationPosition;
	}

	/**
	 * Set 安装位置
	 */
	public void setInstallationPosition(String installationPosition) {
		this.installationPosition = installationPosition;
		addValidField(FieldNames.installationPosition);
	}

	/**
	 * Get 安装人员
	 */
	@Column(name = "INSTALLATION_PERSONNEL")
	public String getInstallationPersonnel() {
		return installationPersonnel;
	}

	/**
	 * Set 安装人员
	 */
	public void setInstallationPersonnel(String installationPersonnel) {
		this.installationPersonnel = installationPersonnel;
		addValidField(FieldNames.installationPersonnel);
	}

	/**
	 * Get 安装时间
	 */
	@Column(name = "INSTALLATION_TIME")
	public Date getInstallationTime() {
		return installationTime;
	}

	/**
	 * Set 安装时间
	 */
	public void setInstallationTime(Date installationTime) {
		this.installationTime = installationTime;
		addValidField(FieldNames.installationTime);
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
