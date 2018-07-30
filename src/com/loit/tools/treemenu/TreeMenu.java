package com.loit.tools.treemenu;

import javax.swing.JTree;
import com.loit.tools.workspace.BasePanel;
import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TreeMenu extends JTree {

	private TreeNode rootNode;
	private JPanel bJpanel;
	private File treeFile = new File("TreeMenuPath");
	private Map<Class<? extends BasePanel>, BasePanel> treeBasePanelCache = new HashMap();

	public TreeMenu(TreeNode rootNode) {
		super(rootNode);
		this.rootNode = rootNode;

		getSelectionModel().setSelectionMode(1);

		addTreeSelectionListener(new MenuTreeSelectionListener(this));

		addTreeWillExpandListener(new MenuTreeExpandListener(this));
	}

	private BasePanel getTreePanel(Class<? extends BasePanel> clazz) {
		if (this.treeBasePanelCache.containsKey(clazz))
			return (BasePanel) this.treeBasePanelCache.get(clazz);
		try {
			BasePanel b = (BasePanel) clazz.newInstance();
			b.init();
			this.treeBasePanelCache.put(clazz, b);
			return b;
		} catch (Exception a) {
			LogFactory.getLog(getClass()).error("Error", a);
		}
		return new BasePanel();
	}

	private void selectNode(TreeSelectionEvent evt) {
		if (this.bJpanel == null) {
			return;
		}
		this.bJpanel.removeAll();
		TreePath selectPath = getSelectionPath();
		if (selectPath == null) {
			return;
		}

		TreeNode lastNode = (TreeNode) selectPath.getLastPathComponent();
		Class<? extends BasePanel> pannelClass = lastNode.getPanelClass();
		if (pannelClass != null) {
			BasePanel pannel = getTreePanel(pannelClass);
			Object[] objParam = lastNode.getParameters();
			if (objParam != null) {
				pannel.setParameters(objParam);
			}
			pannel.refresh();
			this.bJpanel.add(pannel, "Center");
			pannel.requestFocus();
		}
		this.bJpanel.validate();
		this.bJpanel.repaint();
	}

	private void expansionTree(TreeExpansionEvent evt) {
		TreeNode lastNode = (TreeNode) evt.getPath().getLastPathComponent();
		if (lastNode.isChildrenLoaded()) {
			return;
		}
		TreeNodeLoader nodeLoader = lastNode.getChildrenLoader();
		if (nodeLoader != null) {
			new loadTreeChildren(this, lastNode, nodeLoader).start();
		}
	}

	public void refresh() {
		refresh(getRootNode());
	}

	public void refresh(TreeNode treeNode) {
		if (treeNode.getChildrenLoader() != null) {
			treeNode.removeAllChildren();
			treeNode.add(new TreeNode("loading...", null, null, new Object[0]));
			treeNode.setChildrenLoaded(false);
			((DefaultTreeModel) getModel()).reload(treeNode);
			if (isExpanded(new TreePath(treeNode.getPath())))
				new refreshChildren(this, treeNode).start();
		} else {
			for (int i = 0; i < treeNode.getChildCount(); i++) {
				refresh((TreeNode) treeNode.getChildAt(i));
			}
		}
	}

	public void refreshPanels() {
		this.treeBasePanelCache.clear();
	}

	public void saveTreePath() throws Exception {
		TreePath treePath = getSelectionPath();// d;
		Object[] treeObj = (treePath == null ? new Object[0] : treePath.getPath());
		List<String> b = new ArrayList<String>();

		for (int i = 0; i < treeObj.length; i++) {
			b.add(((TreeNode) treeObj[i]).getName());
		}
		FileUtils.writeLines(this.treeFile, b);
	}

	public void loadTreePath() throws Exception {
		SwingUtilities.invokeAndWait(new InitTree(this));

		if (!this.treeFile.exists()) {
			return;
		}
		List<String> treePathList = FileUtils.readLines(this.treeFile);

		TreeNode rootNode = getRootNode();// g
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		Iterator treePathIterator = treePathList.iterator();
		boolean flagRoot = false;
		while (treePathIterator.hasNext()) {
			String treePathName = (String) treePathIterator.next();
			if (nodeList.size() == 0 && treePathName.equals(rootNode.getName())) {
				nodeList.add(rootNode);
				flagRoot = true;
			} else {
				for (int i = 0; i < rootNode.getChildCount(); i++) {
					TreeNode child = (TreeNode) rootNode.getChildAt(i);
					if (treePathName.equals(child.getName())) {
						rootNode = child;
						nodeList.add(child);
						flagRoot = true;
						break;
					}

				}
			}
			if (!flagRoot) {
				break;
			}

			TreePath b = new TreePath(nodeList.toArray());
			SwingUtilities.invokeAndWait(new loadTreePath(this, b));

			if ((!rootNode.isChildrenLoaded()) && (rootNode.getChildrenLoader() != null))
				synchronized (rootNode) {
					while (!rootNode.isChildrenLoaded())
						try {
							rootNode.wait();
						} catch (InterruptedException localInterruptedException) {
						}
				}
			if (!b.equals(getSelectionPath()))
				break;
		}
	}

	public TreeNode getRootNode() {
		return this.rootNode;
	}

	public JPanel getOperatePanel() {
		return this.bJpanel;
	}

	public void setOperatePanel(JPanel operatePanel) {
		operatePanel.setLayout(new BorderLayout());
		this.bJpanel = operatePanel;
	}

	public Collection<BasePanel> getPanels() {
		return this.treeBasePanelCache.values();
	}

	public void setPathFileName(String pathFileName) {
		this.treeFile = new File(pathFileName);
	}

	private class MenuTreeSelectionListener implements TreeSelectionListener {
		private TreeMenu treeMenu;

		MenuTreeSelectionListener(TreeMenu pTreeMenu) {
			this.treeMenu = pTreeMenu;
		}

		@Override
		public void valueChanged(TreeSelectionEvent arg0) {
			this.treeMenu.selectNode(arg0);
		}

	}

	private class MenuTreeExpandListener implements TreeWillExpandListener {
		private TreeMenu treeMenu;

		MenuTreeExpandListener(TreeMenu pTreeMenu) {
			this.treeMenu = pTreeMenu;
		}

		@Override
		public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
			// TODO Auto-generated method stub

		}

		@Override
		public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
			this.treeMenu.expansionTree(event);

		}
	}

	// private class loadTreeChildren extends Thread {
	// private TreeMenu treeMenu;
	//
	// loadTreeChildren(TreeMenu paramTreeMenu, TreeNode paramTreeNode,
	// TreeNodeLoader paramTreeNodeLoader) {
	// }
	//
	// public void run() {
	// try {
	// synchronized (this) {
	// this.treeMenu.loadChildren(this);
	// ((DefaultTreeModel) this.treeMenu.getModel()).reload();
	// this.treeMenu.setChildrenLoaded(true);
	// this.treeMenu.notifyAll();
	// }
	// } catch (Exception a) {
	// LogFactory.getLog(getClass()).error("Error", a);
	// }
	// }
	// }

	private class InitTree implements Runnable {
		private TreeMenu treeMenu;

		InitTree(TreeMenu paramTreeMenu) {
			this.treeMenu = paramTreeMenu;
		}

		public void run() {
			this.treeMenu.setSelectionRow(0);
			this.treeMenu.expandRow(0);
		}
	}

	private class loadTreeChildren extends Thread {
		private TreeMenu treeMenu;
		private TreeNode treeNode;
		private TreeNodeLoader treeNodeLoader;

		loadTreeChildren(TreeMenu paramTreeMenu, TreeNode paramTreeNode, TreeNodeLoader paramTreeNodeLoader) {
			this.treeMenu = paramTreeMenu;
			this.treeNode = paramTreeNode;
			this.treeNodeLoader = paramTreeNodeLoader;
		}

		public void run() {
			try {
				synchronized (this.treeNode) {
					this.treeNodeLoader.loadChildren(this.treeNode);
					((DefaultTreeModel) this.treeMenu.getModel()).reload(this.treeNode);
					this.treeNode.setChildrenLoaded(true);
					this.treeNode.notifyAll();
				}
			} catch (Exception a) {
				LogFactory.getLog(getClass()).error("Error", a);
			}
		}
	}

	private class refreshChildren extends Thread {
		private TreeMenu treeMenu;
		private TreeNode treeNode;

		refreshChildren(TreeMenu paramTreeMenu, TreeNode paramTreeNode) {
			this.treeMenu = paramTreeMenu;
			this.treeNode = paramTreeNode;
		}

		public void run() {
			try {
				synchronized (this.treeNode) {
					this.treeNode.getChildrenLoader().loadChildren(this.treeNode);
					((DefaultTreeModel) this.treeMenu.getModel()).reload(this.treeNode);
					this.treeNode.setChildrenLoaded(true);
					this.treeNode.notifyAll();
				}
			} catch (Exception a) {
				LogFactory.getLog(getClass()).error("Error", a);
			}
		}
	}

	private class loadTreePath implements Runnable {
		private TreeMenu treeMenu;
		private TreePath treePath;

		loadTreePath(TreeMenu paramTreeMenu, TreePath paramTreePath) {
			this.treeMenu = paramTreeMenu;
			this.treePath = paramTreePath;
		}

		public void run() {
			this.treeMenu.expandPath(this.treePath);
			this.treeMenu.setSelectionPath(this.treePath);
			this.treeMenu.scrollPathToVisible(this.treePath);
		}
	}

}
