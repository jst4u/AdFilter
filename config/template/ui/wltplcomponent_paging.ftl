<div class="tplc_nav">
	<div>[共${totalRows}条</div>
	<div>第${currentPage}/${totalPages}页]</div>
	<div onclick="$('#${id}').getWlTplComponent().firstPage();" class="grid_paging_first" title="首页">&nbsp;&nbsp;</div>
	<div onclick="$('#${id}').getWlTplComponent().previousPage();" class="grid_paging_previous" title="前一页">&nbsp;&nbsp;</div>
	<div onclick="$('#${id}').getWlTplComponent().nextPage();" class="grid_paging_next" title="后一页">&nbsp;&nbsp;</div>
	<div onclick="$('#${id}').getWlTplComponent().lastPage();" class="grid_paging_last" title="尾页">&nbsp;&nbsp;</div>
	<div><input type="text" style="width:12px;height:12px;font-size:11px;" id="${id}ToPageInput"></div>
	<div onclick="$('#${id}').getWlTplComponent().toPage($('#${id}ToPageInput').val());" class="grid_paging_to">&nbsp;&nbsp;&nbsp;</div>
</div>

