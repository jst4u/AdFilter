package com.loit.core.utils.excel.vo;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class MyWorkBook {

	private String bookName;
	private Collection<MySheet> sheetCol = new LinkedHashSet<MySheet>();
	
	private MyWorkBook(String bookName){
		this.bookName = bookName;
	}
	
	

	
	public static MyWorkBook createWorkBook(String bookName){
		return new MyWorkBook(bookName);
	}
	
	public String getWorkName(){
		return this.bookName;
	}
	
	/**
	 * 添加脚本
	 * @param mySheet
	 */
	public void add(MySheet mySheet){
		sheetCol.add(mySheet);
	}
	
	public Iterator<MySheet> iteratorOfSheet(){
		return sheetCol.iterator();
	}
	
}
