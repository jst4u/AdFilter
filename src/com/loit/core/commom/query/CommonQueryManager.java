package com.loit.core.commom.query;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.system.model.SysCodeModel;
import com.loit.apps.system.model.SysCodeTypeModel;
import com.loit.apps.system.service.SysCodeManagerImpl;
import com.loit.apps.system.service.SysCodeTypeManagerImpl;
import com.loit.core.commom.fields.FieldDefinition;
import com.loit.core.commom.fields.QueryField;
import com.loit.core.exception.SysException;
import com.loit.core.hibernate.model.BaseModel;
import com.loit.core.spring.CommonManager;
import com.loit.core.spring.SpringContext;
import com.loit.core.web.file.FileToDownload;

@Service
public class CommonQueryManager extends CommonManager {

	@Autowired
	private SysCodeTypeManagerImpl sysCodeTypeManager;

	@Autowired
	private SysCodeManagerImpl sysCodeManager;

	@PostConstruct
	public void init() throws Exception {
		// this.dao.getSessionFactory().g
	}

	/**
	 * 主要用于datagrid值填充
	 */
	public QueryData queryDataTable(QueryInfo paramQueryInfo) {
		return this.query(paramQueryInfo);
	}

	public QueryData query(QueryInfo paramQueryInfo) {
		List resultList = null;
		// 根据querySource后的实际查询配置信息
		String querySource = paramQueryInfo.getQuerySource();
		if (null == querySource || "".equals(querySource)) {
			throw new SysException("no querySource !!!");
		}
		// TODO 从数据库查询sys_query_config
		if (querySource.startsWith("service:")) {
			resultList = this.getServiceMethodList(paramQueryInfo, querySource.substring(8));
		} else if (querySource.startsWith("sql:")) {
			resultList = this.getSqlQueryList(paramQueryInfo, querySource.substring(4));
		} else if (querySource.startsWith("model:")) {
			resultList = this.getModelQueryList(paramQueryInfo, querySource.substring(6));
		} else {
			// 默认自定义"model"
			resultList = this.getModelQueryList(paramQueryInfo, querySource);
		}

		if (null != resultList) {
			resultList = this.changeCode(resultList, paramQueryInfo.getFieldCodeTypes());
		}

		QueryData result = new QueryData(paramQueryInfo.getPagingInfo());
		result.setDataList(resultList);

		return result;
	}

	/**
	 * 调用自定义方法查询 自定义方法由serviceMethodString定义，格式为“service.method(QueryInfo qi)”
	 * 
	 * @param paramQueryInfo
	 * @param modelName
	 * @return
	 */
	public List<?> getServiceMethodList(QueryInfo paramQueryInfo, String serviceMethodString) {

		String[] serviceMethod = serviceMethodString.split("\\.");
		List result = null;
		if (serviceMethod.length != 2) {
			throw new SysException("调用自定义表格查询方法错误[" + serviceMethodString + "],格式应当为[service:serviceName.methodName]!");
		}
		// System.out.println("service:" + serviceMethod[0]);
		// System.out.println("method:" + serviceMethod[1]);
		// 调用自定义方法
		try {
			Object service = SpringContext.getBean(serviceMethod[0]);
			if (!(service instanceof CommonManager)) {
				throw new SysException("Service " + service + "\" is not extends from CommonManager!");
			}
			Method method = service.getClass().getMethod(serviceMethod[1], QueryInfo.class);
			result = (List) method.invoke(service, paramQueryInfo);
		} catch (Exception e) {
			throw new SysException(e.getMessage());
		}
		return result;

	}

	/**
	 * 调用数据库Hibernate对象查询
	 * 
	 * @param paramQueryInfo
	 * @param modelName
	 * @return
	 */
	public List getModelQueryList(QueryInfo paramQueryInfo, String modelName) {
		Class modelClass = null;
		BaseModel model = null;
		try {
			modelClass = Class.forName(modelName);
			model = (BaseModel) modelClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e.getMessage());
		}
		List<QueryField> queryFields = paramQueryInfo.getQueryFields();
		Iterator<QueryField> iter = queryFields.iterator();
		List<Condition> conditionList = new ArrayList();
		while (iter.hasNext()) {
			QueryField queryField = iter.next();
			String fieldName = queryField.getFieldName();
			Object fieldValue = queryField.getFieldValue();
			String fieldType = queryField.getFieldType();
			if ((null == fieldName || "".equals(fieldName) || null == fieldValue || "".endsWith(fieldValue.toString())) && null == queryField.getOperator()) {
				continue;
			}
			String opt = queryField.getOperator();
			if (null == opt || opt.trim().length() == 0) {
				opt = "=";
			}
			if (!fieldName.matches("\\[\\w+(,\\w+)*\\]")) {
				conditionList.add(Condition.operator(fieldName, opt, fieldValue));
			}
		}

		Condition allCondition = Condition.and(conditionList);
		Object[] params = null;
		if (null != allCondition.getParameters()) {
			params = allCondition.getParameters().toArray();
		}
		// System.out.println("model query codition:"+allCondition.getSql());
		List result = dao.findByExample(model, allCondition.getSql(), params, paramQueryInfo.getOrderBy(), paramQueryInfo.getPagingInfo());
		return result;
	}

	public List getSqlQueryList(QueryInfo paramQueryInfo, String sql) {
		sql = this.replaceSqlParam(sql, paramQueryInfo.getQueryFields());
		List result = dao.findBySql(sql, paramQueryInfo.getOrderBy(), paramQueryInfo.getPagingInfo(), null);
		return result;
	}

	/**
	 * 替换sql参数，拼接查询语句
	 * select * from SYS_LOG where [LOG_DATE > '{logDate1}']
	 * @param sql
	 * @param param
	 * @return
	 */
	private String replaceSqlParam(String sql, List<QueryField> param) {
		for (QueryField queryField : param) {
			String name = queryField.getFieldName();
			String value = (String) queryField.getFieldValue();
			if (null == value || "".equals(value.trim())) {
				if (sql.indexOf("{" + name + "}") == -1) {
					continue;
				} else {
					String s1 = sql.substring(0, sql.indexOf(name));
					String s2 = sql.substring(sql.indexOf(name));
					String s11 = s1.substring(s1.lastIndexOf("["));
					String s22 = s2.substring(0, s2.indexOf("]") + 1);
					String r = s11 + s22;
					sql = sql.replace(r, "1=1");
				}
			} else {
				sql = sql.replaceAll("\\{" + name + "\\}", value.trim());
			}
		}
		sql = sql.replaceAll("\\[", "").replaceAll("\\]", "");
		return sql;
	}
	
	/**
	 * 替换combobox参数，拼接查询语句
	 *[pj_id > '{pj_id}']
	 * @param sql
	 * @param param
	 * @return
	 */
	private String replaceComboParam(String sql, List<QueryField> param) {
		if (sql.indexOf("{") == -1) {//无需替换参数
			return sql;
		}
		if (null == param || param.size() == 0) {//有可替换参数，但是没有参数值
			return " 1=1";
		}
		for (QueryField queryField : param) {
			String name = queryField.getFieldName().trim();
			String value = (String) queryField.getFieldValue();
			if (null == value || "".equals(value.trim())) {
				if (sql.indexOf("{" + name + "}") == -1) {
					continue;
				} else {
					String s1 = sql.substring(0, sql.indexOf(name));
					String s2 = sql.substring(sql.indexOf(name));
					String s11 = s1.substring(s1.lastIndexOf("["));
					String s22 = s2.substring(0, s2.indexOf("]") + 1);
					String r = s11 + s22;
					sql = sql.replace(r, "1=1");
				}
			} else {
				sql = sql.replaceAll("\\{" + name + "\\}", value.trim());
			}
		}
		sql = sql.replaceAll("\\[", "").replaceAll("\\]", "");
		return sql;
	}

	public List<?> changeCode(List<?> srcList, Map<String, String> codeType) {
		Map<String, Map<String, String>> codesMap = new HashMap<String, Map<String, String>>();
		Iterator<String> iterKey = codeType.keySet().iterator();
		while (iterKey.hasNext()) {
			String col = iterKey.next();// 需要转化代码的列名称
			String type = codeType.get(col);// 需要转换代码的列对应的代码表类型
			codesMap.put(col, this.getCodeValueMapByType(type, null));// 获取代码表数值
		}
		for (int i = 0; i < srcList.size(); i++) {// 循环没一行数值
			Object resultRow = srcList.get(i);
			// BaseModel bm =
			Iterator<String> iterCol = codesMap.keySet().iterator();
			while (iterCol.hasNext()) {// 循环需要转换代码的列
				String codeCol = iterCol.next();// 列名称
				try {
					String codeValue = BeanUtils.getProperty(resultRow, codeCol);// 原始code值
					if (codesMap.get(codeCol).containsKey(codeValue)) {// 转换成显示值
						codeValue = codesMap.get(codeCol).get(codeValue);
					}
					BeanUtils.setProperty(resultRow, codeCol, codeValue);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return srcList;
	}

	/**
	 * 主要用于combobox值填充
	 */
	public List<Map<String, Object>> queryCode(String codeType, String orderBy, List<QueryField> codeFilter) {
		// codeFilter 用于过滤，sql怎样过滤，静态的怎样过滤
		// TODO 需要进一步考虑
		return this.getCodesListByType(codeType, orderBy, codeFilter);
	}

	/**
	 * 主要用于treegrid值填充
	 */
	public QueryData queryTreegrid(QueryInfo paramQueryInfo, String idField, String pidField, String startId) {
		// 传入当前父id
		List<QueryField> queryFields = paramQueryInfo.getQueryFields();
		boolean findPidField = false;
		for (int i = 0; i < queryFields.size(); i++) {
			QueryField qf = queryFields.get(i);
			if (qf.getFieldName().equals(pidField)) {
				qf.setFieldStringValue(startId);
				findPidField = true;
			}
		}
		if (!findPidField) {
			QueryField qf = new QueryField();
			qf.setFieldName(pidField);
			qf.setFieldStringValue(startId);
			if (null == startId || "".equals(startId)) {
				qf.setOperator("isnull");
			}
			queryFields.add(qf);
		}
		paramQueryInfo.setQueryFields(queryFields);

		QueryData result = this.query(paramQueryInfo);
		List root = result.getDataList();
		for (int i = 0; i < root.size(); i++) {
			// 遍历结果行
			Object bm = root.get(i);
			// 取得child
			Object id = "";
			try {
				id = BeanUtils.getProperty(bm, idField);
				BeanUtils.setProperty(bm, "children", this.queryTreeGridChild(paramQueryInfo, idField, pidField, id));
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return result;
	}

	private List<?> queryTreeGridChild(QueryInfo paramQueryInfo, String idField, String pidField, Object pid) {
		if (pid != null && !"".equals(pid)) {
			String querySource = paramQueryInfo.getQuerySource();
			List<QueryField> queryFields = paramQueryInfo.getQueryFields();
			boolean findPidField = false;
			for (int i = 0; i < queryFields.size(); i++) {
				QueryField qf = queryFields.get(i);
				if (qf.getFieldName().equals(pidField)) {
					qf.setFieldStringValue(pid.toString());
					qf.setOperator("=");
					findPidField = true;
				}
			}
			if (!findPidField) {
				throw new SysException("no pidField define in function queryTreeGridChild!!!");
			}
			paramQueryInfo.setQueryFields(queryFields);
			QueryData result = this.query(paramQueryInfo);
			List thisList = result.getDataList();
			for (int i = 0; i < thisList.size(); i++) {
				// 遍历结果行
				Object bm = thisList.get(i);
				// 取得child
				Object id = "";
				try {
					id = BeanUtils.getProperty(bm, idField);
					List<?> thisChild = this.queryTreeGridChild(paramQueryInfo, idField, pidField, id);
					BeanUtils.setProperty(bm, "children", thisChild);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return thisList;
		} else {
			return null;
		}
	}

	/**
	 * 主要用于tree值填充
	 */
	public QueryData queryTree(QueryInfo paramQueryInfo, String idField, String textField, String pidField, String startId, int openLevel) {
		// CommonQuery<?> commonQuery = this.getModelQuery(paramQueryInfo);
		// if (startId == null || "".equals(startId)) {
		// commonQuery.addCondition(Condition.isNull(pidField));
		// } else {
		// commonQuery.addCondition(Condition.eq(idField, startId));
		// }
		// List<?> root = commonQuery.query(this.dao);
		// List<Map> treeRoot = new ArrayList();
		QueryData result = this.queryTreegrid(paramQueryInfo, idField, pidField, startId);
		List root = result.getDataList();
		List treeRoot = this.prepareTreeFromGridtree(root, idField, textField, openLevel);
		result = new QueryData(paramQueryInfo.getPagingInfo());
		result.setDataList(treeRoot);
		return result;
	}

	public List prepareTreeFromGridtree(List sourceList, String idField, String textField, int openLevel) {
		List resultList = new ArrayList();
		openLevel = openLevel - 1;
		for (int i = 0; i < sourceList.size(); i++) {
			// 遍历结果行
			Object bm = sourceList.get(i);
			Map node = new HashMap();
			// 取得child
			Object id = "";
			try {
				id = BeanUtils.getProperty(bm, idField);
				node.put("id", id);
				node.put("text", BeanUtils.getProperty(bm, textField));
				Object childs = PropertyUtils.getSimpleProperty(bm, "children");
				List resultChilds = null;
				if (null == childs || ((List) childs).size() == 0) {
					node.put("state", "open");
				} else {
					if (openLevel > 0) {
						node.put("state", "open");
					} else {
						node.put("state", "closed");
					}
					resultChilds = this.prepareTreeFromGridtree((List) childs, idField, textField, openLevel);
				}
				node.put("children", resultChilds);
				resultList.add(node);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	// private List<Map> queryTreeChild(QueryInfo paramQueryInfo, String idField, String textField, String pidField, Object pid, int openLevel) {
	// CommonQuery<?> commonQuery = this.getModelQuery(paramQueryInfo);
	// if (pid != null && !"".equals(pid)) {
	// commonQuery.addCondition(Condition.eq(pidField, pid));
	// List<?> thisList = commonQuery.query(this.dao);
	// List<Map> tree = new ArrayList();
	// for (int i = 0; i < thisList.size(); i++) {
	// // 遍历结果行
	// Object bm = thisList.get(i);
	// Map node = new HashMap();
	// // 取得child
	// Object id = "";
	// try {
	// id = BeanUtils.getProperty(bm, idField);
	// node.put("id", BeanUtils.getProperty(bm, idField));
	// node.put("text", BeanUtils.getProperty(bm, textField));
	//
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// } catch (NoSuchMethodException e) {
	// e.printStackTrace();
	// }
	// List childs = this.queryTreeChild(paramQueryInfo, idField, textField, pidField, id, openLevel - 1);
	// if (null == childs || childs.size() == 0) {
	// node.put("state", "open");
	// } else {
	// if (openLevel > 0) {
	// node.put("state", "open");
	// } else {
	// node.put("state", "closed");
	// }
	// }
	// node.put("children", childs);
	//
	// tree.add(node);
	// }
	//
	// return tree;
	// } else {
	// return null;
	// }
	//
	// }

	/*
	 * 根据codetype类型将所有code value对应数值组装成list类型的数据，目前用于combox组件 todo 动态语句查询出的数据有null值会报错吧？
	 */
	public List<Map<String, Object>> getCodesListByType(String codeType, String orderBy, List<QueryField> param) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		SysCodeTypeModel sctm = new SysCodeTypeModel();
		if (codeType == null) {
			return result;
		}
		sctm.setCodeTypeCode(codeType);
		List<SysCodeTypeModel> codeTypes = this.sysCodeTypeManager.findByExample(sctm);
		for (int i = 0; i < codeTypes.size(); i++) {
			sctm = codeTypes.get(i);
			if (null == sctm.getCodeTypeType() || "".equals(sctm.getCodeTypeType()) || "static".equals(sctm.getCodeTypeType())) {
				SysCodeModel scm = new SysCodeModel();
				scm.setCodeTypeId(sctm.getCodeTypeId());
				List<SysCodeModel> codes = this.sysCodeManager.findByExample(scm);
				for (int j = 0; j < codes.size(); j++) {
					SysCodeModel code = codes.get(j);
					Map<String, Object> codesMap = new HashMap<String, Object>();
					codesMap.put("id", code.getCodeName()); // id:code
					codesMap.put("text", code.getCodeValue());// text
					codesMap.put("order", code.getCodeOrder());// order
					codesMap.put("desc", code.getCodeDesc());// desc
					// codesMap.put("selected",
					// true);//selected
					// System.out.println(codesMap);
					// 排序
					if (result.size() == 0) {
						result.add(codesMap);
					} else {
						if (code.getCodeOrder() != null) {
							int k = 0;
							while (k < result.size() && code.getCodeOrder() > (int) result.get(k).get("order")) {
								k++;
							}
							result.add(k, codesMap);
						} else {
							result.add(codesMap);
						}
					}
				}
			} else {
				List codes = null;
				if ("sql".equals(sctm.getCodeTypeType())) {
					String sql = "select " + sctm.getDynColName() + " , " + sctm.getDynColValue() + " from " + sctm.getDynTablename();
					String where = sctm.getDynWhere();
					if (null != where && !"".equals(where)) {
						sql += " where " + replaceComboParam(where, param);
					}
					codes = dao.findBySql(sql, orderBy, null, null);
					String ID = sctm.getDynColValue().toUpperCase();
					String TEXT = sctm.getDynColName().toUpperCase();
					for (int j = 0; null != codes && j < codes.size(); j++) {
						Map<String, Object> codesMap = new HashMap<String, Object>();
						codesMap.put("id", ((Map) codes.get(j)).get(ID)); // id:code
						codesMap.put("text", ((Map) codes.get(j)).get(TEXT));// text
						// codesMap.put("desc",
						// code.getCodeDesc());//desc
						result.add(codesMap);
					}
				} else {
					String hql = "select " + sctm.getDynColName() + " , " + sctm.getDynColValue() + " from " + sctm.getDynTablename();

//					if (null != dynWhere) {
//						hql = hql + " " + dynWhere;
//					}
					if (null != orderBy) {
						hql = hql + " " + orderBy;
					}
					codes = this.dao.getHibernateTemplate().find(hql);
					for (int j = 0; null != codes && j < codes.size(); j++) {
						Map<String, Object> codesMap = new HashMap<String, Object>();
						codesMap.put("id", ((Object[]) codes.get(j))[0].toString()); // id:code
						codesMap.put("text", ((Object[]) codes.get(j))[1].toString());// text
						// codesMap.put("desc",
						// code.getCodeDesc());//desc
						result.add(codesMap);
					}
				}
			}
		}
		return result;
	}

	/*
	 * 根据codetype类型将所有code value对应数值组装成map类型的数据，目前用于tablegrid组件 todo 动态语句查询出的数据有null值会报错吧？
	 */
	public Map<String, String> getCodeValueMapByType(String codeType, String dynWhere) {

		Map<String, String> codesMap = new HashMap<String, String>();
		SysCodeTypeModel sctm = new SysCodeTypeModel();
		sctm.setCodeTypeCode(codeType);
		List<SysCodeTypeModel> codeTypes = this.sysCodeTypeManager.findByExample(sctm);
		for (int i = 0; i < codeTypes.size(); i++) {
			sctm = codeTypes.get(i);
			if (null == sctm.getCodeTypeType() || "".equals(sctm.getCodeTypeType()) || "static".equals(sctm.getCodeTypeType())) {
				SysCodeModel scm = new SysCodeModel();
				scm.setCodeTypeId(sctm.getCodeTypeId());
				List<SysCodeModel> codes = this.sysCodeManager.findByExample(scm);
				for (int j = 0; j < codes.size(); j++) {
					SysCodeModel code = codes.get(j);
					codesMap.put(code.getCodeName(), code.getCodeValue());
				}
			} else {
				if ("sql".equals(sctm.getCodeTypeType())) {
					String sql = "select " + sctm.getDynColName() + " , " + sctm.getDynColValue() + " from " + sctm.getDynTablename();
					List codes = dao.findBySql(sql, null, null, null);
					String ID = sctm.getDynColValue().toUpperCase();
					String TEXT = sctm.getDynColName().toUpperCase();
					for (int j = 0; null != codes && j < codes.size(); j++) {
						codesMap.put(((Map) codes.get(j)).get(ID).toString(), ((Map) codes.get(j)).get(TEXT).toString());
						// Map<String, Object> codesMap = new HashMap<String, Object>();
						// codesMap.put("id", ((Map) codes.get(j)).get(ID)); // id:code
						// codesMap.put("text", ((Map) codes.get(j)).get(TEXT));// text
						// // codesMap.put("desc",
						// // code.getCodeDesc());//desc
						// result.add(codesMap);
					}
				} else {
					String hql = "select " + sctm.getDynColName() + " , " + sctm.getDynColValue() + " from " + sctm.getDynTablename();
					if (null != dynWhere) {
						hql = hql + dynWhere;
					}
					List codes = this.dao.getHibernateTemplate().find(hql);

					for (int j = 0; j < codes.size(); j++) {
						codesMap.put(((Object[]) codes.get(j))[0].toString(), ((Object[]) codes.get(j))[1].toString());
					}
				}
			}
		}
		return codesMap;
	}

	public List<String> getQueryDataItemFields(String paramString) {
		return null;
	}

	public FileToDownload exportExcel(String paramString, QueryInfo paramQueryInfo, List<FieldDefinition> paramList) {
		return null;
	}

	public static void main(String[] args) {
		// String s = "service:sysUserManger.getUserRoles";
		// String ss = s.substring(8);
		// System.out.println(ss);
		// String sss[] = ss.split(".");
		// System.out.println(sss.length);
//		String sql = "select * from SYS_LOG where [LOG_DATE > '{logDate1}']";
//		String name = "logDate1";
//		String value = "ssw";
//		sql = sql.replaceAll("\\[", "").replaceAll("\\]", "");
//		 System.out.println(sql);

//		 String s1 = sql.substring(0, sql.indexOf(name));
//		 String s2 = sql.substring(sql.indexOf(name));
//		 String s11 = s1.substring(s1.lastIndexOf("["));
//		 String s22 = s2.substring(0, s2.indexOf("]") + 1);
//		 String r = s11 + s22;
//		 sql = sql.replace(r, value);
		String sql = "where userId= '[a]'";
		sql = sql.replaceAll("\\[a\\]", "123");
		 System.out.println(sql);
	}
	
}
