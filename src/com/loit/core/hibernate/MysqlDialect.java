package com.loit.core.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;
import java.sql.Types;

public class MysqlDialect extends MySQL5Dialect{

	public MysqlDialect() {
		super();
		registerHibernateType(Types.LONGVARCHAR, Hibernate.STRING.getName());
	}
}
