package com.loit.core.web.chart;

public class AxisLabels extends PoltLabel{
	private Boolean enabled;// true
	private String format;// {value}
	private String formatter;// null
	private String overflow;// null
	private String staggerLines;// null
	private String step;// null
	private Boolean useHTML;// false
	private Integer zIndex;// 7

	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public String getOverflow() {
		return overflow;
	}
	public void setOverflow(String overflow) {
		this.overflow = overflow;
	}

	public String getStaggerLines() {
		return staggerLines;
	}
	public void setStaggerLines(String staggerLines) {
		this.staggerLines = staggerLines;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}

	public Boolean getUseHTML() {
		return useHTML;
	}
	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
	}
	
	public Integer getzIndex() {
		return zIndex;
	}
	public void setzIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}
	
}
