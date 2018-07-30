package com.loit.core.web.tag.highchart.plotoptions;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;
import com.loit.core.web.tag.highchart.ChartTag;


public class TooltipTag extends PlotOptionsTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;

	private String dateTimeLabelFormats;//
	private Boolean followPointer;// false  
	private Boolean followTouchMove;// false
	private String footerFormat;// false   
	private String headerFormat;//         
	private Integer hideDelay;// 500        
	private String pointFormat;// null     
	private Integer valueDecimals;// null   
	private String valuePrefix;// null     
	private String valueSuffix;// null     
	private String xDateFormat;// null   
	
	
	public String getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}
	public void setDateTimeLabelFormats(String dateTimeLabelFormats) {
		this.dateTimeLabelFormats = dateTimeLabelFormats;
	}
	public Boolean getFollowPointer() {
		return followPointer;
	}
	public void setFollowPointer(Boolean followPointer) {
		this.followPointer = followPointer;
	}
	public Boolean getFollowTouchMove() {
		return followTouchMove;
	}
	public void setFollowTouchMove(Boolean followTouchMove) {
		this.followTouchMove = followTouchMove;
	}
	public String getFooterFormat() {
		return footerFormat;
	}
	public void setFooterFormat(String footerFormat) {
		this.footerFormat = footerFormat;
	}
	public String getHeaderFormat() {
		return headerFormat;
	}
	public void setHeaderFormat(String headerFormat) {
		this.headerFormat = headerFormat;
	}
	public Integer getHideDelay() {
		return hideDelay;
	}
	public void setHideDelay(Integer hideDelay) {
		this.hideDelay = hideDelay;
	}
	public String getPointFormat() {
		return pointFormat;
	}
	public void setPointFormat(String pointFormat) {
		this.pointFormat = pointFormat;
	}
	public Integer getValueDecimals() {
		return valueDecimals;
	}
	public void setValueDecimals(Integer valueDecimals) {
		this.valueDecimals = valueDecimals;
	}
	public String getValuePrefix() {
		return valuePrefix;
	}
	public void setValuePrefix(String valuePrefix) {
		this.valuePrefix = valuePrefix;
	}
	public String getValueSuffix() {
		return valueSuffix;
	}
	public void setValueSuffix(String valueSuffix) {
		this.valueSuffix = valueSuffix;
	}
	public String getxDateFormat() {
		return xDateFormat;
	}
	public void setxDateFormat(String xDateFormat) {
		this.xDateFormat = xDateFormat;
	}
	
	public int doStartTag() throws JspException {
		Object chartTag = super.getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = this.initTooltip();

		pageContext.setAttribute(((ChartTag) chartTag).getId(), chartObj);

		return EVAL_BODY_AGAIN;
	}

	private HighChart initTooltip() {
		Object chartTag = super.getParent().getParent();
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		if (dateTimeLabelFormats != null && !dateTimeLabelFormats.equals("")) {
			if (dateTimeLabelFormats.indexOf(";") > 0) {
				String[] dateTimeLabelFormatss = this.dateTimeLabelFormats.split(";");
				for (int i = 0; i < dateTimeLabelFormatss.length; i++) {
					String[] dateTimeLabelFormat = dateTimeLabelFormatss[i].split(":");
					chartObj.getPlotOptions().getSeries().getTooltip()
							.setDateTimeLabelFormats(dateTimeLabelFormat[0], dateTimeLabelFormat[1]);
				}
			} else {
				String[] dateTimeLabelFormat = dateTimeLabelFormats.split(":");
				chartObj.getPlotOptions().getSeries().getTooltip()
						.setDateTimeLabelFormats(dateTimeLabelFormat[0], dateTimeLabelFormat[1]);
			}
		}
		chartObj.getPlotOptions().getSeries().getTooltip().setFollowPointer(this.getFollowPointer());
		chartObj.getPlotOptions().getSeries().getTooltip().setFollowTouchMove(this.getFollowTouchMove());
		chartObj.getPlotOptions().getSeries().getTooltip().setFooterFormat(this.getFooterFormat());
		chartObj.getPlotOptions().getSeries().getTooltip().setHeaderFormat(this.getHeaderFormat());
		chartObj.getPlotOptions().getSeries().getTooltip().setHideDelay(this.getHideDelay());
		chartObj.getPlotOptions().getSeries().getTooltip().setPointFormat(this.getPointFormat());
		chartObj.getPlotOptions().getSeries().getTooltip().setValueDecimals(this.getValueDecimals());
		chartObj.getPlotOptions().getSeries().getTooltip().setValuePrefix(this.getValuePrefix());
		chartObj.getPlotOptions().getSeries().getTooltip().setValueSuffix(this.getValueSuffix());
		chartObj.getPlotOptions().getSeries().getTooltip().setxDateFormat(this.getxDateFormat());

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
