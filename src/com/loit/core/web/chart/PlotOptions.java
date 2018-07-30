package com.loit.core.web.chart;

import com.loit.core.web.chart.plotoptions.Series;

public class PlotOptions {
	private final Series series;// {…} 
	
	public PlotOptions(){
		series = new Series();// object
		
	}

	public Series getSeries() {
		return series;
	}
	
}
