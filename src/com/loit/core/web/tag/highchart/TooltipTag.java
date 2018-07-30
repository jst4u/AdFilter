package com.loit.core.web.tag.highchart;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;

public class TooltipTag extends ChartTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;

	private Boolean animation;// true
	private String backgroundColor;// rgba(255, 255, 255, 0.85)
	private String borderColor;// auto
	private Integer borderRadius;// 3
	private Integer borderWidth;// 1
	private String crosshairs;// null
	private String dateTimeLabelFormats;//
	private Boolean enabled;// true
	private Boolean followPointer;// false
	private Boolean followTouchMove;// false
	private Boolean footerFormat;// false
	private String formatter;// null
	private String headerFormat;//
	private Integer hideDelay;// 500
	private String pointFormat;// null
	private String positioner;// null
	private Boolean shadow;// true
	private Boolean shared;// false
	private String snap;// null
	private String style;// null
	private Boolean useHTML;// false
	private String valueDecimals;// null
	private String valuePrefix;// null
	private String valueSuffix;// null
	private String xDateFormat;// null

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

	public String getCrosshairs() {
		return crosshairs;
	}

	public void setCrosshairs(String crosshairs) {
		this.crosshairs = crosshairs;
	}

	public String getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}

	public void setDateTimeLabelFormats(String dateTimeLabelFormats) {
		this.dateTimeLabelFormats = dateTimeLabelFormats;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public Boolean getFooterFormat() {
		return footerFormat;
	}

	public void setFooterFormat(Boolean footerFormat) {
		this.footerFormat = footerFormat;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
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

	public String getPositioner() {
		return positioner;
	}

	public void setPositioner(String positioner) {
		this.positioner = positioner;
	}

	public Boolean getShadow() {
		return shadow;
	}

	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public Boolean getShared() {
		return shared;
	}

	public void setShared(Boolean shared) {
		this.shared = shared;
	}

	public String getSnap() {
		return snap;
	}

	public void setSnap(String snap) {
		this.snap = snap;
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

	public String getValueDecimals() {
		return valueDecimals;
	}

	public void setValueDecimals(String valueDecimals) {
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
		Object chartTag = super.getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		chartObj.getTooltip().setAnimation(this.getAnimation());
		chartObj.getTooltip().setBackgroundColor(this.getBackgroundColor());
		chartObj.getTooltip().setBorderColor(this.getBorderColor());
		chartObj.getTooltip().setBorderRadius(this.getBorderRadius());
		chartObj.getTooltip().setBorderWidth(this.getBorderWidth());
		chartObj.getTooltip().setCrosshairs(this.getCrosshairs());
		if (dateTimeLabelFormats != null && !dateTimeLabelFormats.equals("")) {
			if (dateTimeLabelFormats.indexOf(",") > 0) {
				String[] dateTimeLabelFormatss = this.dateTimeLabelFormats
						.split(";");
				for (int i = 0; i < dateTimeLabelFormatss.length; i++) {
					String[] dateTimeLabelFormatsss = dateTimeLabelFormatss[i]
							.split(":");
					chartObj.getTooltip().setDateTimeLabelFormats(
							dateTimeLabelFormatsss[0],
							dateTimeLabelFormatsss[1]);
				}
			} else {
				String[] dateTimeLabelFormatss = dateTimeLabelFormats
						.split(":");
				chartObj.getTooltip().setDateTimeLabelFormats(
						dateTimeLabelFormatss[0], dateTimeLabelFormatss[1]);
			}
		}
		chartObj.getTooltip().setEnabled(this.getEnabled());
		chartObj.getTooltip().setFollowPointer(this.getFollowPointer());
		chartObj.getTooltip().setFollowTouchMove(this.getFollowTouchMove());
		chartObj.getTooltip().setFooterFormat(this.getFooterFormat());
		chartObj.getTooltip().setFormatter(this.getFormatter());
		chartObj.getTooltip().setHeaderFormat(this.getHeaderFormat());
		chartObj.getTooltip().setHideDelay(this.getHideDelay());
		chartObj.getTooltip().setPointFormat(this.getPointFormat());
		chartObj.getTooltip().setPositioner(this.getPositioner());
		chartObj.getTooltip().setShadow(this.getShadow());
		chartObj.getTooltip().setShared(this.getShared());
		chartObj.getTooltip().setSnap(this.getSnap());
		if (style != null && !style.equals("")) {
			if (style.indexOf(";") > 0) {
				String[] styles = this.style.split(",");
				for (int i = 0; i < styles.length; i++) {
					String[] styless = styles[i].split(":");
					chartObj.getTooltip().setStyle(styless[0], styless[1]);
				}
			} else {
				String[] styles = style.split(":");
				chartObj.getTooltip().setStyle(styles[0], styles[1]);
			}
		}
		chartObj.getTooltip().setUseHTML(this.getUseHTML());
		chartObj.getTooltip().setValueDecimals(this.getValueDecimals());
		chartObj.getTooltip().setValuePrefix(this.getValuePrefix());
		chartObj.getTooltip().setValueSuffix(this.getValueSuffix());
		chartObj.getTooltip().setxDateFormat(this.getxDateFormat());

		return EVAL_BODY_AGAIN;
	}

	public int doEndTag() throws JspException {
		release();
		return EVAL_PAGE;
	}

	public void release() {

		super.release();
	}
}
