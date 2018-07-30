package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class ComboTag extends BaseEasyUITag {

	private static final long serialVersionUID = 1L;

	protected static String[] ComboOptionNamesString = { "separator",  "keyHandler" , "panelWidth", "panelHeight"};
	protected static String[] ComboOptionNamesBoolean = { "multiple", "selectOnNavigation", "editable", "disabled", "readonly", "hasDownArrow"};
	protected static String[] ComboOptionNamesNumber = { "width", "height", "delay" };

	protected static String[] ComboEventNames = { "onShowPanel","onHidePanel", "onChange" };

	static{
		ComboOptionNamesString = (String[]) ArrayUtils.addAll(ComboOptionNamesString, PanelTag.PanelOptionNamesString);
		ComboOptionNamesBoolean =(String[]) ArrayUtils.addAll(ComboOptionNamesBoolean, PanelTag.PanelOptionNamesBoolean);
		ComboOptionNamesNumber =(String[]) ArrayUtils.addAll(ComboOptionNamesNumber, PanelTag.PanelOptionNamesNumber);
		ComboEventNames = (String[]) ArrayUtils.addAll(ComboEventNames, PanelTag.PanelEventNames);
	}
	
	public ComboTag() {
		super.setTagClass("easyui-combo");
		super.setTagName("select");
		this.setOptionNamesString(ComboOptionNamesString);
		this.setOptionNamesBoolean(ComboOptionNamesBoolean);
		this.setOptionNamesNumber(ComboOptionNamesNumber);
		this.setEventNames(ComboEventNames);
	}


}
