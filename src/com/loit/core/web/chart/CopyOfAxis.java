package com.loit.core.web.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyOfAxis {
	private final List<String> categories;
	private final AxisTitle title ;
	
	private Boolean allowDecimals;// true
	private String alternateGridColor;// null
	private String dateTimeLabelFormats;// null
	private Boolean endOnTick;// false
	private Map events = new HashMap<>();// {…}
	private String gridLineColor;// #C0C0C0
	private String gridLineDashStyle;// Solid
	private Integer gridLineWidth;// 0
	private String id;// null
	private List<AxisLabels> labels;// {…}
	private String lineColor;// #C0D0E0
	private Integer lineWidth;// 1
	private String linkedTo;// null
	private Integer max;// null
	private Double maxPadding;// 0.01
	private String maxZoom;// null
	private Integer min;// null
	private Double minPadding;// 0.01
	private Integer minRange;// null
	private Integer minTickInterval;// null
	private String minorGridLineColor;// #E0E0E0
	private String minorGridLineDashStyle;// Solid
	private Integer minorGridLineWidth;// 1
	private String minorTickColor;// #A0A0A0
	private Integer minorTickInterval;// null
	private Integer minorTickLength;// 2
	private String minorTickPosition;// outside
	private Integer minorTickWidth;// 0
	private Integer offset;// 0
	private Boolean opposite;// false
	private List<PlotBands> plotBands;// {…}
	private List<PlotLines> plotLines;// {…}
	private Boolean reversed;// false
	private Boolean showEmpty;// true
	private Boolean showFirstLabel;// true
	private Boolean showLastLabel;// false
	private Integer startOfWeek;// 1
	private Boolean startOnTick;// false
	private String tickColor;// #C0D0E0
	private Integer tickInterval;// null
	private Integer tickLength;// 5
	private Integer tickPixelInterval;// null
	private String tickPosition;// outside
	private String tickPositioner;// null
	private List<Integer> tickPositions= new ArrayList<>();// null
	private Integer tickWidth;// 1
	private String tickmarkPlacement;// between
	/**
	 * The type of axis. Can be one of "linear", "logarithmic", "datetime" or "category".
	 * Defaults to linear.
	 */
	public String type;

	CopyOfAxis(String titleText,List<String> categories){
		this.title = new AxisTitle(); 
		this.categories = new ArrayList<String>();
		this.labels = new ArrayList<AxisLabels>();
		this.title.setText(titleText);
		this.categories.addAll(categories);
		this.plotBands = new ArrayList<PlotBands>();
		this.plotLines = new ArrayList<PlotLines>();
	}
	
	CopyOfAxis(String titleText){
		this.title = new AxisTitle(); 
		this.categories = new ArrayList<String>();
		this.labels = new ArrayList<AxisLabels>();
		this.title.setText(titleText);
		this.plotBands = new ArrayList<PlotBands>();
		this.plotLines = new ArrayList<PlotLines>();
	}
	
	public CopyOfAxis(){
		this.title = new AxisTitle(); 
		this.categories = new ArrayList<String>();
		this.labels = new ArrayList<AxisLabels>();
		this.plotBands = new ArrayList<PlotBands>();
		this.plotLines = new ArrayList<PlotLines>();
	}

	public Boolean getAllowDecimals() {
		return allowDecimals;
	}

	public void setAllowDecimals(Boolean allowDecimals) {
		this.allowDecimals = allowDecimals;
	}

	public String getAlternateGridColor() {
		return alternateGridColor;
	}

	public void setAlternateGridColor(String alternateGridColor) {
		this.alternateGridColor = alternateGridColor;
	}

	public String getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}

	public void setDateTimeLabelFormats(String dateTimeLabelFormats) {
		this.dateTimeLabelFormats = dateTimeLabelFormats;
	}

	public Boolean getEndOnTick() {
		return endOnTick;
	}

	public void setEndOnTick(Boolean endOnTick) {
		this.endOnTick = endOnTick;
	}

	public Map getEvents() {
		return events;
	}

	public void setEvents(String key,String value) {
		this.events.put(key, value);
	}

	public String getGridLineColor() {
		return gridLineColor;
	}

	public void setGridLineColor(String gridLineColor) {
		this.gridLineColor = gridLineColor;
	}

	public String getGridLineDashStyle() {
		return gridLineDashStyle;
	}

	public void setGridLineDashStyle(String gridLineDashStyle) {
		this.gridLineDashStyle = gridLineDashStyle;
	}

	public Integer getGridLineWidth() {
		return gridLineWidth;
	}

	public void setGridLineWidth(Integer gridLineWidth) {
		this.gridLineWidth = gridLineWidth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLineColor() {
		return lineColor;
	}

	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}

	public Integer getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}

	public String getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Double getMaxPadding() {
		return maxPadding;
	}

	public void setMaxPadding(Double maxPadding) {
		this.maxPadding = maxPadding;
	}

	public String getMaxZoom() {
		return maxZoom;
	}

	public void setMaxZoom(String maxZoom) {
		this.maxZoom = maxZoom;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Double getMinPadding() {
		return minPadding;
	}

	public void setMinPadding(Double minPadding) {
		this.minPadding = minPadding;
	}

	public Integer getMinRange() {
		return minRange;
	}

	public void setMinRange(Integer minRange) {
		this.minRange = minRange;
	}

	public Integer getMinTickInterval() {
		return minTickInterval;
	}

	public void setMinTickInterval(Integer minTickInterval) {
		this.minTickInterval = minTickInterval;
	}

	public String getMinorGridLineColor() {
		return minorGridLineColor;
	}

	public void setMinorGridLineColor(String minorGridLineColor) {
		this.minorGridLineColor = minorGridLineColor;
	}

	public String getMinorGridLineDashStyle() {
		return minorGridLineDashStyle;
	}

	public void setMinorGridLineDashStyle(String minorGridLineDashStyle) {
		this.minorGridLineDashStyle = minorGridLineDashStyle;
	}

	public Integer getMinorGridLineWidth() {
		return minorGridLineWidth;
	}

	public void setMinorGridLineWidth(Integer minorGridLineWidth) {
		this.minorGridLineWidth = minorGridLineWidth;
	}

	public String getMinorTickColor() {
		return minorTickColor;
	}

	public void setMinorTickColor(String minorTickColor) {
		this.minorTickColor = minorTickColor;
	}

	public Integer getMinorTickInterval() {
		return minorTickInterval;
	}

	public void setMinorTickInterval(Integer minorTickInterval) {
		this.minorTickInterval = minorTickInterval;
	}

	public Integer getMinorTickLength() {
		return minorTickLength;
	}

	public void setMinorTickLength(Integer minorTickLength) {
		this.minorTickLength = minorTickLength;
	}

	public String getMinorTickPosition() {
		return minorTickPosition;
	}

	public void setMinorTickPosition(String minorTickPosition) {
		this.minorTickPosition = minorTickPosition;
	}

	public Integer getMinorTickWidth() {
		return minorTickWidth;
	}

	public void setMinorTickWidth(Integer minorTickWidth) {
		this.minorTickWidth = minorTickWidth;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Boolean getOpposite() {
		return opposite;
	}

	public void setOpposite(Boolean opposite) {
		this.opposite = opposite;
	}

	public Boolean getReversed() {
		return reversed;
	}

	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}

	public Boolean getShowEmpty() {
		return showEmpty;
	}

	public void setShowEmpty(Boolean showEmpty) {
		this.showEmpty = showEmpty;
	}

	public Boolean getShowFirstLabel() {
		return showFirstLabel;
	}

	public void setShowFirstLabel(Boolean showFirstLabel) {
		this.showFirstLabel = showFirstLabel;
	}

	public Boolean getShowLastLabel() {
		return showLastLabel;
	}

	public void setShowLastLabel(Boolean showLastLabel) {
		this.showLastLabel = showLastLabel;
	}

	public Integer getStartOfWeek() {
		return startOfWeek;
	}

	public void setStartOfWeek(Integer startOfWeek) {
		this.startOfWeek = startOfWeek;
	}

	public Boolean getStartOnTick() {
		return startOnTick;
	}

	public void setStartOnTick(Boolean startOnTick) {
		this.startOnTick = startOnTick;
	}

	public String getTickColor() {
		return tickColor;
	}

	public void setTickColor(String tickColor) {
		this.tickColor = tickColor;
	}

	public Integer getTickInterval() {
		return tickInterval;
	}

	public void setTickInterval(Integer tickInterval) {
		this.tickInterval = tickInterval;
	}

	public Integer getTickLength() {
		return tickLength;
	}

	public void setTickLength(Integer tickLength) {
		this.tickLength = tickLength;
	}

	public Integer getTickPixelInterval() {
		return tickPixelInterval;
	}

	public void setTickPixelInterval(Integer tickPixelInterval) {
		this.tickPixelInterval = tickPixelInterval;
	}

	public String getTickPosition() {
		return tickPosition;
	}

	public void setTickPosition(String tickPosition) {
		this.tickPosition = tickPosition;
	}

	public String getTickPositioner() {
		return tickPositioner;
	}

	public void setTickPositioner(String tickPositioner) {
		this.tickPositioner = tickPositioner;
	}

	public List<Integer> getTickPositions() {
		return tickPositions;
	}

	public void setTickPositions(List<Integer> tickPositions) {
		this.tickPositions = tickPositions;
	}

	public Integer getTickWidth() {
		return tickWidth;
	}

	public void setTickWidth(Integer tickWidth) {
		this.tickWidth = tickWidth;
	}

	public String getTickmarkPlacement() {
		return tickmarkPlacement;
	}

	public void setTickmarkPlacement(String tickmarkPlacement) {
		this.tickmarkPlacement = tickmarkPlacement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getCategories() {
		return categories;
	}

	public AxisTitle getTitle() {
		return title;
	}

	public List<AxisLabels> getLabels() {
		return labels;
	}

	public void setLabels(List<AxisLabels> labels) {
		this.labels = labels;
	}

	public List<PlotBands> getPlotBands() {
		return plotBands;
	}

	public void setPlotBands(List<PlotBands> plotBands) {
		this.plotBands = plotBands;
	}

	public List<PlotLines> getPlotLines() {
		return plotLines;
	}

	public void setPlotLines(List<PlotLines> plotLines) {
		this.plotLines = plotLines;
	}



}
