package com.loit.core.web.tag.highchart;

import javax.servlet.jsp.JspException;

import com.loit.core.web.chart.HighChart;
import com.loit.core.web.chart.LegendNavigation;
import com.loit.core.web.chart.LegendTitle;

public class LegendTag extends ChartTag {

	/**
      *
      */
	private static final long serialVersionUID = 1L;

	// 标签属性
	private String id;
	private String align; // center
	private String backgroundColor; // null
	private String borderColor; // #909090
	private Integer borderRadius; // 5
	private Integer borderWidth; // 1
	private Boolean enabled; // true
	private Boolean floating; // false
	private String itemHiddenStyle; // null
	private String itemHoverStyle; // null
	private Integer itemMarginBottom; // 0
	private Integer itemMarginTop; // 0
	private String itemStyle; // null
	private String itemWidth; // null
	private String labelFormat; // {name}
	private String labelFormatter; // null
	private String layout; // horizontal
	private Integer lineHeight; // 16
	private Integer lgMargin; // 15
	private String maxHeight; // null
	private Integer padding; // 8
	private Boolean reversed; // false
	private Boolean rtl; // false
	private Boolean shadow; // false
	private String style; // null
	private Integer symbolPadding; // 5
	private Integer symbolWidth; // 30
	private Boolean useHTML; // false
	private String verticalAlign; // bottom
	private Integer width; // null
	private Integer x; // 0
	private Integer y; // 0
	private LegendNavigation navigation;// object
	private LegendTitle legendTitle;// object
	private String titleText;

	public LegendNavigation getNavigation() {
		return navigation;
	}

	public void setNavigation(LegendNavigation navigation) {
		this.navigation = navigation;
	}

	public LegendTitle getLegendTitle() {
		return legendTitle;
	}

	public void setLegendTitle(LegendTitle legendTitle) {
		this.legendTitle = legendTitle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getFloating() {
		return floating;
	}

	public void setFloating(Boolean floating) {
		this.floating = floating;
	}

	public String getItemHiddenStyle() {
		return itemHiddenStyle;
	}

	public void setItemHiddenStyle(String itemHiddenStyle) {
		this.itemHiddenStyle = itemHiddenStyle;
	}

	public String getItemHoverStyle() {
		return itemHoverStyle;
	}

	public void setItemHoverStyle(String itemHoverStyle) {
		this.itemHoverStyle = itemHoverStyle;
	}

	public Integer getItemMarginBottom() {
		return itemMarginBottom;
	}

	public void setItemMarginBottom(Integer itemMarginBottom) {
		this.itemMarginBottom = itemMarginBottom;
	}

	public Integer getItemMarginTop() {
		return itemMarginTop;
	}

	public void setItemMarginTop(Integer itemMarginTop) {
		this.itemMarginTop = itemMarginTop;
	}

	public String getItemStyle() {
		return itemStyle;
	}

	public void setItemStyle(String itemStyle) {
		this.itemStyle = itemStyle;
	}

	public String getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(String itemWidth) {
		this.itemWidth = itemWidth;
	}

	public String getLabelFormat() {
		return labelFormat;
	}

	public void setLabelFormat(String labelFormat) {
		this.labelFormat = labelFormat;
	}

	public String getLabelFormatter() {
		return labelFormatter;
	}

	public void setLabelFormatter(String labelFormatter) {
		this.labelFormatter = labelFormatter;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public Integer getLineHeight() {
		return lineHeight;
	}

	public void setLineHeight(Integer lineHeight) {
		this.lineHeight = lineHeight;
	}

	public Integer getLgMargin() {
		return lgMargin;
	}

	public void setLgMargin(Integer lgMargin) {
		this.lgMargin = lgMargin;
	}

	public String getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}

	public Integer getPadding() {
		return padding;
	}

	public void setPadding(Integer padding) {
		this.padding = padding;
	}

	public Boolean getReversed() {
		return reversed;
	}

	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}

	public Boolean getRtl() {
		return rtl;
	}

	public void setRtl(Boolean rtl) {
		this.rtl = rtl;
	}

	public Boolean getShadow() {
		return shadow;
	}

	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getSymbolPadding() {
		return symbolPadding;
	}

	public void setSymbolPadding(Integer symbolPadding) {
		this.symbolPadding = symbolPadding;
	}

	public Integer getSymbolWidth() {
		return symbolWidth;
	}

	public void setSymbolWidth(Integer symbolWidth) {
		this.symbolWidth = symbolWidth;
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


	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
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
	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chartObj = this.initLegend();


		return EVAL_BODY_AGAIN;
	}

	private HighChart initLegend() {
		Object chartTag = super.getParent();
		HighChart chartObj = (HighChart) pageContext
				.getAttribute(((ChartTag) chartTag).getId());

		chartObj.getLegend().setAlign(this.getAlign());
		chartObj.getLegend().setBackgroundColor(this.getBackgroundColor());
		chartObj.getLegend().setVerticalAlign(this.getVerticalAlign());
		chartObj.getLegend().setLayout(this.getLayout());
		chartObj.getLegend().setBorderColor(this.getBorderColor());
		chartObj.getLegend().setBorderRadius(this.getBorderRadius());
		chartObj.getLegend().setBorderWidth(this.getBorderWidth());
		chartObj.getLegend().setEnabled(this.getEnabled());
		chartObj.getLegend().setFloating(this.getFloating());
		if (itemHiddenStyle != null && !itemHiddenStyle.equals("")) {
			if (itemHiddenStyle.indexOf(",") > 0) {
				String[] itemHiddenStyles = this.itemHiddenStyle.split(",");
				for (int i = 0; i < itemHiddenStyles.length; i++) {
					String[] itemHiddenStyless = itemHiddenStyles[i].split(":");
					chartObj.getLegend().setItemHiddenStyle(itemHiddenStyless[0], itemHiddenStyless[1]);
				}
			} else {
				String[] itemHiddenStyles = itemHiddenStyle.split(":");
				chartObj.getLegend().setItemHiddenStyle(itemHiddenStyles[0], itemHiddenStyles[1]);
			}
		}
		if (itemHoverStyle != null && !itemHoverStyle.equals("")) {
			if (itemHoverStyle.indexOf(",") > 0) {
				String[] itemHoverStyles = this.itemHoverStyle.split(",");
				for (int i = 0; i < itemHoverStyles.length; i++) {
					String[] itemHoverStyless = itemHoverStyles[i].split(":");
					chartObj.getLegend().setItemHoverStyle(itemHoverStyless[0], itemHoverStyless[1]);
				}
			} else {
				String[] itemHoverStyles = itemHoverStyle.split(":");
				chartObj.getLegend().setItemHoverStyle(itemHoverStyles[0], itemHoverStyles[1]);
			}
		}
		chartObj.getLegend().setItemMarginBottom(this.getItemMarginBottom());
		chartObj.getLegend().setItemMarginTop(this.getItemMarginTop());
		if (itemStyle != null && !itemStyle.equals("")) {
			if (itemStyle.indexOf(",") > 0) {
				String[] itemStyles = this.itemStyle.split(",");
				for (int i = 0; i < itemStyles.length; i++) {
					String[] itemStyless = itemStyles[i].split(":");
					chartObj.getLegend().setItemStyle(itemStyless[0], itemStyless[1]);
				}
			} else {
				String[] itemStyles = itemStyle.split(":");
				chartObj.getLegend().setItemStyle(itemStyles[0], itemStyles[1]);
			}
		}
		chartObj.getLegend().setItemWidth(this.getItemWidth());
		chartObj.getLegend().setLabelFormat(this.getLabelFormat());
		chartObj.getLegend().setLabelFormatter(this.getLabelFormatter());
		chartObj.getLegend().setLayout(this.getLayout());
		chartObj.getLegend().setLineHeight(this.getLineHeight());
		chartObj.getLegend().setMargin(this.getLgMargin());
		chartObj.getLegend().setMaxHeight(this.getMaxHeight());
		chartObj.getLegend().setPadding(this.getPadding());
		chartObj.getLegend().setReversed(this.getReversed());
		chartObj.getLegend().setRtl(this.getRtl());
		chartObj.getLegend().setShadow(this.getShadow());
		if (style != null && !style.equals("")) {
			if (style.indexOf(",") > 0) {
				String[] styles = this.style.split(",");
				for (int i = 0; i < styles.length; i++) {
					String[] styless = styles[i].split(":");
					chartObj.getLegend().setStyle(styless[0], styless[1]);
				}
			} else {
				String[] styles = style.split(":");
				chartObj.getLegend().setStyle(styles[0], styles[1]);
			}
		}

		chartObj.getLegend().setSymbolPadding(this.getSymbolPadding());
		chartObj.getLegend().setSymbolWidth(this.getSymbolWidth());
		chartObj.getLegend().setUseHTML(this.getUseHTML());
		chartObj.getLegend().setVerticalAlign(this.getVerticalAlign());
		chartObj.getLegend().setWidth(this.getWidth());
		chartObj.getLegend().setX(this.getX());
		chartObj.getLegend().setY(this.getY());
		chartObj.getLegend().getTitle().setText(this.titleText);
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
