package com.loit.core.web.tag.highchart.axis;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.Axis;
import com.loit.core.web.chart.HighChart;
import com.loit.core.web.tag.highchart.ChartTag;


public class AxisLabelsTag extends AxisTag{

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private Boolean enabled;// true
	private String format;// {value}
	private String formatter;// null
	private String overflow;// null
	private String staggerLines;// null
	private String step;// null
	private Boolean useHTML;// false
	private Integer zIndex;// 7
	private String align;// center
	private Integer rotation;// 0
	private String style;// null
	private Integer x;// null
	private Integer y;// null

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
	public String getOverflow() {
		return overflow;
	}
	public void setOverflow(String overflow) {
		this.overflow = overflow;
	}

	public String getStaggerLines() {
		return staggerLines;
	}
	public void setStaggerLines(String staggerLines) {
		this.staggerLines = staggerLines;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}

	public Boolean getUseHTML() {
		return useHTML;
	}
	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
	}
	
	public Integer getzIndex() {
		return zIndex;
	}
	public void setzIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}
	
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Integer getRotation() {
		return rotation;
	}
	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
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
	
	public int doStartTag() throws JspException {
		Object chartTag = super.getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());
		Axis axis = chartObj.getyAxis();
		if(((AxisTag) this.getParent()).getAxisType()=="xAxis"){
			axis = chartObj.getxAxis();
		}
		
		axis.getLabels().setAlign(this.align);
		axis.getLabels().setEnabled(this.enabled);
		axis.getLabels().setFormat(this.format);
		axis.getLabels().setFormatter(this.formatter);
		axis.getLabels().setOverflow(this.overflow);
		axis.getLabels().setRotation(this.rotation);
		axis.getLabels().setStaggerLines(this.staggerLines);
		axis.getLabels().setStep(this.step);
		if (null != style && !"".equals(style)) {
			if (style.indexOf(";") > 0) {
				String[] styles = this.style.split(";");
				for (int i = 0; i < styles.length; i++) {
					String[] styless = styles[i].split(":");
					axis.getLabels().setStyle(styless[0], styless[1]);
				}
			} else {
				String[] styles = style.split(":");
				axis.getLabels().setStyle(styles[0], styles[1]);
			}
		}
		axis.getLabels().setUseHTML(this.useHTML);
		axis.getLabels().setX(this.x);
		axis.getLabels().setY(this.y);
		axis.getLabels().setzIndex(this.zIndex);
		

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
