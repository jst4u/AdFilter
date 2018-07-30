package com.loit.core.web.chart.plotoptions;

import java.util.HashMap;
import java.util.Map;

public class DateLabels {
	private String align;// center           
	private String backgroundColor;// null   
	private String borderColor;// null       
	private Integer borderRadius;// 0         
	private Integer borderWidth;// 0          
	private String color;// null             
	private Boolean crop;// true              
	private Boolean enabled;// false          
	private String format;// {y}             
	private String formatter;// null         
	private Boolean inside;//                 
	private Integer maxStaggerLines;// 5      
	private Integer padding;// 2              
	private Integer rotation;// 0             
	private Boolean shadow;// false           
	private Map style = new HashMap<>();// null             
	private Boolean useHTML;// false          
	private String verticalAlign;// null     
	private Integer x;// 0                    
	private Integer y;// -6  
	private Integer xHigh;// 0   arearange ,areasplinerange,columnrange                
	private Integer xLow;//  0 	 arearange
	private Integer yHigh;//-6   arearange                 
	private Integer yLow;// 16   arearange
	private Integer zIndex;// 6   
	
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public Integer getBorderRadius() {
		return borderRadius;
	}
	public void setBorderRadius(Integer borderRadius) {
		this.borderRadius = borderRadius;
	}
	public Integer getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(Integer borderWidth) {
		this.borderWidth = borderWidth;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Boolean getCrop() {
		return crop;
	}
	public void setCrop(Boolean crop) {
		this.crop = crop;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public Boolean getInside() {
		return inside;
	}
	public void setInside(Boolean inside) {
		this.inside = inside;
	}
	public Integer getMaxStaggerLines() {
		return maxStaggerLines;
	}
	public void setMaxStaggerLines(Integer maxStaggerLines) {
		this.maxStaggerLines = maxStaggerLines;
	}
	public Integer getPadding() {
		return padding;
	}
	public void setPadding(Integer padding) {
		this.padding = padding;
	}
	public Integer getRotation() {
		return rotation;
	}
	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}
	public Boolean getShadow() {
		return shadow;
	}
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}
	public Map getStyle() {
		return style;
	}
	public void setStyle(String key,String value) {
		this.style.put(key, value);
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
	public Integer getzIndex() {
		return zIndex;
	}
	public void setzIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}
	public Integer getxHigh() {
		return xHigh;
	}
	public void setxHigh(Integer xHigh) {
		this.xHigh = xHigh;
	}
	public Integer getxLow() {
		return xLow;
	}
	public void setxLow(Integer xLow) {
		this.xLow = xLow;
	}
	public Integer getyHigh() {
		return yHigh;
	}
	public void setyHigh(Integer yHigh) {
		this.yHigh = yHigh;
	}
	public Integer getyLow() {
		return yLow;
	}
	public void setyLow(Integer yLow) {
		this.yLow = yLow;
	}


}
