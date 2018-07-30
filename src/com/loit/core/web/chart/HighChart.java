package com.loit.core.web.chart;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class HighChart {
	private  Chart chart;
	private  Legend legend;
	private  Pane pane;
	private  List<Series> series;
	private  Title subtitle;
	private  Title title;
	private  Tooltip tooltip;
	private  Axis xAxis;
	private  Axis yAxis;
	private  PlotOptions plotOptions;
	private  Exporting exporting;
	
	public HighChart(){
		chart = new Chart();
		title=new ChartTitle();
		subtitle=new ChartTitle();
		tooltip = new Tooltip();
		legend = new Legend();
		xAxis = new Axis("xtext");
		yAxis = new Axis("ytext");
		series = new ArrayList<Series>();
		pane = new Pane();
		plotOptions = new PlotOptions();
		exporting = new Exporting();
	}

	public Chart getChart() {
		return chart;
	}

	public Legend getLegend() {
		return legend;
	}

	public Pane getPane() {
		return pane;
	}

	public List<Series> getSeries() {
		return series;
	}

	public Title getSubtitle() {
		return subtitle;
	}

	public Title getTitle() {
		return title;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public Axis getxAxis() {
		return xAxis;
	}

	public Axis getyAxis() {
		return yAxis;
	}
	
	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public void setLegend(Legend legend) {
		this.legend = legend;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public void setSubtitle(Title subtitle) {
		this.subtitle = subtitle;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public void setxAxis(Axis xAxis) {
		this.xAxis = xAxis;
	}

	public void setyAxis(Axis yAxis) {
		this.yAxis = yAxis;
	}

	public PlotOptions getPlotOptions() {
		return plotOptions;
	}

	public void setPlotOptions(PlotOptions plotOptions) {
		this.plotOptions = plotOptions;
	}

	public Exporting getExporting() {
		return exporting;
	}

	public void setExporting(Exporting exporting) {
		this.exporting = exporting;
	}

	public static void main(String[] arg){
		HighChart chart = new HighChart();
		chart.series.add(new Series());
		JsonConfig jc = new JsonConfig();
		String json=new JSONArray().fromObject(chart).toString();
		System.out.println(json);
	}
}
