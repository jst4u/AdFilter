package com.loit.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern ;
import java.util.regex.Matcher;

public class StringUtil {
	
	/**
	 * 编码转换 8859_1 转 gbk
	 * @param str 被转换的字符串
	 * @return 转换后的字符串
	 */
	public static String toGBK(String str) {
		try {
			str = new String(str.getBytes("8859_1") ,"GBK" ) ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 编码转换 gbk 转 utf-8
	 * @param str 被转换的字符串
	 * @return 转换后的字符串
	 */
	public static String toUTF8(String str){
		try{
			str = new String(str.getBytes("gbk"),"utf-8") ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
	/**
     * 将字符串的首字母改为大写
     * @param str  需要改写的字符串
     * @return 改写后的字符串
     */
    public static String firstToUpper(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
	
	public static String ArrayToString(String[] as){
		return StringUtil.ArrayToString(as, ",");
	}
	
	public static String ArrayToString(String[] as,String splitString){
		if(null == as || as.length == 0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<as.length;i++){
			sb.append(as[i]);
			sb.append(splitString);
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	/**
	 * 判断字符是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() < 1);
	}
	/**
	 * 编号转意 eg:(宣)2010-000465  ==> [2010]第000465号
	 * @param str
	 * @return
	 */
	public static String toforprint(String str) {
//		str = str.substring(3);
		str  = str.substring(str.length()-11, str.length());
//		str=str.split(")")[1];
		System.out.println(str);
		String[] shus = str.split("-");
		return "["+shus[0]+"]第"+shus[1]+"号";
	}
	//判断字符串是否是数字  -whj
	public static boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
		{
			return false;
		}
			return true;
	} 
	
	public static String obj2String(Object obj){
		if(obj == null){
			return "";
		}else{
			return obj.toString();
		}
	}
	
	
	public static void main(String[] args){
		Boolean str = StringUtil.isNumeric("434");
		System.out.println(str);
	}
}
