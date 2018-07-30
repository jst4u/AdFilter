<!--button start--->
<span  id="${id}" name="${name}" class="walle_btn ${class}" style="${style}" onclick="${onclick}" onmouseover="$(this).addClass('walle_btn_hover');" onmouseout="$(this).removeClass('walle_btn_hover');">
	<table border="0px" cellpadding="0px" cellspacing="0px">
		<tbody>
			<tr>
				<td><div class="walle_btn_left"></div></td>
				<td>
					<div class="walle_btn_center">
						<div class="walle_btn_center_left_left" style="background:url('${img}') no-repeat 0px 3px;"></div>
					</div>
				</td>
				<td>
					<div class="walle_btn_center">
						<div class="walle_btn_center_right">${value}</div>
					</div>
				</td>
				<td><div class="walle_btn_right"></div></td>
			</tr>
		</tbody>
	</table></span>
	<script type="text/javascript">
		$("#${id}").getWlButton().setOnclick("${onclick}");
	</script>
<!--button end--->