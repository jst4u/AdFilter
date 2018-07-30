package com.loit.core.web.chart;

import java.util.HashMap;
import java.util.Map;

public  class Title {
	private String align;// center       
	private Boolean floating;// false      
	private Integer margin;// 15 
	private Map style = new HashMap<>();// {color: '#3E576F',fontSize: '16px'}       
	private String text;// Chart title       
	private Boolean useHTML;// false      
	private String verticalAlign;//       
	private Integer x;//  0 
	private Integer y;// 15
	
	
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Boolean getFloating() {
		return floating;
	}
	public void setFloating(Boolean floating) {
		this.floating = floating;
	}
	public Integer getMargin() {
		return margin;
	}
	public void setMargin(Integer margin) {
		this.margin = margin;
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
	public Boolean getUseHTML() {
		return useHTML;
	}
	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
	}
	public String getVerticalAlign() {
		return verticalAlign;
	}
	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}


}
