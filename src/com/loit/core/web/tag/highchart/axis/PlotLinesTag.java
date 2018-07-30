package com.loit.core.web.tag.highchart.axis;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.Axis;
import com.loit.core.web.chart.HighChart;
import com.loit.core.web.chart.PlotLines;
import com.loit.core.web.tag.highchart.ChartTag;

public class PlotLinesTag extends AxisTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private String color;// null
	private String dashStyle;// Solid
	private String events;// null
	private String id;// null
	private String value;// null
	private String plotWidth;// null
	private String zIndex;// null

	public PlotLinesTag(){
		setLableType("Lines");
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDashStyle() {
		return dashStyle;
	}

	public void setDashStyle(String dashStyle) {
		this.dashStyle = dashStyle;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPlotWidth() {
		return plotWidth;
	}

	public void setPlotWidth(String plotWidth) {
		this.plotWidth = plotWidth;
	}

	public String getzIndex() {
		return zIndex;
	}

	public void setzIndex(String zIndex) {
		this.zIndex = zIndex;
	}

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = this.initAxis();

		pageContext.setAttribute(((ChartTag) chartTag).getId(), chartObj);

		return EVAL_BODY_AGAIN;
	}

	private HighChart initAxis() {
		Object chartTag = super.getParent().getParent();
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());
		Axis axis = chartObj.getyAxis();
		if(((AxisTag) this.getParent()).getAxisType()=="xAxis"){
			axis = chartObj.getxAxis();
		}
		/**
		 * 解析字符串color,dashStyle,id,value,width,zIndes	
		 * 
		 */
			
			String [] colors = null == this.color ? null : this.color.split(";");
			String []dashStyles = null == this.dashStyle ? null : this.dashStyle.split(";");
			String []ids =  null == this.id ? null : this.id.split(";");
			String []values = null == this.value ? null : this.value.split(";");
			String []widths =  null == this.plotWidth ? null : this.plotWidth.split(";");
			String []zIndexs =  null == this.zIndex ? null : this.zIndex.split(";");
			/**
			 * 设置size为color的长度
			 * color,dashStyle,id,value,width,zIndes赋值时需一一对应
			 */
			int size = 0;
			if(null!=colors){
				 size = colors.length;
			}
			/**
			 * 遍历PlotLines并为其设置值
			 * 
			 */
			for (int i=0;i<size;i++) {
				PlotLines plotLine = new PlotLines();
				if(null!=colors) plotLine.setColor(colors[i]);
//				plotLine.setEvents(this.events);
				if(null!=dashStyles) plotLine.setDashStyle(dashStyles[i]);
				if(null!=ids) plotLine.setId(ids[i]);
				if(null!=values) plotLine.setValue((Integer.valueOf(values[i])));
				if(null!=widths) plotLine.setWidth((Integer.valueOf(widths[i])));
				if(null!=zIndexs) plotLine.setzIndex((Integer.valueOf(zIndexs[i])));
				axis.getPlotLines().add(plotLine);
			}
		
		return chartObj;

	}
	
}
