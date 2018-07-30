package com.loit.tools.generator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.loit.core.commom.SysConfig;
import com.loit.core.utils.SqlUtil;
import com.loit.tools.utils.ConnectionUtils;

public class QueryGenerator extends Generator
{
  public void generateQuery(String packageName, String queryName, String sql, Map<String, String> paramTypes, Map<String, Boolean> paramDynamics)
    throws Exception
  {
    if (queryName.toLowerCase().endsWith("query")) {
      queryName = queryName.substring(0, queryName.length() - 5);
    }
    //处理参数
    String className = queryName.substring(0, 1).toUpperCase() + queryName.substring(1);
    setDataModel(packageName, className, sql, paramTypes, paramDynamics);
    String QueryHbmXml = SysConfig.SOURCE_DIR + "/" + packageName.replace('.', '/') + "/" + className + "Query.hbm.xml";
    //生成文件
    super.generateFile(QueryHbmXml, "/template/query/QueryHbmXml.ftl");
    super.generateJava(packageName, className + "QueryItem", "/template/query/QueryItem.ftl");
    super.generateJava(packageName, className + "QueryCondition", "/template/query/QueryCondition.ftl"); } 
	private void setDataModel(String packageName, String className, String sql, Map<String, String> paramTypes, Map<String, Boolean> paramDynamics) throws Exception { 
	  try { 
		  this.dataModel.clear();
		  this.dataModel.put("packageName", packageName);
		  this.dataModel.put("className", className);

      Connection conn = ConnectionUtils.getConnection();
      
//      TreeMap SqlParameters = SqlUtil.getSqlParameters(sql = SqlUtil.convertParametersToJavaStyle(sql));
      String javaSql = SqlUtil.convertParametersToJavaStyle(sql);
      Map SqlParameters = SqlUtil.getSqlParameters(javaSql);

      Statement stat = null;
      ResultSet res = null;
      List columnInfoList = new ArrayList();
      Set columnNameSet = new HashSet();
      boolean hasDateColumns = false;
      boolean hasLobColumns = false;
      Map<String, Object> columnInfo = null;
      String fieldName;
      String setterMethodName;
      String getterMethodName;

      try { String defaultRunableSql = SqlUtil.getDefaultRunableSql(sql);

        ResultSetMetaData metaData = (res = (stat = conn.createStatement())
          .executeQuery(defaultRunableSql))
          .getMetaData();
        
        for (int i=0; i < metaData.getColumnCount(); i++) {
          columnInfo = new HashMap();
          String columnName = metaData.getColumnName(i + 1);

          if (columnNameSet.contains(columnName)) {
            throw new Exception("Duplicated column " + columnName);
          }
          columnNameSet.add(columnName);

          if (!columnName.matches("\\D\\w*")) {
            throw new Exception("Invalid column name: " + columnName);
          }

          fieldName = SqlUtil.dbNameToJavaName(columnName, false);
          getterMethodName = "get" + SqlUtil.dbNameToJavaName(columnName, true);
          setterMethodName = "set" + SqlUtil.dbNameToJavaName(columnName, true);

          columnInfo.put("columnName", columnName);
          columnInfo.put("fieldName", fieldName);
          columnInfo.put("getterMethodName", getterMethodName);
          columnInfo.put("setterMethodName", setterMethodName);

          int dataType = metaData.getColumnType(i + 1);
          String columnType = "VARCHAR";
          String fieldType = "String";
          if ((dataType == 3) || 
            (dataType == 2)) {
            columnType = "DECIMAL";
            if (metaData.getScale(i + 1) == 0)
            {
              fieldType = "Integer";

              fieldType = "Long";
            }
            else {
              fieldType = "Double";
            }
          } else if (dataType == 8) {
            columnType = "DOUBLE";
            fieldType = "Double";
          } else if (dataType == 4) {
            columnType = "INTEGER";
            fieldType = "Integer";
          } else if (dataType == 1) {
            columnType = "CHAR";
            fieldType = "String";
          } else if (dataType == 12) {
            columnType = "VARCHAR";
            fieldType = "String";
          } else if ((dataType == 93) || 
            (dataType == 91) || 
            (dataType == 92)) {
            columnType = "TIMESTAMP";
            fieldType = "Date";
            hasDateColumns = true;
          } else if (dataType == 2004) {
            columnType = "BLOB";
            fieldType = "byte[]";
            hasLobColumns = true;
          } else if (dataType == -4) {
            columnType = "LONGVARBINARY";
            fieldType = "byte[]";
            hasLobColumns = true;
          } else if (dataType == 2005) {
            columnType = "CLOB";
            fieldType = "String";
            hasLobColumns = true;
          } else if (dataType == -1) {
            columnType = "LONGVARCHAR";
            fieldType = "String";
            hasLobColumns = true;
          } else {
//            throw (q.getPrecision(o + 1) < 10 ? (o = 0) : 
//              new Exception("Not supported column type: " + c));
          }
          columnInfo.put("columnType", columnType);
          columnInfo.put("fieldType", fieldType);

          columnInfoList.add(columnInfo);
        }
      } finally {
        try {
          res.close();
        } catch (Exception localException1) {
        }
        try {
          stat.close();
        }
        catch (Exception localException2) {
        }
      }
      this.dataModel.put("columns", columnInfoList);
      this.dataModel.put("hasDateColumns", Boolean.valueOf(hasDateColumns));
      this.dataModel.put("hasLobColumns", Boolean.valueOf(hasLobColumns));

      List columnList = new ArrayList();
      boolean paramTypesEqualsDate = false;
      Iterator it = SqlParameters.values().iterator();  
      while (it.hasNext()) {
    	String columnInfoNext = (String)it.next();
    	Map<String, Object> MapColumnInfo = new HashMap<String, Object>();
        MapColumnInfo.put("fieldName", columnInfoNext);
        MapColumnInfo.put("fieldType", paramTypes.get(columnInfoNext));
        String paramTypesNext = (String)paramTypes.get(columnInfoNext);
        if ((paramTypesNext).equals("Date") || (paramTypesNext).equals("Date[]")) {
          paramTypesEqualsDate = true;
        }
        columnInfoNext = columnInfoNext.substring(0, 1).toUpperCase() + columnInfoNext.substring(1);
        MapColumnInfo.put("getterMethodName", "get" + columnInfoNext);
        MapColumnInfo.put("setterMethodName", "set" + columnInfoNext);
        columnList.add(MapColumnInfo);
      }
      this.dataModel.put("params", columnList);
      this.dataModel.put("hasDateParams", Boolean.valueOf(paramTypesEqualsDate));

      String[] sqlSplit = sql.replace("\r", "").split("\n");
      List sqlLinesList = new ArrayList();
      int sqlSplitLength =  sqlSplit.length; 
      for (int g = 0; g < sqlSplitLength; g++) { 
    	  String line = sqlSplit[g];
        Map lineMap = new HashMap();
        lineMap.put("line", line);
        sqlLinesList.add(lineMap);
      }
      this.dataModel.put("sqlLines", sqlLinesList);
    } catch (Exception ex)
    {
      this.dataModel.clear();
      throw ex;
    }
  }
}