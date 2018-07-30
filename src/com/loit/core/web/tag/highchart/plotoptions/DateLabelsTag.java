package com.loit.core.web.tag.highchart.plotoptions;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;
import com.loit.core.web.tag.highchart.ChartTag;

public class DateLabelsTag extends ChartTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;
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
	private String style;// null
	private Boolean useHTML;// false
	private String verticalAlign;// null
	private Integer x;// 0
	private Integer y;// -6
	private Integer xHigh;// 0 arearange ,areasplinerange,columnrange
	private Integer xLow;// 0 arearange
	private Integer yHigh;// -6 arearange
	private Integer yLow;// 16 arearange
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
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

	public Integer getzIndex() {
		return zIndex;
	}

	public void setzIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = this.initDateLabels();

		pageContext.setAttribute(((ChartTag) chartTag).getId(), chartObj);

		return EVAL_BODY_AGAIN;
	}

	private HighChart initDateLabels() {
		Object chartTag = super.getParent().getParent();
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setAlign(this.align);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setBackgroundColor(this.backgroundColor);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setBorderColor(this.borderColor);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setBorderRadius(this.borderRadius);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setBorderWidth(this.borderWidth);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setColor(this.color);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setCrop(this.crop);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setEnabled(this.enabled);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setFormat(this.format);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setFormatter(this.formatter);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setInside(this.inside);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setMaxStaggerLines(this.maxStaggerLines);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setPadding(this.padding);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setRotation(this.rotation);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setShadow(this.shadow);
		if (style != null && !style.equals("")) {
			if (style.indexOf(";") > 0) {
				String[] styles = this.style.split(";");
				for (int i = 0; i < styles.length; i++) {
					String[] styless = styles[i].split(":");
					chartObj.getPlotOptions().getSeries().getDataLabels()
							.setStyle(styless[0], styless[1]);
				}
			} else {
				String[] styles = style.split(":");
				chartObj.getPlotOptions().getSeries().getDataLabels()
						.setStyle(styles[0], styles[1]);
			}
		}

		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setUseHTML(this.useHTML);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setVerticalAlign(this.verticalAlign);
		chartObj.getPlotOptions().getSeries().getDataLabels().setX(this.x);
		chartObj.getPlotOptions().getSeries().getDataLabels().setY(this.y);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setzIndex(this.zIndex);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setxHigh(this.xHigh);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setxLow(this.xLow);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setyHigh(this.yHigh);
		chartObj.getPlotOptions().getSeries().getDataLabels()
				.setyLow(this.yLow);
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
