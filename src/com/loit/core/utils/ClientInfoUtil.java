package com.loit.core.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class ClientInfoUtil {
	
	private static Logger logger = Logger.getLogger(ClientInfoUtil.class);

	/**
	 * @param request
	 * @return
	 */
	public static String getMACAddress(HttpServletRequest request) {		
		String ip = request.getRemoteAddr();
		String line = null;
		String address=null;
		try {
			Process pp = Runtime.getRuntime().exec("nbtstat -A "+ip);		
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());			
			LineNumberReader input = new LineNumberReader(ir);		
			for (int i = 1; i<100; i++) {
				line = input.readLine();
				if (line != null) {
					if (line.indexOf("MAC Address") > -1) {
						address = line.substring(line.indexOf("=")+1).trim() ;
					}
				}
			}
		} catch (IOException e) {			
			logger.error(e.getMessage());
		}
		String GetAdd = ip + " (" + request.getRemoteHost() + ") :" + address;
		logger.info(GetAdd);		
		return address;
	}
	
	/**
	 * @param request
	 * @return
	 */
	public static String getIPAddress(HttpServletRequest request){
		String ip = null;;
		try {
			ip = request.getRemoteAddr();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("IP:"+ip);
		return ip;
	}
}
