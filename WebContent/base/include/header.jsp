<%@page import="com.loit.core.spring.SpringContext"%>
<%@page import="com.loit.core.commom.SysConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/loit.tld" prefix="loit" %>
<%@ taglib uri="/WEB-INF/loitchart.tld" prefix="loitchart" %>
<%
	String contextPath = request.getContextPath();
	String themesName =request.getParameter("tn");
	if(null == themesName || themesName.equals("")){//url指定的样式级别最高
		themesName = (String)request.getSession().getAttribute("THEME_DEFAULT");//当前用户session的样式级别第二
			if(null == themesName || themesName.equals("")){//都没有 就取系统默认样式
				themesName = SysConfig.getSysCfg("THEME_DEFAULT");
		}
		if(null == themesName || themesName.equals("")){//系统忘了配置了，设置一个最默认的
			themesName = "default";
		}
	}else{
		if(",bootstrap,black,cupertino,dark,default,gray,metro,mblue,mgray,mgreen,morange,mred,".indexOf(","+themesName+",")<0){
			themesName = "default";
		}
	}
	 request.getSession().setAttribute("THEME_DEFAULT",themesName);
	 
	 String themesNameChart =request.getParameter("tnc");
	 if(null == themesNameChart || themesNameChart.equals("")){//url指定的样式级别最高
		 themesNameChart = (String)request.getSession().getAttribute("THEME_CHART_DEFAULT");
		 if(null == themesNameChart || themesNameChart.equals("")){//都没有 就取系统默认样式
			 themesNameChart = SysConfig.getSysCfg("THEME_CHART_DEFAULT");
		 }
		 if(null == themesNameChart || themesNameChart.equals("")){//系统忘了配置了，设置一个最默认的
		 	themesNameChart = "skies";
		 }
	 }else{
			if(",dark-blue,dark-green,gray,grid,skies,".indexOf(","+themesNameChart+",")<0){
				themesNameChart = "skies";
			}
		}
	 request.getSession().setAttribute("THEME_CHART_DEFAULT",themesNameChart);
%>

<script type="text/javascript">

	var contextPath = "<%=contextPath%>";
	var APP_CONTEXT_PATH =  "<%=contextPath%>";
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link class="ui_themes_class"  rel="stylesheet" type="text/css" href="<%=contextPath%>/base/ui/jeui/themes/icon.css" />
<link class="ui_themes_class"  rel="stylesheet" type="text/css" href="<%=contextPath%>/base/ui/jeui/themes/<%=themesName%>/easyui.css" />
<link class="ui_themes_class"  rel="stylesheet" type="text/css" href="<%=contextPath%>/base/ui/ztree/css/zTreeStyle.css" />
<link class="ui_themes_class"  rel="stylesheet" type="text/css" href="<%=contextPath%>/base/ui/loit/loit.css" />
<link class="ui_themes_class"  rel="stylesheet" type="text/css" href="<%=contextPath%>/modules/portal/default/rs/frame.css" />
<link class="ui_themes_class"  rel="stylesheet" type="text/css" href="<%=contextPath%>/base/ui/loit/themes/<%=themesName%>/loit.css" />

<script type="text/javascript" src="<%=contextPath%>/base/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/json/jquery.json-2.4.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/base/ui/hchart/highcharts.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/hchart/highcharts-more.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/hchart/modules/data.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/hchart/modules/exporting.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/hchart/themes/<%=themesNameChart%>.js"></script>

<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/highchart.loit.js"></script>

<!-- jquery easyui plugins，user as sequences -->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.parser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/jquery.easyui.loit.js"></script><!-- base extends change for easyui -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/My97DatePicker/WdatePicker.js"></script><!-- 扩展EasyUI日期控件 -->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.draggable.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.droppable.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.resizable.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.panel.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.menu.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.linkbutton.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.progressbar.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.tooltip.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.calendar.js"></script>

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.form.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/plugins/jquery.easyui.loit.form.js"></script><!--easyui form-->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.slider.js"></script><!--draggable-->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.pagination.js"></script><!--linkbutton  -->



<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.accordion.js"></script><!--panel  -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.splitbutton.js"></script><!--menu linkbutton  -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.menubutton.js"></script><!--menu linkbutton  -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.searchbox.js"></script><!--menubutton  -->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.window.js"></script><!--draggable panel resizable  -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.dialog.js"></script><!--window  linkbutton -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.messager.js"></script><!--window  linkbutton progressbar-->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.layout.js"></script><!--panel resizable  -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.tabs.js"></script><!--panel linkbutton  -->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.validatebox.js"></script><!--tooltip  -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/plugins/jquery.easyui.loit.validatebox.js"></script><!--easyui validatebox-->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.combo.js"></script><!--validatebox  panel-->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.combobox.js"></script><!--combo-->
<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/plugins/jquery.easyui.loit.combobox.js"></script><!--easyui datagrid-->

<!-- im -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.tree.js"></script><!--draggable droppable-->
<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/plugins/jquery.easyui.loit.tree.js"></script><!--easyui tree-->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.datagrid.js"></script><!--panel resizable linkbutton pagination-->
<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/plugins/jquery.easyui.loit.datagrid.js"></script><!--easyui datagrid-->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.treegrid.js"></script><!--datagrid-->
<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/plugins/jquery.easyui.loit.treegrid.js"></script><!--easyui treegrid-->
<!-- im -->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.propertygrid.js"></script><!--datagrid-->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.combotree.js"></script><!--combo tree-->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.combogrid.js"></script><!--combo datagrid-->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.numberbox.js"></script><!--validatebox -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.datebox.js"></script><!--combo calendar-->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.spinner.js"></script><!--validatebox  -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.numberspinner.js"></script><!--spinner  numberbox-->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.timespinner.js"></script><!--spinner  -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.datetimebox.js"></script><!--datebox timespinner-->

<%-- <script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/locale/easyui-lang-<%= org.springframework.context.i18n.LocaleContextHolder.getLocale() %>.js"></script> --%>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="<%=contextPath%>/base/js/loit.js"></script>

<script type="text/javascript" src="<%=contextPath%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=contextPath%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/dwr_interfaces.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/My97DatePicker/WdatePicker.js"></script>  
<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/plugins/jquery.my97.js"></script>  

