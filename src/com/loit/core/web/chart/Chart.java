package com.loit.core.web.chart;

import java.util.HashMap;
import java.util.Map;

public class Chart {
	private Boolean alignTicks;// true
	private Boolean animation;// true
	private String backgroundColor;// #FFFFFF
	private String borderColor;// #4572A7
	private Integer borderRadius;// 5
	private Integer borderWidth;// 0
	private String className;// null
	private String defaultSeriesType;// line
	private Map events = new HashMap<>();// {…}
	private Integer height;// null
	private Boolean ignoreHiddenSeries;// true
	private Boolean inverted;// false
	private String margin;// null
	private String marginBottom;// null
	private String marginLeft;// null
	private String marginRight;// null
	private String marginTop;// null
	private String pinchType;// null
	private String plotBackgroundColor;// null
	private String plotBackgroundImage;// null
	private String plotBorderColor;// #C0C0C0
	private Integer plotBorderWidth;// 0
	private Boolean plotShadow;// false
	private Boolean polar;// false
	private Boolean reflow;// true
	private String renderTo;// null
	private String selectionMarkerFill;// rgba(69,114,167,0.25)
	private Boolean shadow;// false
	private Boolean showAxes;// false
	private Integer spacingBottom;// 15
	private Integer spacingLeft;// 10
	private Integer spacingRight;// 10
	private Integer spacingTop;// 10
	private Map style = new HashMap<>();// {…}
	private String type;// line
	private Integer width;// null
	private String zoomType;// null
	
	public Boolean getAlignTicks() {
		return alignTicks;
	}
	public void setAlignTicks(Boolean alignTicks) {
		this.alignTicks = alignTicks;
	}
	public Boolean getAnimation() {
		return animation;
	}
	public void setAnimation(Boolean animation) {
		this.animation = animation;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getDefaultSeriesType() {
		return defaultSeriesType;
	}
	public void setDefaultSeriesType(String defaultSeriesType) {
		this.defaultSeriesType = defaultSeriesType;
	}
	public Map getEvents() {
		return events;
	}
	public void setEvents(String key,String value) {
		this.events.put(key, value);
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Boolean getIgnoreHiddenSeries() {
		return ignoreHiddenSeries;
	}
	public void setIgnoreHiddenSeries(Boolean ignoreHiddenSeries) {
		this.ignoreHiddenSeries = ignoreHiddenSeries;
	}
	public Boolean getInverted() {
		return inverted;
	}
	public void setInverted(Boolean inverted) {
		this.inverted = inverted;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public String getMarginBottom() {
		return marginBottom;
	}
	public void setMarginBottom(String marginBottom) {
		this.marginBottom = marginBottom;
	}
	public String getMarginLeft() {
		return marginLeft;
	}
	public void setMarginLeft(String marginLeft) {
		this.marginLeft = marginLeft;
	}
	public String getMarginRight() {
		return marginRight;
	}
	public void setMarginRight(String marginRight) {
		this.marginRight = marginRight;
	}
	public String getMarginTop() {
		return marginTop;
	}
	public void setMarginTop(String marginTop) {
		this.marginTop = marginTop;
	}
	public String getPinchType() {
		return pinchType;
	}
	public void setPinchType(String pinchType) {
		this.pinchType = pinchType;
	}
	public String getPlotBackgroundColor() {
		return plotBackgroundColor;
	}
	public void setPlotBackgroundColor(String plotBackgroundColor) {
		this.plotBackgroundColor = plotBackgroundColor;
	}
	public String getPlotBackgroundImage() {
		return plotBackgroundImage;
	}
	public void setPlotBackgroundImage(String plotBackgroundImage) {
		this.plotBackgroundImage = plotBackgroundImage;
	}
	public String getPlotBorderColor() {
		return plotBorderColor;
	}
	public void setPlotBorderColor(String plotBorderColor) {
		this.plotBorderColor = plotBorderColor;
	}
	public Integer getPlotBorderWidth() {
		return plotBorderWidth;
	}
	public void setPlotBorderWidth(Integer plotBorderWidth) {
		this.plotBorderWidth = plotBorderWidth;
	}
	public Boolean getPlotShadow() {
		return plotShadow;
	}
	public void setPlotShadow(Boolean plotShadow) {
		this.plotShadow = plotShadow;
	}
	public Boolean getPolar() {
		return polar;
	}
	public void setPolar(Boolean polar) {
		this.polar = polar;
	}
	
	public Boolean getReflow() {
		return reflow;
	}
	public void setReflow(Boolean reflow) {
		this.reflow = reflow;
	}
	public String getRenderTo() {
		return renderTo;
	}
	public void setRenderTo(String renderTo) {
		this.renderTo = renderTo;
	}
	public String getSelectionMarkerFill() {
		return selectionMarkerFill;
	}
	public void setSelectionMarkerFill(String selectionMarkerFill) {
		this.selectionMarkerFill = selectionMarkerFill;
	}
	public Boolean getShadow() {
		return shadow;
	}
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}
	public Boolean getShowAxes() {
		return showAxes;
	}
	public void setShowAxes(Boolean showAxes) {
		this.showAxes = showAxes;
	}
	public Integer getSpacingBottom() {
		return spacingBottom;
	}
	public void setSpacingBottom(Integer spacingBottom) {
		this.spacingBottom = spacingBottom;
	}
	public Integer getSpacingLeft() {
		return spacingLeft;
	}
	public void setSpacingLeft(Integer spacingLeft) {
		this.spacingLeft = spacingLeft;
	}
	public Integer getSpacingRight() {
		return spacingRight;
	}
	public void setSpacingRight(Integer spacingRight) {
		this.spacingRight = spacingRight;
	}
	public Integer getSpacingTop() {
		return spacingTop;
	}
	public void setSpacingTop(Integer spacingTop) {
		this.spacingTop = spacingTop;
	}
	public Map getStyle() {
		return events;
	}
	public void setStyle(String key,String value) {
		this.events.put(key, value);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public String getZoomType() {
		return zoomType;
	}
	public void setZoomType(String zoomType) {
		this.zoomType = zoomType;
	}
	
	
}
