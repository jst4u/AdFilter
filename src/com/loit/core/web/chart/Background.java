package com.loit.core.web.chart;

import java.util.HashMap;
import java.util.Map;


public class Background {

//	private Map backgroundColor = new HashMap<>();
	private String backgroundColor;
	private Integer borderWidth;// 0
	private String borderColor;
	private String outerRadius;// 105%
	private String innerRadius;// 103%

//	public Map getBackgroundColor() {
//		return backgroundColor;
//	}
//	public void setBackgroundColor(String key,String value) {
//		this.backgroundColor.put(key, value);
//	}

	public Integer getBorderWidth() {
		return borderWidth;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setBorderWidth(Integer borderWidth) {
		this.borderWidth = borderWidth;
	}

	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public String getOuterRadius() {
		return outerRadius;
	}

	public void setOuterRadius(String outerRadius) {
		this.outerRadius = outerRadius;
	}

	public String getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(String innerRadius) {
		this.innerRadius = innerRadius;
	}

		
}
