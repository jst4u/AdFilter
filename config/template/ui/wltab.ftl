<!-- wltab start -->
<div id="${id}" class="wltab" style="${style}">
	<div style="" class="wltab_tab_nav">
		<#list items as item>
	       <div class="wltab_tab_item"><span id="${item.id}">${item.title}</span></div>
	    </#list>
	</div>
	<div class="wltab_bottom">
		<div class="wltab_body">
		<#list items as item>
		 <div id="${item.id}" type="tab_item" style="display:none;height:100%;">
	        	<#if item.src!="">
	        		<iframe id="${item.id}_frm" name="${item.id}_frm" src="${item.src}" frameborder="0px" style="width:100%;height:100%"></iframe>
	        	<#else>
	        	${item.bodyContent}
	        	</#if>
	        </div>
		</#list>
		</div>
	</div>
</div>
<script type="text/javascript">
	var ${id}_params = {};
	<#if width?exists>${id}_params.width = "${width}";</#if>
	<#if height?exists>${id}_params.height = "${height}";</#if>
	$("#${id}").getWlTab(${id}_params);
</script>
<!-- wltab end -->