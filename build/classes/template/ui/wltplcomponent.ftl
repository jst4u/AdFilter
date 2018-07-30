<div id="${id}" jid="${jid}" class="tplc">
	<div class="tplc_content">
	</div>
</div>
<script type='text/javascript'> 
	$('#${id}').getWlTplComponent({jid:'${jid}'});
	$('#${id}').getWlTplComponent().set({param:${param}});
	<#if ondataloaded?exists>
	$('#${id}').getWlTplComponent().addListener("ondataloaded", ${ondataloaded});
	</#if>
	<#if init=="true" >
	$('#${id}').getWlTplComponent().refresh();
	</#if>
</script>