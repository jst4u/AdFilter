package com.loit.apps.project.tianxing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.loit.core.hibernate.model.BaseModel;

/**
 * Model class for TxSoftwareInfo
 */
@Entity
@Table(name = "tx_software_info")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TxSoftwareInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "TxSoftwareInfo";

	public static final class FieldNames {
		/**
		 * 软件信息ID=用户ID
		 */
		public static final String uid = "uid";
		/**
		 * UUID机器码
		 */
		public static final String uuid = "uuid";
		/**
		 * 渠道编号
		 */
		public static final String channelId = "channelId";
		/**
		 * 站长ID
		 */
		public static final String stationmasterId = "stationmasterId";
		/**
		 * 版本号
		 */
		public static final String version = "version";
		/**
		 * 安装时间
		 */
		public static final String addtime = "addtime";
		/**
		 * 第一次运行时间
		 */
		public static final String runtime1 = "runtime1";
		/**
		 * 第二次运行时间
		 */
		public static final String runtime2 = "runtime2";
		/**
		 * 第三次运行时间
		 */
		public static final String runtime3 = "runtime3";
		/**
		 * 软件状态
		 */
		public static final String status = "status";
	}

	//软件信息ID=用户ID
	private Integer uid;
	//UUID机器码
	private String uuid;
	//渠道编号
	private String channelId;
	//站长ID
	private String stationmasterId;
	//版本号
	private String version;
	//安装时间
	private String addtime;
	//第一次运行时间
	private String runtime1;
	//第二次运行时间
	private String runtime2;
	//第三次运行时间
	private String runtime3;
	//软件状态
	private String status;

	/**
	 * Get 软件信息ID=用户ID
	 */
	@Column(name = "UID")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getUid() {
		return uid;
	}

	/**
	 * Set 软件信息ID=用户ID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
		addValidField(FieldNames.uid);
	}

	/**
	 * Get UUID机器码
	 */
	@Column(name = "UUID")
	public String getUuid() {
		return uuid;
	}

	/**
	 * Set UUID机器码
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
		addValidField(FieldNames.uuid);
	}

	/**
	 * Get 渠道编号
	 */
	@Column(name = "channel_id")
	public String getChannelId() {
		return channelId;
	}

	/**
	 * Set 渠道编号
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
		addValidField(FieldNames.channelId);
	}

	/**
	 * Get 站长ID
	 */
	@Column(name = "stationmaster_id")
	public String getStationmasterId() {
		return stationmasterId;
	}

	/**
	 * Set 站长ID
	 */
	public void setStationmasterId(String stationmasterId) {
		this.stationmasterId = stationmasterId;
		addValidField(FieldNames.stationmasterId);
	}

	/**
	 * Get 版本号
	 */
	@Column(name = "version")
	public String getVersion() {
		return version;
	}

	/**
	 * Set 版本号
	 */
	public void setVersion(String version) {
		this.version = version;
		addValidField(FieldNames.version);
	}

	/**
	 * Get 安装时间
	 */
	@Column(name = "addtime")
	public String getAddtime() {
		return addtime;
	}

	/**
	 * Set 安装时间
	 */
	public void setAddtime(String addtime) {
		this.addtime = addtime;
		addValidField(FieldNames.addtime);
	}

	/**
	 * Get 第一次运行时间
	 */
	@Column(name = "runtime1")
	public String getRuntime1() {
		return runtime1;
	}

	/**
	 * Set 第一次运行时间
	 */
	public void setRuntime1(String runtime1) {
		this.runtime1 = runtime1;
		addValidField(FieldNames.runtime1);
	}

	/**
	 * Get 第二次运行时间
	 */
	@Column(name = "runtime2")
	public String getRuntime2() {
		return runtime2;
	}

	/**
	 * Set 第二次运行时间
	 */
	public void setRuntime2(String runtime2) {
		this.runtime2 = runtime2;
		addValidField(FieldNames.runtime2);
	}

	/**
	 * Get 第三次运行时间
	 */
	@Column(name = "runtime3")
	public String getRuntime3() {
		return runtime3;
	}

	/**
	 * Set 第三次运行时间
	 */
	public void setRuntime3(String runtime3) {
		this.runtime3 = runtime3;
		addValidField(FieldNames.runtime3);
	}

	/**
	 * Get 软件状态
	 */
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 软件状态
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
	}

}
