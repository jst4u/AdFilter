package com.loit.core.web.tag.easyui;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.ArrayUtils;

public class ValidateBoxTag extends BaseEasyUITag {

	private static final long serialVersionUID = 1L;
	protected static String[] ValidateBoxOptionNamesString = { "validType",
			"missingMessage", "invalidMessage", "textField",
			"decimalSeparator", "groupSeparator", "prefix", "suffix",
			"valueField", "mode", "url", "method", "data", "filter", "loader",
			"loadFilter", "codeFilter", "panelHeight", "panelWidth", "tagType",
			};
	protected static String[] ValidateBoxOptionNamesBoolean = { "disabled",
			"required", "separator", "min", "max", "showSeconds", "editable" };
	protected static String[] ValidateBoxOptionNamesNumber = { "delay",
			"deltaX", "min", "max", "precision", "highlight", "width",
			"height", "increment" };
	protected static String[] ValidateBoxEventNames = { "formatter",
			"onDestroy", "validate", "isValid", "filter", "formatter",
			"parser", "onChange", "spin", "onSpinUp", "onSpinDown",
			"onBeforeLoad", "onLoadSuccess", "onLoadError", "onSelect",
			"onUnselect" };

	static {
		ValidateBoxOptionNamesString = (String[]) ArrayUtils.addAll(
				ValidateBoxOptionNamesString,
				ToolTipTag.ToolTipOptionNamesString);
		ValidateBoxOptionNamesBoolean = (String[]) ArrayUtils.addAll(
				ValidateBoxOptionNamesBoolean,
				ToolTipTag.ToolTipOptionNamesBoolean);
		ValidateBoxOptionNamesNumber = (String[]) ArrayUtils.addAll(
				ValidateBoxOptionNamesNumber,
				ToolTipTag.ToolTipOptionNamesNumber);
		ValidateBoxEventNames = (String[]) ArrayUtils.addAll(
				ValidateBoxEventNames, ToolTipTag.ToolTipEventNames);
		
		ValidateBoxOptionNamesString = (String[]) ArrayUtils.addAll(
				ValidateBoxOptionNamesString,
				ComboBoxTag.ComboBoxOptionNamesString);
		ValidateBoxOptionNamesBoolean = (String[]) ArrayUtils.addAll(
				ValidateBoxOptionNamesBoolean,
				ComboBoxTag.ComboBoxOptionNamesBoolean);
		ValidateBoxOptionNamesNumber = (String[]) ArrayUtils.addAll(
				ValidateBoxOptionNamesNumber,
				ComboBoxTag.ComboBoxOptionNamesNumber);
		ValidateBoxEventNames = (String[]) ArrayUtils.addAll(
				ValidateBoxEventNames, ComboBoxTag.ComboBoxEventNames);
	}

	public int doStartTag() throws JspException {

		if (null == getTagType()) {
			this.setTagClass("easyui-validatebox");
			this.setTagName("input");
			this.setOptionNamesString(ValidateBoxOptionNamesString);
			this.setOptionNamesBoolean(ValidateBoxOptionNamesBoolean);
			this.setOptionNamesNumber(ValidateBoxOptionNamesNumber);
			this.setEventNames(ValidateBoxEventNames);
		} else {
			this.setTagName("input");
			this.setOptionNamesString(ValidateBoxOptionNamesString);
			this.setOptionNamesBoolean(ValidateBoxOptionNamesBoolean);
			this.setOptionNamesNumber(ValidateBoxOptionNamesNumber);
			this.setEventNames(ValidateBoxEventNames);
			switch (getTagType()) {
			case "validatebox":
				this.setTagClass("easyui-validatebox");
				break;
			case "":
				this.setTagClass("easyui-validatebox");
				break;
			case "numberbox":
				this.setTagClass("easyui-numberbox");
				break;
			case "datebox":
				this.setTagClass("easyui-datebox");
				break;
			case "datetimebox":
				this.setTagClass("easyui-datetimebox");
				break;
			case "timespinner":
				this.setTagClass("easyui-timespinner");
				break;
			case "combobox":
				this.setTagClass("easyui-combobox");
				break;
			default:
				break;
			}
		}
		if ("area" == this.getType()) {
			this.setTagName("area");
		}
		if ("textarea" == this.getType()) {
			this.setTagName("textarea");
		}
		return super.doStartTag();
	}
}
