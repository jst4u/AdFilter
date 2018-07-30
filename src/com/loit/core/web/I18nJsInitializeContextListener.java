package com.loit.core.web;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.io.FileUtils;

//TODO 
public class I18nJsInitializeContextListener implements ServletContextListener {
	private static final String a = "i18n";

	public void contextInitialized(ServletContextEvent event) {
		try {
//			String contextName = event.getServletContext().getServletContextName();//p
//			String contextPath = event.getServletContext().getRealPath("/");//n
//			if (contextPath == null) {
//				contextPath = event.getServletContext().getResource("/").getPath();
//			}
//			if (contextPath == null) {
//				contextPath = "";
//			}
//			String separator = System.getProperty("file.separator");
//			if (!contextPath.endsWith(separator)){
//				contextPath = contextPath + separator;
//			}
//			
//			String p = System.getProperty("file.separator");
//			if (!contextPath.endsWith(p))
//				contextPath = contextPath + p;
//			
//			Properties pp= new Properties();//o
//			pp.load(getClass().getResourceAsStream("/basenames.properties"));
//			String[] pps = pp.getProperty("basenames").split(",");//
//			String[] m;
//			String[] arrayOfString1;
//			int j = (arrayOfString1 = o.getProperty("locales")
//					.split(",")).length;
//
//			int i = 0;
//			for (tmpTernaryOp = i; i < j; i++) {
//				String l = arrayOfString1[i];
//				StringBuilder k = new StringBuilder();
//				k.append("i18n={};\n");
//				Set j = new HashSet();
//				String[] arrayOfString2;
//				int m = (arrayOfString2 = n).length;
//				int k = 0;
//				for (tmpTernaryOp = k; k < m; k++) {
//					String i = arrayOfString2[k];
//					ResourceBundle h = ResourceBundle
//							.getBundle(i,
//									org.springframework.util.StringUtils
//											.parseLocaleString(l));
//					Enumeration localEnumeration1 = h
//							.getKeys();
//					Enumeration g;
//					tmpTernaryOp = g;
//					while (g.hasMoreElements()) {
//						String f = (String) g
//								.nextElement();
//						if (f.lastIndexOf(".") >= 0) {
//							String e = f.substring(
//									0,
//									f.lastIndexOf("."));
//							if (!j.contains(e)) {
//								String[] c = e.split("\\.");
//								int n = 1;
//								int b;
//								for (tmpTernaryOp = b; b <= c.length; b++) {
//									String a = org.apache.commons.lang.StringUtils
//											.join(c,
//													".",
//													0,
//													b);
//									if (!j.contains(a)) {
//										j.add(a);
//										k.append("i18n."
//												+ a
//												+ "={};\n");
//									}
//								}
//							}
//						}
//						String d = h.getString(f);
//						d = d.replace("'", "\\'");
//						k.append("i18n." + f + "='" + d
//								+ "';\n");
//					}
//				}
//				FileUtils.writeStringToFile(new File(contextPath
//						+ "i18n_" + l + ".js"),
//						k.toString(), "UTF-8");
//			}
		} catch (Exception q) {
			throw new RuntimeException(q);
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}
}