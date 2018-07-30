package com.loit.apps.system.service.test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;

public class ServiceClassTest {

	public static void main(String[] args) {
		try {
			String wsdl = "http://localhost:8081/esm/services/BottleService?wsdl";
			URL url = new URL(wsdl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			Client client = new Client(conn.getInputStream(), null);
			Object[] result1 = client.invoke("getName", new Object[] {"sunsw."});
			System.out.println(result1[0]);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 以下为 Clendar 测试代码
//		int yyyy, mm, dd;
//		Calendar c = Calendar.getInstance();
//		yyyy = c.get(Calendar.YEAR);
//		mm = c.get(Calendar.MONTH) + 1;
//		dd = c.get(Calendar.DAY_OF_MONTH);
//		System.out.println("year = "+yyyy);
//		System.out.println("month = "+mm);
//		System.out.println("day = "+dd);
//		// 1. System.currentTimeMillis() 获取系统时间的代码
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		String dateStr1 = ts.toString();
//		System.out.println("System.currentTimeMillis() = "+dateStr1);
//		// 2. date 为获得系统时间的另外一种方法
//		Date date = new Date();
//		String dateStr2 = new Timestamp(date.getTime()).toString();
//		System.out.println(dateStr2);
//		// 得到JAVA运行环境的一些基本属性
//		System.getProperties().list(System.out);
	}
}
