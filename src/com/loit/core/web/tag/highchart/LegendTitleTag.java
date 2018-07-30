package com.loit.core.web.tag.highchart;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;

/**
 * 
 * @author Administrator 只能使用text，style属性
 */
public class LegendTitleTag extends ChartTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String style;// null
	private String text;// Chart title

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

		chartObj.getLegend().getTitle().setText(this.text);
		if (style != null && !style.equals("")) {
			if (style.indexOf(",") > 0) {
				String[] styles = this.style.split(",");
				for (int i = 0; i < styles.length; i++) {
					String[] styless = styles[i].split(":");
					chartObj.getLegend().getTitle().setStyle(styless[0], styless[1]);
				}
			} else {
				String[] styles = style.split(":");
				chartObj.getLegend().getTitle().setStyle(styles[0], styles[1]);
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
