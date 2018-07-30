package com.loit.core.hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModel extends BaseObject implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ROW_STATE_ADDED = "Added";
	public static final String ROW_STATE_DELETED = "Deleted";
	public static final String ROW_STATE_MODIFIED = "Modified";
//	
	private String rowState;
	private List<String> validFieldNameList = new ArrayList<String>();
	private List children; 
//	
	public String getRowState() {
		return this.rowState;
	}

	public void setRowState(String rowState) {
		this.rowState = rowState;
	}
//
	public void addValidField(String validFieldName) {
		this.validFieldNameList.add(validFieldName);
	}

	public List<String> validFields() {
		return this.validFieldNameList;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}
	
	


}
