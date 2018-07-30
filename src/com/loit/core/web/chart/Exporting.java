package com.loit.core.web.chart;

import java.util.HashMap;
import java.util.Map;

public class Exporting {
	private Map buttons = new HashMap<>();// null 
	private String chartOptions; //null
	private Boolean enabled; //true
	private String filename; //chart
	private String formAttributes;
	private Integer scale;// 2
	private Integer sourceHeight;
	private Integer sourceWidth;
	private String type; //image/png
	private String url; //http://export.highcharts.com
	private Integer width;// undefined
	
	public Map getButtons() {
		return buttons;
	}
	public void setButtons(String key,String value) {
		this.buttons.put(key, value);
	}
	public String getChartOptions() {
		return chartOptions;
	}
	public void setChartOptions(String chartOptions) {
		this.chartOptions = chartOptions;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFormAttributes() {
		return formAttributes;
	}
	public void setFormAttributes(String formAttributes) {
		this.formAttributes = formAttributes;
	}
	public Integer getScale() {
		return scale;
	}
	public void setScale(Integer scale) {
		this.scale = scale;
	}
	public Integer getSourceHeight() {
		return sourceHeight;
	}
	public void setSourceHeight(Integer sourceHeight) {
		this.sourceHeight = sourceHeight;
	}
	public Integer getSourceWidth() {
		return sourceWidth;
	}
	public void setSourceWidth(Integer sourceWidth) {
		this.sourceWidth = sourceWidth;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	
}
