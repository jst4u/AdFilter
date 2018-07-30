package com.loit.core.web.tag.highchart.plotoptions;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;
import com.loit.core.web.tag.highchart.ChartTag;

public class MarkerStatesTag extends MarkerTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private String hover;
	private String select;

	public String getHover() {
		return hover;
	}

	public void setHover(String hover) {
		this.hover = hover;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent().getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		if (hover != null && !hover.equals("")) {
			if(hover.indexOf(",")>0){
			String[] hovers = this.hover.split(",");
			for (int i = 0; i < hovers.length; i++) {
				String[] hover = hovers[i].split(":");
				chartObj.getPlotOptions().getSeries().getMarker().getStates()
						.setHover(hover[0], hover[1]);
			}
			}else{
				String[] hovers = hover.split(":");
				chartObj.getPlotOptions().getSeries().getMarker().getStates()
						.setHover(hovers[0], hovers[1]);
			}
		}
		if (select != null && !select.equals("")) {
			if(select.indexOf(",")>0){
				String[] selects = this.select.split(",");
				for (int i = 0; i < selects.length; i++) {
					String[] select = selects[i].split(":");
					chartObj.getPlotOptions().getSeries().getMarker().getStates()
							.setSelect(select[0], select[1]);
				}
				}else{
					String[] selects = select.split(":");
					chartObj.getPlotOptions().getSeries().getMarker().getStates()
							.setSelect(selects[0], selects[1]);
				}
		}

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
