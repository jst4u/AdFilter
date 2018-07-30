package com.loit.core.utils.excel.vo;

public class ColumnView {
	private int colNum;
	private int width;
	
	public ColumnView(int colNum,int width){
		this.colNum = colNum;
		this.width = width;
	}
	
	public int getColNum() {
		return colNum;
	}

	public int getWidth() {
		return width;
	}
}
