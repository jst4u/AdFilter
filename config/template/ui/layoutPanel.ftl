<!-- layout panel start-->
<div id=${id} class="form" style="${style}" width="${width}" height="${height}">
	<div class="title">${title}</div>
	<div style="width:100%;height:100%;overflow:auto;padding:5px;">	
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#${id}").children().eq(1).height($("#${id}").parent().height()-28);
		$("#${id}").parent().resize(function(){
			$(this).children().children().eq(1).height($(this).height()-28);
		});
	});
</script>
<!-- layout panel end-->
