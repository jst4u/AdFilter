package com.loit.core.web.tag.highchart;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.Background;
import com.loit.core.web.chart.HighChart;

public class BackgroundTag extends ChartTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private String backgroundColor;// #DDD
	private String bgBorderWidth;// 0
	private String borderColor;// 0
	private String outerRadius;// 105%
	private String innerRadius;// 103%

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBgBorderWidth() {
		return bgBorderWidth;
	}

	public void setBgBorderWidth(String bgBorderWidth) {
		this.bgBorderWidth = bgBorderWidth;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getOuterRadius() {
		return outerRadius;
	}

	public void setOuterRadius(String outerRadius) {
		this.outerRadius = outerRadius;
	}

	public String getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(String innerRadius) {
		this.innerRadius = innerRadius;
	}

	public int doStartTag1() throws JspException {
		

		return EVAL_BODY_AGAIN;
		
	}
	public int doStartTag() throws JspException {
		Object chartTag = super.getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = this.initPane();

		pageContext.setAttribute(((ChartTag) chartTag).getId(), chartObj);

		return EVAL_BODY_AGAIN;
	}
/**
 * 初始化Axis
 * @return
 */
	private HighChart initPane() {
		Object chartTag = super.getParent().getParent();
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());
	/**
	 * 解析字符串color,from,if,to,zIndes	
	 * 
	 */
		
//		String [] backgroundColors = null == this.backgroundColor ? null : this.backgroundColor.split("-");
		String [] backgroundColors = null == this.backgroundColor ? null : this.backgroundColor.split(";");
		String [] borderWidths = null == this.bgBorderWidth ? null : this.bgBorderWidth.split(";");
		String [] borderColors = null == this.borderColor ? null : this.borderColor.split(";");
		String [] outerRadiuss =  null == this.outerRadius ? null : this.outerRadius.split(";");
		String [] innerRadiuss = null == this.innerRadius ? null : this.innerRadius.split(";");
		/**
		 * 设置size为from的长度
		 * color,from,if,to,zIndes赋值时需一一对应
		 */
		int size = 0;
		if(null!=borderWidths){
			 size = borderWidths.length;
		}
		//TODO backgroundColor标签还有问题，设置不上，第一个可以第二个就会出错（  " linearGradient":"{ x1: 0, y1: 0, x2: 0, y2: 1 }"×, "linearGradient": { x1: 0, y1: 0, x2: 0, y2: 1 }√）；不能设置只有颜色的：backgroundColor: '#DDD',
		for (int i=0;i<size;i++) {
			Background background = new Background();
			if(null!=backgroundColors) background.setBackgroundColor(backgroundColors[i]);

//			if (backgroundColors[i] != null && !backgroundColors[i].equals("")) {
//				if (backgroundColors[i].indexOf(";") > 0) {
//					String[] backgroundColorss = backgroundColors[i].split(";");
//					for (int j = 0; j < backgroundColorss.length; j++) {
//						String[] backgroundColorsss = backgroundColorss[j].split("/");
//						background.setBackgroundColor(backgroundColorsss[0], backgroundColorsss[1]);
//					}
//				} else {
//					String[] backgroundColorss = backgroundColors[i].split("/");
//					background.setBackgroundColor(backgroundColorss[0], backgroundColorss[1]);
//				}
//			}
			if(null!=borderWidths) background.setBorderWidth(Integer.valueOf(borderWidths[i]));
			if(null!=borderColors) background.setBorderColor(borderColors[i]);
			if(null!=outerRadiuss) background.setOuterRadius(outerRadiuss[i]);
			if(null!=innerRadiuss) background.setInnerRadius(innerRadiuss[i]);
			chartObj.getPane().getBackground().add(background);
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
