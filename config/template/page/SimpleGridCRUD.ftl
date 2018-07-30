<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="../../themes/default/header.jsp"%>

<script type="text/javascript">
//<!--
$(document).ready(function(){
	query${entityName}();
	$("#${entityName}_form_win").getWlWin({width:550,height:300});
});

function query${entityName}(){
    $("#${entityName}_grid").getWlGrid().refresh($("#${entityName}_query_form").getWlForm().fieldToQueryJSON());
}

function new${entityName}(){
	$("#${entityName}_form_win").show();
	$("#${entityName}_edit_form").getWlForm().clear();
}

function edit${entityName}(){
	var ${entityName}_grid = $("#${entityName}_grid").getWlGrid();
	var rowIndex = ${entityName}_grid.getRow();
	if(rowIndex>-1){
		$("#${entityName}_form_win").show();
		var pk = {id:${entityName}_grid.getValue(${entityName}_grid.getRow(), "${idFieldName}")};
    	$("#${entityName}_edit_form").getWlForm().refresh("${entityNameLowered}Manager", "get", pk);
    }else{
    	alert("请选择要编辑的${entityChineseName}");
    }
}

function save${entityName}(){
	var ${entityName}_edit_form = $("#${entityName}_edit_form").getWlForm();
	if(!${entityName}_edit_form.validate())return;
	var param = {model:${entityName}_edit_form.fieldToJSON()};
	${entityName}_edit_form.submit("${entityNameLowered}Manager", "save", param, function(data){
		alert("保存成功");
		$("#${entityName}_form_win").hide();
		query${entityName}();
	});
}

function delete${entityName}(){
	var ${entityName}_grid = $("#${entityName}_grid").getWlGrid();
	var rowIndex = ${entityName}_grid.getRow();
	if(rowIndex>-1){
		if(!confirm("删除选中的${entityChineseName}？"))return;
		var pk = {id:${entityName}_grid.getValue(${entityName}_grid.getRow(), "${idFieldName}")};
    	$("#${entityName}_edit_form").getWlForm().submit("${entityNameLowered}Manager", "removeByPk", pk, function(data){
    		query${entityName}();
    	});
    }else{
    	alert("请选择要删除的${entityChineseName}");
    }
}
//-->
</script>

<div class="form" style="margin:10 0 10 10">
	<div class="title">查询条件</div>
	<wl:form id="${entityName}_query_form">
		<table>
<#list queryFieldNames as fieldName>
<#if fieldName_index % 3 == 0>
			<tr>
</#if>
				<wl:formfield name="${entityName}.${fieldName}" />
<#if fieldName_index % 3 == 2>
			</tr>
</#if>
</#list>
<#if queryFieldNames?size % 3 != 0>
			</tr>
</#if>
		</table>
	</wl:form>
</div>

<div class="bt_div" style="margin-left:30">
	<input type="button" class="button" value=" 查&nbsp;询 " onclick="query${entityName}()" />&nbsp;
	<input type="button" class="button" value=" 新&nbsp;增 " onclick="new${entityName}()" />&nbsp;
	<input type="button" class="button" value=" 编&nbsp;辑 " onclick="edit${entityName}()" />&nbsp;
	<input type="button" class="button" value=" 删&nbsp;除 " onclick="delete${entityName}()" />
</div>

<div class="form" style="margin-left:10">
	<div class="title">${entityChineseName}列表</div>
	<wl:grid datamodel="${entityName}Model" id="${entityName}_grid" pagesize="20">
<#list gridFieldNames as fieldName>
		<wl:col fieldname="${entityName}.${fieldName}" />
</#list>
	</wl:grid>
</div>

<div id="${entityName}_form_win" title="${entityChineseName}信息">
	<div class="form_edit">
		<wl:form id="${entityName}_edit_form" >
			<wl:formfield name="${entityName}.${idFieldName}" fieldtype="" />
			<table>
<#list editFieldNames as fieldName>
<#if fieldName_index % 2 == 0>
				<tr>
</#if>
					<wl:formfield name="${entityName}.${fieldName}" />
<#if fieldName_index % 2 == 1>
				</tr>
</#if>
</#list>
<#if editFieldNames?size % 2 != 0>
				</tr>
</#if>
			</table>
		</wl:form>
	</div>
	<div style="text-align:center">
		<input type="button" class="button" value=" 保&nbsp;存 " onclick="save${entityName}()" />&nbsp;
		<input type="button" class="button" value=" 关&nbsp;闭 " onclick="$('#${entityName}_form_win').hide()" />
	</div>
</div>

<%@include file="../../themes/default/footer.jsp"%>
