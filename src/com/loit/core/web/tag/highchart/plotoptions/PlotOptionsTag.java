package com.loit.core.web.tag.highchart.plotoptions;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;
import com.loit.core.web.chart.plotoptions.DateLabels;
import com.loit.core.web.chart.plotoptions.Marker;
import com.loit.core.web.chart.plotoptions.Point;
import com.loit.core.web.chart.plotoptions.StatesHover;
import com.loit.core.web.chart.plotoptions.Tooltip;
import com.loit.core.web.tag.highchart.ChartTag;

public class PlotOptionsTag extends ChartTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;

	private Boolean allowPointSelect;// false
	private Boolean animation;// true boxplot没有此属性
	private String borderColor;// #FFFFFF. bar，column，columnrange有这个属性
	private Integer borderRadius;// 0 bar，column，columnrange有这个属性
	private Integer borderWidth;// 1 bar，column，columnrange有这个属性
	private String color;// null
	private Boolean colorByPoint;// false
									// bar，boxplot,column，columnrange，errorbar有这个属性
	private String colors;// null bar，boxplot,column，columnrange，errorbar有这个属性
	private Boolean connectEnds;// true
								// arearange,areasplinerange，bar，boxplot,bubble，column，columnrange，errorbar没有此属性
	private Boolean connectNulls;// false
									// bar，boxplot,bubble，column，columnrange，errorbar没有此属性
	private Integer cropThreshold;// 300 boxplot,errorbar没有此属性
	private String cursor;// null
	private String dashStyle;// null
								// bar，boxplot,column，columnrange，errorbar没有此属性
	private DateLabels dataLabels;// {…} boxplot, errorbar没有此属性
	private Boolean enableMouseTracking;// true
	private String events;
	private String fillColor;// null area,boxplot有这个属性
	private Float fillOpacity;// 0.75 area有这个属性
	private Float groupPadding;// 0.2 bar,boxplot,column有这个属性
	private Boolean grouping;// true bar,boxplot,column有这个属性
	private String id;// null
	private String lineColor;// null area有这个属性
	private Integer lineWidth;// 2
	private String linkedTo;//
	private Marker marker;// {…} areasplinerange, bar, boxplot, column,
							// columnrange, errorbar, funnel, pie,waterfall没有此属性
	private Float minPointLength;// 0.2 bar,column有这个属性
	private String negativeColor;// null
	private String negativeFillColor;// null area有这个属性
	private Point point;// {…}
	private Integer pointInterval;// 1
	private Float pointPadding;// 0.1 bar,column有这个属性
	private String pointPlacement;// null
	private Integer pointRange;// bar,column有这个属性
	private Integer pointStart;// 0
	private Integer pointWidth;// bar,column有这个属性
	private Boolean selected;// false
	private Boolean shadow;// false errorbar没有此属性
	private Boolean showCheckbox;// false errorbar没有此属性
	private Boolean showInLegend;// true errorbar没有此属性
	private String stacking;// null errorbar没有此属性
	private StatesHover states;// {…}
	private String step;// false bar,errorbar没有此属性
	private String stemColor;// null errorbar有这个属性
	private String stemDashStyle;// Solid errorbar有这个属性
	private Integer stemWidth;// null errorbar有这个属性
	private Boolean stickyTracking;// true
	private Integer threshold;// 0
	private Tooltip tooltip;// {…}
	private Integer turboThreshold;// 1000
	private Boolean visible;// true
	private String whiskerColor;// null errorbar有这个属性
	private String whiskerLength;// 50% errorbar有这个属性
	private Integer whiskerWidth;// null errorbar有这个属性
	private Integer zIndex;// null

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

	public DateLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DateLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	public Boolean getEnableMouseTracking() {
		return enableMouseTracking;
	}

	public void setEnableMouseTracking(Boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
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

	public void setMinPointLength(Float minPointLength) {
		this.minPointLength = minPointLength;
	}

	public Boolean getGrouping() {
		return grouping;
	}

	public void setGrouping(Boolean grouping) {
		this.grouping = grouping;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public Float getMinPointLength() {
		return minPointLength;
	}

	public String getNegativeColor() {
		return negativeColor;
	}

	public void setNegativeColor(String negativeColor) {
		this.negativeColor = negativeColor;
	}

	public String getNegativeFillColor() {
		return negativeFillColor;
	}

	public void setNegativeFillColor(String negativeFillColor) {
		this.negativeFillColor = negativeFillColor;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Integer getPointInterval() {
		return pointInterval;
	}

	public void setPointInterval(Integer pointInterval) {
		this.pointInterval = pointInterval;
	}

	public Float getPointPadding() {
		return pointPadding;
	}

	public void setPointPadding(Float pointPadding) {
		this.pointPadding = pointPadding;
	}

	public String getPointPlacement() {
		return pointPlacement;
	}

	public void setPointPlacement(String pointPlacement) {
		this.pointPlacement = pointPlacement;
	}

	public Integer getPointRange() {
		return pointRange;
	}

	public void setPointRange(Integer pointRange) {
		this.pointRange = pointRange;
	}

	public Integer getPointStart() {
		return pointStart;
	}

	public void setPointStart(Integer pointStart) {
		this.pointStart = pointStart;
	}

	public Integer getPointWidth() {
		return pointWidth;
	}

	public void setPointWidth(Integer pointWidth) {
		this.pointWidth = pointWidth;
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

	public StatesHover getStates() {
		return states;
	}

	public void setStates(StatesHover states) {
		this.states = states;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
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

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
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

	public Integer getzIndex() {
		return zIndex;
	}

	public void setzIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = this.initLine();

		pageContext.setAttribute(((ChartTag) chartTag).getId(), chartObj);

		return EVAL_BODY_AGAIN;
	}

	private HighChart initLine() {
		Object chartTag = super.getParent();
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		chartObj.getPlotOptions().getSeries()
				.setAllowPointSelect(this.getAllowPointSelect());
		chartObj.getPlotOptions().getSeries().setAnimation(this.getAnimation());
		chartObj.getPlotOptions().getSeries().setColor(this.getColor());
		chartObj.getPlotOptions().getSeries()
				.setConnectEnds(this.getConnectEnds());
		chartObj.getPlotOptions().getSeries()
				.setConnectNulls(this.getConnectNulls());
		chartObj.getPlotOptions().getSeries()
				.setCropThreshold(this.getCropThreshold());
		chartObj.getPlotOptions().getSeries().setCursor(this.getCursor());
		chartObj.getPlotOptions().getSeries().setDashStyle(this.getDashStyle());
		chartObj.getPlotOptions().getSeries()
				.setEnableMouseTracking(this.getEnableMouseTracking());
		if (null != events && !"".equals(events)) {
			if (events.indexOf("/") > 0) {
				String[] eventss = this.events.split("/");
				for (int i = 0; i < eventss.length; i++) {
					String[] eventsss = eventss[i].split(":");
					chartObj.getPlotOptions().getSeries()
							.setEvents(eventsss[0], eventsss[1]);
				}
			} else {
				String[] eventss = events.split(":");
				chartObj.getPlotOptions().getSeries()
						.setEvents(eventss[0], eventss[1]);
			}
		}
		chartObj.getPlotOptions().getSeries().setId(this.getId());
		chartObj.getPlotOptions().getSeries().setLineWidth(this.getLineWidth());
		chartObj.getPlotOptions().getSeries().setLinkedTo(this.getLinkedTo());
		chartObj.getPlotOptions().getSeries()
				.setNegativeColor(this.getNegativeColor());
		chartObj.getPlotOptions().getSeries()
				.setPointInterval(this.getPointInterval());
		chartObj.getPlotOptions().getSeries()
				.setPointPlacement(this.getPointPlacement());
		chartObj.getPlotOptions().getSeries()
				.setPointStart(this.getPointStart());
		chartObj.getPlotOptions().getSeries().setSelected(this.getSelected());
		chartObj.getPlotOptions().getSeries().setShadow(this.getShadow());
		chartObj.getPlotOptions().getSeries()
				.setShowCheckbox(this.getShowCheckbox());
		chartObj.getPlotOptions().getSeries()
				.setShowInLegend(this.getShowInLegend());
		chartObj.getPlotOptions().getSeries().setStacking(this.getStacking());
		chartObj.getPlotOptions().getSeries().setStep(this.getStep());
		chartObj.getPlotOptions().getSeries()
				.setStickyTracking(this.getStickyTracking());
		chartObj.getPlotOptions().getSeries().setThreshold(this.getThreshold());
		chartObj.getPlotOptions().getSeries()
				.setTurboThreshold(this.getTurboThreshold());
		chartObj.getPlotOptions().getSeries().setVisible(this.getVisible());
		chartObj.getPlotOptions().getSeries().setzIndex(this.getzIndex());

		chartObj.getPlotOptions().getSeries().setBorderColor(this.borderColor);
		chartObj.getPlotOptions().getSeries().setBorderRadius(this.borderRadius);
		chartObj.getPlotOptions().getSeries().setBorderWidth(this.borderWidth);
		chartObj.getPlotOptions().getSeries().setColorByPoint(this.colorByPoint);
		chartObj.getPlotOptions().getSeries().setColors(this.colors);
		chartObj.getPlotOptions().getSeries().setFillColor(this.fillColor);
		chartObj.getPlotOptions().getSeries().setFillOpacity(this.fillOpacity);
		chartObj.getPlotOptions().getSeries().setGrouping(this.grouping);
		chartObj.getPlotOptions().getSeries().setGroupPadding(this.groupPadding);
		chartObj.getPlotOptions().getSeries().setLineColor(this.lineColor);
		chartObj.getPlotOptions().getSeries().setMinPointLength(this.minPointLength);
		chartObj.getPlotOptions().getSeries()
				.setNegativeFillColor(this.negativeFillColor);
		chartObj.getPlotOptions().getSeries().setPointPadding(this.pointPadding);
		chartObj.getPlotOptions().getSeries().setPointRange(this.pointRange);
		chartObj.getPlotOptions().getSeries().setPointWidth(this.pointWidth);
		chartObj.getPlotOptions().getSeries().setStemColor(this.stemColor);
		chartObj.getPlotOptions().getSeries().setStemDashStyle(this.stemDashStyle);
		chartObj.getPlotOptions().getSeries().setStemWidth(this.stemWidth);
		chartObj.getPlotOptions().getSeries().setWhiskerColor(this.whiskerColor);
		chartObj.getPlotOptions().getSeries().setWhiskerLength(this.whiskerLength);
		chartObj.getPlotOptions().getSeries().setWhiskerWidth(this.whiskerWidth);

		return chartObj;

	}

	public int doEndTag() throws JspException {

		release();
		return EVAL_PAGE;
	}

	public void release() {

		super.release();
	}

}
