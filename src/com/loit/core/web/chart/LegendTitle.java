package com.loit.core.web.chart;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Administrator
 * 只能使用text，style属性
 */
public class LegendTitle{
	private Map style = new HashMap<>();// null         
	private String text;// Chart title   

	public Map getStyle() {
		return style;
	}
	public void setStyle(String key,String value) {
		this.style.put(key, value);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
