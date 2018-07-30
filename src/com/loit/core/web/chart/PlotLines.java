package com.loit.core.web.chart;

public class PlotLines {
	private String color;// null
	private String dashStyle;// Solid
	private String events;// null
	private String id;// null
	private final PoltLabel label;// {…}
	private Integer value;// null
	private Integer width;// null
	private Integer zIndex;// null
	
	public PlotLines(){
		label = new PoltLabel() ;// {…}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDashStyle() {
		return dashStyle;
	}

	public void setDashStyle(String dashStyle) {
		this.dashStyle = dashStyle;
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getzIndex() {
		return zIndex;
	}

	public void setzIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}

	public PoltLabel getLabel() {
		return label;
	}

	
}
