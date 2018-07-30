package com.loit.core.web.chart;

import java.util.HashMap;
import java.util.Map;

public class LegendNavigation {
	private String activeColor;// #3E576F
	private Boolean  animation;// true
	private Integer arrowSize;// 12
	private String inactiveColor;// #CCC
	private Map style = new HashMap<>();// null     
	
	public String getActiveColor() {
		return activeColor;
	}
	public void setActiveColor(String activeColor) {
		this.activeColor = activeColor;
	}
	public Boolean getAnimation() {
		return animation;
	}
	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}
	public Integer getArrowSize() {
		return arrowSize;
	}
	public void setArrowSize(Integer arrowSize) {
		this.arrowSize = arrowSize;
	}
	public String getInactiveColor() {
		return inactiveColor;
	}
	public void setInactiveColor(String inactiveColor) {
		this.inactiveColor = inactiveColor;
	}
	public Map getStyle() {
		return style;
	}
	public void setStyle(String key,String value) {
		this.style.put(key, value);
	}

}
