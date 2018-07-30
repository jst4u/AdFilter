package com.loit.core.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 *1.取得汉字拼音码
 *2.将句子中的汉字按照指定格式转换成拼音
 *依赖pingyin4j-2.5.0.jar
 * 
 */
public class PyUtil {
	/**
	 * 将s中的汉字转换成拼音，其他字符不变
	 * @param s:需要转换拼音的字符串
	 * @return 转换后的结果，全部小写
	 * 		   
	 */
	public static String getPy(String s){
		if(null == s || s.length() == 0){
			return "";
		}
		StringBuffer py = new StringBuffer();
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		char w = ' ';
		String[] wpy = null;
		try {
			for(int i=0;i<s.length();i++){
				w = s.charAt(i);
				wpy = PinyinHelper.toHanyuPinyinStringArray(s.charAt(i), outputFormat);
				if(null == wpy){
					py.append(w);
				}else{
					py.append(wpy[0]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			py.append(w);
		}
		return py.toString();
	}
	
	/**
	 * 对多音字，取得其所有的音，不带音调
	 * 例如“单”：dan,chan,shan
	 * @param char s = '单'
	 * @return String pys[] = {"dan","chan","shan"}
	 */
	public static String[] getPyMany(char s){
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		return PyUtil.getPyChar(s, outputFormat);
	}
	/**
	 * 对多音字，取得其所有的音，带音调,音调标在元音上
	 * 例如“单”：dan,chan,shan
	 * @param char s = '单'
	 * @return String pys[] = {"dan","chan","shan"}
	 */
	public static String[] getPyManyWithTone(char s){
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
		return PyUtil.getPyChar(s, outputFormat);
	}
	/**
	 * 对多音字，取得其所有的音，带音调,音调用数字1-4表示
	 * 例如“单”：dan1,chan2,shan4
	 * @param char s = '单'
	 * @return String pys[] = {"dan1","chan2","shan4"}
	 */
	public static String[] getPyManyWithToneNumber(char s){
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
		return PyUtil.getPyChar(s, outputFormat);
	}
	private static String[] getPyChar(char s,HanyuPinyinOutputFormat outputFormat){
		String[] py = null;
		try {
			py = PinyinHelper.toHanyuPinyinStringArray(s, outputFormat);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			return null;
		}
		return py;
	}
	
//	public String getPyWithTone(String s){
//		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
//		
//		return s;
//	}
	public String getPyWithTone(char s){
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
		try {
			String[] py = PinyinHelper.toHanyuPinyinStringArray(s, outputFormat);
			if(null == py){
				return new StringBuffer(s).toString();
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			return "-";
		}
		return "";
	}
	public String getPyWithToneNumber(String s){
		
		return s;
	}
	
	public static void main(String[] args){

		try {
			System.out.println(StringUtil.ArrayToString(PyUtil.getPyMany('单')));
			System.out.println(StringUtil.ArrayToString(PyUtil.getPyManyWithTone('单')));
//			System.out.println(StringUtils.ArrayToString(PyUtils.getPyManyWithToneNumber('单')));
//			HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
//			outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
//			outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//			System.out.println(PinyinHelper.toHanyuPinyinStringArray('牛', outputFormat)[0]);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
