package com.loit.core.dwr;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.FileUtils;

import com.loit.core.spring.CommonManager;
import com.loit.core.spring.SpringContext;
import com.loit.core.utils.SpringBeanUtils;

public class DwrInterfacesJsInitializeContextListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {
		try {
			String contextName = event.getServletContext().getContextPath();// event.getServletContext().getServletContextName();//p
			String contextPath = event.getServletContext().getRealPath("/");// n
			if (contextPath == null) {
				contextPath = event.getServletContext().getResource("/").getPath();
			}
			if (contextPath == null) {
				contextPath = "";
			}
			String separator = System.getProperty("file.separator");
			if (!contextPath.endsWith(separator)) {
				contextPath = contextPath + separator;
			}

			StringBuilder jScript = new StringBuilder();// l
			jScript.append("if (typeof dwr == 'undefined' || dwr.engine == 'undefined') throw new Error('You must include DWR engine before including this file');\n");
			jScript.append("var DWR_SERVICE_PATH = '" + contextName + "/dwr';\n");
			jScript.append("(function() {\n");

			Map<String, CommonManager> managers = SpringContext.getBeansOfType(CommonManager.class);// k
			Iterator<String> managerIter = managers.keySet().iterator();
			while (managerIter.hasNext()) {
				String managerName = managerIter.next();
				System.out.println("===bean name:" + managerName);
				Object manager = managers.get(managerName);
				Class managerClass = SpringBeanUtils.RealClassForCGLIBean(manager.getClass());
				String jsManagerName = managerName; // managerName.substring(0, 1).toUpperCase() + managerName.substring(1);
				jScript.append("if (dwr.engine._getObject('" + jsManagerName + "') == undefined) { var " + jsManagerName + " = {};\n");
				Method[] methods = managerClass.getDeclaredMethods();// f
				for (int i = 0; i < methods.length; i++) {
					Method m = methods[i];
					String mName = m.getName();
					StringBuilder mJScript = new StringBuilder();
					for (int j = 0; j < m.getParameterTypes().length; j++) {
						mJScript.append("p" + j + ",");
					}
					jScript.append(jsManagerName + "." + mName + " = function(");
					jScript.append(mJScript + "callback) {dwr.engine._execute(DWR_SERVICE_PATH, '");
					jScript.append(jsManagerName + "', '" + mName + "', " + "arguments);};\n");
				}
				jScript.append("dwr.engine._setObject('" + jsManagerName + "', " + jsManagerName + ");\n");
				jScript.append("}\n");
			}
			jScript.append("})();\n");
			File jsFile = new File(contextPath + "dwr_interfaces.js");
			FileUtils.writeStringToFile(jsFile, jScript.toString(), "UTF-8");
			System.out.println("DWR,dwr_interfaces.js file path:" + jsFile.getPath());
		} catch (Exception o) {
			throw new RuntimeException(o);
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

}
