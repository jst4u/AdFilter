package com.loit.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetUtil {
	
	/**
	 * 获取当前用户的IP
	 * @return
	 */
	public static String getIp( ) {
		try {
			InetAddress myIPaddress = InetAddress.getLocalHost();
			return myIPaddress.getHostAddress() ;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "" ;
		}
	}
}
