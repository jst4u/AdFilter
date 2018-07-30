package com.loit.tools.generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.util.StringUtils;

import com.loit.core.commom.SysConfig;
import com.loit.core.utils.SqlUtil;
import com.loit.tools.utils.ConnectionUtils;
import com.loit.tools.utils.UIUtils;

public class CrudGenerator extends Generator {

	public void generateModel(String packageName, String tableName) throws Exception {
		String className = getClassName(tableName);
		parseTable(packageName, className, tableName);
		super.generateJava(packageName + ".model", className + "Model", "/template/crud/Model.ftl");
	}

	public void generateManager(String packageName, String tableName) throws Exception {
		String className = getClassName(tableName);
		parseTable(packageName, className, tableName);
//		super.generateJava(packageName + ".service", className + "Manager", "/template/crud/Manager.ftl");
//		super.generateJava(packageName + ".service.impl", className + "ManagerImpl", "/template/crud/ManagerImpl.ftl");
//		super.generateJava(packageName + ".service", className + "Manager", "/template/crud/Manager.ftl");
		super.generateJava(packageName + ".service", className + "ManagerImpl", "/template/crud/ManagerImpl.ftl");
	}

	public void generateFieldDef(String packageName, String tableName) throws Exception {
		String className = getClassName(tableName);
		parseTable(packageName, className, tableName);
		String a = SysConfig.SOURCE_DIR + "/" + packageName.replace('.', '/') + "/model/" + className + "Fields.xml";
		super.generateFile(a, "/template/crud/Fields.ftl");
	}
	
	public void generateJsp(String packageName, String tableName) throws Exception {
		String className = getClassName(tableName);
		parseTable(packageName, className, tableName);
		String a = SysConfig.WEB_DIR + "/" + packageName.replace('.', '/')+"/" + this.dataModel.get("varName") + "CRUD.jsp";
		super.generateFile(a, "/template/page/DataGridCRUD.ftl");
	}

	private String getClassName(String tableName) {
		int a = tableName.indexOf(".");
		if (a >= 0) {
			tableName = tableName.substring(a + 1);
		}
		return SqlUtil.dbNameToJavaName(tableName, true);
	}

	private void parseTable(String packageName, String className, String tableName) throws Exception {
		try {
			Connection conn = ConnectionUtils.getConnection();// vc
			DatabaseMetaData metaData = conn.getMetaData();
			String dbUser = metaData.getUserName().toUpperCase();

			int checkTableName = tableName.indexOf(".");
			if (checkTableName >= 0) {
				dbUser = tableName.substring(0, checkTableName);
				tableName = tableName.substring(checkTableName + 1);
			}

			// 判断table是否被解析过了
			if (tableName.equals(this.dataModel.get("tableName"))) {
				return;
			}

			String varName = className.substring(0, 1).toLowerCase() + className.substring(1);
			String entityName = packageName+".model."+className+"Model";
			String managerName = varName+"Manager";
			
			this.dataModel.clear();
			this.dataModel.put("tableName", tableName);
			this.dataModel.put("packageName", packageName);
			this.dataModel.put("className", className);
			this.dataModel.put("varName", varName);
			this.dataModel.put("entityName", entityName);
			this.dataModel.put("managerName", managerName);
			
			// 获得表主键信息指针
			ResultSet resultSet = metaData.getPrimaryKeys(null, dbUser, tableName);
			List<Map<String, Object>> columns = new ArrayList<Map<String, Object>>();
			boolean isOperationLog = false;
			boolean hasDateColumns = false;
			boolean hasLobColumns = false;
			boolean hasVersionColumn = false;
			try {
				List<String> keys = new ArrayList<String>();
				// 1.表主键信息处理开始
				while (resultSet.next()) {
					keys.add(resultSet.getString("COLUMN_NAME"));
				}
				resultSet.close();

				if (keys.size() == 0) {
					this.log.info("Table with no PK not supported. Use the first column as default PK");
					UIUtils.showInformation("Warn", "Table " + tableName + " has no PK. Use the first column as default PK");
				}
				if (keys.size() > 1) {
					this.log.info("Composite PK not supported. Use the first column as default PK");
					UIUtils.showInformation("Warn", "Table " + tableName
							+ " composite PK not supported. Use the first column as default PK");
					keys.clear();
				}
				// 表主键信息处理end

				// 2.表信息处理开始
				resultSet = metaData.getTables(null, dbUser, tableName, null);
				String tableRemarks = null;

				if (resultSet.next()) {
					tableRemarks = resultSet.getString("REMARKS");
				}
				resultSet.close();

				String label = className;
				List<String> remarkLines = new ArrayList<String>();
				if ((tableRemarks != null) && (tableRemarks.trim().length() != 0)) {
					StringTokenizer fc = new StringTokenizer(tableRemarks, " 　,:;，：；\t\n");
					label = fc.nextToken();
					tableRemarks = tableRemarks.substring(label.length());
					remarkLines.addAll(Arrays.asList(StringUtils.tokenizeToStringArray(tableRemarks, "\n")));
				}
				this.dataModel.put("label", label);
				this.dataModel.put("remarkLines", remarkLines);
				// 表信息处理end

				// 3.表的列信息处理开始
				//2013.12.19   SQL Server 字段说明问题   
				ResultSet rs = null;
				if (metaData.getDriverName().contains("SQL Server")) {
					 PreparedStatement pst = null;  
						pst = conn.prepareStatement("SELECT value FROM ::fn_listextendedproperty (NULL, 'user', 'dbo', 'table', ?, 'column', default)");  
						pst.setString(1,tableName); 
						rs = pst.executeQuery();
					 }
				//2013.12.19  SQL Server 字段说明问题   
				List<String> columnNameList = new ArrayList<String>();

				resultSet = metaData.getColumns(null, dbUser, tableName, null);

				while (resultSet.next()) {
					Map<String, Object> columnInfo = new HashMap<String, Object>();
					String columnName = resultSet.getString("COLUMN_NAME");
					String fieldName = SqlUtil.dbNameToJavaName(columnName, false);
					String getterMethodName = "get" + SqlUtil.dbNameToJavaName(columnName, true);
					String setterMethodName = "set" + SqlUtil.dbNameToJavaName(columnName, true);

					if (keys.size() == 0) {
						keys.add(columnName);
					}

					columnNameList.add(columnName);

					columnInfo.put("columnName", columnName);
					columnInfo.put("fieldName", fieldName);
					columnInfo.put("getterMethodName", getterMethodName);
					columnInfo.put("setterMethodName", setterMethodName);

					int columnSize = resultSet.getInt("COLUMN_SIZE");
					int decimalDigits = resultSet.getInt("DECIMAL_DIGITS");
					int dataType = resultSet.getInt("DATA_TYPE");

					int columnLength = 0;// j
					int columnPrecision = 0;// i
					int columnScale = 0;
					int columnWidth = 0;
					String columnType = "VARCHAR";
					String fieldType = "String";
					String uiFieldType = "string";

					if (dataType == 3) {
						columnType = "DECIMAL";
						if (resultSet.getInt("DECIMAL_DIGITS") == 0) {
							columnType = columnSize < 10 ? "Integer" : "Long";
							fieldType = "Integer";
							uiFieldType = "int";

							columnPrecision = columnSize;
							columnWidth = columnSize;
						} else {
							columnType = "Double";
							fieldType = "double";
							uiFieldType = "double";

							columnPrecision = columnSize;
							columnScale = decimalDigits;
							columnWidth = columnSize + 1;
						}
					} else if (dataType == 8) {
						columnType = "DOUBLE";
						fieldType = "Double";
						uiFieldType = "double";
						columnPrecision = columnSize;
						columnScale = decimalDigits;
						columnWidth = columnSize + 1;
					} else if (dataType == 4) {
						columnType = "INTEGER";
						fieldType = "Integer";
						uiFieldType = "int";
						columnPrecision = columnSize;
						columnScale = decimalDigits;
						columnWidth = columnSize + 1;
					} else if (dataType == 1) {
						columnType = "CHAR";
						fieldType = "String";
						uiFieldType = "string";
						columnLength = columnSize;
						columnWidth = columnSize;
					} else if (dataType == 12) {
						columnType = "VARCHAR";
						fieldType = "String";
						uiFieldType = "string";
						columnLength = columnSize;
						columnWidth = columnSize;
					} else if ((dataType == 93) || (dataType == 91) || (dataType == 92)) {
						columnType = "TIMESTAMP";
						fieldType = "Date";
						hasDateColumns = true;
						if (fieldName.toLowerCase().endsWith("time")) {
							uiFieldType = "datetime";
							columnWidth = 19;
						} else if (fieldName.toLowerCase().endsWith("month")) {
							uiFieldType = "month";
							columnWidth = 10;
						} else {
							uiFieldType = "date";
							columnWidth = 10;
						}
					} else {
						if (dataType == 2004) {
							columnType = "BLOB";
							fieldType = "byte[]";
							hasLobColumns = true;
							uiFieldType = "bytes";
						} else {
							if (dataType == -4) {
								columnType = "LONGVARBINARY";
								fieldType = "byte[]";
								hasLobColumns = true;
								uiFieldType = "bytes";
							} else if (dataType == 2005) {
								columnType = "CLOB";
								fieldType = "String";
								hasLobColumns = true;
								uiFieldType = "text";
								columnWidth = 20;
							} else if (dataType == -1) {
								columnType = "LONGVARCHAR";
								fieldType = "String";
								hasLobColumns = true;
								uiFieldType = "text";
								columnWidth = 20;
							} else {
								this.log.info(tableName + "." + columnName + ", not supported column type: " + dataType
										+ ". Use String as default");
								UIUtils.showInformation("Warn", tableName + "." + columnName + ", not supported column type: "
										+ dataType + ". Use String as default");
								columnType = "VARCHAR";
								fieldType = "String";
								uiFieldType = "string";
								columnLength = columnSize;
								columnWidth = columnSize;
							}
						}
					}
					columnInfo.put("columnType", columnType);
					columnInfo.put("fieldType", fieldType);
					columnInfo.put("length", Integer.toString(columnLength));
					columnInfo.put("precision", Integer.toString(columnPrecision));
					columnInfo.put("scale", Integer.toString(columnScale));

					if (keys.contains(columnName)) {
						columnInfo.put("isPK", Boolean.valueOf(true));
						this.dataModel.put("pkColumnName", columnName);
						this.dataModel.put("pkFieldName", fieldName);
						this.dataModel.put("pkFieldType", fieldType);
						this.dataModel.put("pkFieldLength", Integer.toString(columnLength));
					} else {
						columnInfo.put("isPK", Boolean.valueOf(false));
					}

					if (("REC_VER".equalsIgnoreCase(columnName)) || ("RECORD_VERSION".equalsIgnoreCase(columnName))) {
						columnInfo.put("isVersion", Boolean.valueOf(true));
						hasVersionColumn = true;
					} else {
						columnInfo.put("isVersion", Boolean.valueOf(false));
					}
					
					
					
					String columnRemarks = resultSet.getString("REMARKS");
					
					//2013.12.19  SQL Server 字段说明问题     
					if (metaData.getDriverName().contains("SQL Server")) {
						 try {
									rs.next();
						    	    columnRemarks =rs.getString(1);
						 	} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
						      }  
						}
					//2013.12.19
					
					String columnLabel = fieldName;
					List<String> columnRemarkLines = new ArrayList<String>();
					if ((columnRemarks != null) && (columnRemarks.trim().length() != 0)) {
						StringTokenizer c = new StringTokenizer(columnRemarks, " 　,:;，：；\t\n");
						columnLabel = c.nextToken();
						columnRemarks = columnRemarks.substring(columnLabel.length());
						columnRemarkLines.addAll(Arrays.asList(StringUtils.tokenizeToStringArray(columnRemarks, "\n")));
					}
					columnInfo.put("label", columnLabel);
					columnInfo.put("remarkLines", columnRemarkLines);
					String[] remarkArray = StringUtils.tokenizeToStringArray(columnRemarks, " 　,:;，：；\t\n");
					if (remarkArray != null) {
						for (int i = 0; i < remarkArray.length; i++) {
							if (remarkArray[i].startsWith("selectCode.")) {
								uiFieldType = remarkArray[i];
								columnWidth = 20;
								break;
							}
						}

					}

					columnInfo.put("uiFieldType", uiFieldType);

					if (columnWidth < 10) {
						columnWidth = 10;
					}
					if (columnWidth > 20) {
						columnWidth = 20;
					}
					columnWidth *= 10;
					columnInfo.put("width", Integer.toString(columnWidth));

					columnInfo.put("nullable", "YES".equalsIgnoreCase(resultSet.getString("IS_NULLABLE")) ? "true" : "false");
					columnInfo.put("sortable", (dataType != 2004) && (dataType != 2005) ? "true" : "false");

					columns.add(columnInfo);
				}

				if (columnNameList.containsAll(Arrays.asList(new String[] { "creator", "createTime", "modifier", "modifyTime" }))) {
					isOperationLog = true;
				}
			} finally {
				try {
					resultSet.close();
					
				} catch (Exception localException1) {
				}
			}
			this.dataModel.put("columns", columns);
			this.dataModel.put("implementsOperationLog", Boolean.valueOf(isOperationLog));
			this.dataModel.put("hasDateColumns", Boolean.valueOf(hasDateColumns));
			this.dataModel.put("hasLobColumns", Boolean.valueOf(hasLobColumns));
			this.dataModel.put("hasVersionColumn", Boolean.valueOf(hasVersionColumn));
		} catch (Exception uc) {
			this.dataModel.clear();
			throw uc;
		}
	}

}
