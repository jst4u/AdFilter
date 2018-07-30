package com.loit.core.web.tag.highchart.axis;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.Axis;
import com.loit.core.web.chart.HighChart;
import com.loit.core.web.tag.highchart.ChartTag;

public  class AxisTitleTag extends AxisTag{

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private String align;// center       
	private Integer axisMargin;// null
	private Integer offset;// null 
	private Integer rotation;// 0 
	private String  style ;// {color: '#6D869F',fontWeight: 'bold'}       
	private String text;// Chart title       
	
	
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Integer getAxisMargin() {
		return axisMargin;
	}
	public void setAxisMargin(Integer axisMargin) {
		this.axisMargin = axisMargin;
	}
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
		
		axis.getTitle().setAlign(this.align);
		axis.getTitle().setMargin(this.axisMargin);
		axis.getTitle().setOffset(this.offset);
		axis.getTitle().setRotation(this.rotation);
		if (null != style && !"".equals(style)) {
			if (style.indexOf(",") > 0) {
				String[] styles = this.style.split(",");
				for (int i = 0; i < styles.length; i++) {
					String[] styless = styles[i].split(":");
					axis.getTitle().setStyle(styless[0], styless[1]);
				}
			} else {
				String[] styles = style.split(":");
				axis.getTitle().setStyle(styles[0], styles[1]);
			}
		}
		axis.getTitle().setText(this.text);

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
