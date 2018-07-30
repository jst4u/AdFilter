package com.loit.core.web.chart;

import java.util.ArrayList;
import java.util.List;

public class Series {

	public String name;

	/**
	 * The type of series. Can be one of area, areaspline, bar, column,
	 * line, pie, scatter or spline. From version 2.3, arearange,
	 * areasplinerange and columnrange are supported with the
	 * highcharts-more.js component.
	 */
	public String type;

	/**
	 * When using dual or multiple x axes, this number defines which xAxis
	 * the particular series is connected to. It refers to either the axis
	 * id or the index of the axis in the xAxis array, with 0 being the
	 * first. Defaults to 0.
	 */
	public Integer xAxis;

	/**
	 * When using dual or multiple x axes, this number defines which yAxis
	 * the particular series is connected to. It refers to either the axis
	 * id or the index of the axis in the yAxis array, with 0 being the
	 * first. Defaults to 0.
	 */
	public Integer yAxis;
	
	/**
	 * Pie series only. Whether to display a slice offset from the center. Defaults to false.
	 */
	public Boolean sliced;
	
	public final List data = new ArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getxAxis() {
		return xAxis;
	}

	public void setxAxis(Integer xAxis) {
		this.xAxis = xAxis;
	}

	public Integer getyAxis() {
		return yAxis;
	}

	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}

	public Boolean getSliced() {
		return sliced;
	}

	public void setSliced(Boolean sliced) {
		this.sliced = sliced;
	}

	public List getData() {
		return data;
	}

	
}
