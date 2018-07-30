package com.loit.core.web.tag.highchart;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;

public class PaneTag extends ChartTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private String background;// null
	private String center;// ["50%", "50%"]
	private Integer endAngle;// null
	private Integer startAngle;// null

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Integer getEndAngle() {
		return endAngle;
	}

	public void setEndAngle(Integer endAngle) {
		this.endAngle = endAngle;
	}

	public Integer getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(Integer startAngle) {
		this.startAngle = startAngle;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		//TODO 设置background值
//		//chartObj.getPane().setBackground();
//		if (null != background && !"".equals(background)) {
//			if (background.indexOf(";") > 0) {
//				String[] backgrounds = this.background.split(";");
//				for (int i = 0; i < backgrounds.length; i++) {
//					String[] backgroundss = backgrounds[i].split(":");
//					chartObj.getPane().setBackground(backgroundss[0], backgroundss[1]);
//				}
//			} else {
//				String[] backgrounds = background.split(":");
//				chartObj.getPane().setBackground(backgrounds[0], backgrounds[1]);
//			}
//		}	
		String[] pcenter = chartObj.getPane().getCenter();
		if (center != null && !center.equals("")) {
			if (center.indexOf(",") > 0) {
				String[] cen = this.center.split(",");	//如果有顿号则根据填写值设置如[40%,50%]
				pcenter[0] = cen[0];
				pcenter[1] = cen[1];
			} else {
				pcenter[0] = center;		//如果没有顿号则设成相同的值如[50%,50%]
				pcenter[1] = center;

			}
		}

		chartObj.getPane().setEndAngle(this.getEndAngle());
		chartObj.getPane().setStartAngle(this.getStartAngle());

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
