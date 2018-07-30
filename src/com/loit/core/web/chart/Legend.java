package com.loit.core.web.chart;

import java.util.HashMap;
import java.util.Map;

public class Legend {
	private String align;// center
	private String backgroundColor;// null
	private String borderColor;// #909090
	private Integer borderRadius;// 5
	private Integer borderWidth;// 1
	private Boolean enabled;// true
	private Boolean floating;// false
	private Map itemHiddenStyle = new HashMap<>();// null
	private Map itemHoverStyle = new HashMap<>();// null
	private Integer itemMarginBottom;// 0
	private Integer itemMarginTop;// 0
	private Map itemStyle = new HashMap<>();// null
	private String itemWidth;// null
	private String labelFormat;// {name}
	private String labelFormatter;// null
	private String layout;// horizontal
	private Integer lineHeight;// 16
	private Integer margin;// 15
	private String maxHeight;// null
	private final LegendNavigation navigation;// object
	private Integer padding;// 8
	private Boolean reversed;// false
	private Boolean rtl;// false
	private Boolean shadow;// false
	private Map style = new HashMap<>();// null
	private Integer symbolPadding;// 5
	private Integer symbolWidth;// 30
	private final LegendTitle title;// object
	private Boolean useHTML;// false
	private String verticalAlign;// bottom
	private Integer width;// null
	private Integer x;// 0
	private Integer y;// 0
	
	Legend(){
		navigation = new LegendNavigation();// object
		title=new LegendTitle();// object
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
	public Map getItemHiddenStyle() {
		return itemHiddenStyle;
	}
	public void setItemHiddenStyle(String key,String value) {
		this.itemHiddenStyle.put(key, value);
	}
	public Map getItemHoverStyle() {
		return itemHoverStyle;
	}
	public void setItemHoverStyle(String key,String value) {
		this.itemHoverStyle.put(key, value);
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
	public Map getItemStyle() {
		return itemStyle;
	}
	public void setItemStyle(String key,String value) {
		this.itemStyle.put(key, value);
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
	public Integer getMargin() {
		return margin;
	}
	public void setMargin(Integer margin) {
		this.margin = margin;
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
	public Map getStyle() {
		return style;
	}
	public void setStyle(String key,String value) {
		this.style.put(key, value);
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
	public LegendNavigation getNavigation() {
		return navigation;
	}
	public LegendTitle getTitle() {
		return title;
	}
	
}
