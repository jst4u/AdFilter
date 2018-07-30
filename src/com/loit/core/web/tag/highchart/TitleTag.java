package com.loit.core.web.tag.highchart;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;

public class TitleTag extends ChartTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;

	private String align;// center
	private Boolean floating;// false
	private Integer titleMargin;// 15
	private String style;// {color: '#3E576F',fontSize: '16px'}
	private String text;// Chart title
	private Boolean useHTML;// false
	private String verticalAlign;//
	private Integer x;// 0
	private Integer y;// 15

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Boolean getFloating() {
		return floating;
	}

	public void setFloating(Boolean floating) {
		this.floating = floating;
	}

	public Integer getTitleMargin() {
		return titleMargin;
	}

	public void setTitleMargin(Integer titleMargin) {
		this.titleMargin = titleMargin;
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

	public Boolean getUseHTML() {
		return useHTML;
	}

	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
	}

	public String getVerticalAlign() {
		return verticalAlign;
	}

	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
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
		Object chartTag = super.getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		chartObj.getTitle().setAlign(this.align);
		chartObj.getTitle().setFloating(this.floating);
		chartObj.getTitle().setMargin(this.titleMargin);
		if (style != null && !style.equals("")) {
			if (style.indexOf(",") > 0) {
				String[] styles = this.style.split(",");
				for (int i = 0; i < styles.length; i++) {
					String[] styless = styles[i].split(":");
					chartObj.getTitle().setStyle(styless[0], styless[1]);
				}
			} else {
				String[] styles = style.split(":");
				chartObj.getTitle().setStyle(styles[0], styles[1]);
			}
		}
		chartObj.getTitle().setText(this.text);
		chartObj.getTitle().setUseHTML(this.useHTML);
		chartObj.getTitle().setVerticalAlign(this.verticalAlign);
		chartObj.getTitle().setX(this.x);
		chartObj.getTitle().setY(this.y);

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
