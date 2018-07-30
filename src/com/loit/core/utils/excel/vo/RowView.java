package com.loit.core.utils.excel.vo;

public class RowView {
	private int rowNum;
	
	private int height;
	
	public RowView(int rowNum, int height) {
		this.rowNum = rowNum;
		this.height = height;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
