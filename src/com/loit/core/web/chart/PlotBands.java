package com.loit.core.web.chart;

public class PlotBands {
	private String color;// null
	private Integer from;// 
	private Integer to;// 
	private String events;// null
	private String id;// null
	private PoltLabel label;// {…}
	private String zIndex;// null
	
	public PlotBands(){
		label = new PoltLabel() ;// {…}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getzIndex() {
		return zIndex;
	}

	public void setzIndex(String zIndex) {
		this.zIndex = zIndex;
	}

	public PoltLabel getLabel() {
		return label;
	}


	
}
