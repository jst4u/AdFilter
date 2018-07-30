package com.loit.core.web.chart.plotoptions;


public class Marker {
	private Boolean enabled;// true     
	private String fillColor;// null   
	private String lineColor;// #FFFFFF
	private Integer lineWidth;// 0      
	private Integer radius;// 0         
	private final MarkerStates states;// {…}      
	private String symbol;// null  
	
	public Marker(){
		states = new MarkerStates();// object
		
	} 
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getFillColor() {
		return fillColor;
	}
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	public String getLineColor() {
		return lineColor;
	}
	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}
	public Integer getLineWidth() {
		return lineWidth;
	}
	public void setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}
	public Integer getRadius() {
		return radius;
	}
	public void setRadius(Integer radius) {
		this.radius = radius;
	}
	public MarkerStates getStates() {
		return states;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
