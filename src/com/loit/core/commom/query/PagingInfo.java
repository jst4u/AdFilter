package com.loit.core.commom.query;

import com.loit.core.hibernate.model.BaseObject;


public class PagingInfo extends BaseObject
{
  private int pageSize;
  private int currentPage;//A
  private int totalRows;

  public PagingInfo()
  {
  }

  public PagingInfo(int pageSize, int currentPage)
  {
    this.pageSize = pageSize;
    this.currentPage = currentPage;
  }

  public int getCurrentRow() {
    if (this.currentPage <= 0) {
      return 0;
    }
    return this.pageSize * (this.currentPage - 1);
  }

  public int getTotalPages() {
    if ((this.totalRows <= 0) || (this.pageSize <= 0)) {
      return 0;
    }
    return (this.totalRows - 1) / this.pageSize + 1;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getCurrentPage() {
    return this.currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getTotalRows() {
    return this.totalRows;
  }

  public void setTotalRows(int totalRows) {
    this.totalRows = totalRows;
  }
}