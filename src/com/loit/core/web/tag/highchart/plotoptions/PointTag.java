package com.loit.core.web.tag.highchart.plotoptions;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;
import com.loit.core.web.tag.highchart.ChartTag;

public class PointTag extends PlotOptionsTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private String events;
	
	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = this.initPoint();

		pageContext.setAttribute(((ChartTag) chartTag).getId(), chartObj);

		return EVAL_BODY_AGAIN;
	}

	private HighChart initPoint() {
		Object chartTag = super.getParent().getParent();
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		if (events != null && !events.equals("")) {
			if (events.indexOf(",") > 0) {
				String[] eventss = this.events.split(",");
				for (int i = 0; i < eventss.length; i++) {
					String[] eventsss = eventss[i].split(":");
					chartObj.getPlotOptions().getSeries().getPoint()
							.setEvents(eventsss[0], eventsss[1]);
				}
			} else {
				String[] eventss = events.split(":");
				chartObj.getPlotOptions().getSeries().getPoint()
						.setEvents(eventss[0], eventss[1]);
			}
		}

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
