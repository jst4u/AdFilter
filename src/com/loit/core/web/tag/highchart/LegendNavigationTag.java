package com.loit.core.web.tag.highchart;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;

public class LegendNavigationTag extends ChartTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String activeColor;// #3E576F
	private Boolean animation;// true
	private Integer arrowSize;// 12
	private String inactiveColor;// #CCC
	private String style;// null

	public String getActiveColor() {
		return activeColor;
	}

	public void setActiveColor(String activeColor) {
		this.activeColor = activeColor;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	public Integer getArrowSize() {
		return arrowSize;
	}

	public void setArrowSize(Integer arrowSize) {
		this.arrowSize = arrowSize;
	}

	public String getInactiveColor() {
		return inactiveColor;
	}

	public void setInactiveColor(String inactiveColor) {
		this.inactiveColor = inactiveColor;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int doStartTag() throws JspException {
		// Object legendTag = super.getParent();
		// if (legendTag == null || !(legendTag instanceof LegendTag)) {
		// throw new JspException("必须是legendTag!");
		// }
		//
		// HighChart chartObj = (HighChart) pageContext
		// .getAttribute(((LegendTag) legendTag).getId());
		Object chartTag = super.getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		chartObj.getLegend().getNavigation().setActiveColor(this.activeColor);
		chartObj.getLegend().getNavigation().setAnimation(this.animation);
		chartObj.getLegend().getNavigation().setArrowSize(this.arrowSize);
		chartObj.getLegend().getNavigation()
				.setInactiveColor(this.inactiveColor);
		if (style != null && !style.equals("")) {
			if (style.indexOf(",") > 0) {
				String[] styles = this.style.split(",");
				for (int i = 0; i < styles.length; i++) {
					String[] styless = styles[i].split(":");
					chartObj.getLegend().getNavigation()
					.setStyle(styless[0], styless[1]);
				}
			} else {
				String[] styles = style.split(":");
				chartObj.getLegend().getNavigation()
				.setStyle(styles[0], styles[1]);
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
