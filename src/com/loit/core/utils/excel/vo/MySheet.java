package com.loit.core.utils.excel.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;


public class MySheet {

	/**
	 * 纵向打印
	 */
	public static final int PRINT_FORWARD_VALIAN = 0;
	/**
	 * 横向打印
	 */
	
	public static final int PRINT_FORWARD_ALIGN = 1;
	/**
	 * 工作区名称
	 */
	private String sheetName;
	private int num;
	private Collection<MyLabel> myLabelCol = new LinkedHashSet<MyLabel>();
	private List<ColumnView> cvList= new ArrayList<ColumnView>();
	private List<RowView> rvList = new ArrayList<RowView>();
	/**
	 * 打印上边距
	 */
	private double topMargin = 1;
	/**
	 * 打印下边距
	 */
	private double bottomMargin = 1;
	/**
	 * 打印左边距
	 */
	private double leftMargin = 0.76;
	/**
	 * 打印右边距
	 */
	private double rightMargin = 0.76;
	
	/**
	 * 设置打印缩放百分比
	 */
	private int scaleFactor =100;
	/**
	 * 获取打印缩放百分比
	 */
	public int getScaleFactor(){
		return this.scaleFactor;
	}
	/**
	 * 设置打印缩放百分比
	 */
	public void setScaleFactor(int scaleFactor){
		this.scaleFactor = scaleFactor;
	}
	
	/**
	 * 获取打印 上边距
	 * @return
	 */
	public double getTopMargin() {
		return topMargin;
	}
	/**
	 * 设置打印 上边距
	 * @return
	 */
	public void setTopMargin(double topMargin) {
		this.topMargin = topMargin;
	}
	/**
	 * 获取打印 下边距
	 * @return
	 */
	public double getBottomMargin() {
		return bottomMargin;
	}
	/**
	 * 设置打印 下边距
	 * @return
	 */
	public void setBottomMargin(double bottomMargin) {
		this.bottomMargin = bottomMargin;
	}
	/**
	 * 获取打印 左边距
	 * @return
	 */
	public double getLeftMargin() {
		return leftMargin;
	}

	/**
	 * 设置打印 左边距
	 * @return
	 */
	public void setLeftMargin(double leftMargin) {
		this.leftMargin = leftMargin;
	}

	/**
	 * 获取打印 右边距
	 * @return
	 */
	public double getRightMargin() {
		return rightMargin;
	}

	/**
	 * 设置打印 右边距
	 * @return
	 */
	public void setRightMargin(double rightMargin) {
		this.rightMargin = rightMargin;
	}
	
	
	private int printForward = PRINT_FORWARD_VALIAN;
	
	
	
	public int getPrintForward() {
		return printForward;
	}

	/**
	 * 设置打印方向
	 * @param printForward
	 */
	public void setPrintForward(int printForward) {
		this.printForward = printForward;
	}

	private MySheet(){
	}
	
	/**
	 * 创建sheet 工作表
	 * @param sheetName 工作表的名称 
	 * @param num 工作表的位置。
	 * @return
	 */
	public static MySheet createMySheet(String sheetName,int num){
		MySheet sheet = new MySheet();
		sheet.setNum(num);
		sheet.setSheetName(sheetName);
		return sheet;
		
	}
	
	private void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	private void setNum(int num) {
		this.num = num;
	}
	
	public void add(MyLabel myLabel){
		myLabelCol.add(myLabel);
	}
	
	public String getSheetName(){
		return this.sheetName;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public Iterator<MyLabel> iteratorOfMySheet(){
		return this.myLabelCol.iterator();
	}
	
	public Iterator<ColumnView> iteratorOfColumnViews(){
		return this.cvList.iterator();
	}
	
	public Iterator<RowView> iteratorOfRowViews(){
		return this.rvList.iterator();
	}
	
	public void add(ColumnView columnView){
		this.cvList.add(columnView);
	}
	
	public void add(RowView rowView){
		this.rvList.add(rowView);
	}
}
