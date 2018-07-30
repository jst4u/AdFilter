package com.loit.core.web.chart.plotoptions;

public class StatesHover {
	private Boolean enabled;// true 
	private Integer lineWidth;// 2  
	private final StatesHoverMarker marker;// {…}  
	
	public StatesHover(){
		marker = new StatesHoverMarker();
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Integer getLineWidth() {
		return lineWidth;
	}
	public void setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}
	public StatesHoverMarker getMarker() {
		return marker;
	}

	
}
