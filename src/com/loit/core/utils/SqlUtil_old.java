package com.loit.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class SqlUtil_old {
	
	/**
	 * 判断jdbc or hibernate 语句是否是多表查询，是否有查询条件
	 * @param sql
	 * @return
	 */
	public static int[] chekSql( String sql ) {
		int selectNum = sql.indexOf("select") ;
		int whereNum = sql.indexOf("where") ; //判断是否有where条件
		String ckSql = "" ;
		if( whereNum != -1  ) {
			ckSql =  sql.substring( sql.indexOf("from") , sql.indexOf("where"));
		}else {
			ckSql =  sql.substring( sql.indexOf("from") );
		}
		String regex = ",";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(ckSql);
		int tablecount = 1 ;
		while (m.find()) {
			tablecount ++ ;
		}
		int[] arry = {selectNum,tablecount} ;
		return arry ;
	}
	
	
	/**
	 * 通过request获取查询条件的值，并封装好查询语句
	 * @param sql
	 * @param request
	 * @return
	 */
	public static String getSql(String sql, HttpServletRequest request) {

		String regex = "\\[(.*?)\\]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(sql);
		while (m.find()) {
			String temp = m.group() ; 
			String regex1 = "\\{(.*?)\\}";
			Pattern p1 = Pattern.compile(regex1);
			Matcher m1 = p1.matcher(temp);
			while (m1.find()) {
				String val = m1.group() ; 
				String str = val.replace("{", "").replace("}", ""); //删除 { 和 }
				String[] tempStr = str.split(":");// 切割
				String key = tempStr[0]; // request请求中的参数
				String type = "" ;       //类型
				if(tempStr.length == 1)  //这里是判断如果请求的{username} 没有指定  ：s的时候默认就是字符串类型
					type = "s" ;
				else
					type = tempStr[1] ;
				if (type.equals("s")) { // 类型是字符串
					boolean falg = false ;
					if( key.indexOf("%") != -1   ) {
						key = key.replace("%", "").replace("%", "") ;
						falg = true ;
					}
					String value = request.getParameter(key);
					if (value == null || value.equals("")) {
						String strValue = "";
						Object strObj = request.getAttribute(key);
						if (strObj != null) {
							strValue = strObj.toString().trim();
							if( falg ) {
								sql = sql.replace(val, "'%"+strValue+"%'");
							}else {
								sql = sql.replace(val, "'"+strValue+"'");
							}
							
						} else {
							//log.info("request中没有hql或sql语句中封装的值 {request_A:s},请检查 {xxx:s}  ") ;
							int num = sql.indexOf("where") ;
							if( num != -1) {
								sql = sql.replace(temp, "1=1") ;
							}
						}
					} else {
						if( falg ) {
							sql = sql.replace(val, "'%"+value.trim()+"%'");
						}else {
							sql = sql.replace(val, "'"+value.trim()+"'");
						}
						
						
					}
				} else if (type.equals("int") || type.equals("i")) {// 字段是数字
					String intValue = request.getParameter(key);
					Integer value = 0;
					if (intValue != null && !intValue.equals("") ) {
						sql = sql.replace(val, intValue.trim()+"") ;
					} else {
						Object intObj = request.getAttribute(key);
						if (intObj != null && !intObj.toString().equals("") ) {
							value = Integer.parseInt(intObj.toString().trim()) ;
							sql = sql.replace(val, value+"") ;
						} else {
							//log.info("request中没有hql或sql语句中封装的值 {request_A:s},请检查 {xxx:s}  ") ;
							int num = sql.indexOf("where") ;
							if( num != -1) {
								sql = sql.replace(temp, "1=1") ;
							}
						}
					}
				}
			}
		}
		sql = sql.replace("[", "").replace("]", "") ;
		return sql ;
	}
	
	/**
	 * 封装sql
	 * @param fieldName     查询条件的字段名 jdbc请用表的字段  hibernate 请用pojo的属性名 如果是多表查询的话，请给表起别名
	 * @param parameterName 从request取值时用的key的名字
	 * @param type          该字段的类型 s代表字符串  int or  i 代表数字
	 * @param request       请求对象
	 * @return
	 */
	public static String appendSql(String fieldName , String parameterName , String type ,HttpServletRequest request ) {
		String value = request.getParameter(parameterName) ;
		StringBuffer buffer = new StringBuffer() ;
		if( null != value && !value.equals("") ) {
			String parameterValue = "" ;
			if(type.equals("s")) {
				parameterValue = "'"+value+"'" ;
			}else if(type.equals("int") || type.equals("i")) {
				parameterValue = value ;
			}
			buffer.append(" and " + fieldName + " = " + parameterValue +" " ) ;
		}else {
			buffer.append(" and 1=1 ") ;
		}
		return buffer.toString() ;
	}
	
	/**
	 * 以in的形式构建查询条件
	 * @param columnName
	 * @param stypeArray
	 * @return
	 */
	public static String createInCondition(String columnName,String[]stypeArray){
		if(stypeArray==null || stypeArray.length==0){
			return "";
		}
		StringBuffer conditionSql=new StringBuffer();
		conditionSql.append(" and ").append(columnName).append(" in(");
		for (int i = 0; i < stypeArray.length; i++) {
			if(i!=0){
				conditionSql.append(",");
			}
			conditionSql.append("'").append(stypeArray[i]).append("'");	
		}
		conditionSql.append(")");
		
		return conditionSql.toString();
	}
	
	public static String appendSqlInt(String fieldName , String parameterName,HttpServletRequest request ) {
		return SqlUtil_old.appendSql(fieldName, parameterName, "int", request);
	}
	
	public static String appendSqlString(String fieldName , String parameterName,HttpServletRequest request ) {
		return SqlUtil_old.appendSql(fieldName, parameterName, "s", request);
	}
}
