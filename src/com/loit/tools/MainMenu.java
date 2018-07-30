package com.loit.tools;

import com.loit.tools.fields.FieldDefinitionsNodeLoader;
import com.loit.tools.generator.CrudGeneratorPanel;
import com.loit.tools.generator.QueryGeneratorPanel;
import com.loit.tools.generator.UpdateGeneratorPanel;
import com.loit.tools.testclient.TestClientNodeLoader;
import com.loit.tools.treemenu.TreeMenu;
import com.loit.tools.treemenu.TreeNode;

public class MainMenu extends TreeMenu {

	public MainMenu(TreeNode rootNode) {
		super(rootNode);
	}

	public MainMenu() {
		super(new TreeNode("LOIT \"数据层\"开发工具 - ", null, null, new Object[0]));
		TreeNode node = new TreeNode("自动编写代码", null, null, new Object[0]);
		node.add(new TreeNode("编写\"数据库操作\"代码", null, CrudGeneratorPanel.class, new Object[0]));
		node.add(new TreeNode("编写\"自定义查询\"代码", null, QueryGeneratorPanel.class, new Object[0]));
		node.add(new TreeNode("编写\"自定义更新\"代码", null, UpdateGeneratorPanel.class, new Object[0]));

		getRootNode().add(node);
		getRootNode().add(new TreeNode("编辑\"数据库对象\"属性", new FieldDefinitionsNodeLoader(), null, new Object[0]));
		getRootNode().add(new TreeNode("代码测试客户端", new TestClientNodeLoader(), null, new Object[0]));
	}
}
