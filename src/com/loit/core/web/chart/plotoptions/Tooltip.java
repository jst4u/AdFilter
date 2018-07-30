package com.loit.core.web.chart.plotoptions;

import java.util.HashMap;
import java.util.Map;

public class Tooltip {
	private Map dateTimeLabelFormats = new HashMap<>();// 
	private Boolean followPointer;// false  
	private Boolean followTouchMove;// false
	private String footerFormat;// false   
	private String headerFormat;//         
	private Integer hideDelay;// 500        
	private String pointFormat;// null     
	private Integer valueDecimals;// null   
	private String valuePrefix;// null     
	private String valueSuffix;// null     
	private String xDateFormat;// null   
	
	
	public Map getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}
	public void setDateTimeLabelFormats(String key,String value) {
		this.dateTimeLabelFormats.put(key, value);
	}
	public Boolean getFollowPointer() {
		return followPointer;
	}
	public void setFollowPointer(Boolean followPointer) {
		this.followPointer = followPointer;
	}
	public Boolean getFollowTouchMove() {
		return followTouchMove;
	}
	public void setFollowTouchMove(Boolean followTouchMove) {
		this.followTouchMove = followTouchMove;
	}
	public String getFooterFormat() {
		return footerFormat;
	}
	public void setFooterFormat(String footerFormat) {
		this.footerFormat = footerFormat;
	}
	public String getHeaderFormat() {
		return headerFormat;
	}
	public void setHeaderFormat(String headerFormat) {
		this.headerFormat = headerFormat;
	}
	public Integer getHideDelay() {
		return hideDelay;
	}
	public void setHideDelay(Integer hideDelay) {
		this.hideDelay = hideDelay;
	}
	public String getPointFormat() {
		return pointFormat;
	}
	public void setPointFormat(String pointFormat) {
		this.pointFormat = pointFormat;
	}
	public Integer getValueDecimals() {
		return valueDecimals;
	}
	public void setValueDecimals(Integer valueDecimals) {
		this.valueDecimals = valueDecimals;
	}
	public String getValuePrefix() {
		return valuePrefix;
	}
	public void setValuePrefix(String valuePrefix) {
		this.valuePrefix = valuePrefix;
	}
	public String getValueSuffix() {
		return valueSuffix;
	}
	public void setValueSuffix(String valueSuffix) {
		this.valueSuffix = valueSuffix;
	}
	public String getxDateFormat() {
		return xDateFormat;
	}
	public void setxDateFormat(String xDateFormat) {
		this.xDateFormat = xDateFormat;
	}

}
