package com.loit.core.web.tag.highchart.axis;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.Axis;
import com.loit.core.web.chart.HighChart;
import com.loit.core.web.chart.PlotBands;
import com.loit.core.web.chart.PlotLines;
import com.loit.core.web.tag.highchart.ChartTag;

public class PoltLabelTag extends AxisTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;
	private String align;// center
	private String rotation;// 0
	private String style;// null
	private String text;//
	private String textAlign;// null
	private String verticalAlign;// top
	private String x;// null
	private String y;// null

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getRotation() {
		return rotation;
	}

	public void setRotation(String rotation) {
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

	public String getTextAlign() {
		return textAlign;
	}

	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
	}

	public String getVerticalAlign() {
		return verticalAlign;
	}

	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent().getParent().getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());
		Axis axis = chartObj.getyAxis();
		if (((AxisTag) this.getParent().getParent()).getAxisType() == "xAxis") {
			axis = chartObj.getxAxis();
		}
		List plot = new ArrayList<>();
		plot = axis.getPlotBands();
		if(((AxisTag) this.getParent()).getLableType()=="Lines"){
			plot = axis.getPlotLines();
			
		}
		
		/**
		 * 新建数组aligns长度为size 若align不为空，以";"分割字符串align为数组a 遍历PlotBands并为其赋值
		 * 
		 */
		// 每个属性都有for循环进行赋值，比较麻烦
		int size = plot.size();
		int j = 0;
		String[] aligns = new String[size];
		String[] align = null == this.align ? null : this.align.split(";");
		while(null != align && j < align.length){
			aligns[j] = align[j];
			j++;
		}
		j = 0;
		String[] texts = new String[size];
		String[] text = null == this.text ? null : this.text.split(";");
		while(null != text && j < text.length){
			texts[j] = text[j];
			j++;
		}
		j = 0;
		String[] rotations = new String[size];
		String[] rotation = null == this.rotation ? null : this.rotation.split(";");
		while(null != rotation && j < rotation.length){
			rotations[j] = rotation[j];
			j++;
		}
		j = 0;
		String[] styles = new String[size];
		String[] style = null == this.style ? null : this.style.split(";");
		while(null != style && j < style.length){
			styles[j] = style[j];
			j++;
		}
		j = 0;
		String[] textAligns = new String[size];
		String[] textAlign = null == this.textAlign ? null : this.textAlign.split(";");
		while(null != textAlign && j < textAlign.length){
			textAligns[j] = textAlign[j];
			j++;
		}
		j = 0;
		String[] xs = new String[size];
		String[] x = null == this.x ? null : this.x.split(";");
		while(null != x && j < x.length){
			xs[j] = x[j];
			j++;
		}
		j = 0;
		String[] ys = new String[size];
		String[] y = null == this.y ? null : this.y.split(";");
		while(null != y && j < y.length){
			ys[j] = y[j];
			j++;
		}
		
		if(((AxisTag) this.getParent()).getLableType()=="Lines"){
			for (int i = 0; i < size; i++) {
				PlotLines plotLines = axis.getPlotLines().get(i);
				if (null != aligns[i])
					plotLines.getLabel().setAlign(aligns[i]);
				// plotLines.getLabel().setRotation(this.rotation);
				if (null != rotations[i]) plotLines.getLabel().setRotation(Integer.valueOf(rotations[i]));
				String sty = styles[i];
				if (null != sty && !"".equals(sty)) {
					if (sty.indexOf(",") > 0) {
						String[] stys = sty.split(",");
						for (int m = 0; m < stys.length; m++) {
							String[] styss = stys[m].split(":");
							plotLines.getLabel().setStyle(styss[0], styss[1]);
						}
					} else {
						String[] stys = sty.split(":");
						plotLines.getLabel().setStyle(stys[0], stys[1]);
					}
				}

				if (null != texts[i]) plotLines.getLabel().setText(texts[i]);
				if (null != textAligns[i]) plotLines.getLabel().setTextAlign(textAligns[i]);
				if (null != xs[i]) plotLines.getLabel().setX(Integer.valueOf(xs[i]));
				if (null != ys[i]) plotLines.getLabel().setY(Integer.valueOf(ys[i]));
			}
		}else{
			for (int i = 0; i < size; i++) {
				PlotBands plotBand = axis.getPlotBands().get(i);
				if (null != aligns[i])
					plotBand.getLabel().setAlign(aligns[i]);
				// plotBand.getLabel().setRotation(this.rotation);
				if (null != rotations[i]) plotBand.getLabel().setRotation(Integer.valueOf(rotations[i]));
				String sty = styles[i];
				if (null != sty && !"".equals(sty)) {
					if (sty.indexOf(",") > 0) {
						String[] stys = sty.split(",");
						for (int m = 0; m < stys.length; m++) {
							String[] styss = stys[m].split(":");
							plotBand.getLabel().setStyle(styss[0], styss[1]);
						}
					} else {
						String[] stys = sty.split(":");
						plotBand.getLabel().setStyle(stys[0], stys[1]);
					}
				}

				if (null != texts[i]) plotBand.getLabel().setText(texts[i]);
				if (null != textAligns[i]) plotBand.getLabel().setTextAlign(textAligns[i]);
				if (null != xs[i]) plotBand.getLabel().setX(Integer.valueOf(xs[i]));
				if (null != ys[i]) plotBand.getLabel().setY(Integer.valueOf(ys[i]));
			}
		}
		return EVAL_BODY_AGAIN;
	}

}
