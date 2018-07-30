package com.loit.core.web.chart;

import java.util.ArrayList;
import java.util.List;

public class Pane {
	private List<Background> background;
	private final String[] center;// ["50%", "50%"]
	private Integer endAngle;// null
	private Integer startAngle;// null
	
	Pane(){
		center = new String[2];
		center[0]="50%";
		center[1]="50%";
		background = new ArrayList<>();
	}


	public List<Background> getBackground() {
		return background;
	}


	public void setBackground(List<Background> background) {
		this.background = background;
	}


	public Integer getEndAngle() {
		return endAngle;
	}

	public void setEndAngle(Integer endAngle) {
		this.endAngle = endAngle;
	}

	public Integer getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(Integer startAngle) {
		this.startAngle = startAngle;
	}

	public String[] getCenter() {
		return center;
	}
	
	
}
