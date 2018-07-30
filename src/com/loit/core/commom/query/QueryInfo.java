package com.loit.core.commom.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loit.core.commom.fields.QueryField;
import com.loit.core.hibernate.model.BaseObject;

public class QueryInfo extends BaseObject {
	private String querySource;
	private List<QueryField> queryFields;
	private String orderBy;
	private PagingInfo pagingInfo;
	private Map<String, String> fieldCodeTypes;

	public String getQuerySource() {
		return querySource;
	}

	public void setQuerySource(String querySource) {
		this.querySource = querySource;
	}

	public List<QueryField> getQueryFields() {
		return this.queryFields;
	}

	public void setQueryFields(List<QueryField> queryFields) {
		this.queryFields = queryFields;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public PagingInfo getPagingInfo() {
		return this.pagingInfo;
	}

	public void setPagingInfo(PagingInfo pagingInfo) {
		this.pagingInfo = pagingInfo;
	}

	public Map<String, String> getFieldCodeTypes() {
		return this.fieldCodeTypes;
	}

	public void setFieldCodeTypes(Map<String, String> fieldCodeTypes) {
		this.fieldCodeTypes = fieldCodeTypes;
	}

	/**
	 * Fields转成Map
	 * 
	 * @return null或者Map<'FieldName, QueryField>
	 */
	public Map<String, QueryField> getQueryFieldsMap() {
		if (queryFields.size() == 0) {
			return null;
		}
		Map<String, QueryField> map = new HashMap<String, QueryField>();
		for (int i = 0; i < queryFields.size(); i++) {
			QueryField qf = queryFields.get(i);
			map.put(qf.getFieldName(), qf);
		}
		return map;
	}

}
