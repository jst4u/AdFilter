package com.loit.core.commom.query;

import java.util.List;

import com.loit.core.commom.fields.QueryField;

public abstract interface QueryDataSource
{
  public abstract List<?> getData(List<QueryField> paramList, String paramString, PagingInfo paramPagingInfo);

  public abstract Class<?> getDataItemClass();
}
