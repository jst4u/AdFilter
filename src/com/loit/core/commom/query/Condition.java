package com.loit.core.commom.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.impl.SessionFactoryImpl;

import com.loit.core.spring.SpringContext;
import com.loit.core.utils.SqlUtil;

public class Condition {
	private String sql;// A
	private List<Object> parameters;// a

	private Condition(String sql, Object[] parameters) {
		this.sql = sql;
		if (parameters != null)
			this.parameters = Arrays.asList(parameters);
	}

	private Condition(String logicOperator, Condition[] conditions) {
		if ((conditions != null) && (conditions.length > 0)) {
			List d = new ArrayList();
			this.parameters = new ArrayList();
			for(int i=0;i<conditions.length;i++){
				Condition con = conditions[i];
				String sql = con.getSql();
				List parameters = con.getParameters();
				if(sql != null && sql.trim().length() > 0){
					d.add(sql);
				}
				if(parameters != null){
					this.parameters.addAll(parameters);
				}
			}
			this.sql = ("(" + StringUtils.join(d, new StringBuilder(" ").append(logicOperator).append(" ").toString()) + ")");
		}
	}

	public static Condition eq(String propertyName, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " = ?", new Object[] { value });
	}

	
	public static Condition ieq(String propertyName, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		SessionFactoryImpl sf =  (SessionFactoryImpl) SpringContext.getBeanOfType(SessionFactory.class);
		Dialect dialect = sf.getDialect();
		String a = dialect.getLowercaseFunction();
		return new Condition(a + "(" + propertyName + ") = " + a + "(?)", new Object[] { value });
	}

	public static Condition ne(String propertyName, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " <> ?", new Object[] { value });
	}

	public static Condition like(String propertyName, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " like ?", new Object[] { value });
	}

	public static Condition likeStart(String propertyName, Object value) {
		return like(propertyName, value + "%");
	}

	public static Condition likeEnd(String propertyName, Object value) {
		return like(propertyName, "%" + value);
	}

	public static Condition likeAnywhere(String propertyName, Object value) {
		return like(propertyName, "%" + value + "%");
	}

	public static Condition ilike(String propertyName, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		SessionFactoryImpl sf =  (SessionFactoryImpl) SpringContext.getBeanOfType(SessionFactory.class);
		Dialect dialect = sf.getDialect();
		String a = dialect.getLowercaseFunction();
		return new Condition(a + "(" + propertyName + ") like " + a + "(?)", new Object[] { value });
	}

	public static Condition ilikeStart(String propertyName, Object value) {
		return ilike(propertyName, value + "%");
	}

	public static Condition ilikeEnd(String propertyName, Object value) {
		return ilike(propertyName, "%" + value);
	}

	public static Condition ilikeAnywhere(String propertyName, Object value) {
		return ilike(propertyName, "%" + value + "%");
	}

	public static Condition gt(String propertyName, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " > ?", new Object[] { value });
	}

	public static Condition ge(String propertyName, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " >= ?", new Object[] { value });
	}

	public static Condition lt(String propertyName, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " < ?", new Object[] { value });
	}

	public static Condition le(String propertyName, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " <= ?", new Object[] { value });
	}

	public static Condition between(String propertyName, Object value1, Object value2) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " between ? and ?", new Object[] { value1, value2 });
	}

	public static Condition dateBegin(String propertyName, Date value) {
		value = DateUtils.truncate(value, 5);
		return ge(propertyName, value);
	}

	public static Condition dateEnd(String propertyName, Date value) {
		value = DateUtils.addDays(value = DateUtils.truncate(value, 5), 1);
		return lt(propertyName, value);
	}

	public static Condition dateBetween(String propertyName, Date value1, Date value2) {
		return and(new Condition[] { dateBegin(propertyName, value1), dateEnd(propertyName, value2) });
	}

	public static Condition in(String propertyName, Object[] values) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " in (?)", new Object[] { values });
	}

	public static Condition notIn(String propertyName, Object[] values) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " not in (?)", new Object[] { values });
	}

	public static Condition isNull(String propertyName) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " is null", new Object[0]);
	}

	public static Condition isNotNull(String propertyName) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		return new Condition(propertyName + " is not null", new Object[0]);
	}

	public static Condition eqProperty(String propertyName1, String propertyName2) {
		propertyName1 = SqlUtil.convertAllJavaNamesToDbNames(propertyName1);
		propertyName2 = SqlUtil.convertAllJavaNamesToDbNames(propertyName2);
		return new Condition(propertyName1 + " = " + propertyName2, new Object[0]);
	}

	public static Condition neProperty(String propertyName1, String propertyName2) {
		propertyName1 = SqlUtil.convertAllJavaNamesToDbNames(propertyName1);
		propertyName2 = SqlUtil.convertAllJavaNamesToDbNames(propertyName2);
		return new Condition(propertyName1 + " <> " + propertyName2, new Object[0]);
	}

	public static Condition gtProperty(String propertyName1, String propertyName2) {
		propertyName1 = SqlUtil.convertAllJavaNamesToDbNames(propertyName1);
		propertyName2 = SqlUtil.convertAllJavaNamesToDbNames(propertyName2);
		return new Condition(propertyName1 + " > " + propertyName2, new Object[0]);
	}

	public static Condition geProperty(String propertyName1, String propertyName2) {
		propertyName1 = SqlUtil.convertAllJavaNamesToDbNames(propertyName1);
		propertyName2 = SqlUtil.convertAllJavaNamesToDbNames(propertyName2);
		return new Condition(propertyName1 + " >= " + propertyName2, new Object[0]);
	}

	public static Condition ltProperty(String propertyName1, String propertyName2) {
		propertyName1 = SqlUtil.convertAllJavaNamesToDbNames(propertyName1);
		propertyName2 = SqlUtil.convertAllJavaNamesToDbNames(propertyName2);
		return new Condition(propertyName1 + " < " + propertyName2, new Object[0]);
	}

	public static Condition leProperty(String propertyName1, String propertyName2) {
		propertyName1 = SqlUtil.convertAllJavaNamesToDbNames(propertyName1);
		propertyName2 = SqlUtil.convertAllJavaNamesToDbNames(propertyName2);
		return new Condition(propertyName1 + " <= " + propertyName2, new Object[0]);
	}

	public static Condition and(Condition[] conditions) {
		return new Condition("and", conditions);
	}

	public static Condition and(List<Condition> conditions) {
		return new Condition("and", (Condition[]) conditions.toArray(new Condition[conditions.size()]));
	}

	public static Condition or(Condition[] conditions) {
		return new Condition("or", conditions);
	}

	public static Condition or(List<Condition> conditions) {
		return new Condition("or", (Condition[]) conditions.toArray(new Condition[conditions.size()]));
	}

	public static Condition not(Condition condition) {
		return new Condition("not (" + condition.getSql() + ")", condition.getParameters().toArray());
	}

	public static Condition operator(String propertyName, String operator, Object value) {
		propertyName = SqlUtil.convertAllJavaNamesToDbNames(propertyName);
		if ("in".equalsIgnoreCase(operator.trim())) {
			if ((value instanceof List)) {
				value = ((List) value).toArray();
			}
			return in(propertyName, (Object[]) value);
		}
		if ("notIn".equalsIgnoreCase(operator.trim())) {
			if ((value instanceof List)) {
				value = ((List) value).toArray();
			}
			return notIn(propertyName, (Object[]) value);
		}
		if ("likeStart".equalsIgnoreCase(operator.trim()))
			return likeStart(propertyName, value);
		if ("likeEnd".equalsIgnoreCase(operator.trim()))
			return likeEnd(propertyName, value);
		if ("likeAnywhere".equalsIgnoreCase(operator.trim()))
			return likeAnywhere(propertyName, value);
		if ("ilike".equalsIgnoreCase(operator.trim()))
			return ilike(propertyName, value);
		if ("ilikeStart".equalsIgnoreCase(operator.trim()))
			return ilikeStart(propertyName, value);
		if ("ilikeEnd".equalsIgnoreCase(operator.trim()))
			return ilikeEnd(propertyName, value);
		if ("ilikeAnywhere".equalsIgnoreCase(operator.trim()))
			return ilikeAnywhere(propertyName, value);
		if ("ieq".equalsIgnoreCase(operator.trim()))
			return ieq(propertyName, value);
		if ("isNull".equalsIgnoreCase(operator.trim()))
			return isNull(propertyName);
		if ("isNotNull".equalsIgnoreCase(operator.trim()))
			return isNotNull(propertyName);
		if ("dateBegin".equalsIgnoreCase(operator.trim()))
			return dateBegin(propertyName, (Date) value);
		if ("dateEnd".equalsIgnoreCase(operator.trim())) {
			return dateEnd(propertyName, (Date) value);
		}
		return new Condition(propertyName + " " + operator + " ?", new Object[] { value });
	}

	public static Condition sql(String sql, Object[] parameters) {
		return new Condition(sql, parameters);
	}

	public String getRunableSql() {
		return SqlUtil.getRunableSql(this.sql, this.parameters);
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParameters() {
		return this.parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}
}
