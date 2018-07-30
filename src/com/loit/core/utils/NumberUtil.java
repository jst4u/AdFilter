package com.loit.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * <p>Title: NumberUtil.java</p>
 * <p>Description: 对数字按指定格式进行格式化，或按指定数量单位格式化数字及其他对数字的操作</p>
 * <p>Copyright: Copyright (c) 2009</p>
 *
 */

public class NumberUtil {

    public static String DEFAULT_FORMAT_PATTERN = "#,##0.00";
    public static String DEFAULT_NO_VALID_DATA = "-";
	/**
	 * 在数字前面添加指定长度，重复添加字符
	 * @param formatNum		需要格式化的数值
	 * @param appendChar	重复添加的字符
	 * @param length		指定长度
	 * @return
	 */
	public static String numberFormat(int formatNum, char appendChar, int length) {
		String result = "";
		StringBuffer format = new StringBuffer();
		for (int i = 0; i < length; i++) {
			format.append(String.valueOf(appendChar));
		}
		DecimalFormat decimalFormat = new DecimalFormat(format.toString());
		result = decimalFormat.format(formatNum);
		return result;
	}

	/**
	 * 将小数按指定小数位四舍五入
	 * 
	 * @param value
	 *            传入数值
	 * @param scale
	 *            保留小数位数
	 * @return double 返回值
	 * 
	 */
	public static double roundNumber(double value, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		} else {
			BigDecimal b = new BigDecimal(Double.toString(value));
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, 4).doubleValue();
		}
	}
	
	/**
     * 按给定的模式格式化给定的对象（一般是数字，如：Integer，Double，Long或String）
     * @param obj 			要被格式化的数字对象
     * @param formatPattern 格式化模式
     * @param fractionLen 	要保留的小数位数（小数点往后推算，四舍五入）
     * @param integerLen 	要保留的整数位数（小数点往前推算）
     * @return 				格式化后的字串
     */
    public static String formatNumber(Object obj,String formatPattern,int fractionLen,int integerLen) {
        String res = "";
        DecimalFormat theFormat = null;
        try {
	        if ( (formatPattern == null) || (formatPattern.length() < 1) )
	            theFormat = new DecimalFormat(DEFAULT_FORMAT_PATTERN);
	        else
	            theFormat = new DecimalFormat(formatPattern);
	        if (fractionLen >= 0)
	            theFormat.setMaximumFractionDigits(fractionLen);
	        if (integerLen > 0)
	            theFormat.setMaximumIntegerDigits(integerLen);
	        if (obj instanceof String)
	            obj = new Double((String)obj);
	        //如果要格式化的值为Double.MIN_VALUE，则不格式化，直接返回"无数据"
	        if (obj.equals(new Double(Double.MIN_VALUE)))
	            return DEFAULT_NO_VALID_DATA;
	        res = theFormat.format(obj);
        } catch (Exception e) {
            //e.printStackTrace();
            res = (String)obj;
        }
        return res;
    }

    /**
     * 按给定的模式格式化给定的对象（一般是数字，如：Integer，Double，Long或String）
     * @param obj 			要被格式化的数字对象
     * @param formatPattern 格式化模式
     * @param fractionLen 	要保留的小数位数（小数点往后推算，四舍五入）
     * @param integerLen 	要保留的整数位数（小数点往前推算）
     * @param dataUnit		单位数量，以10为底数，dataUnit为指数来换算数值，
     * 	如dataUnit=-2时，10的-2次方为0.01，得到结果为"%",
     * dataUnit=1是单位为"十"，dataUnit=2是单位为"百"，最大取值为8，即"亿"
     * @return
     * String				格式化后的字串
     *
     */
    public static String formatNumber(Object obj,String formatPattern,int fractionLen,
        int integerLen,int dataUnit)
    {
       String res = "";
       if (obj instanceof String)
           obj = new Double((String)obj);
       //如果要格式化的值为Double.MIN_VALUE，则不格式化，直接返回"无数据"
       if (obj.equals(new Double(Double.MIN_VALUE)))
           return DEFAULT_NO_VALID_DATA;

       obj = new Double(convertValue(((Double)obj).doubleValue(),dataUnit));
       //如果数据为0，直接返回0
       if(((Double)obj).doubleValue() == 0)
           return "0";
       res = formatNumber(obj,formatPattern,fractionLen,integerLen) + getUnit(dataUnit);
       return res;
    }

    /**
     * 
     * 根据传入的单位取得对应的字符串描述
     * @param unit
     * @return
     * String
     *
     */
    public static String getUnit(int unit)
    {
       float dataUnit =(float)Math.pow(10,unit);
       if(dataUnit == 0.01)
       {
           return "%";
       }
       if(dataUnit == 1)
           return "";
       if(dataUnit == 10)
           return "十";
       if(dataUnit == 100)
           return "百";
       if(dataUnit == 1000)
           return "千";
       if(dataUnit == 10000)
           return "万";
       if(dataUnit == 100000)
           return "十万";
       if(dataUnit == 1000000)
           return "百万";
       if(dataUnit == 10000000)
           return "千万";
       if(dataUnit == 100000000)
           return "亿";
       return "";
    }

    /**
     * 将value值转换为大单位值（如100000转换为10）
     * @param value  待转换的值
     * @param dataUnit 转换的单位（1表示个位，10表示十位，100表示百位...以此类推）
     * @return 转换后的值
     */
    public static double convertValue(double value,int dataUnit)
    {
        return value/Math.pow(10,dataUnit);
    }

	/**
	 * 将数字按指定的格式，格式化为字符串，传入的参数需要先进行验证
	 * 
	 * @param num
	 * @param pattern
	 * @return String
	 * 
	 */
	public static String formatNum2String(int num, String pattern) {
		if(pattern==null) {
			pattern = "";
		}
		DecimalFormat format = new DecimalFormat(pattern);
		return format.format(num);
	}
	/**
	 * 将数字按指定的格式，格式化为字符串，传入的参数需要先进行验证
	 * 
	 * @param num
	 * @param pattern
	 * @return String
	 * 
	 */
	public static String formatNum2String(double num, String pattern) {
		if(pattern==null) {
			pattern = "";
		}
		DecimalFormat format = new DecimalFormat(pattern);
		return format.format(num);
	}

	/**
	 * 将数字按指定的格式，格式化为字符串，传入的参数需要先进行验证
	 * 
	 * @param num
	 * @param pattern
	 * @return String
	 * 
	 */
	public static String formatNum2String(long num, String pattern) {
		if(pattern==null) {
			pattern = "";
		}
		DecimalFormat format = new DecimalFormat(pattern);
		return format.format(num);
	}

	/**
	 * 将数字按指定的格式（如"#,###.##"），格式化为字符串，传入的参数需要先进行验证
	 * 
	 * @param num
	 * @param pattern
	 * @return String
	 * 
	 */
	public static String formatNum2String(BigDecimal num, String pattern) {
		if(pattern==null) {
			pattern = "";
		}
		DecimalFormat format = new DecimalFormat(pattern);
		return format.format(num.doubleValue());
	}
	
	public static Double stringToDouble(String str) {
		
		if (null == str) {
			return null;
		}
		str = str.trim();
		if(str.length() == 0){
			return null;
		}
		try {
			return new Double(str.trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 除法运算
	 * @param dividend 被除数
	 * @param divisor 除数
	 * @return
	 */
	public static double division(int dividend,int divisor){
		
		return division((double)dividend,(double)divisor);
		
	}
	/**
	 * 除法运算
	 * @param dividend 被除数
	 * @param divisor 除数
	 * @return
	 */
	public static double division(double dividend,double divisor){
		return Double.valueOf((double)dividend/(double)divisor);
	}
	/**
	 * 
	 * @param dividend 被除数
	 * @param divisor 除数
	 * @param decimalLength 保留小数位数
	 * @return
	 */
	public static String divisionToString(double dividend,double divisor,int decimalLength){
		return String.format("%."+decimalLength+"f", division(dividend, divisor));
	}
	/**
	 * 
	 * @param dividend 被除数
	 * @param divisor 除数
	 * @param decimalLength 保留小数位数
	 * @return
	 */
	public static String divisionToString(int dividend,int divisor,int decimalLength){
		return String.format("%."+decimalLength+"f", division(dividend, divisor));
	}
	
	/**
	 * 
	 * @param dividend 被除数
	 * @param divisor 除数
	 * @param decimalLength 保留小数位数
	 * @return
	 */
	public static double division(double dividend,double divisor,int decimalLength){
		BigDecimal   b   =   new   BigDecimal(division(dividend, divisor));  
		double   f1   =   b.setScale(decimalLength,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		return f1;
	}
	/**
	 * 
	 * @param dividend 被除数
	 * @param divisor 除数
	 * @param decimalLength 保留小数位数
	 * @return
	 */
	public static double division(int dividend,int divisor,int decimalLength){
		BigDecimal   b   =   new   BigDecimal(division(dividend, divisor));  
		double   f1   =   b.setScale(decimalLength,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		return f1;
	}
	
	public static void main(String [] args){
		System.out.println(division(222, -33333,4));

	}
	

}
