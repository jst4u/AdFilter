package com.loit.core.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.engine.NamedSQLQueryDefinition;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.query.ParameterMetadata;
import org.hibernate.engine.query.QueryPlanCache;
import org.hibernate.impl.SessionFactoryImpl;

import com.loit.core.spring.SpringContext;
import com.loit.core.spring.support.CustomBeanWrapper;

public class SqlUtil {

	public static final String SQL_EXTRA_CONDITIONS_MACRO = "##CONDITIONS##";
	public static final String SQL_DATA_FILTER_CONDITIONS_MACRO = "##CONDITIONS\\$\\w+\\$\\w+##";
	private static final Pattern SQL_DATA_FILTER_PATTERN = Pattern.compile("##CONDITIONS\\$\\w+\\$\\w+##");

	private static Pattern A = Pattern.compile("\\p{Lower}\\w*\\p{Upper}\\w*");

	private static Pattern a = Pattern.compile("<<.*?>>");

	public static TreeMap<Integer, String> getSqlParameters(String sql) {
		SessionFactory sessionFactory = SpringContext.getBeanOfType(SessionFactory.class);
		ParameterMetadata parameterMetadata = ((SessionFactoryImplementor) sessionFactory).getQueryPlanCache().getSQLParameterMetadata(sql);

		Set<String> parameterNames = parameterMetadata.getNamedParameterNames();

		TreeMap<Integer, String> sqlParameters = new TreeMap<Integer, String>();

		for (String name : parameterNames) {
			int[] b = parameterMetadata.getNamedParameterSourceLocations(name);

			for (int i = 0; i < b.length; i++) {

				sqlParameters.put(Integer.valueOf(b[i]), name);
			}
		}
		return sqlParameters;
	}

	public static String convertParametersToJavaStyle(String sql) {
		TreeMap<Integer, String> sqlParameters = getSqlParameters(sql);

		List<Integer> index = new ArrayList<Integer>(sqlParameters.keySet());
		Collections.reverse(index);
		StringBuilder c = new StringBuilder(sql);
		for (Iterator localIterator = index.iterator(); localIterator.hasNext();) {
			Integer b = (Integer) localIterator.next();
			String a = (String) sqlParameters.get(b);
			c.delete(b.intValue() + 1, b.intValue() + a.length() + 1);
			c.insert(b.intValue() + 1, dbNameToJavaName(a, false));
		}
		return c.toString();
	}

	public static String getDefaultRunableSql(String sql) {

		TreeMap g = getSqlParameters(sql = (sql = (sql = sql.replace("##CONDITIONS##", "0=0")).replaceAll("##CONDITIONS\\$\\w+\\$\\w+##", "0=0"))
				.replaceAll("<<.*?>>", ""));
		List f;
		Collections.reverse(f = new ArrayList(g.keySet()));

		StringBuilder e = new StringBuilder(sql);
		for (Iterator localIterator = f.iterator(); localIterator.hasNext();) {
			Integer d = (Integer) localIterator.next();
			String a = (String) g.get(d);
			e.delete(d.intValue(), d.intValue() + a.length() + 1);
			e.insert(d.intValue(), "null");
		}
		int[] c = getOrdinalParameterLocations(e.toString());

		for (int b = c.length - 1; b >= 0; b--) {
			e.delete(c[b], c[b] + 1);
			e.insert(c[b], "null");
		}

		return "select * from(" + e.toString() + ") T__ROW__ where 1=0";
	}

	public static String toSqlValue(Object value) {
		if (value == null)
			return "null";
		if ((value instanceof String))
			return "'" + value + "'";
		if ((value instanceof Number))
			return value.toString();
		if ((value instanceof Date))
			return "to_date('" + DateUtil.formatDateTime((Date) value) + "', 'yyyy-mm-dd hh24:mi:ss')";
		if (value.getClass().isArray()) {
			String[] b;
			if ((b = new String[Array.getLength(value)]).length == 0) {
				return "null";
			}

			for (int a = 0; a < b.length; a++) {
				b[a] = toSqlValue(Array.get(value, a));
			}
			return StringUtils.join(b, ", ");
		}
		return value.toString();
	}

	public static String getRunableSql(String sql, List<Object> parameterValues) {
		int[] c;
		if ((c = getOrdinalParameterLocations(sql)).length == 0) {
			return sql;
		}
		if (c.length != parameterValues.size()) {
			throw new RuntimeException("Wrong number of parameters");
		}
		StringBuilder b = new StringBuilder(sql);
		for (int a = c.length - 1; a >= 0; a--) {
			b.delete(c[a], c[a] + 1);
			b.insert(c[a], toSqlValue(parameterValues.get(a)));
		}

		return b.toString();
	}

	public static String getRunableSql(String sql, Map<String, Object> parameterValues) {
		TreeMap e;
		if ((e = getSqlParameters(sql)).size() == 0)
			return sql;
		List d;
		Collections.reverse(d = new ArrayList(e.keySet()));

		StringBuilder c = new StringBuilder(sql);

		for (Iterator localIterator = d.iterator(); localIterator.hasNext();) {
			Integer b = (Integer) localIterator.next();
			String a = (String) e.get(b);
			c.delete(b.intValue(), b.intValue() + a.length() + 1);
			c.insert(b.intValue(), toSqlValue(parameterValues.get(a)));
		}

		return c.toString();
	}

	public static int[] getOrdinalParameterLocations(String sql) {
		SessionFactory sf = SpringContext.getBeanOfType(SessionFactory.class) ;
		
		ParameterMetadata pm = ((SessionFactoryImplementor) sf).getQueryPlanCache().getSQLParameterMetadata(sql);

		int[] paramLocations = new int[pm.getOrdinalParameterCount()];
		for(int i=0;i<paramLocations.length;i++){
			paramLocations[i] = pm.getOrdinalParameterSourceLocation(i + 1);
		}
		
		return paramLocations;
	}

	public static String dbNameToJavaName(String dbName, boolean firstCharUppered) {
		if ((dbName == null) || (dbName.trim().length() == 0)) {
			return "";
		}
		if ((dbName.indexOf("_") >= 0) || (dbName.equals(dbName.toUpperCase()))) {
			dbName = dbName.toLowerCase();
		}
		String[] c = dbName.split("_");
		StringBuilder b = new StringBuilder();
		String[] arrayOfString1;
		int j = (arrayOfString1 = c).length;
		for (int i = 0; i < j; i++) {
			String a;
			if ((a = arrayOfString1[i]).length() == 0) {
				continue;
			}
			b.append(a.substring(0, 1).toUpperCase());
			b.append(a.substring(1));
		}
		if (firstCharUppered) {
			return b.toString();
		}
		return b.substring(0, 1).toLowerCase() + b.substring(1);
	}

	public static String javaNameToDbName(String name) {
		if ((name == null) || (name.trim().length() == 0)) {
			return "";
		}
		if (name.indexOf("_") >= 0) {
			return name;
		}
		if (Character.isUpperCase(name.charAt(0))) {
			name = name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		
		StringBuilder c = new StringBuilder();
		char[] arrayOfChar1 = name.toCharArray();
		
		for (int i = 0; i < arrayOfChar1.length; i++) {
			char a = arrayOfChar1[i];
			if (Character.isUpperCase(a)) {
				c.append("_");
				c.append(a);
			} else {
				c.append(Character.toUpperCase(a));
			}
		}
		return c.toString();
	}

	  public static String convertAllJavaNamesToDbNames(String sql)
	  {
	    StringBuffer c = new StringBuffer();
	    Matcher b = A.matcher(sql);
	    while (b.find()) {
	      String a = b.group();
	      b.appendReplacement(c, javaNameToDbName(a));
	    }
	    b.appendTail(c);
	    return c.toString();
	  }

	  public static Order[] parseOrderByToHibernateOrders(String orderBy) {
	    if ((orderBy == null) || (orderBy.trim().length() == 0)) {
	      return null;
	    }
	    List c = new ArrayList();
	    String[] b;
	    String[] arrayOfString1 = orderBy.split(",");
	    
	    for (int i=0; i < arrayOfString1.length; i++) { String a = arrayOfString1[i];
	      a = a.trim();
	      if (a.length() == 0) {
	        continue;
	      }
	      if (a.toLowerCase().endsWith(" asc"))
	        c.add(Order.asc(dbNameToJavaName(a.substring(0, a.length() - 4).trim(), false)));
	      else if (a.toLowerCase().endsWith(" desc"))
	        c.add(Order.desc(dbNameToJavaName(a.substring(0, a.length() - 5).trim(), false)));
	      else {
	        c.add(Order.asc(dbNameToJavaName(a.trim(), false)));
	      }
	    }
	    return (Order[])c.toArray(new Order[c.size()]);
	  }

	  public static String parseOrderByToSqlStyle(String orderBy)
	  {
	    if ((orderBy == null) || (orderBy.trim().length() == 0)) {
	      return null;
	    }
	    StringBuilder c = new StringBuilder();
	    String[] b;
	    String[] arrayOfString1 = orderBy.split(",");
	    for (int i=0; i <arrayOfString1.length; i++) { String a = arrayOfString1[i];
	      a = a.trim();
	      if (a.length() == 0) {
	        continue;
	      }
	      if (a.toLowerCase().endsWith(" asc")) {
	        c.append(javaNameToDbName(a.substring(0, a.length() - 4).trim()));
	      } else if (a.toLowerCase().endsWith(" desc")) {
	        c.append(javaNameToDbName(a.substring(0, a.length() - 5).trim()));
	        c.append(" desc");
	      } else {
	        c.append(javaNameToDbName(a.trim()));
	      }
	      c.append(", ");
	    }
	    c.setLength(c.length() - 2);
	    return c.toString();
	  }

	  public static String addExtraConditions(String sql, String sqlCondition) {
	    if ((sqlCondition != null) && (sqlCondition.trim().length() != 0)) {
	      sqlCondition = "(" + sqlCondition + ")";
	      if (sql.indexOf("##CONDITIONS##") >= 0)
	        sql = sql.replace("##CONDITIONS##", sqlCondition);
	      else
	        throw new RuntimeException("No extra conditions macro in the sql. Can not add condition: \"" + sqlCondition + "\"");
	    }
	    else {
	      sql = sql.replace("##CONDITIONS##", "0=0");
	    }
	    return sql;
	  }

	  public static String addDataFilterConditions(String sql) {
	    StringBuffer f = new StringBuffer();
	    Matcher e = SQL_DATA_FILTER_PATTERN.matcher(sql);
	    while (e.find())
	    {
	      String d = e.group();
	      String c = d.substring(d.indexOf("$") + 1, d.lastIndexOf("$"));
	      String b = d.substring(d.lastIndexOf("$") + 1, d.length() - 2);

	      String a = "0=0";

	      a = "(" + a + ")";

	      e.appendReplacement(f, a);
	    }
	    e.appendTail(f);
	    return f.toString();
	  }

	  public static String getNamedSql(String sqlName)
	  {
	    SessionFactory a;
	    return ((SessionFactoryImpl)(SessionFactory)SpringContext.getBeanOfType(SessionFactory.class))
	      .getNamedSQLQuery(sqlName).getQueryString();
	  }

//	public static String getDynamicNamedSql(String sqlName, Object valueBean) {
//		String g = getNamedSql(sqlName);
//
//		CustomBeanWrapper f = new CustomBeanWrapper(valueBean);
//		StringBuffer e = new StringBuffer();
//		Matcher d;
//		break label152;
//		c = d.group();
//		b = false;
//		break label103;
//		a = (String) localIterator.next();
//
//		b = true;
//
//		b = ParameterUtils.isParamValid(f.getPropertyValue(a)) ? (localIterator = getSqlParameters(c).values().iterator()) : false;
//
//		tmpTernaryOp = (d = a.matcher(g));
//		label103: do {
//			String c;
//			boolean b;
//			Iterator localIterator;
//			do {
//				String a;
//				break;
//			} while (localIterator.hasNext());
//
//			if (b)
//				d.appendReplacement(e, c.substring(2, c.length() - 2));
//			else
//				d.appendReplacement(e, "");
//		} while (d.find());
//
//		label152: d.appendTail(e);
//
//		return g = (g = e.toString()).replaceAll("([ \t]*\n\t*)+", "\n");
//	}
}
