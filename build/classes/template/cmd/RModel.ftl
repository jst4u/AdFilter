package cn.walle.esm.monitoring.protocal.receive.model;

public class Receive${className}Model {
	
	<#list columns as column>
		//${column.label}
		private ${column.fieldType} ${column.fieldName};
	</#list>
	 
	<#list columns as column>
		/**
		 * Set ${column.label}
		 */
		public void ${column.setterMethodName}(${column.fieldType} ${column.fieldName}) {
			this.${column.fieldName} = ${column.fieldName};
		}
		
		/**
		 * Get ${column.label}
		 */
		public ${column.fieldType} ${column.getterMethodName}() {
			return ${column.fieldName};
		}
	</#list>
	
}