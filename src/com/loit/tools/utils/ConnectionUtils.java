package com.loit.tools.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import oracle.jdbc.driver.OracleConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.spring.SpringUtils;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.loit.core.spring.SpringContext;

public class ConnectionUtils {

	private static final Log logger = LogFactory.getLog(ConnectionUtils.class);
	private static Connection conn;

	private static void initConn() throws SQLException {
		logger.debug("Get connection");
		DataSource datasource = SessionFactoryUtils.getDataSource((SessionFactory) SpringContext.getBeanOfType(SessionFactory.class));
		conn = datasource.getConnection();
		NativeJdbcExtractor nativeJdbc = (NativeJdbcExtractor) SpringContext.getBeanOfType(NativeJdbcExtractor.class);
		conn = nativeJdbc.getNativeConnection(conn);
		if ((conn instanceof OracleConnection)) {
			logger.debug("OracleConnection, enable RemarksReporting");
			((OracleConnection) conn).setRemarksReporting(true);
		}
	}

	public static Connection getConnection() throws SQLException {
		if (conn == null || conn.isClosed() ) {
			synchronized (ConnectionUtils.class) {
					initConn();
			}
		}
		return conn;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
