package com.loit.tools.workspace;

import javax.swing.JPanel;

public class BasePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String title;
	protected Object[] parameters;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object[] getParameters() {
		return this.parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public boolean init() {
		return true;
	}

	public boolean refresh() {
		return true;
	}

	public boolean save() {
		return true;
	}

	public boolean cancel() {
		return true;
	}

	public boolean close() {
		return true;
	}

}
