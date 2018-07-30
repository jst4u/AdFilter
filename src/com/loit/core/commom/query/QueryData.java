package com.loit.core.commom.query;

import java.util.List;
import java.util.Map;

import com.loit.core.hibernate.model.BaseObject;

public class QueryData extends BaseObject
{
  private List<?> dataList ;
  //private Map<String, Map<Object, String>> selectCodeValues;
  private PagingInfo pagingInfo;

  private QueryData(){
	  
  }
  public QueryData(PagingInfo pagingInfo){
	  this.pagingInfo = pagingInfo;
  }
  public List<?> getDataList()
  {
    return this.dataList;
  }
  public void setDataList(List<?> dataList) {
    this.dataList = dataList;
  }
//  public Map<String, Map<Object, String>> getSelectCodeValues() {
//    return this.selectCodeValues;
//  }
//
//  public void setSelectCodeValues(Map<String, Map<Object, String>> selectCodeValues) {
//    this.selectCodeValues = selectCodeValues;
//  }
  public PagingInfo getPagingInfo() {
    return this.pagingInfo;
  }
  public void setPagingInfo(PagingInfo pagingInfo) {
    this.pagingInfo = pagingInfo;
  }
}