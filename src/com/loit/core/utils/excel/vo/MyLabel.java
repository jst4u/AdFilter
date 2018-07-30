package com.loit.core.utils.excel.vo;

public class MyLabel {
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int CENTER = 3;
	
	private static final String FONTNAME_SONGTI = "宋体";
	/**
	 * 单元格横坐标
	 */
	private int x;

	/**
	 * 单元格纵坐标
	 */
	private int y;
	
	/**
	 * 单元格内容
	 */
	private String text;
	/**
	 * 合并列数
	 */
	private int colspan = 1;
	/**
	 * 合并行数
	 */
	private int rowspan = 1;
	/**
	 * 字体大小
	 */
	private int fontNum = 12;
	/**
	 * 单元格是否带边框
	 */
	private boolean border = false;
	/**
	 * 是否加粗
	 */
	private boolean isBold = false;
	
	/**
	 * 是否自动换行
	 */
	private boolean isAutoNewLine = true;
	/**
	 * 水平对齐
	 */
	private int align = 1;
	
	/**
	 * 设置字体类型
	 */
	private String fontName = FONTNAME_SONGTI;

	/**
	 * 获取是否自动换行
	 * @return
	 */
	public boolean isAutoNewLine() {
		return isAutoNewLine;
	}
	/**
	 * 设置是否自动换行
	 * @return
	 */
	public void setAutoNewLine(boolean isAutoNewLine) {
		this.isAutoNewLine = isAutoNewLine;
	}

	/**
	 * 设置是否加粗
	 * @param isBold
	 */
	public void setIsBold(boolean isBold){
		this.isBold = isBold;
	}
	
	/**
	 * 获取是否加粗
	 * @return
	 */
	public boolean getIsBold(){
		return this.isBold;
	}

	private MyLabel(){
	}
	
	
	private void setX(int x) {
		this.x = x;
	}

	/**
	 * 获取单元格横坐标
	 * @return
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * 获取单元格纵坐标
	 * @return
	 */
	public int getY(){
		return this.y;
	}

	private void setY(int y) {
		this.y = y;
	}

	/**
	 * 获取字体类型
	 * @return
	 */
	public String getFontName() {
		return fontName;
	}
	/**
	 * 设置字体类型
	 * @param fontName
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	private void setY(String y){
		char[] chars = y.toCharArray();
		int pattern = 1;
		for(int i = chars.length -1 ; i < chars.length ; i++){
			if(i == chars.length -1 ){
				pattern = 1;
			}else{
				pattern = pattern * 26;
			}
			this.y = this.y  + (Character.toUpperCase(chars[i]) - 64)*pattern;
		}
	}

	
	
	private void setText(String text) {
		this.text = text;
	}
	
	/**
	 * 获取单元格文本
	 * @return
	 */
	public String getText(){
		return this.text;
	}
	
	/**
	 * 获取合并的列数
	 * @return
	 */
	public int getColspan() {
		return colspan;
	}


	/**
	 * 设置合并的列数
	 * @param colspan
	 */
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}


	/**
	 * 获取合并的行数
	 * @return
	 */
	public int getRowspan() {
		return rowspan;
	}


	/**
	 * 获取字体的大小
	 * @return
	 */
	public int getFontNum() {
		return fontNum;
	}
	/** 
	 *  设置水平对齐
	 */
	public int getAlign() {
		return align;
	}


	/**
	 * 设置水平对齐
	 * @param align
	 */
	public void setAlign(int align) {
		this.align = align;
	}

	/**
	 * 设置字体的大小，默认15
	 * @param fontNum
	 */
	public void setFontNum(int fontNum) {
		this.fontNum = fontNum;
	}

	/**
	 * 设置合并的行数
	 * @param rowspan
	 */
	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}
	
	
	/**
	 * 是否有边框
	 */
	
	public boolean isBorder(){
		return border;
	}
	
	public void setBorder(boolean border){
		this.border = border;
	}
	
	/**
	 * 创建单元格对象
	 * @param x 单元格横坐标
	 * @param y 单元格纵坐标
	 * @param text 单元格内容
	 * @return MyLabel
	 */
	public static MyLabel createMyLabel(int x,int y,String text){
		MyLabel label = new MyLabel();
		label.setText(text);
		label.setX(x);
		label.setY(y);
		return label;
	}
	/**
	 * 创建单元格对象
	 * @param x 单元格横坐标
	 * @param y 单元格纵坐标
	 * @param text 单元格内容
	 * @return MyLabel
	 */
	public static MyLabel createMyLabel(int x,String y,String text){
		MyLabel label = new MyLabel();
		label.setText(text);
		label.setX(x);
		label.setY(y);
		return label;
	}
	/**
	 * 创建单元格对象
	 * @param x 单元格横坐标
	 * @param y 单元格纵坐标
	 * @param text 单元格内容
	 * @param fontSize 字体大小
	 * @return MyLabel
	 */
	public static MyLabel createMyLabel(int x,String y,String text,int fontSize){
		MyLabel label = new MyLabel();
		label.setText(text);
		label.setX(x);
		label.setY(y);
		label.setFontNum(fontSize);
		return label;
	}
}
