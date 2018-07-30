package com.loit.core.commom;

import java.util.Properties;

public class SysConfig {

	public static final String PROJECT_NAME;
	public static final String SOURCE_DIR;
	public static final String CONFIG_DIR;
	public static final String CLASS_DIR;
	public static final String WEB_DIR;
	public static final String BASE_PACKAGE;
	public static final String USER_HOME_DIR;
	public static final Properties pros;

	static {
		pros = new Properties();
		try {
			pros.load(SysConfig.class.getResourceAsStream("/sysConfig.properties"));
			pros.load(SysConfig.class.getResourceAsStream("/security.properties"));
			pros.load(SysConfig.class.getResourceAsStream("/ui.properties"));
		} catch (Exception localException) {
		}
		PROJECT_NAME = pros.getProperty("PROJECT_NAME", "Walle");
		SOURCE_DIR = pros.getProperty("SOURCE_DIR", "src");
		CONFIG_DIR = pros.getProperty("CONFIG_DIR", "config");
		CLASS_DIR = pros.getProperty("CLASS_DIR", "build/classes");
		WEB_DIR = pros.getProperty("WEB_DIR", "WebContent");
		BASE_PACKAGE = pros.getProperty("BASE_PACKAGE", "cn");
		USER_HOME_DIR = pros.getProperty("USER_HOME_DIR", System.getProperty("user.home"));
	}
	
	public static String getSysCfg(String cfgName){
		return pros.getProperty(cfgName, "appname");
	}
	
	public static String getSysCfg(String cfgName, String defaultValue){
		return pros.getProperty(cfgName, defaultValue);
	}
}
