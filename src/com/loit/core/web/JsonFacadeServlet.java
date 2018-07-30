package com.loit.core.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.omg.CORBA.portable.ApplicationException;

import com.loit.core.exception.SysException;
import com.loit.core.spring.CommonManager;
import com.loit.core.spring.SpringContext;
import com.loit.core.utils.JSONDataUtil;
import com.loit.core.web.file.FileToDownload;
import com.loit.core.web.file.FileUploadHandler;

public class JsonFacadeServlet extends HttpServlet {
	private Log logger = LogFactory.getLog(getClass());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		JSONObject jsObject = new JSONObject();//fb
		InputStream inputStream = request.getInputStream();//eb
		OutputStream outputStream = response.getOutputStream();//db
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				FileUploadHandler fileUploadHandler = (FileUploadHandler) SpringContext.getBeanOfType(FileUploadHandler.class);//cb
				String uuid = request.getParameter("uuid");//z
				String type = request.getParameter("type");//u
				DiskFileItemFactory diskFileItem = new DiskFileItemFactory();//t
				ServletFileUpload fileUpload = new ServletFileUpload(diskFileItem);//r
				FileItemIterator itemIterator= fileUpload.getItemIterator(request);
				
				while(itemIterator.hasNext()){
					FileItemStream fs =itemIterator.next();
					if( !fs.isFormField() && fs.getName().length()>0){
						String itemName = fs.getName();
						fileUploadHandler.uploadFile(uuid, type, itemName, fs.openStream());
					}
				}
				return;
			}
			String jsParam =  IOUtils.toString(inputStream, "UTF-8").trim();//ab
			if ( jsParam.length() == 0) {
				jsParam = request.getParameter("json_parameters");
			} else if (!jsParam.startsWith("{")) {
				jsParam = URLDecoder.decode(jsParam, "UTF-8");
				jsParam = jsParam.substring(jsParam.indexOf("=") + 1);
			 }
			
			jsObject = new JSONObject(jsParam) ;//x
			String serviceName = jsObject.getString("serviceName");//v
			String methodName = jsObject.getString("methodName");//s
			
			JSONObject jsObjectParam = jsObject.getJSONObject("parameters");
			int paramLength = jsObjectParam.length();//o
			Object service = SpringContext.getBean(serviceName);//n
			
			if(! (service instanceof CommonManager)){
				throw new SysException("Service " +service
						+ "\" is not extends from CommonManager!");
			}
			Method[] methods = service.getClass().getMethods();
			Method method = null;
			for(int i=0;i<methods.length;i++){
				if(methods[i].getName().equals(methodName) && methods[i].getParameterTypes().length == paramLength ){
					method = methods[i];
					break;
				}
			}
			
			if (method == null) {
				throw new SysException("Method " + methodName + " not found in service " + serviceName
						+ ", or parameters not matched");
			}

			
			Object[] h = JSONDataUtil.parseParametersJSONObject(service.getClass(), method, jsObjectParam);
			try {
				Object result = method.invoke(service, h);
        			if ((result instanceof FileToDownload)) {
        				FileToDownload d = (FileToDownload) result;
        				response.setHeader("Pragma", "public");
        				response.setHeader("Expires", "0");
        				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        				response.setHeader("Content-Type", "application/force-download");
        				response.setHeader("Content-Type", d.getContentType());
        				String b;
        				if ((b = d.getFileName()) != null) {
        					b = URLEncoder.encode(b, "UTF-8");
        					response.setHeader("Content-Disposition", "attachment;filename=" + b);
        				}
        				IOUtils.copy(d.getContent(), outputStream);
        			} else {
        				jsObject.put("result", JSONDataUtil.buildJSONValue(result));
//        				System.out.println("wlpost result:"+jsObject.toString());
        				IOUtils.write(jsObject.toString(), outputStream, "UTF-8");
        			}
			} catch (InvocationTargetException c) {
				throw c.getTargetException();
			}
		} catch (Throwable bb) {
			this.logger.error("JsonFacadeServlet error", bb);
			try {
				Throwable w;
				if ((w = ExceptionUtils.getRootCause(bb)) == null) {
					w = bb;
				}
				if ((w instanceof ApplicationException))
					jsObject.put("exception", w.getMessage());
				else {
					jsObject.put("exception", w.toString());
				}
				IOUtils.write(jsObject.toString(), outputStream, "UTF-8");
			} catch (Throwable y) {
				this.logger.error("Error returning exception info", y);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
