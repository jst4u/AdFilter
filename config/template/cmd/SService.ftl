package cn.walle.esm.monitoring.protocal.send.service;

import cn.walle.esm.monitoring.protocal.send.model.*;
import cn.walle.esm.util.socketCMD.impl.Service;
import cn.walle.esm.util.socketCMD.model.SendModel;
import cn.walle.esm.util.socketCMD.util.CmdUtil;
import cn.walle.esm.util.socketCMD.util.SocketProUtil;

public class ${className}Service extends Service{

	
	<#list columns as column>
	protected static final int CMD_${column.fieldName}_len = ${column.fieldSize};//${column.label}
	</#list>
	
<#global temp="">
<#list columns as column>
   <#if temp == "">
	   	<#global temp>${column.fieldName}</#global>
   </#if> 
  <#if column_index == 0>
	protected static final int CMD_${column.fieldName}_offset = ${column.fieldSize};//${column.label}
  <#else>
	protected static final int CMD_${column.fieldName}_offset = CMD_${temp}_offset+CMD_${temp}_len;//${column.label}
	<#if !column_has_next>
	protected static final int CMD_CRC_offset = CMD_${column.fieldName}_offset+CMD_${column.fieldName}_len;
	</#if>
  </#if>
  
  <#global temp>${column.fieldName}</#global>
</#list>
	protected static final int CMD_CRC_len = 2;
	public static final int CMD_Data_len = 0<#list columns as column>+CMD_${column.fieldName}_len</#list>;
	private static byte[] bytes = new byte[${className}Service.CMD_Data_len +Service.CMD_Data_len+CMD_CRC_len];

	public ${className}Service(){
		this.FillDefaultSendHeadData();
	}
	
	public  void FillHeadData(SendModel sendModel){
		Service.setBytes(bytes,sendModel);
	}
	
	public void FillDefaultSendHeadData(){
		SendModel sendModel = new SendModel();
		sendModel.setVersion(1);
		sendModel.setType(Integer.parseInt(SocketProUtil.getProperties().getProperty("socket.send.receive${className?lower_case}.type")));
		sendModel.setId(1);
		Service.setBytes(bytes,sendModel);
	}
	
	public  byte[] getBytes(${className}Model ${className?lower_case}Model){
<#list columns as column>
	<#if column.fieldType == "String">
		CmdUtil.SetBytesFromString(bytes, CMD_${column.fieldName}_offset, CMD_${column.fieldName}_len, ${className?lower_case}Model.${column.getterMethodName}());
 	<#else>
 		CmdUtil.SetBytesFromLong(bytes, CMD_${column.fieldName}_offset, CMD_${column.fieldName}_len, ${className?lower_case}Model.${column.getterMethodName}());
 	</#if>
</#list>
	
		setLength();
		crc();
		return bytes;
	}
	
	private void setLength(){
		CmdUtil.SetBytesFromLong(bytes,CMD_Length_offset, CMD_Length_len, CMD_Data_len);
	}
	private void crc(){
		CmdUtil.SetBytesFromLong(bytes,CMD_CRC_offset, CMD_CRC_len, bytes.length);
	}
}
