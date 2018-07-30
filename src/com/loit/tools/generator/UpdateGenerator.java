package com.loit.tools.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.loit.core.commom.SysConfig;
import com.loit.core.utils.SqlUtil;

public class UpdateGenerator extends Generator
{
  public void generateUpdate(String packageName, String updateName, String sql, Map<String, String> paramTypes)
    throws Exception
  {
    if (updateName.toLowerCase().endsWith("update")) {
      updateName = updateName.substring(0, updateName.length() - 6);
    }
    String b = updateName.substring(0, 1).toUpperCase() + updateName.substring(1);
    a(packageName, b, sql, paramTypes);
    String a = SysConfig.SOURCE_DIR + "/" + packageName.replace('.', '/') + "/" + b + "Update.hbm.xml";
    super.generateFile(a, "/template/update/UpdateHbmXml.ftl");
    super.generateJava(packageName, b + "UpdateCondition", "/template/update/UpdateCondition.ftl");
  }

  private void a(String packageName, String className, String sql, Map<String, String> paramTypes) throws Exception
  {
    try {
      this.dataModel.clear();
      this.dataModel.put("packageName", packageName);
      this.dataModel.put("className", className);

      String javaSql = SqlUtil.convertParametersToJavaStyle(sql);
      Map SqlParameters = SqlUtil.getSqlParameters(javaSql);

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

//      tmpTernaryOp = (localIterator = new LinkedHashSet(j.values()).iterator());
    }
    catch (Exception ex)
    {
      List h;
      boolean g;
      Iterator localIterator;
      String[] e;
      Object d;
      String[] arrayOfString1;
      int j;
      int i;
      this.dataModel.clear();
      throw ex;
    }
  }
}