package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class ComboTreeTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;

	private static String[] ComboTreeOptionNamesString = { "idField", "textField", "pidField"};
	private static String[] ComboTreeOptionNamesBoolean = { "required" ,"editable"};
	private static String[] ComboTreeOptionNamesNumber = { "width"};

	private static String[] ComboTreeEventNames = { };

	static{
		ComboTreeOptionNamesString = (String[]) ArrayUtils.addAll(ComboTreeOptionNamesString, ComboTag.ComboOptionNamesString);
		ComboTreeOptionNamesBoolean =(String[]) ArrayUtils.addAll(ComboTreeOptionNamesBoolean, ComboTag.ComboOptionNamesBoolean);
		ComboTreeOptionNamesNumber =(String[]) ArrayUtils.addAll(ComboTreeOptionNamesNumber, ComboTag.ComboOptionNamesNumber);
		ComboTreeEventNames = (String[]) ArrayUtils.addAll(ComboTreeEventNames, ComboTag.ComboEventNames);
		
		ComboTreeOptionNamesString = (String[]) ArrayUtils.addAll(ComboTreeOptionNamesString, TreeTag.TreeOptionNamesString);
		ComboTreeOptionNamesBoolean =(String[]) ArrayUtils.addAll(ComboTreeOptionNamesBoolean, TreeTag.TreeOptionNamesBoolean);
		ComboTreeOptionNamesNumber =(String[]) ArrayUtils.addAll(ComboTreeOptionNamesNumber, TreeTag.TreeOptionNamesNumber);
		ComboTreeEventNames = (String[]) ArrayUtils.addAll(ComboTreeEventNames, TreeTag.TreeEventNames);
	}
	
	public ComboTreeTag() {
		this.setTagClass("easyui-combotree");
		super.setTagName("input");
		this.setOptionNamesString(ComboTreeOptionNamesString);
		this.setOptionNamesBoolean(ComboTreeOptionNamesBoolean);
		this.setOptionNamesNumber(ComboTreeOptionNamesNumber);
		this.setEventNames(ComboTreeEventNames);
	}

}
