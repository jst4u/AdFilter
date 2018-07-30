package com.loit.core.web.chart;

import java.util.HashMap;
import java.util.Map;

public class Tooltip {
	private Boolean animation;// true
	private String backgroundColor;// rgba(255, 255, 255, 0.85)
	private String borderColor;// auto
	private Integer borderRadius;// 3
	private Integer borderWidth;// 1
	private String crosshairs;// null
	private Map dateTimeLabelFormats = new HashMap<>();
	private Boolean enabled;// true
	private Boolean followPointer;// false
	private Boolean followTouchMove;// false
	private Boolean footerFormat;// false
	private String formatter;// null
	private String headerFormat;//
	private Integer hideDelay;// 500
	private String pointFormat;// null
	private String positioner;// null
	private Boolean shadow;// true
	private Boolean shared;// false
	private String snap;// null
	private Map style = new HashMap<>();
	private Boolean useHTML;// false
	private String valueDecimals;// null
	private String valuePrefix;// null
	private String valueSuffix;// null
	private String xDateFormat;// null
	public Boolean getAnimation() {
		return animation;
	}
	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public Integer getBorderRadius() {
		return borderRadius;
	}
	public void setBorderRadius(Integer borderRadius) {
		this.borderRadius = borderRadius;
	}
	public Integer getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(Integer borderWidth) {
		this.borderWidth = borderWidth;
	}
	public String getCrosshairs() {
		return crosshairs;
	}
	public void setCrosshairs(String crosshairs) {
		this.crosshairs = crosshairs;
	}
	public Map getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}
	public void setDateTimeLabelFormats(String key,String value) {
		this.dateTimeLabelFormats.put(key, value);
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
	public Boolean getFooterFormat() {
		return footerFormat;
	}
	public void setFooterFormat(Boolean footerFormat) {
		this.footerFormat = footerFormat;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
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
	public String getPositioner() {
		return positioner;
	}
	public void setPositioner(String positioner) {
		this.positioner = positioner;
	}
	public Boolean getShadow() {
		return shadow;
	}
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}
	public Boolean getShared() {
		return shared;
	}
	public void setShared(Boolean shared) {
		this.shared = shared;
	}
	public String getSnap() {
		return snap;
	}
	public void setSnap(String snap) {
		this.snap = snap;
	}
	public Map getStyle() {
		return style;
	}
	public void setStyle(String key,String value) {
		this.style.put(key, value);
	}
	public Boolean getUseHTML() {
		return useHTML;
	}
	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
	}
	public String getValueDecimals() {
		return valueDecimals;
	}
	public void setValueDecimals(String valueDecimals) {
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
