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
 * Model class for TxSystemInfo
 */
@Entity
@Table(name = "tx_system_info")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TxSystemInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "TxSystemInfo";

	public static final class FieldNames {
		/**
		 * 系统信息ID
		 */
		public static final String uid = "uid";
		/**
		 * 电脑型号
		 */
		public static final String cm = "cm";
		/**
		 * 操作系统
		 */
		public static final String os = "os";
		/**
		 * CPU信息
		 */
		public static final String cpu = "cpu";
		/**
		 * 主板
		 */
		public static final String mb = "mb";
		/**
		 * 内存信息
		 */
		public static final String ram = "ram";
		/**
		 * 硬盘
		 */
		public static final String hdd = "hdd";
		/**
		 * 显卡
		 */
		public static final String vga = "vga";
		/**
		 * 显示器
		 */
		public static final String moniter = "moniter";
		/**
		 * 光驱
		 */
		public static final String rom = "rom";
		/**
		 * 声卡
		 */
		public static final String aud = "aud";
		/**
		 * 网卡
		 */
		public static final String lan = "lan";
		/**
		 * 无线网卡
		 */
		public static final String wlan = "wlan";
		/**
		 * MAC地址
		 */
		public static final String mac = "mac";
		/**
		 * IP地址
		 */
		public static final String ip = "ip";
		/**
		 * 硬盘码
		 */
		public static final String cid = "cid";
	}

	//系统信息ID
	private Integer uid;
	//电脑型号
	private String cm;
	//操作系统
	private String os;
	//CPU信息
	private String cpu;
	//主板
	private String mb;
	//内存信息
	private String ram;
	//硬盘
	private String hdd;
	//显卡
	private String vga;
	//显示器
	private String moniter;
	//光驱
	private String rom;
	//声卡
	private String aud;
	//网卡
	private String lan;
	//无线网卡
	private String wlan;
	//MAC地址
	private String mac;
	//IP地址
	private String ip;
	//硬盘码
	private String cid;

	/**
	 * Get 系统信息ID
	 */
	@Column(name = "UID")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getUid() {
		return uid;
	}

	/**
	 * Set 系统信息ID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
		addValidField(FieldNames.uid);
	}

	/**
	 * Get 电脑型号
	 */
	@Column(name = "CM")
	public String getCm() {
		return cm;
	}

	/**
	 * Set 电脑型号
	 */
	public void setCm(String cm) {
		this.cm = cm;
		addValidField(FieldNames.cm);
	}

	/**
	 * Get 操作系统
	 */
	@Column(name = "OS")
	public String getOs() {
		return os;
	}

	/**
	 * Set 操作系统
	 */
	public void setOs(String os) {
		this.os = os;
		addValidField(FieldNames.os);
	}

	/**
	 * Get CPU信息
	 */
	@Column(name = "CPU")
	public String getCpu() {
		return cpu;
	}

	/**
	 * Set CPU信息
	 */
	public void setCpu(String cpu) {
		this.cpu = cpu;
		addValidField(FieldNames.cpu);
	}

	/**
	 * Get 主板
	 */
	@Column(name = "MB")
	public String getMb() {
		return mb;
	}

	/**
	 * Set 主板
	 */
	public void setMb(String mb) {
		this.mb = mb;
		addValidField(FieldNames.mb);
	}

	/**
	 * Get 内存信息
	 */
	@Column(name = "RAM")
	public String getRam() {
		return ram;
	}

	/**
	 * Set 内存信息
	 */
	public void setRam(String ram) {
		this.ram = ram;
		addValidField(FieldNames.ram);
	}

	/**
	 * Get 硬盘
	 */
	@Column(name = "HDD")
	public String getHdd() {
		return hdd;
	}

	/**
	 * Set 硬盘
	 */
	public void setHdd(String hdd) {
		this.hdd = hdd;
		addValidField(FieldNames.hdd);
	}

	/**
	 * Get 显卡
	 */
	@Column(name = "VGA")
	public String getVga() {
		return vga;
	}

	/**
	 * Set 显卡
	 */
	public void setVga(String vga) {
		this.vga = vga;
		addValidField(FieldNames.vga);
	}

	/**
	 * Get 显示器
	 */
	@Column(name = "Moniter")
	public String getMoniter() {
		return moniter;
	}

	/**
	 * Set 显示器
	 */
	public void setMoniter(String moniter) {
		this.moniter = moniter;
		addValidField(FieldNames.moniter);
	}

	/**
	 * Get 光驱
	 */
	@Column(name = "ROM")
	public String getRom() {
		return rom;
	}

	/**
	 * Set 光驱
	 */
	public void setRom(String rom) {
		this.rom = rom;
		addValidField(FieldNames.rom);
	}

	/**
	 * Get 声卡
	 */
	@Column(name = "AUD")
	public String getAud() {
		return aud;
	}

	/**
	 * Set 声卡
	 */
	public void setAud(String aud) {
		this.aud = aud;
		addValidField(FieldNames.aud);
	}

	/**
	 * Get 网卡
	 */
	@Column(name = "LAN")
	public String getLan() {
		return lan;
	}

	/**
	 * Set 网卡
	 */
	public void setLan(String lan) {
		this.lan = lan;
		addValidField(FieldNames.lan);
	}

	/**
	 * Get 无线网卡
	 */
	@Column(name = "WLAN")
	public String getWlan() {
		return wlan;
	}

	/**
	 * Set 无线网卡
	 */
	public void setWlan(String wlan) {
		this.wlan = wlan;
		addValidField(FieldNames.wlan);
	}

	/**
	 * Get MAC地址
	 */
	@Column(name = "MAC")
	public String getMac() {
		return mac;
	}

	/**
	 * Set MAC地址
	 */
	public void setMac(String mac) {
		this.mac = mac;
		addValidField(FieldNames.mac);
	}

	/**
	 * Get IP地址
	 */
	@Column(name = "IP")
	public String getIp() {
		return ip;
	}

	/**
	 * Set IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
		addValidField(FieldNames.ip);
	}

	/**
	 * Get 硬盘码
	 */
	@Column(name = "Cid")
	public String getCid() {
		return cid;
	}

	/**
	 * Set 硬盘码
	 */
	public void setCid(String cid) {
		this.cid = cid;
		addValidField(FieldNames.cid);
	}

}
