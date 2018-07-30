package com.loit.core.web.chart;

import java.util.HashMap;
import java.util.Map;

public  class AxisTitle {
	private String align;// center       
	private Integer margin;// null
	private Integer offset;// null 
	private Integer rotation;// 0 
	private Map style = new HashMap<>();// {color: '#6D869F',fontWeight: 'bold'}       
	private String text;// Chart title       
	
	
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Integer getMargin() {
		return margin;
	}
	public void setMargin(Integer margin) {
		this.margin = margin;
	}
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getRotation() {
		return rotation;
	}
	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}
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
