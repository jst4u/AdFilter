package com.loit.core.web.tag.highchart.axis;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.Axis;
import com.loit.core.web.chart.HighChart;
import com.loit.core.web.chart.PlotBands;
import com.loit.core.web.tag.highchart.ChartTag;

public class PlotBandsTag extends AxisTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private String color;// null
	private String from;//
	private String to;//
	private String events;// null
	private String id;// null
	private String zIndex;// null
	
	public PlotBandsTag(){
		setLableType("Bands");
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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
/**
 * 初始化Axis
 * @return
 */
	private HighChart initAxis() {
		Object chartTag = super.getParent().getParent();
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());
		Axis axis = chartObj.getyAxis();
		if (((AxisTag) this.getParent()).getAxisType() == "xAxis") {
			axis = chartObj.getxAxis();
		}
	/**
	 * 解析字符串color,from,if,to,zIndes	
	 * 
	 */
		
		String [] colors = null == this.color ? null : this.color.split(";");
		String []froms = null == this.from ? null : this.from.split(";");
		String []ids =  null == this.id ? null : this.id.split(";");
		String []tos = null == this.to ? null : this.to.split(";");
		String []zIndexs =  null == this.zIndex ? null : this.zIndex.split(";");
		/**
		 * 设置size为from的长度
		 * color,from,if,to,zIndes赋值时需一一对应
		 */
		int size = 0;
		if(null!=froms){
			 size = froms.length;
		}
		/**
		 * 遍历PlotBands并为其设置值
		 * 
		 */
		for (int i=0;i<size;i++) {
			PlotBands plotBand = new PlotBands();
			if(null!=colors) plotBand.setColor(colors[i]);
//			plotBand.setEvents(this.events);
			if(null!=froms) plotBand.setFrom(Integer.valueOf(froms[i]));
			if(null!=ids) plotBand.setId(ids[i]);
			if(null!=tos) plotBand.setTo(Integer.valueOf(tos[i]));
			if(null!=zIndexs) plotBand.setzIndex(zIndexs[i]);
			axis.getPlotBands().add(plotBand);
		}

		return chartObj;

	}


}
