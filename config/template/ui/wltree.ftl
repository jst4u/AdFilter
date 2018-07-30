<!-- wltree start -->
<div id="${id}" class="wltree"></div>
<script type="text/javascript">
	var ${id}_params = {};
	${id}_params.service = "${service}";
	${id}_params.parameters = "${parameters}";
	<#if multiselect?exists>${id}_params.multiselect = "${multiselect}";</#if>
	var ${id}_tree = $("#${id}").getWlTree(${id}_params);
	${id}_tree.refresh();
</script>
<!-- wltree end -->