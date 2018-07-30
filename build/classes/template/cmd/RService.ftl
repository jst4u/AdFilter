package cn.walle.esm.monitoring.protocal.receive.service;

import org.springframework.context.ApplicationContextException;

import cn.walle.esm.monitoring.protocal.receive.model.*;
import cn.walle.esm.util.socketCMD.impl.Service;
import cn.walle.esm.util.socketCMD.util.CRCUitl;
import cn.walle.esm.util.socketCMD.util.CmdUtil;
import cn.walle.esm.util.socketCMD.util.SocketProUtil;


/**
 * @author sigangjun
 *
 */
public class Receive${className}Service extends Service{

	<#list columns as column>
		protected static final int CMD_${column.fieldName}_len = ${column.fieldSize};//${column.label}
	</#list>
	
<#global temp="">
<#list columns as column>
   <#if temp == "">
	   	<#global temp>${column.fieldName}</#global>
   </#if> 
  <#if column_index == 0>
		protected static final int CMD_${column.fieldName}_offset = 8;//${column.label}
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

	    public Receive${className}Model getDate(byte[] bytes){
	    	
	    	if(!CRCUitl.crc(bytes)){
	    		throw new ApplicationContextException("CRC校验失败！");
	    	}
	    	if(bytes.length<8){
	    		throw new ApplicationContextException("服务器端返回的数据有问题！");
	    	}
	    	
	    	if(CmdUtil.GetIntValueFromBytes(bytes, Service.CMD_Type_offset, Service.CMD_Type_len)!=Integer.parseInt(SocketProUtil.getProperties().getProperty("socket.receive.${className?lower_case}.type"))){
	    		throw new ApplicationContextException("【${classLabel}】返回指令错误--->>>服务器端发送指令错误！");
	    	}
	    	
	    	Receive${className}Model receive${className}Model = new Receive${className}Model();
	    	
   	<#list columns as column>
   		<#if column.fieldType == "String">
  	 		receive${className}Model.${column.setterMethodName}(CmdUtil.GetStringFromBytes(bytes,CMD_${column.fieldName}_offset,CMD_${column.fieldName}_len));
  	 	<#else>
  	 		receive${className}Model.${column.setterMethodName}(CmdUtil.GetIntValueFromBytes(bytes,CMD_${column.fieldName}_offset,CMD_${column.fieldName}_len));
  	 	</#if>
	</#list>
	    	return receive${className}Model;
	    }
	
}
