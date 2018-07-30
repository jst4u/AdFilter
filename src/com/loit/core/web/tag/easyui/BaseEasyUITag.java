package com.loit.core.web.tag.easyui;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * easyui 标签基础类，所有共同属性和事件放在这里处理
 * 
 * @author Administrator panel去掉了
 * 
 */
public class BaseEasyUITag extends BaseHtmlTag {

	private static final long serialVersionUID = 469528325048159830L;

	private String[] OptionNamesString = {};
	private String[] OptionNamesBoolean = {};
	private String[] OptionNamesNumber = {};
	private String[] EventNames = {};
	
	

	private String options = null;
	private String tagClass = null;
	private String tagName = null;

	// 自定义属性
	private String queryform;
	private String querysource;
	private String codeType;
	private String type;
	private String pidField;
	private String startId;
	private String openLevel;

	private String treeField;
	private String onContextMenu;
	private String textField;
	private String animate;
	private String resizeHandle;
	private String toolbar;
	private String method;
	private String idField;
	private String url;
	private String loadMsg;
	private String pagePosition;
	private String sortName;
	private String sortOrder;
	private String pagination;
	private String rownumbers;
	private String singleSelect;
	private String autoRowHeight;
	private String striped;
	private String nowrap;
	private String checkOnSelect;
	private String selectOnCheck;
	private String pageNumber;
	private String pageSize;

	private String remoteSort;
	private String showHeader;
	private String showFooter;
	private String scrollbarSize;
	private String columns;
	private String frozenColumns;
	private String fitColumns;
	private String data;
	private String rowStyler;
	private String queryParams;
	private String pageList;
	private String loader;
	private String loadFilter;
	private String editors;
	private String view;
	private String onLoadSuccess;
	private String onLoadError;
	private String onBeforeLoad;
	private String onClickRow;
	private String onDblClickRow;
	private String onClickCell;
	private String onDblClickCell;
	private String onSortColumn;
	private String onResizeColumn;
	private String onSelect;
	private String onUnselect;
	private String onSelectAll;
	private String onUnselectAll;
	private String onCheck;
	private String onUncheck;
	private String onCheckAll;
	private String onUncheckAll;
	private String onBeforeEdit;
	private String onAfterEdit;
	private String onCancelEdit;
	private String onHeaderContextMenu;
	private String onRowContextMenu;

	private String field;
	private String rowspan;
	private String colspan;
	private String align;
	private String halign;
	private String sortable;
	private String order;
	private String resizable;
	private String fixed;
	private String hidden;
	private String checkbox;
	private String formatter;
	private String styler;
	private String sorter;
	private String editor;

	// options 属性
	private String id;
	private String iconCls;
	private String width;
	private String height;
	private String left;
	private String top;
	private String cls;
	private String headerCls;
	private String bodyCls;
	private String fit;
	private String border;
	private String doSize;
	private String noheader;
	private String content;
	private String collapsible;
	private String minimizable;
	private String maximizable;
	private String closable;
	private String tools;
	private String collapsed;
	private String minimized;
	private String maximized;
	private String closed;
	private String href;
	private String cache;
	private String loadingMessage;
	private String extractor;
	private String disabled;
	private String cascadeCheck;
	private String onlyLeafCheck;
	private String lines;
	private String dnd;

	// events 事件
	private String onLoad;
	private String onBeforeOpen;
	private String onOpen;
	private String onBeforeClose;
	private String onClose;
	private String onBeforeDestroy;
	private String onDestroy;
	private String onBeforeCollapse;
	private String onCollapse;
	private String onBeforeExpand;
	private String onExpand;
	private String onResize;
	private String onMove;
	private String onMaximize;
	private String onRestore;
	private String onMinimize;

	private String onClick;
	private String onDblClick;
	private String onBeforeCheck;
	private String onBeforeSelect;
	private String onBeforeDrag;
	private String onStartDrag;
	private String onStopDrag;
	private String onDragEnter;
	private String onDragOver;
	private String onDragLeave;
	private String onBeforeDrop;
	private String onDrop;

	private String readonly;
	private String style = null;
	private String styleClass = null;
	private String title = null;

	// validateBoxTag
	private String validate;
	private String isValid;
	private String name;

	private String validType;
	private String missingMessage;
	private String invalidMessage;
	private String required;
	private String delay;
	private String deltaX;
	private String tagType;//定义validateBoxTag标签的类型

	// comboTree
	private String setValues;
	private String setValue;
	private String clear;
	private String reload;
	private String loadData;
	private String tree;
	private String editable;

	// window
	private String zIndex;
	private String draggable;
	private String shadow;
	private String inline;
	private String modal;

	// toolTip
	private String position;
	private String trackMouse;
	private String deltaY;
	private String showEvent;
	private String hideEvent;
	private String showDelay;
	private String hideDelay;
	private String onShow;
	private String onHide;
	private String onUpdate;
	private String onPosition;

	// draggable
	private String proxy;
	private String revert;
	private String cursor;
	private String handle;
	private String edge;
	private String axis;
	private String onDrag;

	// droppable
	private String accept;

	// resizable
	private String handles;
	private String minWidth;
	private String minHeight;
	private String maxWidth;
	private String maxHeight;
	private String onStartResize;
	private String onStopResize;

	// progressBar
	private String value;
	private String text;
	private String onChange;

	// menu
	private String onclick;

	// linkButton
	private String toggle;
	private String selected;
	private String group;
	private String plain;
	private String iconAlign;

	// calendar
	private String firstDay;
	private String weeks;
	private String months;
	private String year;
	private String month;
	private String current;

	// combo
	private String panelWidth;
	private String panelHeight;
	private String multiple;
	private String selectOnNavigation;
	private String separator;
	private String hasDownArrow;
	private String keyHandler;
	private String onShowPanel;
	private String onHidePanel;

	// comboBox
	private String valueField;
	private String mode;
	private String filter;
	private String codePlease;
	private String codeFilter;

	// comboGrid

	// menuButton
	private String menu;
	private String duration;

	// numberBox
	private String min;
	private String max;
	private String precision;
	private String decimalSeparator;
	private String groupSeparator;
	private String prefix;
	private String suffix;
	private String parser;

	// dateBox
	private String currentText;
	private String closeText;
	private String okText;

	// dateTimeBox
	private String timeSeparator;
	private String showSeconds;

	// spinner
	private String increment;
	private String spin;
	private String onSpinUp;
	private String onSpinDown;

	// numberSpinner

	// timeSpinner
	private String highlight;

	// spinner
	private String reversed;
	private String showTip;
	private String step;
	private String rule;
	private String tipFormatter;
	private String onSlideStart;
	private String onSlideEnd;

	// dialog
	private String buttons;

	// propertyGrid
	private String showGroup;
	private String groupField;
	private String groupFormatter;

	// pagination
	private String total;
	private String showPageList;
	private String loading;
	private String showRefresh;
	private String beforePageText;
	private String afterPageText;
	private String displayMsg;
	private String onSelectPage;
	private String onBeforeRefresh;
	private String onRefresh;
	private String onChangePageSize;

	// searchBox
	private String prompt;
	private String searcher;

	// tabs
	private String scrollIncrement;
	private String scrollDuration;
	private String toolPosition;
	private String tabPosition;
	private String headerWidth;
	private String tabWidth;
	private String tabHeight;
	private String onAdd;
	
	//form
	private String onSubmit;
	private String success;
	
	//accordion
	private String onBeforeRemove;
	private String onRemove;
	
	//messager
	private String ok;
	private String cancle;

	//area
	private String alt;
	private String coords;
	private String nohref;
	private String shape;
	private String target;
	private String onblur;
	private String onfocus;
	private String onkeyup;
	private String onkeydown;
	private String onkeypress;
	private String onmouseout;
	private String onmousemove;
	private String onmouseover;
	private String onmouseup;
	private String onmousedown;
	private String ondblclick;

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getCoords() {
		return coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

	public String getNohref() {
		return nohref;
	}

	public void setNohref(String nohref) {
		this.nohref = nohref;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getOnfocus() {
		return onfocus;
	}

	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	public String getOnkeyup() {
		return onkeyup;
	}

	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	public String getOnkeydown() {
		return onkeydown;
	}

	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	public String getOnkeypress() {
		return onkeypress;
	}

	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	public String getOnmouseout() {
		return onmouseout;
	}

	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	public String getOnmousemove() {
		return onmousemove;
	}

	public void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}

	public String getOnmouseover() {
		return onmouseover;
	}

	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	public String getOnmouseup() {
		return onmouseup;
	}

	public void setOnmouseup(String onmouseup) {
		this.onmouseup = onmouseup;
	}

	public String getOnmousedown() {
		return onmousedown;
	}

	public void setOnmousedown(String onmousedown) {
		this.onmousedown = onmousedown;
	}

	public String getOndblclick() {
		return ondblclick;
	}

	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getCancle() {
		return cancle;
	}

	public void setCancle(String cancle) {
		this.cancle = cancle;
	}

	public String getOnBeforeRemove() {
		return onBeforeRemove;
	}

	public void setOnBeforeRemove(String onBeforeRemove) {
		this.onBeforeRemove = onBeforeRemove;
	}

	public String getOnRemove() {
		return onRemove;
	}

	public void setOnRemove(String onRemove) {
		this.onRemove = onRemove;
	}

	public String getOnSubmit() {
		return onSubmit;
	}

	public void setOnSubmit(String onSubmit) {
		this.onSubmit = onSubmit;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getScrollIncrement() {
		return scrollIncrement;
	}

	public void setScrollIncrement(String scrollIncrement) {
		this.scrollIncrement = scrollIncrement;
	}

	public String getScrollDuration() {
		return scrollDuration;
	}

	public void setScrollDuration(String scrollDuration) {
		this.scrollDuration = scrollDuration;
	}

	public String getToolPosition() {
		return toolPosition;
	}

	public void setToolPosition(String toolPosition) {
		this.toolPosition = toolPosition;
	}

	public String getTabPosition() {
		return tabPosition;
	}

	public void setTabPosition(String tabPosition) {
		this.tabPosition = tabPosition;
	}

	public String getHeaderWidth() {
		return headerWidth;
	}

	public void setHeaderWidth(String headerWidth) {
		this.headerWidth = headerWidth;
	}

	public String getTabWidth() {
		return tabWidth;
	}

	public void setTabWidth(String tabWidth) {
		this.tabWidth = tabWidth;
	}

	public String getTabHeight() {
		return tabHeight;
	}

	public void setTabHeight(String tabHeight) {
		this.tabHeight = tabHeight;
	}

	public String getOnAdd() {
		return onAdd;
	}

	public void setOnAdd(String onAdd) {
		this.onAdd = onAdd;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getSearcher() {
		return searcher;
	}

	public void setSearcher(String searcher) {
		this.searcher = searcher;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getShowPageList() {
		return showPageList;
	}

	public void setShowPageList(String showPageList) {
		this.showPageList = showPageList;
	}

	public String getLoading() {
		return loading;
	}

	public void setLoading(String loading) {
		this.loading = loading;
	}

	public String getShowRefresh() {
		return showRefresh;
	}

	public void setShowRefresh(String showRefresh) {
		this.showRefresh = showRefresh;
	}

	public String getBeforePageText() {
		return beforePageText;
	}

	public void setBeforePageText(String beforePageText) {
		this.beforePageText = beforePageText;
	}

	public String getAfterPageText() {
		return afterPageText;
	}

	public void setAfterPageText(String afterPageText) {
		this.afterPageText = afterPageText;
	}

	public String getDisplayMsg() {
		return displayMsg;
	}

	public void setDisplayMsg(String displayMsg) {
		this.displayMsg = displayMsg;
	}

	public String getOnSelectPage() {
		return onSelectPage;
	}

	public void setOnSelectPage(String onSelectPage) {
		this.onSelectPage = onSelectPage;
	}

	public String getOnBeforeRefresh() {
		return onBeforeRefresh;
	}

	public void setOnBeforeRefresh(String onBeforeRefresh) {
		this.onBeforeRefresh = onBeforeRefresh;
	}

	public String getOnRefresh() {
		return onRefresh;
	}

	public void setOnRefresh(String onRefresh) {
		this.onRefresh = onRefresh;
	}

	public String getOnChangePageSize() {
		return onChangePageSize;
	}

	public void setOnChangePageSize(String onChangePageSize) {
		this.onChangePageSize = onChangePageSize;
	}

	public String getShowGroup() {
		return showGroup;
	}

	public void setShowGroup(String showGroup) {
		this.showGroup = showGroup;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public String getGroupFormatter() {
		return groupFormatter;
	}

	public void setGroupFormatter(String groupFormatter) {
		this.groupFormatter = groupFormatter;
	}

	public String getButtons() {
		return buttons;
	}

	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	public String getReversed() {
		return reversed;
	}

	public void setReversed(String reversed) {
		this.reversed = reversed;
	}

	public String getShowTip() {
		return showTip;
	}

	public void setShowTip(String showTip) {
		this.showTip = showTip;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getTipFormatter() {
		return tipFormatter;
	}

	public void setTipFormatter(String tipFormatter) {
		this.tipFormatter = tipFormatter;
	}

	public String getOnSlideStart() {
		return onSlideStart;
	}

	public void setOnSlideStart(String onSlideStart) {
		this.onSlideStart = onSlideStart;
	}

	public String getOnSlideEnd() {
		return onSlideEnd;
	}

	public void setOnSlideEnd(String onSlideEnd) {
		this.onSlideEnd = onSlideEnd;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	public String getSpin() {
		return spin;
	}

	public void setSpin(String spin) {
		this.spin = spin;
	}

	public String getOnSpinUp() {
		return onSpinUp;
	}

	public void setOnSpinUp(String onSpinUp) {
		this.onSpinUp = onSpinUp;
	}

	public String getOnSpinDown() {
		return onSpinDown;
	}

	public void setOnSpinDown(String onSpinDown) {
		this.onSpinDown = onSpinDown;
	}

	public String getTimeSeparator() {
		return timeSeparator;
	}

	public void setTimeSeparator(String timeSeparator) {
		this.timeSeparator = timeSeparator;
	}

	public String getShowSeconds() {
		return showSeconds;
	}

	public void setShowSeconds(String showSeconds) {
		this.showSeconds = showSeconds;
	}

	public String getCurrentText() {
		return currentText;
	}

	public void setCurrentText(String currentText) {
		this.currentText = currentText;
	}

	public String getCloseText() {
		return closeText;
	}

	public void setCloseText(String closeText) {
		this.closeText = closeText;
	}

	public String getOkText() {
		return okText;
	}

	public void setOkText(String okText) {
		this.okText = okText;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}

	public String getGroupSeparator() {
		return groupSeparator;
	}

	public void setGroupSeparator(String groupSeparator) {
		this.groupSeparator = groupSeparator;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getParser() {
		return parser;
	}

	public void setParser(String parser) {
		this.parser = parser;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCodePlease() {
		return codePlease;
	}

	public void setCodePlease(String codePlease) {
		this.codePlease = codePlease;
	}

	public String getCodeFilter() {
		return codeFilter;
	}

	public void setCodeFilter(String codeFilter) {
		this.codeFilter = codeFilter;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	private String operator;

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getPanelWidth() {
		return panelWidth;
	}

	public void setPanelWidth(String panelWidth) {
		this.panelWidth = panelWidth;
	}

	public String getPanelHeight() {
		return panelHeight;
	}

	public void setPanelHeight(String panelHeight) {
		this.panelHeight = panelHeight;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getSelectOnNavigation() {
		return selectOnNavigation;
	}

	public void setSelectOnNavigation(String selectOnNavigation) {
		this.selectOnNavigation = selectOnNavigation;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getHasDownArrow() {
		return hasDownArrow;
	}

	public void setHasDownArrow(String hasDownArrow) {
		this.hasDownArrow = hasDownArrow;
	}

	public String getKeyHandler() {
		return keyHandler;
	}

	public void setKeyHandler(String keyHandler) {
		this.keyHandler = keyHandler;
	}

	public String getOnShowPanel() {
		return onShowPanel;
	}

	public void setOnShowPanel(String onShowPanel) {
		this.onShowPanel = onShowPanel;
	}

	public String getOnHidePanel() {
		return onHidePanel;
	}

	public void setOnHidePanel(String onHidePanel) {
		this.onHidePanel = onHidePanel;
	}

	public String getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getToggle() {
		return toggle;
	}

	public void setToggle(String toggle) {
		this.toggle = toggle;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getIconAlign() {
		return iconAlign;
	}

	public void setIconAlign(String iconAlign) {
		this.iconAlign = iconAlign;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getHandles() {
		return handles;
	}

	public void setHandles(String handles) {
		this.handles = handles;
	}

	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	public String getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}

	public String getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
	}

	public String getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getOnStartResize() {
		return onStartResize;
	}

	public void setOnStartResize(String onStartResize) {
		this.onStartResize = onStartResize;
	}

	public String getOnStopResize() {
		return onStopResize;
	}

	public void setOnStopResize(String onStopResize) {
		this.onStopResize = onStopResize;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getRevert() {
		return revert;
	}

	public void setRevert(String revert) {
		this.revert = revert;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getAxis() {
		return axis;
	}

	public void setAxis(String axis) {
		this.axis = axis;
	}

	public String getOnDrag() {
		return onDrag;
	}

	public void setOnDrag(String onDrag) {
		this.onDrag = onDrag;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTrackMouse() {
		return trackMouse;
	}

	public void setTrackMouse(String trackMouse) {
		this.trackMouse = trackMouse;
	}

	public String getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(String deltaY) {
		this.deltaY = deltaY;
	}

	public String getShowEvent() {
		return showEvent;
	}

	public void setShowEvent(String showEvent) {
		this.showEvent = showEvent;
	}

	public String getHideEvent() {
		return hideEvent;
	}

	public void setHideEvent(String hideEvent) {
		this.hideEvent = hideEvent;
	}

	public String getShowDelay() {
		return showDelay;
	}

	public void setShowDelay(String showDelay) {
		this.showDelay = showDelay;
	}

	public String getHideDelay() {
		return hideDelay;
	}

	public void setHideDelay(String hideDelay) {
		this.hideDelay = hideDelay;
	}

	public String getOnShow() {
		return onShow;
	}

	public void setOnShow(String onShow) {
		this.onShow = onShow;
	}

	public String getOnHide() {
		return onHide;
	}

	public void setOnHide(String onHide) {
		this.onHide = onHide;
	}

	public String getOnUpdate() {
		return onUpdate;
	}

	public void setOnUpdate(String onUpdate) {
		this.onUpdate = onUpdate;
	}

	public String getOnPosition() {
		return onPosition;
	}

	public void setOnPosition(String onPosition) {
		this.onPosition = onPosition;
	}

	public String getzIndex() {
		return zIndex;
	}

	public void setzIndex(String zIndex) {
		this.zIndex = zIndex;
	}

	public String getDraggable() {
		return draggable;
	}

	public void setDraggable(String draggable) {
		this.draggable = draggable;
	}

	public String getShadow() {
		return shadow;
	}

	public void setShadow(String shadow) {
		this.shadow = shadow;
	}

	public String getInline() {
		return inline;
	}

	public void setInline(String inline) {
		this.inline = inline;
	}

	public String getModal() {
		return modal;
	}

	public void setModal(String modal) {
		this.modal = modal;
	}

	public String getSetValues() {
		return setValues;
	}

	public void setSetValues(String setValues) {
		this.setValues = setValues;
	}

	public String getSetValue() {
		return setValue;
	}

	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}

	public String getClear() {
		return clear;
	}

	public void setClear(String clear) {
		this.clear = clear;
	}

	public String getReload() {
		return reload;
	}

	public void setReload(String reload) {
		this.reload = reload;
	}

	public String getLoadData() {
		return loadData;
	}

	public void setLoadData(String loadData) {
		this.loadData = loadData;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValidType() {
		return validType;
	}

	public void setValidType(String validType) {
		this.validType = validType;
	}

	public String getMissingMessage() {
		return missingMessage;
	}

	public void setMissingMessage(String missingMessage) {
		this.missingMessage = missingMessage;
	}

	public String getInvalidMessage() {
		return invalidMessage;
	}

	public void setInvalidMessage(String invalidMessage) {
		this.invalidMessage = invalidMessage;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	public String getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(String deltaX) {
		this.deltaX = deltaX;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getCascadeCheck() {
		return cascadeCheck;
	}

	public void setCascadeCheck(String cascadeCheck) {
		this.cascadeCheck = cascadeCheck;
	}

	public String getOnlyLeafCheck() {
		return onlyLeafCheck;
	}

	public void setOnlyLeafCheck(String onlyLeafCheck) {
		this.onlyLeafCheck = onlyLeafCheck;
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public String getDnd() {
		return dnd;
	}

	public void setDnd(String dnd) {
		this.dnd = dnd;
	}

	public String getOpenLevel() {
		return openLevel;
	}

	public void setOpenLevel(String openLevel) {
		this.openLevel = openLevel;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getOnDblClick() {
		return onDblClick;
	}

	public void setOnDblClick(String onDblClick) {
		this.onDblClick = onDblClick;
	}

	public String getOnBeforeCheck() {
		return onBeforeCheck;
	}

	public void setOnBeforeCheck(String onBeforeCheck) {
		this.onBeforeCheck = onBeforeCheck;
	}

	public String getOnBeforeSelect() {
		return onBeforeSelect;
	}

	public void setOnBeforeSelect(String onBeforeSelect) {
		this.onBeforeSelect = onBeforeSelect;
	}

	public String getOnBeforeDrag() {
		return onBeforeDrag;
	}

	public void setOnBeforeDrag(String onBeforeDrag) {
		this.onBeforeDrag = onBeforeDrag;
	}

	public String getOnStartDrag() {
		return onStartDrag;
	}

	public void setOnStartDrag(String onStartDrag) {
		this.onStartDrag = onStartDrag;
	}

	public String getOnStopDrag() {
		return onStopDrag;
	}

	public void setOnStopDrag(String onStopDrag) {
		this.onStopDrag = onStopDrag;
	}

	public String getOnDragEnter() {
		return onDragEnter;
	}

	public void setOnDragEnter(String onDragEnter) {
		this.onDragEnter = onDragEnter;
	}

	public String getOnDragOver() {
		return onDragOver;
	}

	public void setOnDragOver(String onDragOver) {
		this.onDragOver = onDragOver;
	}

	public String getOnDragLeave() {
		return onDragLeave;
	}

	public void setOnDragLeave(String onDragLeave) {
		this.onDragLeave = onDragLeave;
	}

	public String getOnBeforeDrop() {
		return onBeforeDrop;
	}

	public void setOnBeforeDrop(String onBeforeDrop) {
		this.onBeforeDrop = onBeforeDrop;
	}

	public String getOnDrop() {
		return onDrop;
	}

	public void setOnDrop(String onDrop) {
		this.onDrop = onDrop;
	}

	public String getStartId() {
		return startId;
	}

	public void setStartId(String startId) {
		this.startId = startId;
	}

	public String getTreeField() {
		return treeField;
	}

	public void setTreeField(String treeField) {
		this.treeField = treeField;
	}

	public String getOnContextMenu() {
		return onContextMenu;
	}

	public void setOnContextMenu(String onContextMenu) {
		this.onContextMenu = onContextMenu;
	}

	public String getTextField() {
		return textField;
	}

	public void setTextField(String textField) {
		this.textField = textField;
	}

	public String getAnimate() {
		return animate;
	}

	public void setAnimate(String animate) {
		this.animate = animate;
	}

	public String getPidField() {
		return pidField;
	}

	public void setPidField(String pidField) {
		this.pidField = pidField;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getRowspan() {
		return rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public String getColspan() {
		return colspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getHalign() {
		return halign;
	}

	public void setHalign(String halign) {
		this.halign = halign;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getResizable() {
		return resizable;
	}

	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

	public String getFixed() {
		return fixed;
	}

	public void setFixed(String fixed) {
		this.fixed = fixed;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getStyler() {
		return styler;
	}

	public void setStyler(String styler) {
		this.styler = styler;
	}

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getResizeHandle() {
		return resizeHandle;
	}

	public void setResizeHandle(String resizeHandle) {
		this.resizeHandle = resizeHandle;
	}

	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLoadMsg() {
		return loadMsg;
	}

	public void setLoadMsg(String loadMsg) {
		this.loadMsg = loadMsg;
	}

	public String getPagePosition() {
		return pagePosition;
	}

	public void setPagePosition(String pagePosition) {
		this.pagePosition = pagePosition;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String getRownumbers() {
		return rownumbers;
	}

	public void setRownumbers(String rownumbers) {
		this.rownumbers = rownumbers;
	}

	public String getSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(String singleSelect) {
		this.singleSelect = singleSelect;
	}

	public String getAutoRowHeight() {
		return autoRowHeight;
	}

	public void setAutoRowHeight(String autoRowHeight) {
		this.autoRowHeight = autoRowHeight;
	}

	public String getStriped() {
		return striped;
	}

	public void setStriped(String striped) {
		this.striped = striped;
	}

	public String getNowrap() {
		return nowrap;
	}

	public void setNowrap(String nowrap) {
		this.nowrap = nowrap;
	}

	public String getCheckOnSelect() {
		return checkOnSelect;
	}

	public void setCheckOnSelect(String checkOnSelect) {
		this.checkOnSelect = checkOnSelect;
	}

	public String getSelectOnCheck() {
		return selectOnCheck;
	}

	public void setSelectOnCheck(String selectOnCheck) {
		this.selectOnCheck = selectOnCheck;
	}

	public String getRemoteSort() {
		return remoteSort;
	}

	public void setRemoteSort(String remoteSort) {
		this.remoteSort = remoteSort;
	}

	public String getShowHeader() {
		return showHeader;
	}

	public void setShowHeader(String showHeader) {
		this.showHeader = showHeader;
	}

	public String getShowFooter() {
		return showFooter;
	}

	public void setShowFooter(String showFooter) {
		this.showFooter = showFooter;
	}

	public String getScrollbarSize() {
		return scrollbarSize;
	}

	public void setScrollbarSize(String scrollbarSize) {
		this.scrollbarSize = scrollbarSize;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getFrozenColumns() {
		return frozenColumns;
	}

	public void setFrozenColumns(String frozenColumns) {
		this.frozenColumns = frozenColumns;
	}

	public String getFitColumns() {
		return fitColumns;
	}

	public void setFitColumns(String fitColumns) {
		this.fitColumns = fitColumns;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRowStyler() {
		return rowStyler;
	}

	public void setRowStyler(String rowStyler) {
		this.rowStyler = rowStyler;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	public String getPageList() {
		return pageList;
	}

	public void setPageList(String pageList) {
		this.pageList = pageList;
	}

	public String getLoader() {
		return loader;
	}

	public void setLoader(String loader) {
		this.loader = loader;
	}

	public String getLoadFilter() {
		return loadFilter;
	}

	public void setLoadFilter(String loadFilter) {
		this.loadFilter = loadFilter;
	}

	public String getEditors() {
		return editors;
	}

	public void setEditors(String editors) {
		this.editors = editors;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getOnLoadSuccess() {
		return onLoadSuccess;
	}

	public void setOnLoadSuccess(String onLoadSuccess) {
		this.onLoadSuccess = onLoadSuccess;
	}

	public String getOnLoadError() {
		return onLoadError;
	}

	public void setOnLoadError(String onLoadError) {
		this.onLoadError = onLoadError;
	}

	public String getOnBeforeLoad() {
		return onBeforeLoad;
	}

	public void setOnBeforeLoad(String onBeforeLoad) {
		this.onBeforeLoad = onBeforeLoad;
	}

	public String getOnClickRow() {
		return onClickRow;
	}

	public void setOnClickRow(String onClickRow) {
		this.onClickRow = onClickRow;
	}

	public String getOnDblClickRow() {
		return onDblClickRow;
	}

	public void setOnDblClickRow(String onDblClickRow) {
		this.onDblClickRow = onDblClickRow;
	}

	public String getOnClickCell() {
		return onClickCell;
	}

	public void setOnClickCell(String onClickCell) {
		this.onClickCell = onClickCell;
	}

	public String getOnDblClickCell() {
		return onDblClickCell;
	}

	public void setOnDblClickCell(String onDblClickCell) {
		this.onDblClickCell = onDblClickCell;
	}

	public String getOnSortColumn() {
		return onSortColumn;
	}

	public void setOnSortColumn(String onSortColumn) {
		this.onSortColumn = onSortColumn;
	}

	public String getOnResizeColumn() {
		return onResizeColumn;
	}

	public void setOnResizeColumn(String onResizeColumn) {
		this.onResizeColumn = onResizeColumn;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}

	public String getOnUnselect() {
		return onUnselect;
	}

	public void setOnUnselect(String onUnselect) {
		this.onUnselect = onUnselect;
	}

	public String getOnSelectAll() {
		return onSelectAll;
	}

	public void setOnSelectAll(String onSelectAll) {
		this.onSelectAll = onSelectAll;
	}

	public String getOnUnselectAll() {
		return onUnselectAll;
	}

	public void setOnUnselectAll(String onUnselectAll) {
		this.onUnselectAll = onUnselectAll;
	}

	public String getOnCheck() {
		return onCheck;
	}

	public void setOnCheck(String onCheck) {
		this.onCheck = onCheck;
	}

	public String getOnUncheck() {
		return onUncheck;
	}

	public void setOnUncheck(String onUncheck) {
		this.onUncheck = onUncheck;
	}

	public String getOnCheckAll() {
		return onCheckAll;
	}

	public void setOnCheckAll(String onCheckAll) {
		this.onCheckAll = onCheckAll;
	}

	public String getOnUncheckAll() {
		return onUncheckAll;
	}

	public void setOnUncheckAll(String onUncheckAll) {
		this.onUncheckAll = onUncheckAll;
	}

	public String getOnBeforeEdit() {
		return onBeforeEdit;
	}

	public void setOnBeforeEdit(String onBeforeEdit) {
		this.onBeforeEdit = onBeforeEdit;
	}

	public String getOnAfterEdit() {
		return onAfterEdit;
	}

	public void setOnAfterEdit(String onAfterEdit) {
		this.onAfterEdit = onAfterEdit;
	}

	public String getOnCancelEdit() {
		return onCancelEdit;
	}

	public void setOnCancelEdit(String onCancelEdit) {
		this.onCancelEdit = onCancelEdit;
	}

	public String getOnHeaderContextMenu() {
		return onHeaderContextMenu;
	}

	public void setOnHeaderContextMenu(String onHeaderContextMenu) {
		this.onHeaderContextMenu = onHeaderContextMenu;
	}

	public String getOnRowContextMenu() {
		return onRowContextMenu;
	}

	public void setOnRowContextMenu(String onRowContextMenu) {
		this.onRowContextMenu = onRowContextMenu;
	}

	public String getQueryform() {
		return queryform;
	}

	public void setQueryform(String queryform) {
		this.queryform = queryform;
	}

	public String getQuerysource() {
		return querysource;
	}

	public void setQuerysource(String querysource) {
		this.querysource = querysource;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getHeaderCls() {
		return headerCls;
	}

	public void setHeaderCls(String headerCls) {
		this.headerCls = headerCls;
	}

	public String getBodyCls() {
		return bodyCls;
	}

	public void setBodyCls(String bodyCls) {
		this.bodyCls = bodyCls;
	}

	public String getFit() {
		return fit;
	}

	public void setFit(String fit) {
		this.fit = fit;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getDoSize() {
		return doSize;
	}

	public void setDoSize(String doSize) {
		this.doSize = doSize;
	}

	public String getNoheader() {
		return noheader;
	}

	public void setNoheader(String noheader) {
		this.noheader = noheader;
	}

	public String getContent() {
		return content;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCollapsible() {
		return collapsible;
	}

	public void setCollapsible(String collapsible) {
		this.collapsible = collapsible;
	}

	public String getMinimizable() {
		return minimizable;
	}

	public void setMinimizable(String minimizable) {
		this.minimizable = minimizable;
	}

	public String getMaximizable() {
		return maximizable;
	}

	public void setMaximizable(String maximizable) {
		this.maximizable = maximizable;
	}

	public String getClosable() {
		return closable;
	}

	public void setClosable(String closable) {
		this.closable = closable;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getCollapsed() {
		return collapsed;
	}

	public void setCollapsed(String collapsed) {
		this.collapsed = collapsed;
	}

	public String getMinimized() {
		return minimized;
	}

	public void setMinimized(String minimized) {
		this.minimized = minimized;
	}

	public String getMaximized() {
		return maximized;
	}

	public void setMaximized(String maximized) {
		this.maximized = maximized;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCache() {
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public String getLoadingMessage() {
		return loadingMessage;
	}

	public void setLoadingMessage(String loadingMessage) {
		this.loadingMessage = loadingMessage;
	}

	public String getExtractor() {
		return extractor;
	}

	public void setExtractor(String extractor) {
		this.extractor = extractor;
	}

	public String getOnLoad() {
		return onLoad;
	}

	public void setOnLoad(String onLoad) {
		this.onLoad = onLoad;
	}

	public String getOnBeforeOpen() {
		return onBeforeOpen;
	}

	public void setOnBeforeOpen(String onBeforeOpen) {
		this.onBeforeOpen = onBeforeOpen;
	}

	public String getOnOpen() {
		return onOpen;
	}

	public void setOnOpen(String onOpen) {
		this.onOpen = onOpen;
	}

	public String getOnBeforeClose() {
		return onBeforeClose;
	}

	public void setOnBeforeClose(String onBeforeClose) {
		this.onBeforeClose = onBeforeClose;
	}

	public String getOnClose() {
		return onClose;
	}

	public void setOnClose(String onClose) {
		this.onClose = onClose;
	}

	public String getOnBeforeDestroy() {
		return onBeforeDestroy;
	}

	public void setOnBeforeDestroy(String onBeforeDestroy) {
		this.onBeforeDestroy = onBeforeDestroy;
	}

	public String getOnDestroy() {
		return onDestroy;
	}

	public void setOnDestroy(String onDestroy) {
		this.onDestroy = onDestroy;
	}

	public String getOnBeforeCollapse() {
		return onBeforeCollapse;
	}

	public void setOnBeforeCollapse(String onBeforeCollapse) {
		this.onBeforeCollapse = onBeforeCollapse;
	}

	public String getOnCollapse() {
		return onCollapse;
	}

	public void setOnCollapse(String onCollapse) {
		this.onCollapse = onCollapse;
	}

	public String getOnBeforeExpand() {
		return onBeforeExpand;
	}

	public void setOnBeforeExpand(String onBeforeExpand) {
		this.onBeforeExpand = onBeforeExpand;
	}

	public String getOnExpand() {
		return onExpand;
	}

	public void setOnExpand(String onExpand) {
		this.onExpand = onExpand;
	}

	public String getOnResize() {
		return onResize;
	}

	public void setOnResize(String onResize) {
		this.onResize = onResize;
	}

	public String getOnMove() {
		return onMove;
	}

	public void setOnMove(String onMove) {
		this.onMove = onMove;
	}

	public String getOnMaximize() {
		return onMaximize;
	}

	public void setOnMaximize(String onMaximize) {
		this.onMaximize = onMaximize;
	}

	public String getOnRestore() {
		return onRestore;
	}

	public void setOnRestore(String onRestore) {
		this.onRestore = onRestore;
	}

	public String getOnMinimize() {
		return onMinimize;
	}

	public void setOnMinimize(String onMinimize) {
		this.onMinimize = onMinimize;
	}

	public String getTagClass() {
		if (null != this.getStyleClass()) {
			return tagClass + " " + this.getStyleClass();
		}
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

	public String[] getOptionNamesString() {
		return OptionNamesString;
	}

	public void setOptionNamesString(String[] tagOptionNamesString) {
		OptionNamesString = tagOptionNamesString;
	}

	public String[] getOptionNamesBoolean() {
		return OptionNamesBoolean;
	}

	public void setOptionNamesBoolean(String[] tagOptionNamesBoolean) {
		OptionNamesBoolean = tagOptionNamesBoolean;
	}

	public String[] getOptionNamesNumber() {
		return OptionNamesNumber;
	}

	public void setOptionNamesNumber(String[] tagOptionNamesNumber) {
		OptionNamesNumber = tagOptionNamesNumber;
	}

	public String[] getEventNames() {
		return EventNames;
	}

	public void setEventNames(String[] tagEventNames) {
		EventNames = tagEventNames;
	}

	protected void appendOptions(StringBuffer sb, String[] options,
			String valueRound) {

		for (int i = 0; i < options.length; i++) {
			String option = options[i];
			String value = null;

			if (sb.indexOf("," + option + ":") >= 0) {
				continue;
			}
			try {
				value = BeanUtils.getProperty(this, option);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (value != null && !"".equals(value)) {
				sb.append(option);
				sb.append(":");
				sb.append(valueRound);
				sb.append(value);
				sb.append(valueRound);
				sb.append(",");
			}

		}

	}

	
	protected void prepareAttrNames(StringBuffer sb) {
		prepareAttribute(sb, "id", this.getId());
		prepareAttribute(sb, "class", this.getTagClass());
		prepareAttribute(sb, "style", this.getStyle());
		prepareAttribute(sb, "name", this.getName());
		prepareAttribute(sb, "type", this.getType());
		prepareAttribute(sb, "href", this.getHref());
		prepareAttribute(sb, "onclick", this.getOnclick());
		prepareAttribute(sb, "codeType", this.getCodeType());
		prepareAttribute(sb, "codeFilter", this.getCodeFilter());
		prepareAttribute(sb, "codePlease", this.getCodePlease());
		prepareAttribute(sb, "operator", this.getOperator());
		prepareAttribute(sb, "queryform", this.getQueryform());
		prepareAttribute(sb, "querysource", this.getQuerysource());
		prepareAttribute(sb, "value", this.getValue());
		prepareAttribute(sb, "idField", this.getIdField());
		prepareAttribute(sb, "pidField", this.getPidField());
		prepareAttribute(sb, "alt", this.getAlt());
		prepareAttribute(sb, "coords", this.getCoords());
		prepareAttribute(sb, "nohref", this.getNohref());
		prepareAttribute(sb, "shape", this.getShape());
		prepareAttribute(sb, "target", this.getTarget());
		prepareAttribute(sb, "ondblclick", this.getOndblclick());
		prepareAttribute(sb, "onChange", this.getOnChange());
		prepareAttribute(sb, "onmousedown", this.getOnmousedown());
		prepareAttribute(sb, "onmouseup", this.getOnmouseup());
		prepareAttribute(sb, "onmouseover", this.getOnmouseover());
		prepareAttribute(sb, "onmousemove", this.getOnmousemove());
		prepareAttribute(sb, "onmouseout", this.getOnmouseout());
		prepareAttribute(sb, "onkeypress", this.getOnkeypress());
		prepareAttribute(sb, "onkeydown", this.getOnkeydown());
		prepareAttribute(sb, "onkeyup", this.getOnkeyup());
		prepareAttribute(sb, "onfocus", this.getOnfocus());
		prepareAttribute(sb, "onblur", this.getOnblur());
		prepareAttribute(sb, "method", this.getMethod());
	}

	protected void prepareOptions(StringBuffer sb, String[] optionNamesString,
			String[] optionNamesBoolean, String[] optionNamesNumber,
			String[] eventNames) {
		// 增加“,”的目的：
		// appendOptions内部需要对属性 optionNamesString 名称进行判断重复的不进行添加
		// 判断标准是",optionName:"
		// 另一种方式是在option内部对 name进行去重

		StringBuffer options = new StringBuffer(",");

		appendOptions(options, optionNamesString, "'");

		appendOptions(options, optionNamesBoolean, "");

		appendOptions(options, optionNamesNumber, "");

		appendOptions(options, eventNames, "");

		options.deleteCharAt(0);

		//niujiale 2013-10-10
		//浏览器兼容模式下，生成的js属性以逗号结尾的会出现问题
		//去掉结尾多余的逗号后，兼容模式和正常模式都没有问题
		if(options.length()>1){
			options.deleteCharAt(options.length()-1);
		}
		// 标签开发测试使用
		if (null != this.getOptions()) {
			this.setOptions(this.getOptions() + "," + options.toString());
		} else {
			this.setOptions(options.toString());
		}
		prepareAttribute(sb, "data-options", this.getOptions());

	}
	

	

	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer("<");
		if (null == this.getTagName()) {
			this.setTagName("err");
		}
		sb.append(this.getTagName());

		this.prepareAttrNames(sb);

		this.prepareOptions(sb, this.getOptionNamesString(),
				this.getOptionNamesBoolean(), this.getOptionNamesNumber(),
				this.getEventNames());

		sb.append(">");

		this.outHtml(sb);

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {

		this.outHtml("</" + this.getTagName() + ">");
		release();
		return EVAL_PAGE;
	}

	public void release() {
		this.setOptions(null);
		super.release();
	}

}
