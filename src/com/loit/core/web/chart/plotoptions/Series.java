package com.loit.core.web.chart.plotoptions;

import java.util.HashMap;
import java.util.Map;

public class Series {
	     
	private Boolean allowPointSelect;// false   
	private Boolean animation;// true   		boxplot没有此属性
	private String borderColor;// #FFFFFF.  	bar，column，columnrange有这个属性
	private Integer borderRadius;// 0  			bar，column，columnrange有这个属性
	private Integer borderWidth;// 1 			bar，column，columnrange有这个属性
	private String color;// null 
	private Boolean colorByPoint;// false		bar，boxplot,column，columnrange，errorbar有这个属性
	private String colors;// null				bar，boxplot,column，columnrange，errorbar有这个属性
	private Boolean connectEnds;// true      	arearange,areasplinerange，bar，boxplot,bubble，column，columnrange，errorbar没有此属性    
	private Boolean connectNulls;// false       bar，boxplot,bubble，column，columnrange，errorbar没有此属性
	private Integer cropThreshold;// 300        boxplot,errorbar没有此属性
	private String cursor;// null              
	private String dashStyle;// null           	bar，boxplot,column，columnrange，errorbar没有此属性
	private final DateLabels dataLabels;// {…}   boxplot, errorbar没有此属性       
	private Boolean enableMouseTracking;// true 
	private Map events = new HashMap<>();
	private String fillColor;// null  			area,boxplot有这个属性
	private Float fillOpacity;// 0.75 		area有这个属性
	private Float groupPadding;// 0.2 		bar,boxplot,column有这个属性
	private Boolean grouping;// true 			bar,boxplot,column有这个属性
	private String id;// null 
	private String lineColor;// null 			area有这个属性
	private Integer lineWidth;// 2
	private String linkedTo;// 
	private final Marker marker;// {…}      	areasplinerange, bar, boxplot, column, columnrange, errorbar, funnel, pie,waterfall没有此属性       
	private Float minPointLength;// 0.2 		bar,column有这个属性
	private String negativeColor;// null
	private String negativeFillColor;// null	area有这个属性
	private final Point point;// {…}               
	private Integer pointInterval;// 1   
	private Float pointPadding;// 0.1   		bar,column有这个属性
	private String pointPlacement;// null  
	private Integer pointRange;//    			bar,column有这个属性
	private Integer pointStart;// 0 
	private Integer pointWidth;//    			bar,column有这个属性
	private Boolean selected;// false           
	private Boolean shadow;// false             errorbar没有此属性
	private Boolean showCheckbox;// false       errorbar没有此属性
	private Boolean showInLegend;// true        errorbar没有此属性
	private String stacking;// null             errorbar没有此属性
	private final StatesHover states;// {…}              
	private String step;// false           	    bar,errorbar没有此属性
	private String stemColor;// null 			boxplot,errorbar有这个属性
	private String stemDashStyle;// Solid 		boxplot,errorbar有这个属性
	private Integer stemWidth;// null 			boxplot,errorbar有这个属性
	private Boolean stickyTracking;// true      
	private Integer threshold;// 0              
	private final Tooltip tooltip;// {…}             
	private Integer turboThreshold;// 1000      
	private Boolean visible;// true  
	private String whiskerColor;// null 		boxplot,errorbar有这个属性
	private String whiskerLength;//  50% 		boxplot,errorbar有这个属性
	private Integer whiskerWidth;// null 		boxplot,errorbar有这个属性
	private Integer zIndex;// null              
	
	public Series(){
		dataLabels = new DateLabels();// object
		marker = new Marker();// object
		point = new Point();// object
		states = new StatesHover();// object
		tooltip = new Tooltip();// object
	}

	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}

	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getConnectEnds() {
		return connectEnds;
	}

	public void setConnectEnds(Boolean connectEnds) {
		this.connectEnds = connectEnds;
	}

	public Boolean getConnectNulls() {
		return connectNulls;
	}

	public void setConnectNulls(Boolean connectNulls) {
		this.connectNulls = connectNulls;
	}

	public Integer getCropThreshold() {
		return cropThreshold;
	}

	public void setCropThreshold(Integer cropThreshold) {
		this.cropThreshold = cropThreshold;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public String getDashStyle() {
		return dashStyle;
	}

	public void setDashStyle(String dashStyle) {
		this.dashStyle = dashStyle;
	}

	public Boolean getEnableMouseTracking() {
		return enableMouseTracking;
	}

	public void setEnableMouseTracking(Boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
	}

	public Map getEvents() {
		return events;
	}

	public void setEvents(String key, String value) {
		this.events.put(key, value);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}

	public String getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	public String getNegativeColor() {
		return negativeColor;
	}

	public void setNegativeColor(String negativeColor) {
		this.negativeColor = negativeColor;
	}

	public Integer getPointInterval() {
		return pointInterval;
	}

	public void setPointInterval(Integer pointInterval) {
		this.pointInterval = pointInterval;
	}

	public String getPointPlacement() {
		return pointPlacement;
	}

	public void setPointPlacement(String pointPlacement) {
		this.pointPlacement = pointPlacement;
	}

	public Integer getPointStart() {
		return pointStart;
	}

	public void setPointStart(Integer pointStart) {
		this.pointStart = pointStart;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean getShadow() {
		return shadow;
	}

	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public Boolean getShowCheckbox() {
		return showCheckbox;
	}

	public void setShowCheckbox(Boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}

	public Boolean getShowInLegend() {
		return showInLegend;
	}

	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	public String getStacking() {
		return stacking;
	}

	public void setStacking(String stacking) {
		this.stacking = stacking;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public Boolean getStickyTracking() {
		return stickyTracking;
	}

	public void setStickyTracking(Boolean stickyTracking) {
		this.stickyTracking = stickyTracking;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public Integer getTurboThreshold() {
		return turboThreshold;
	}

	public void setTurboThreshold(Integer turboThreshold) {
		this.turboThreshold = turboThreshold;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Integer getzIndex() {
		return zIndex;
	}

	public void setzIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}

	public DateLabels getDataLabels() {
		return dataLabels;
	}

	public Marker getMarker() {
		return marker;
	}

	public Point getPoint() {
		return point;
	}

	public StatesHover getStates() {
		return states;
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

	public Boolean getColorByPoint() {
		return colorByPoint;
	}

	public void setColorByPoint(Boolean colorByPoint) {
		this.colorByPoint = colorByPoint;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public Float getFillOpacity() {
		return fillOpacity;
	}

	public void setFillOpacity(Float fillOpacity) {
		this.fillOpacity = fillOpacity;
	}

	public Float getGroupPadding() {
		return groupPadding;
	}

	public void setGroupPadding(Float groupPadding) {
		this.groupPadding = groupPadding;
	}

	public Boolean getGrouping() {
		return grouping;
	}

	public void setGrouping(Boolean grouping) {
		this.grouping = grouping;
	}

	public String getLineColor() {
		return lineColor;
	}

	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}

	public Float getMinPointLength() {
		return minPointLength;
	}

	public void setMinPointLength(Float minPointLength) {
		this.minPointLength = minPointLength;
	}

	public String getNegativeFillColor() {
		return negativeFillColor;
	}

	public void setNegativeFillColor(String negativeFillColor) {
		this.negativeFillColor = negativeFillColor;
	}

	public Float getPointPadding() {
		return pointPadding;
	}

	public void setPointPadding(Float pointPadding) {
		this.pointPadding = pointPadding;
	}

	public Integer getPointRange() {
		return pointRange;
	}

	public void setPointRange(Integer pointRange) {
		this.pointRange = pointRange;
	}

	public Integer getPointWidth() {
		return pointWidth;
	}

	public void setPointWidth(Integer pointWidth) {
		this.pointWidth = pointWidth;
	}

	public String getStemColor() {
		return stemColor;
	}

	public void setStemColor(String stemColor) {
		this.stemColor = stemColor;
	}

	public String getStemDashStyle() {
		return stemDashStyle;
	}

	public void setStemDashStyle(String stemDashStyle) {
		this.stemDashStyle = stemDashStyle;
	}

	public Integer getStemWidth() {
		return stemWidth;
	}

	public void setStemWidth(Integer stemWidth) {
		this.stemWidth = stemWidth;
	}

	public String getWhiskerColor() {
		return whiskerColor;
	}

	public void setWhiskerColor(String whiskerColor) {
		this.whiskerColor = whiskerColor;
	}

	public String getWhiskerLength() {
		return whiskerLength;
	}

	public void setWhiskerLength(String whiskerLength) {
		this.whiskerLength = whiskerLength;
	}

	public Integer getWhiskerWidth() {
		return whiskerWidth;
	}

	public void setWhiskerWidth(Integer whiskerWidth) {
		this.whiskerWidth = whiskerWidth;
	}

	public void setEvents(Map events) {
		this.events = events;
	}

	
}
