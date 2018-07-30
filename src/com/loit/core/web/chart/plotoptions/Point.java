package com.loit.core.web.chart.plotoptions;

import java.util.HashMap;
import java.util.Map;

public class Point {
	private Map events = new HashMap<>();
	
	public Map getEvents() {
		return events;
	}
	public void setEvents(String key,String value) {
		this.events.put(key, value);
	}
}
