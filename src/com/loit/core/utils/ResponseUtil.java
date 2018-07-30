package com.loit.core.utils;

import javax.servlet.http.HttpServletResponse;

import com.loit.core.exception.SysException;

public class ResponseUtil {
	private ResponseUtil(){
	}
	
	public static void setExcel(HttpServletResponse response,String fileName) throws SysException{
		//response.setContentType( "application/vnd.ms-excel;charset=utf-8"); 
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
//		response.setHeader( "Content-disposition",   "attachment;filename="+new String(fileName.getBytes(),"utf8")+".xls"); 
		try{
			response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"),"iso-8859-1"));
			//response.setHeader( "Content-disposition",   "attachment;filename=\""+fileName+".xls\""); 
		}catch(Exception e){
			throw new SysException(e);
		}
	}
}
