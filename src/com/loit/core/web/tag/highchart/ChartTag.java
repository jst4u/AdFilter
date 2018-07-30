package com.loit.core.web.tag.highchart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.loit.core.web.chart.HighChart;
import com.loit.core.web.chart.Series;
import com.loit.core.web.tag.easyui.BaseHtmlTag;

public class ChartTag extends BaseHtmlTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 标签属性
	private String id;
	private String type;// 图形类型
	private String title = "";
	private String subtitle = "";
	private Integer width;
	private Integer height;

	private String xAxisType;
	private String xAxisTitle;

	private String yAxisTitle;
	private String yAxisTitleAlign;
	private Integer yAxisMin;
	private String yAxisLabelsOverflow;

	private String legendAlign;
	private String legendLayout;
	private String legendVerticalAlign;
	private String legendBackgroundColor;

	private Boolean alignTicks;// true
	private Boolean animation;// true
	private String backgroundColor;// #FFFFFF
	private String borderColor;// #4572A7
	private Integer borderRadius;// 5
	private Integer borderWidth;// 0
	private String className;// null
	private String defaultSeriesType;// line
	private String events ;// {…}
	private Boolean ignoreHiddenSeries;// true
	private Boolean inverted;// false
	private String margin;// null
	private String marginBottom;// null
	private String marginLeft;// null
	private String marginRight;// null
	private String marginTop;// null
	private String pinchType;// null
	private String plotBackgroundColor;// null
	private String plotBackgroundImage;// null
	private String plotBorderColor;// #C0C0C0
	private Integer plotBorderWidth;// 0
	private Boolean plotShadow;// false
	private Boolean polar;// false
	private Boolean reflow;// true
	private String renderTo;// null
	private String selectionMarkerFill;// rgba(69,114,167,0.25)
	private Boolean shadow;// false
	private Boolean showAxes;// false
	private Integer spacingBottom;// 15
	private Integer spacingLeft;// 10
	private Integer spacingRight;// 10
	private Integer spacingTop;// 10
	private String style;// null
	private String zoomType;// null
	
	//导出属性——exporting
	private String exportingButtons; //null
	private String exportingChartOptions; //null
	private Boolean exportingEnabled; //true
	private String exportingFilename; //chart
	private String exportingFormAttributes;
	private Integer exportingScale;// 2
	private Integer exportingSourceHeight;
	private Integer exportingSourceWidth;
	private String exportingType; //image/png
	private String exportingUrl; //http://export.highcharts.com
	private Integer exportingWidth;// undefined
	

	public String getExportingButtons() {
		return exportingButtons;
	}

	public void setExportingButtons(String exportingButtons) {
		this.exportingButtons = exportingButtons;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getxAxisType() {
		return xAxisType;
	}

	public void setxAxisType(String xAxisType) {
		this.xAxisType = xAxisType;
	}

	public String getxAxisTitle() {
		return xAxisTitle;
	}

	public void setxAxisTitle(String xAxisTitle) {
		this.xAxisTitle = xAxisTitle;
	}

	public String getyAxisTitle() {
		return yAxisTitle;
	}

	public void setyAxisTitle(String yAxisTitle) {
		this.yAxisTitle = yAxisTitle;
	}

	public String getyAxisTitleAlign() {
		return yAxisTitleAlign;
	}

	public void setyAxisTitleAlign(String yAxisTitleAlign) {
		this.yAxisTitleAlign = yAxisTitleAlign;
	}

	public Integer getyAxisMin() {
		return yAxisMin;
	}

	public void setyAxisMin(Integer yAxisMin) {
		this.yAxisMin = yAxisMin;
	}

	public String getyAxisLabelsOverflow() {
		return yAxisLabelsOverflow;
	}

	public void setyAxisLabelsOverflow(String yAxisLabelsOverflow) {
		this.yAxisLabelsOverflow = yAxisLabelsOverflow;
	}

	public String getLegendAlign() {
		return legendAlign;
	}

	public void setLegendAlign(String legendAlign) {
		this.legendAlign = legendAlign;
	}

	public String getLegendLayout() {
		return legendLayout;
	}

	public void setLegendLayout(String legendLayout) {
		this.legendLayout = legendLayout;
	}

	public String getLegendVerticalAlign() {
		return legendVerticalAlign;
	}

	public void setLegendVerticalAlign(String legendVerticalAlign) {
		this.legendVerticalAlign = legendVerticalAlign;
	}


	public String getLegendBackgroundColor() {
		return legendBackgroundColor;
	}

	public void setLegendBackgroundColor(String legendBackgroundColor) {
		this.legendBackgroundColor = legendBackgroundColor;
	}

	public Boolean getAlignTicks() {
		return alignTicks;
	}

	public void setAlignTicks(Boolean alignTicks) {
		this.alignTicks = alignTicks;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public Integer getBorderRadius() {
		return borderRadius;
	}

	public void setBorderRadius(Integer borderRadius) {
		this.borderRadius = borderRadius;
	}


	public Integer getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(Integer borderWidth) {
		this.borderWidth = borderWidth;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDefaultSeriesType() {
		return defaultSeriesType;
	}

	public void setDefaultSeriesType(String defaultSeriesType) {
		this.defaultSeriesType = defaultSeriesType;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public Boolean getIgnoreHiddenSeries() {
		return ignoreHiddenSeries;
	}

	public void setIgnoreHiddenSeries(Boolean ignoreHiddenSeries) {
		this.ignoreHiddenSeries = ignoreHiddenSeries;
	}

	public Boolean getInverted() {
		return inverted;
	}

	public void setInverted(Boolean inverted) {
		this.inverted = inverted;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public String getMarginBottom() {
		return marginBottom;
	}

	public void setMarginBottom(String marginBottom) {
		this.marginBottom = marginBottom;
	}

	public String getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(String marginLeft) {
		this.marginLeft = marginLeft;
	}

	public String getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(String marginRight) {
		this.marginRight = marginRight;
	}

	public String getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(String marginTop) {
		this.marginTop = marginTop;
	}

	public String getPinchType() {
		return pinchType;
	}

	public void setPinchType(String pinchType) {
		this.pinchType = pinchType;
	}

	public String getPlotBackgroundColor() {
		return plotBackgroundColor;
	}

	public void setPlotBackgroundColor(String plotBackgroundColor) {
		this.plotBackgroundColor = plotBackgroundColor;
	}

	public String getPlotBackgroundImage() {
		return plotBackgroundImage;
	}

	public void setPlotBackgroundImage(String plotBackgroundImage) {
		this.plotBackgroundImage = plotBackgroundImage;
	}

	public String getPlotBorderColor() {
		return plotBorderColor;
	}

	public void setPlotBorderColor(String plotBorderColor) {
		this.plotBorderColor = plotBorderColor;
	}

	public Integer getPlotBorderWidth() {
		return plotBorderWidth;
	}

	public void setPlotBorderWidth(Integer plotBorderWidth) {
		this.plotBorderWidth = plotBorderWidth;
	}

	public Boolean getPlotShadow() {
		return plotShadow;
	}

	public void setPlotShadow(Boolean plotShadow) {
		this.plotShadow = plotShadow;
	}

	public Boolean getPolar() {
		return polar;
	}

	public void setPolar(Boolean polar) {
		this.polar = polar;
	}

	public Boolean getReflow() {
		return reflow;
	}

	public void setReflow(Boolean reflow) {
		this.reflow = reflow;
	}

	public String getRenderTo() {
		return renderTo;
	}

	public void setRenderTo(String renderTo) {
		this.renderTo = renderTo;
	}

	public String getSelectionMarkerFill() {
		return selectionMarkerFill;
	}

	public void setSelectionMarkerFill(String selectionMarkerFill) {
		this.selectionMarkerFill = selectionMarkerFill;
	}

	public Boolean getShadow() {
		return shadow;
	}

	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public Boolean getShowAxes() {
		return showAxes;
	}

	public void setShowAxes(Boolean showAxes) {
		this.showAxes = showAxes;
	}

	public Integer getSpacingBottom() {
		return spacingBottom;
	}

	public void setSpacingBottom(Integer spacingBottom) {
		this.spacingBottom = spacingBottom;
	}

	public Integer getSpacingLeft() {
		return spacingLeft;
	}

	public void setSpacingLeft(Integer spacingLeft) {
		this.spacingLeft = spacingLeft;
	}

	public Integer getSpacingRight() {
		return spacingRight;
	}

	public void setSpacingRight(Integer spacingRight) {
		this.spacingRight = spacingRight;
	}

	public Integer getSpacingTop() {
		return spacingTop;
	}

	public void setSpacingTop(Integer spacingTop) {
		this.spacingTop = spacingTop;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getZoomType() {
		return zoomType;
	}

	public void setZoomType(String zoomType) {
		this.zoomType = zoomType;
	}

	public String getExportingChartOptions() {
		return exportingChartOptions;
	}

	public void setExportingChartOptions(String exportingChartOptions) {
		this.exportingChartOptions = exportingChartOptions;
	}

	public Boolean getExportingEnabled() {
		return exportingEnabled;
	}

	public void setExportingEnabled(Boolean exportingEnabled) {
		this.exportingEnabled = exportingEnabled;
	}

	public String getExportingFilename() {
		return exportingFilename;
	}

	public void setExportingFilename(String exportingFilename) {
		this.exportingFilename = exportingFilename;
	}

	public String getExportingFormAttributes() {
		return exportingFormAttributes;
	}

	public void setExportingFormAttributes(String exportingFormAttributes) {
		this.exportingFormAttributes = exportingFormAttributes;
	}

	public Integer getExportingScale() {
		return exportingScale;
	}

	public void setExportingScale(Integer exportingScale) {
		this.exportingScale = exportingScale;
	}

	public Integer getExportingSourceHeight() {
		return exportingSourceHeight;
	}

	public void setExportingSourceHeight(Integer exportingSourceHeight) {
		this.exportingSourceHeight = exportingSourceHeight;
	}

	public Integer getExportingSourceWidth() {
		return exportingSourceWidth;
	}

	public void setExportingSourceWidth(Integer exportingSourceWidth) {
		this.exportingSourceWidth = exportingSourceWidth;
	}

	public String getExportingType() {
		return exportingType;
	}

	public void setExportingType(String exportingType) {
		this.exportingType = exportingType;
	}

	public String getExportingUrl() {
		return exportingUrl;
	}

	public void setExportingUrl(String exportingUrl) {
		this.exportingUrl = exportingUrl;
	}

	public Integer getExportingWidth() {
		return exportingWidth;
	}

	public void setExportingWidth(Integer exportingWidth) {
		this.exportingWidth = exportingWidth;
	}

	public int doStartTag() throws JspException {
		if (null == this.id) {
			throw new JspException("如果使用图形标签，标签的id属性必须设置且唯一!");
		}
//		TODO 需要检测id唯一性
		
		HighChart chartObj = this.initChart();

		pageContext.setAttribute(this.id, chartObj);
		
		return EVAL_BODY_INCLUDE;
	}

	private HighChart initChart() {
		HighChart chartObj = new HighChart();

		chartObj.getChart().setType(this.getType());
		chartObj.getTitle().setText(this.getTitle());
		chartObj.getSubtitle().setText(this.getSubtitle());
		chartObj.getxAxis().getTitle().setText(this.getxAxisTitle());
		chartObj.getxAxis().setType(this.getxAxisType());
		chartObj.getyAxis().getTitle().setText(this.getyAxisTitle());
		chartObj.getyAxis().getTitle().setAlign(this.getyAxisTitleAlign());
		chartObj.getyAxis().setMin(this.getyAxisMin());
		chartObj.getLegend().setAlign(this.getLegendAlign());
		chartObj.getLegend().setLayout(this.getLegendLayout());
		chartObj.getLegend().setVerticalAlign(this.getLegendVerticalAlign());
		chartObj.getLegend().setBackgroundColor(this.getLegendBackgroundColor());
		
		chartObj.getChart().setAlignTicks(this.alignTicks);
		chartObj.getChart().setAnimation(this.animation);
		chartObj.getChart().setBackgroundColor(this.backgroundColor);
		chartObj.getChart().setBorderColor(this.borderColor);
		chartObj.getChart().setBorderRadius(this.borderRadius);
		chartObj.getChart().setBorderWidth(this.borderWidth);
		chartObj.getChart().setClassName(this.className);
		chartObj.getChart().setDefaultSeriesType(this.defaultSeriesType);
		if (null != this.events && !"".equals(this.events)) {
			if (this.events.indexOf(",") > 0) {
				String[] eventss = this.events.split(",");
				for (int i = 0; i < eventss.length; i++) {
					String[] eventsss = eventss[i].split(":");
					chartObj.getChart().setEvents(eventsss[0], eventsss[1]);
				}
			} else {
				String[] eventss = this.events.split(":");
				chartObj.getChart().setEvents(eventss[0], eventss[1]);
			}
		}
		chartObj.getChart().setHeight(this.height);
		chartObj.getChart().setIgnoreHiddenSeries(this.ignoreHiddenSeries);
		chartObj.getChart().setInverted(this.inverted);
		chartObj.getChart().setMargin(this.margin);
		chartObj.getChart().setMarginBottom(this.marginBottom);
		chartObj.getChart().setMarginLeft(this.marginLeft);
		chartObj.getChart().setMarginRight(this.marginRight);
		chartObj.getChart().setMarginTop(this.marginTop);
		chartObj.getChart().setPinchType(this.pinchType);
		chartObj.getChart().setPlotBackgroundColor(this.plotBackgroundColor);
		chartObj.getChart().setPlotBackgroundImage(this.plotBackgroundImage);
		chartObj.getChart().setPlotBorderColor(this.plotBorderColor);
		chartObj.getChart().setPlotBorderWidth(this.plotBorderWidth);
		chartObj.getChart().setPlotShadow(this.plotShadow);
		chartObj.getChart().setPolar(this.polar);
		chartObj.getChart().setReflow(this.reflow);
		chartObj.getChart().setRenderTo(this.renderTo);
		chartObj.getChart().setSelectionMarkerFill(this.selectionMarkerFill);
		chartObj.getChart().setShadow(this.shadow);
		chartObj.getChart().setShowAxes(this.showAxes);
		chartObj.getChart().setSpacingBottom(this.spacingBottom);
		chartObj.getChart().setSpacingLeft(this.spacingLeft);
		chartObj.getChart().setSpacingRight(this.spacingRight);
		chartObj.getChart().setSpacingTop(this.spacingTop);
		if (this.style != null && !this.style.equals("")) {
			if (this.style.indexOf(",") > 0) {
				String[] styles = this.style.split(",");
				for (int i = 0; i < styles.length; i++) {
					String[] styless = styles[i].split(":");
					chartObj.getChart().setStyle(styless[0], styless[1]);
				}
			} else {
				String[] styles = this.style.split(":");
				chartObj.getChart().setStyle(styles[0], styles[1]);
			}
		}
		chartObj.getChart().setWidth(this.width);
		chartObj.getChart().setZoomType(this.zoomType);
		//导出属性——exporting
		if (this.exportingButtons != null && !this.exportingButtons.equals("")) {
			if (this.exportingButtons.indexOf(",") > 0) {
				String[] buttons = this.exportingButtons.split(",");
				for (int i = 0; i < buttons.length; i++) {
					String[] buttonss = buttons[i].split(":");
					chartObj.getExporting().setButtons(buttonss[0], buttonss[1]);
				}
			} else {
				String[] buttons = this.exportingButtons.split(":");
				chartObj.getExporting().setButtons(buttons[0], buttons[1]);
			}
		}
		chartObj.getExporting().setChartOptions(this.exportingChartOptions);
		chartObj.getExporting().setEnabled(this.exportingEnabled);
		chartObj.getExporting().setFilename(this.exportingFilename);
		chartObj.getExporting().setFormAttributes(this.exportingFormAttributes);
		chartObj.getExporting().setScale(this.exportingScale);
		chartObj.getExporting().setSourceHeight(this.exportingSourceHeight);
		chartObj.getExporting().setSourceWidth(this.exportingSourceWidth);
		chartObj.getExporting().setType(this.exportingType);
		chartObj.getExporting().setUrl(this.exportingUrl);
		chartObj.getExporting().setWidth(this.exportingSourceWidth);
		return chartObj;
	}

	public int doEndTag() throws JspException {
		// 渲染对象占位div
		super.outHtml("<div id='" + this.id + "'></div>");
		super.outHtml("<script language=\"javascript\">");
		super.outHtml("$(function(){$('#" + this.id + "').highcharts(");
		// String chartJson = this.getChartOptScript();
		

		HighChart chartObj = (HighChart) pageContext.getAttribute(this.id);

		super.outHtml(this.getChartJson(chartObj));

		super.outHtml(");});");
		super.outHtml("</script>");

		release();
		return EVAL_PAGE;
	}

	

	private String getChartJson(HighChart chartObj) {
		JsonConfig jc = new JsonConfig();
		jc.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (null == value) {
					return true;
				}
				if (value instanceof List<?> && ((List<?>) value).size() == 0) {
					return true;
				}
				return false;
			}
		});
		String json = JSONObject.fromObject(chartObj, jc).toString();
		return json;
	}

	public void release() {
		super.release();
	}

	public static void main(String[] arg) {
		HighChart chart = new HighChart();
		chart.getChart().setType("bar");
		chart.getTitle().setText("测试title");
		chart.getSubtitle().setText("测试subtitle");
		chart.getxAxis().getTitle().setText("xAxis Title");
		chart.getxAxis().setType("category");
		chart.getyAxis().getTitle().setText("Population (millions)");
		chart.getyAxis().getTitle().setAlign("center");
		chart.getTooltip().setValueSuffix("millions");

		Series s1 = new Series();
		s1.setName("Year 1800");
		s1.getData().add(107);
		s1.getData().add(31);
		s1.getData().add(635);
		s1.getData().add(203);
		Series s2 = new Series();
		s2.setName("Year 1900");
		s2.getData().add(133);
		s2.getData().add(156);
		s2.getData().add(947);
		s2.getData().add(408);

		Series s3 = new Series();
		s3.setName("Year 2008");
		s3.getData().add(973);
		s3.getData().add(914);
		s3.getData().add(4054);
		s3.getData().add(732);

		chart.getSeries().add(s1);
		chart.getSeries().add(s2);
		chart.getSeries().add(s3);

		JsonConfig jc = new JsonConfig();
		jc.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (null == value) {
					return true;
				}
				if (value instanceof List<?> && ((List<?>) value).size() == 0) {
					return true;
				}
				return false;
			}
		});

		JSONObject jo = new JSONObject();
		String json = JSONArray.fromObject(chart, jc).toString();
		System.out.println(json);
		json = JSONObject.fromObject(chart, jc).toString();
		System.out.println(json);
	}
	
	
}
