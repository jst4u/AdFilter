<!-- wlwindow start -->
<div id="${id}" class="wlwin"  style="height:${height}px;width:${width}px">
	<ul>
		<li title="${title}"  style="height: 26px;" class="wlwin_title_c">
			<div class="wlwin_title_l"></div>
			<div class="wlwin_title_img"></div>
			<div class='wlwin_title'>${title}</div>
			<div class="wlwin_title_bt_close"></div>
			<div class="wlwin_title_r"></div>
		</li>
		<li style="overflow: auto;height: 100%;">
			<div class="wlwin_content">
				<#if src!="">
	        		<iframe id="${id}_frm" src="${src}" frameborder="0px" style="width:100%;height:100%"></iframe>
	        	<#else>
	        	${bodyContent}
	        	</#if>
		 	</div>
		</li>
	</ul>
</div>
<script type="text/javascript">
	$("#${id}").getWlWin({width:${width}, height:${height}});
	$("#${id} .wlwin_content,#${id}>ul").css("height",($("#${id}").height()-27)+"px");
	<#if src!="">
		$("#${id}").getWlWin().set({src:"${src}"});
	</#if>
</script>
<!-- wlwindow end -->