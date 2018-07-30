package com.loit.core.web.chart.plotoptions;

import java.util.HashMap;
import java.util.Map;

public class MarkerStates {
	private Map hover = new HashMap<>();
	private Map select = new HashMap<>();
	
	public Map getHover() {
		return hover;
	}
	public void setHover(String key,String value) {
		this.hover.put(key, value);
	}
	public Map getSelect() {
		return select;
	}
	public void setSelect(String key,String value) {
		this.select.put(key, value);
	}
}
