package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;


public class TabsTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] TabsOptionNamesString = {"toolPosition","tabPosition", "id","group", "text","iconCls","iconAlign","href"};
	protected static String[] TabsOptionNamesBoolean = {"fit","border","disabled","toggle","selected","plain"};
	protected static String[] TabsOptionNamesNumber = {"width","height","scrollIncrement","scrollDuration","headerWidth","tabWidth","tabHeight","deltaX","deltaY","showDelay","hideDelay"};
	
	protected static String[] TabsEventNames = {"tools","onLoad","onSelect","onBeforeClose","onClose","onAdd","onUpdate","onContextMenu","onclick"};
	
	static{
		TabsOptionNamesString = (String[]) ArrayUtils.addAll(TabsOptionNamesString, PanelTag.PanelOptionNamesString);
		TabsOptionNamesBoolean =(String[]) ArrayUtils.addAll(TabsOptionNamesBoolean, PanelTag.PanelOptionNamesBoolean);
		TabsOptionNamesNumber =(String[]) ArrayUtils.addAll(TabsOptionNamesNumber, PanelTag.PanelOptionNamesNumber);
		TabsEventNames = (String[]) ArrayUtils.addAll(TabsEventNames, PanelTag.PanelEventNames);
	}
	
	public TabsTag(){
		this.setTagClass("easyui-tabs");
		this.setTagName("div");
		this.setOptionNamesString(TabsOptionNamesString);
		this.setOptionNamesBoolean(TabsOptionNamesBoolean);
		this.setOptionNamesNumber(TabsOptionNamesNumber);
		this.setEventNames(TabsEventNames);
	}
	
	
}
