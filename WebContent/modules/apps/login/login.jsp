<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.loit.core.spring.SpringContext"%>
<%@page import="com.loit.core.commom.SysConfig"%>
<%@page import="com.loit.apps.system.model.*" %>
<%@page isELIgnored="true" %>
<%@taglib uri="/WEB-INF/loit.tld" prefix="loit" %>
<%@taglib uri="/WEB-INF/loitchart.tld" prefix="loitchart" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<head>
<title><%=SysConfig.getSysCfg("APP_NAME")%> - 欢迎登陆！</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="renderer" content="ie-stand">
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



<script type="text/javascript" src="<%=contextPath%>/base/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/json/jquery.json-2.4.min.js"></script>

<!-- jquery easyui plugins，user as sequences -->
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.parser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/loit/jquery.easyui.loit.js"></script><!-- base extends change for easyui -->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.panel.js"></script>
<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/plugins/jquery.layout.js"></script><!--panel resizable  -->

<script type="text/javascript" src="<%=contextPath%>/base/ui/jeui/locale/easyui-lang-<%= org.springframework.context.i18n.LocaleContextHolder.getLocale() %>.js"></script>

<script type="text/javascript" src="<%=contextPath%>/base/js/loit.js"></script>

<link href="<%=contextPath%>/modules/apps/login/css/owl.carousel.css"
	rel="stylesheet" type="text/css"/>
<link href="<%=contextPath%>/modules/apps/login/css/owl.theme.css"
	rel="stylesheet" type="text/css" />

	
<style type="text/css">
body,div,ul,li{margin:0; padding:0; font-size:12px; font-family:Arial, Helvetica, sans-serif; line-height:150%;}
body{background:url(<%=contextPath%>/modules/apps/login/images/bg.jpg) no-repeat center 0; color:#999;}
li{list-style:none;}
.clearfix{*zoom:1;}
.clearfix:before,.clearfix:after{display:table; content:""; line-height:0;}
.clearfix:after{clear:both;}
a{color:#80C65E; text-decoration:none;}
.undis{display:none;}

.logo{background-image:url(<%=contextPath%>/modules/apps/login/images/nlogo.png); width:540px; height:65px; margin:30px auto 50px;}
.w960{width:960px; margin:0 auto;}

.left{width:600px; float:left; position:relative;}
.left .pic{position:absolute; left:0; top:0;}
.left .pic div{width:600px; text-align:center; font:20px/50px "Microsoft YaHei"; color:#222;}
.left .bg{background-color:#fff; height:340px; filter:alpha(opacity=36); opacity:0.36;}
.left .bg3{background-color:#000; height:3px; filter:alpha(opacity=6); opacity:0.06; overflow:hidden;}

.right{width:330px; float:right; position:relative;}
.right .title{background-color:#fff; border-bottom:1px solid #D8EAE6;}
.right .title strong{float:left; margin-left:20px; font:20px/50px "Microsoft YaHei"; color:#888888;}
.right .title a{float:right; margin-right:20px; line-height:50px;}
.right .bg{background-color:#fff; height:290px; filter:alpha(opacity=36); opacity:0.36;}
.right .bg3{background-color:#000; height:3px; filter:alpha(opacity=6); opacity:0.06; overflow:hidden;}
.right .c{padding:25px 20px; position:absolute; left:0; top:50px;}
.right .c li{margin-bottom:10px;}

input.input_text{padding:16px 10px; font-size:14px; width:270px; background-color:#fff; border:1px solid #D8EAE5; -webkit-box-shadow:inset 0 1px 1px #eee; -moz-box-shadow:inset 0 1px 1px #eee; box-shadow:inset 0 1px 1px #eee; -webkit-transition:border linear 0.2s, box-shadow linear 0.2s;-moz-transition:border linear 0.2s, box-shadow linear 0.2s;-o-transition:border linear 0.2s, box-shadow linear 0.2s;transition:border linear 0.2s, box-shadow linear 0.2s; color:#999;}
input.input_text:focus{border-color:#75B9F0;-webkit-box-shadow:inset 0 1px 1px #eee, 0 0 8px #75B9F0; -moz-box-shadow:inset 0 1px 1px #eee, 0 0 8px #75B9F0;box-shadow:inset 0 1px 1px #eee, 0 0 8px #75B9F0; color:#333;}
input[type="checkbox"]{vertical-align:middle;}
.sub{border:none; background:url(<%=contextPath%>/modules/apps/login/images/sub.gif); width:290px; height:50px; cursor:pointer;}
.sub:hover{background-position:0 -50px;}

.footer{text-align:center; width:100%; clear:both; padding-top:50px;}
</style>
<script type="text/javascript">
$(function(){
	(function(){
		var curr = 0;
		$("#jsNav .trigger").each(function(i){
			$(this).click(function(){
				curr = i;
				$("#js .pic").eq(i).fadeIn("slow").siblings(".pic").hide();
				$(this).siblings(".trigger").removeClass("imgSelected").end().addClass("imgSelected");
				return false;
			});
		});
		
		var pg = function(flag){
			if (flag) {
				if (curr == 0) {
					todo = 2;
				} else {
					todo = (curr - 1) % 4;
				}
			} else {
				todo = (curr + 1) % 4;
			}
			$("#jsNav .trigger").eq(todo).click();
		};
		
		//自动翻
		var timer = setInterval(function(){
			todo = (curr + 1) % 4;
			$("#jsNav .trigger").eq(todo).click();
		},8000);
		
	})();
});
</script>
	
<script src="<%=contextPath%>/modules/apps/login/js/owl.carousel.js"></script>
</head>
<html>
<body>
	<div class="logo"></div>
	<loit:layout fit="true" >
	<loit:layoutCenter id="content1">
		<div id="content-wrapper">
			<div class="w960">
				<div class="left" id="js">
					<div class="pic pic01">
						<img src="<%=contextPath%>/modules/apps/login/images/gugong.jpg"><div></div>
					</div>
					<div id="jsNav" class="undis">
						<a class="trigger imgSelected" href="javascript:void(0)">1</a><a
							class=trigger href="javascript:void(0)">2</a><a class=trigger
							href="javascript:void(0)">3</a><a class=trigger
							href="javascript:void(0)">4</a>
					</div>
					<div class="bg"></div>
					<div class="bg3"></div>
				</div>
				<div id="login-box" class="right">
					<form method="post"
						action="<%=contextPath
							+ SysConfig.getSysCfg("url.login.acegi")%>"
						onkeydown="IsEnterKeyPress()">
						<div class="title clearfix">
							<strong>登录</strong><a href="">重置</a>
						</div>
						<div class="bg"></div>
						<div class="bg3"></div>
						<ul class="c">
							<li><input type="text" id="j_username" name="j_username"
								class="input-text-line input_text" placeholder="请输入用户名"
								value="admin" /></li>
							<li><input type="password" id="j_password" name="j_password"
								onblur="submit.focus()" class="input-text-line input_text"
								placeholder="请输入密码" value="888888" /></li>
							<li style="padding: 12px 0 15px;"><input id="save_username"
								type="checkbox">记住用户名<input id="save_password"
									type="checkbox" style="margin-left: 15px;">记住密码 </li>
							<li id="line-for-submit-button"><input type="submit"
								value="" type="submit" id="submit" name="submit"
								class="submit-button sub" /></li>
						</ul>
						<!--
							 <input
								type="reset" id="submit" name="submit" value="取&nbsp;&nbsp;消"
								class="submit-button" />
						-->
					</form>
				</div>
				<div class="footer">Copyright©2010-2013 Timeloit CO LTD. All
					rights reserved. Support by AppSoft Center.</div>
			</div>
		</div>
	</loit:layoutCenter>
	</loit:layout>
</body>
</html>
<script type="text/javascript">
	$(document).ready(function(){
		//判断是否为IE
        var isIE = false;  
        if (window.navigator.appName.indexOf("Microsoft") != -1 || window.navigator.appName.indexOf("Netscape") != -1){ //IE11是Netscape
            isIE= true;
        }
        var is360 = navigator.userAgent.toLowerCase().indexOf('360se')>-1 ? true : false;
        if(!isIE) {
       	 alert("为了您能方便快捷浏览，请您使用IE浏览器浏览！"); 
        } else if(isIE && is360){
            //document.body.innerText = '360浏览器';
       	 alert("为了您能方便快捷浏览，请您使用IE浏览器浏览！"); 
        }
        //判断其他浏览器
//         var ua = navigator.userAgent.toLowerCase();  
//         var ieVersion = ua.match(/msie ([\d.]+)/) ? ua.match(/msie ([\d.]+)/)[1] : 0;  
//         if(typeof(ieVersion)!="undefined"&&ieVersion>6 ){  
//            //具体操作  
//         }  
//         else{  
//            alert("为了您能方便快捷浏览，请您使用IE浏览器浏览！");  
//         } 
		$("#owl-demo").owlCarousel({
			navigation : false, // Show next and prev buttons
			slideSpeed : 300,
			paginationSpeed : 400,
			autoPlay : 3000,
	
			items : 1,
			itemsDesktop : false,
			itemsDesktopSmall : false,
			itemsTablet : false,
			itemsMobile : false
	
		});
		
	});
	
	//如果登录页面不是主页面，自动刷新整个页面到登陆页
	if (top.location != self.location) {
		top.location = self.location;
	}
	function f_onload() {
		document.all.j_username.focus();
	}

	function JumpByEnter(NextElement) {

		var lKeyCode = (navigator.appname == "Netscape") ? event.which
				: window.event.keyCode;
		if (lKeyCode == 13) {
			var username = document.all.j_username.value;
			if (username == "") {
				alert("请输入用户名");
				return false;
			}
			NextElement.focus();
		}
	}

	function IsEnterKeyPress() {
		var lKeyCode = (navigator.appname == "Netscape") ? event.which
				: event.keyCode;
		if (lKeyCode == 13) {
			login();
		} else
			return false;
	}

	function login() {
		var username = document.all.j_username.value;
		if (username == "") {
			alert("请输入用户名");
			document.all.j_username.focus();
			return;
		}
		var password = document.all.j_password.value;
		if (password == "") {
			alert("请输入密码");
			document.all.j_password.focus();
			return;
		}
		document.forms[0].submit();
	}

	function callFun(data) {
		var pos = data.indexOf("|");
		var len = data.length;
		var str = data.substr(0, pos);
		if (str == 0) {
			document.forms[0].submit();
		} else {
			alert(data.substr(pos + 1, len));
		}
	}

	//新建cookie。
	//hours为空字符串时,cookie的生存期至浏览器会话结束。hours为数字0时,建立的是一个失效的cookie,这个cookie会覆盖已经建立过的同名、同path的cookie（如果这个cookie存在）。
	function setCookie(name, value, hours, path) {
		var name = escape(name);
		var value = escape(value);
		var expires = new Date();
		expires.setTime(expires.getTime() + hours * 3600000);
		path = path == "" ? "" : ";path=" + path;
		_expires = (typeof hours) == "string" ? "" : ";expires="
				+ expires.toUTCString();
		document.cookie = name + "=" + value + _expires + path;
	}
	//获取cookie值
	function getCookieValue(name) {
		var name = escape(name);
		//读cookie属性，这将返回文档的所有cookie
		var allcookies = document.cookie;
		//查找名为name的cookie的开始位置
		name += "=";
		var pos = allcookies.indexOf(name);
		//如果找到了具有该名字的cookie，那么提取并使用它的值
		if (pos != -1) { //如果pos值为-1则说明搜索"version="失败
			var start = pos + name.length; //cookie值开始的位置
			var end = allcookies.indexOf(";", start); //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
			if (end == -1)
				end = allcookies.length; //如果end值为-1说明cookie列表里只有一个cookie
			var value = allcookies.substring(start, end); //提取cookie的值
			return unescape(value); //对它解码      
		} else
			return ""; //搜索失败，返回空字符串
	}
	//删除cookie
	function deleteCookie(name, path) {
		var name = escape(name);
		var expires = new Date(0);
		path = path == "" ? "" : ";path=" + path;
		document.cookie = name + "=" + ";expires=" + expires.toUTCString()
				+ path;
	}

	window.onload = function() {
		//分析cookie值，显示上次的登陆信息
		var userNameValue = getCookieValue("userName");
		// $("j_username").value = userNameValue;
		var passwordValue = getCookieValue("password");
		//  $("j_password").value = passwordValue;    
		if (!userNameValue == "") {
			document.getElementById("save_username").checked = true;
		} else {
			document.getElementById("save_username").checked = false;
		}
		if (!passwordValue == "") {
			document.getElementById("save_password").checked = true;
		} else {
			document.getElementById("save_password").checked = false;
		}
		//写入点击事件
		$("submit").onclick = function() {

			var userNameValue = $("j_username").value;
			var passwordValue = $("j_password").value;

			if (userNameValue == "") {
				alert("请输入用户名");
				document.all.j_username.focus();
				return;
			}

			if (passwordValue == "") {
				alert("请输入密码");
				document.all.j_password.focus();
				return;
			}

			if ($("save_username").checked) {
				setCookie("userName", $("j_username").value, 24, "/");
			} else {
				setCookie("userName", "", 24, "/");
			}
			if ($("save_password").checked) {
				setCookie("password", $("j_password").value, 24, "/");
			} else {
				setCookie("password", "", 24, "/");
			}
			document.forms[0].submit();
		}
		// $("submit").click();
	}

	//根据class获取元素
	document.getElementByClass = function(n) { 
        var el = [],
            _el = document.getElementsByTagName('*');
        for (var i=0; i<_el.length; i++ ) {

            if (_el[i].className == n ) {
                el[el.length] = _el[i];
            }
        }
        return el;
    }
	
</script>
<%
	String error = request.getParameter("error");
	if (error != null && "true".equals(error)) {
		out.println("<script>");
		out.println("alert('用户名密码错误,或用户已停用！');");
		out.println("</script>");
	}
%>
