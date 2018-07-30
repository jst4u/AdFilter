<!--grid begin-->
<div id="${gridId}" class="grid_panel">
		<div name='tpl' style="display:none">${tpl}</div>
		<div class="grid_head">
	        <table cellspacing="0" cellpadding="0">
	        	<tbody>
	        		<tr class="grid_head_col">
		        		<td>
		        			<span>
			        			<#if multiSelect=="true">
			        				<input type='checkbox' class="grid_chk_all" />
			        			</#if>
		        			</span>
		        		</td>
		        	<#list fields as field>
						<td class="title_td">
							<div>
								<span class="label_bar">${field.label}</span>
								<span class="resizebar"></span>
							</div>
						</td>
					</#list>
					<!--表头最后一列加宽，解决滚动条拖到最右边不对齐问题-->
	        			<td class="title_td" ><div style="width:200px;"></div></td>
	        		</tr>
	        	<tbody>
	        </table>
    	</div>
    	
    	        
        <div class="grid_body">
	        <table cellspacing="0" bgcolor="#ffffff"  cellpadding="0">
	        	<tbody></tbody>
	        </table>
        </div>
    	
    	 <div class="grid_footer">
    	 	<div class="wlgridTotalRowsBegin">共</div><div class="wlgridTotalRows">0</div><div>条</div>
    	 	
    	 	<div class="wlgridCurrentPageBegin">第</div><div class="wlgridCurrentPage">0</div><div>/</div><div class="wlgridTotalPages">0</div><div>页</div>
    	 	
    	 	<div href="#" class="wlgridFirstPage">
    	 		<img src="${appContext}/walle/themes/default/image/grid/first.gif" title="首页"/>
    	 	</div>
    	 	
    	 	<div href="#" class="wlgridPreviousPage">
    	 		<img src="${appContext}/walle/themes/default/image/grid/previous.gif" title="前一页"/>
    	 	</div>
    	 	
    	 	<div href="#" class="wlgridNextPage">
    	 		<img src="${appContext}/walle/themes/default/image/grid/next.gif" title="后一页"/>
    	 	</div>
    	 	
    	 	<div href="#" class="wlgridLastPage">
    	 		<img src="${appContext}/walle/themes/default/image/grid/last.gif" title="尾页"/>
    	 	</div>
    	 	
    	 	<div><input type="text" class="wlgridToPageInput"></div>
    	 	
    	 	<div href="#" class="wlgridgo">
    	 		<img src="${appContext}/walle/themes/default/image/grid/go.gif" align="middle"  class="wlgridToPage"/>
    	 	</div>
        	
        	<div class="grid_exp_excel" title="导出excel文档" onclick="${gridId}_grid.exportToExcel()"></div>
        </div>
</div>


<script type="text/javascript">
	var ${gridId}_grid = $("#${gridId}").getWlGrid();
	
	var ${gridId}_fields = [
		<#list fields as field>
			{fieldName:"${field.fieldName}", label:"${field.label}", fieldType:"${field.fieldType}", sortable:"${field.sortable}", length:"${field.length}", width:"${field.width}px"}
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
	$("#${gridId}").css("margin","0px");
</script>
<!--grid end-->
