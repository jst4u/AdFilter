package com.loit.core.web.tag.tree;

import java.util.ArrayList;
import java.util.List;

public class WebTreeNode {
	private String e;
	private String D;
	private String d;
	private String C;
	private boolean c = true;
	private boolean B = true;
	private String b;
	private Object A;
	private List<WebTreeNode> a;

	public WebTreeNode() {
	}

	public WebTreeNode(String id, String label) {
		this.a = new ArrayList();
		this.e = id;
		this.D = label;
	}

	public WebTreeNode(String id, String label, String desc) {
		this.a = new ArrayList();
		this.e = id;
		this.D = label;
		this.C = desc;
	}

	public WebTreeNode(String id, String label, String desc, String icon) {
		this.a = new ArrayList();
		this.e = id;
		this.D = label;
		this.C = desc;
		this.d = icon;
	}

	public String getId() {
		return this.e;
	}

	public void setId(String id) {
		this.e = id;
	}

	public String getLabel() {
		return this.D;
	}

	public void setLabel(String label) {
		this.D = label;
	}

	public String getDesc() {
		return this.C;
	}

	public void setDesc(String desc) {
		this.C = desc;
	}

	public String getIcon() {
		return this.d;
	}

	public void setIcon(String icon) {
		this.d = icon;
	}

	public boolean isLeaf() {
		return this.c;
	}

	public boolean isChildLoaded() {
		return this.B;
	}

	public void setChildLoaded(boolean isChildLoaded) {
		this.B = isChildLoaded;
	}

	public String getParent() {
		return this.b;
	}

	public void setParent(String parent) {
		this.b = parent;
	}

	public void setLeaf(boolean isLeaf) {
		this.c = isLeaf;
	}

	public Object getObject() {
		return this.A;
	}

	public void setObject(Object object) {
		this.A = object;
	}

	public List<WebTreeNode> getChildren() {
		return this.a;
	}

	public void setChildren(List<WebTreeNode> children) {
		this.a = children;
	}

	public void addChildNode(WebTreeNode childNode) {
		this.a.add(childNode);
	}

	public void addChildNodes(List<WebTreeNode> childNodes) {
		this.a.addAll(childNodes);
	}
}
