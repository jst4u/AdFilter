package com.loit.core.web.tag.highchart.plotoptions;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;
import com.loit.core.web.tag.highchart.ChartTag;

public class MarkerTag extends PlotOptionsTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private Boolean enabled;// true
	private String fillColor;// null
	private String lineColor;// #FFFFFF
	private Integer lineWidth;// 0
	private Integer radius;// 0
	private String symbol;// null

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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = this.initMarker();

		pageContext.setAttribute(((ChartTag) chartTag).getId(), chartObj);

		return EVAL_BODY_AGAIN;
	}

	private HighChart initMarker() {
		Object chartTag = super.getParent().getParent();
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		chartObj.getPlotOptions().getSeries().getMarker()
				.setEnabled(this.getEnabled());
		chartObj.getPlotOptions().getSeries().getMarker()
				.setFillColor(this.getFillColor());
		chartObj.getPlotOptions().getSeries().getMarker()
				.setLineColor(this.getLineColor());
		chartObj.getPlotOptions().getSeries().getMarker()
				.setLineWidth(this.getLineWidth());
		chartObj.getPlotOptions().getSeries().getMarker()
				.setRadius(this.getRadius());
		chartObj.getPlotOptions().getSeries().getMarker()
				.setSymbol(this.getSymbol());

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
