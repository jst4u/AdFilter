package com.loit.core.web.tag.easyui;

public class ColumnTag extends BaseEasyUITag {
	/**
	 * Dependencies
	 * panel
	 * resizable
	 */
	private static final long serialVersionUID = 1L;

	private static String[] ColumnOptionNamesString = {"title","field","align","halign"};
	private static String[] ColumnOptionNamesBoolean = {"sortable","order","resizable","fixed","hidden","checkbox","frozened"};
	private static String[] ColumnOptionNamesNumber = {"width","rowspan","colspan"};
	private static String[] ColumnEventNames = {"formatter","styler","sorter","editor"};
	
	private String frozened ;
	
	public String getFrozened() {
		return frozened;
	}

	public void setFrozened(String frozened) {
		this.frozened = frozened;
	}

	public ColumnTag() {
		this.setTagClass("");
		this.setOptionNamesString(ColumnOptionNamesString);
		this.setOptionNamesBoolean(ColumnOptionNamesBoolean);
		this.setOptionNamesNumber(ColumnOptionNamesNumber);
		this.setEventNames(ColumnEventNames);
	}
	
	public int doStartTag(){
		if(null==this.getHalign()){
			this.setHalign("Center");
		}
		StringBuffer sb = new StringBuffer("<th");
		this.prepareAttrNames(sb);
		this.prepareOptions(sb, this.getOptionNamesString(), this.getOptionNamesBoolean(), this.getOptionNamesNumber(),
				this.getEventNames());
		sb.append("></th>");
		
		try {
			DataGridTag parentTag = (DataGridTag)getParent();
			if("true".equals(this.frozened)){
				String cols = parentTag.getFrozenColumns();
				if(null == cols){
					cols = "";
				}
				parentTag.setFrozenColumns(cols+sb.toString());
			}else{
				String cols = parentTag.getColumns();
				if(null == cols){
					cols = "";
				}
				parentTag.setColumns(cols+sb.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return SKIP_BODY;
	}
	
	public int doEndTag(){
		release();
		return EVAL_PAGE;
	}
}
