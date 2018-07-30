package com.loit.core.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class HtmlUtil {
	private final static Log logger = LogFactory.getLog((HtmlUtil.class));
	public static String removeMark(String htmlMarkString){
		if(null == htmlMarkString){
			return null;
		}
		return htmlMarkString.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "")
		.replaceAll("</[a-zA-Z]+[1-9]?>", ""); 
	}
	/**
	 * 生成一组checkbox框
	 * @param itemList 
	 * @param inputName
	 * @param t_colNum 行控件数量
	 * @param checkedValueArray 默认选中的值
	 * @return
	 */
	public static String createCheckBoxHtml(List itemList,String inputName,int t_colNum,String []checkedValueArray){
		logger.debug("--------------------execute HtmlUtil.createCheckBoxHtml()---------------------");
		if(itemList==null || itemList.size()==0 || StringUtil.isEmpty(inputName)){
			return "";
		}
		int colNum=6;
		if(t_colNum>0){
			colNum=t_colNum;
		}
		StringBuffer html=new StringBuffer();
		html.append("<table class='checkbox_layout'>");
		for(int i=0;i<itemList.size();i++)
		{	
			if(i%colNum==0){
				html.append("<tr>");
			}
			Map map=(Map)itemList.get(i);
			
			String label,value;
			html.append("<td class='input2'").append(" width='").append(100/colNum).append("%'").append(" >")
			
			;
			if(map.containsKey("label")){
				
				label=(String)map.get("label");
				if(map.containsKey("value")){
					value=(String)map.get("value");
				}
				else{
					value=label;
				}
				
				if(!StringUtil.isEmpty(value)){
					String checked="";
					if(checkedValueArray!=null)
						for(int k=0;k<checkedValueArray.length;k++){
							if(checkedValueArray[k].equals(value)){
								checked="checked";
								break;
							}	
						}
					html.append("<input type='checkbox' name='")
					.append(inputName)
					.append("' value='")
					.append(value)
					.append("' ").append(checked).append(" />").append(label);
					;
				
				}
			}
			html.append("</td>");
		}
		for(int i=0;i<itemList.size()%t_colNum;i++){
			html.append("<td class='input2'").append(" width='").append(100/colNum).append("%'").append(" >&nbsp;</td>");
		}
		html.append("</table>");
		logger.debug("html:"+html.toString());
		return html.toString();
	}
	
	public static void main(String[] a){
		System.out.println(removeMark("	   \r\n\t\t\t\t            <a href=\"#\" onclick=\"viewDatas(4,$('listForm'),'view');\">邓刚荣</a>   \r\n\t\t\t\t        "));
		System.out.println(removeMark("	   \r\n\t\t\t\t            <a href=\"#\" onclick=\"viewDatas(4,$('listForm'),'view');\">邓刚荣</a>   \r\n\t\t\t\t        ").trim());
		}
}
