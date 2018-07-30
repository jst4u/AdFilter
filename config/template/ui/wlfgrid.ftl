<!--fgrid begin-->
<div id="${gridId}" class="grid_panel">
	<div name='tpl' style="display:none">${tpl}</div>
	<div name="loading"></div>
    <table cellspacing="1" cellpadding="0" width="100%">
    	<thead>
    		<tr class="grid_head_col">
    		<td style="border-right:#cbcbcb 1px solid"><input type='checkbox' id="grid_chk_all"/></td>
			<#list fields as field>
				<td name="title_td" style="border-right:#cbcbcb 1px solid">
					<div style="cursor:hand;position:relative;">
						<span name="label_bar" style="margin-left:5px;width:100%">${field.label}</span>
					</div>
				</td>
			</#list>
    		</tr>
    	</thead>
    	<tbody>
    	</tbody>
    </table>
	<div class="grid_footer">
    	<span>共</span><span id="wlgridTotalRows" style="margin:0 4 0 4">0</span><span>条</span>
    	<span>第<span><span id="wlgridCurrentPage" style="margin-left:4">0</span><span>/</span><span id="wlgridTotalPages" style="margin-right:4">0</span><span>页</span>
		<span> <a href="#" id="wlgridFirstPage"><img src="${appContext}/walle/themes/default/image/grid/first.gif" title="首页"/></a></span>
		  <a href="#" id="wlgridPreviousPage"><img src="${appContext}/walle/themes/default/image/grid/previous.gif" title="前一页"/></a>
		  <a href="#" id="wlgridNextPage"><img src="${appContext}/walle/themes/default/image/grid/next.gif" title="后一页"/></a>
		  <a href="#" id="wlgridLastPage"><img src="${appContext}/walle/themes/default/image/grid/last.gif" title="尾页"/></a>
    	  <input type="text" id="wlgridToPageInput" style="width:20px;height:18px;font-size:11px;">
    	  <a href="#"><img src="${appContext}/walle/themes/default/image/grid/go.gif" align="absmiddle"  id="wlgridToPage"/></a>
    	  <span class="grid_exp_excel" title="导出excel文档" onclick="${gridId}_grid.exportToExcel()"></span>
    </div>
</div>
<script type="text/javascript">
	var ${gridId}_grid = $("#${gridId}").getWlFGrid();
	
	var ${gridId}_fields = [
		<#list fields as field>
			{fieldName:"${field.fieldName}", label:"${field.label}", fieldType:"${field.fieldType}", sortable:"${field.sortable}", length:"${field.length}", width:"${field.width}"}
			<#if field_has_next>,</#if>
		</#list>
	];
	
	var ${gridId}_fieldCodeTypes = {
		${selectCodes}
	};
	
	${gridId}_grid.set({width:"${width}", height:"${height}",dataModel:"${dataModel}", pageSize:${pageSize}, multiSelect:${multiSelect}, fields: ${gridId}_fields, fieldCodeTypes:${gridId}_fieldCodeTypes, orderBy:"${orderBy}"});
	${gridId}_grid.tablefy();
	<#if onchange!="">
		${gridId}_grid.addListener("onchange", ${onchange});
	</#if>
	<#if ondbclick!="">
		${gridId}_grid.addListener("ondbclick", ${ondbclick});
	</#if>
	<#if ondataloaded!="">
		${gridId}_grid.addListener("ondataloaded", ${ondataloaded});
	</#if>
	<#if init=="true" >
		${gridId}_grid.refresh();
	</#if>
</script>
<!--fgrid end-->