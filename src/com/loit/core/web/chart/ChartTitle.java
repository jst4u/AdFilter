package com.loit.core.web.chart;

public class ChartTitle extends Title {
	private Boolean floating;// false     
	private Boolean useHTML;// false      
	private String verticalAlign;//      
	private Integer x;// 0                
	private Integer y;// 15               
	public Boolean getFloating() {
		return floating;
	}
	public void setFloating(Boolean floating) {
		this.floating = floating;
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
