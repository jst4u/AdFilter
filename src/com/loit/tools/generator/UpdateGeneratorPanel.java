package com.loit.tools.generator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oracle.security.o3logon.a;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.tools.common.ClassUtils;

import com.loit.core.utils.SpringUtil;
import com.loit.core.utils.SqlUtil;
import com.loit.tools.utils.UIUtils;
import com.loit.tools.workspace.BasePanel;

public class UpdateGeneratorPanel extends BasePanel
{
  private static final long serialVersionUID = 265280396368300462L;
  protected final Log logger = LogFactory.getLog(getClass());
  private JTable parametersTable;
  private JScrollPane parametersJScrollPan;
  private JPanel parametersPanel;
  private JTabbedPane centerTabbedPane;
  private JPanel updateInfoPanel;
  private JTextField updateTextField;
  private JLabel updateNameLabel;
  private JTextArea viewTextArea;
  private JScrollPane sqlScrollPane;
  private JPanel sqlPanel;
  private JPanel updatePanel;
  private JButton generateButton;
  private JPanel buttonPanel;
  private JPanel layoutPanelCenter;
  private JComboBox genPackageList;
  private JLabel packageNameLabel;
  private JPanel packagePanel;
  private DefaultTableModel parametersTableModel = new DefaultTableModel(new String[][] { { "loading..." } }, new String[] { "Parameter Name", "Type"  });
  private UpdateGenerator updateGenerator;
  private Map<String, String> parametersMap = new HashMap();

  public UpdateGeneratorPanel()
  {
    b();
  }

  private void b() {
    try {
      setLayout(new BorderLayout());
      setPreferredSize(new Dimension(640, 480));
      setTitle("Update Generator");

      this.layoutPanelCenter = new JPanel();
      add(this.layoutPanelCenter, "Center");
      this.layoutPanelCenter.setLayout(new BorderLayout());

      this.centerTabbedPane = new JTabbedPane();
      this.layoutPanelCenter.add(this.centerTabbedPane, "Center");

      this.sqlPanel = new JPanel();
      this.centerTabbedPane.addTab("Sql", null, this.sqlPanel, null);
      this.sqlPanel.setLayout(new BorderLayout());

      this.sqlScrollPane = new JScrollPane();
      this.sqlPanel.add(this.sqlScrollPane, "Center");

      this.viewTextArea = new JTextArea();
      this.sqlScrollPane.setViewportView(this.viewTextArea);
//      this.viewTextArea.setText("update table1 t\n   set t.c1 = :p1\n where t.c2 = :p2");
      this.viewTextArea.setText("update table1 new_table set new_tablecol = :p1 \n where new_tablecol = :p2");

      this.parametersPanel = new JPanel();
      this.centerTabbedPane.addTab(
        "Parameters", 
        null, 
        this.parametersPanel, 
        null);
      this.parametersPanel.setLayout(new BorderLayout());
      this.parametersPanel.addComponentListener(new ComponentAdapter(){
    	  public void componentShown(ComponentEvent evt)
    	  {
    	    a(evt);
    	  }
      });

      this.parametersJScrollPan = new JScrollPane();
      this.parametersPanel.add(
        this.parametersJScrollPan, "Center");

      this.parametersTable = new JTable();
      this.parametersJScrollPan.setViewportView(this.parametersTable);
      this.parametersTable.setModel(this.parametersTableModel);
      this.parametersTable.getColumnModel().getColumn(1).setCellEditor(
        new DefaultCellEditor(
        new JComboBox(new String[] { "String", "Integer", "Double", "Date", 
        "String[]", "Integer[]", "Double[]", "Date[]" })));

      this.updatePanel = new JPanel();
      this.layoutPanelCenter.add(this.updatePanel, "South");
      BoxLayout e = new BoxLayout(this.updatePanel, 1);
      this.updatePanel.setLayout(e);

      this.packagePanel = new JPanel();
      this.updatePanel.add(this.packagePanel);
      FlowLayout d = new FlowLayout();
      d.setAlignment(0);
      this.packagePanel.setLayout(d);

      this.packageNameLabel = new JLabel();
      this.packagePanel.add(this.packageNameLabel);
      this.packageNameLabel.setText("Package name:");
      this.packageNameLabel.setPreferredSize(new Dimension(99, 15));
      this.packageNameLabel.setHorizontalAlignment(11);

      ComboBoxModel a = new DefaultComboBoxModel();
      this.genPackageList = new JComboBox();
      this.packagePanel.add(this.genPackageList);
      this.genPackageList.setModel(a);
      this.genPackageList.setEditable(true);
      this.genPackageList.setPreferredSize(new Dimension(300, 20));

      this.updateInfoPanel = new JPanel();
      FlowLayout c = new FlowLayout();
      c.setAlignment(0);
      this.updatePanel.add(this.updateInfoPanel);
      this.updateInfoPanel.setLayout(c);

      this.updateNameLabel = new JLabel();
      this.updateInfoPanel.add(this.updateNameLabel);
      this.updateNameLabel.setText("Update name:");
      this.updateNameLabel.setPreferredSize(new Dimension(99, 15));
      this.updateNameLabel.setHorizontalAlignment(11);

      this.updateTextField = new JTextField();
      this.updateInfoPanel.add(this.updateTextField);
      this.updateTextField.setText("XxxUpdate");
      this.updateTextField.setPreferredSize(new Dimension(300, 20));

      this.buttonPanel = new JPanel();
      FlowLayout b = new FlowLayout();
      b.setAlignment(0);
      this.updatePanel.add(this.buttonPanel);
      this.buttonPanel.setLayout(b);

      this.buttonPanel.add(Box.createRigidArea(new Dimension(99, 15)));

      this.generateButton = new JButton();
      this.buttonPanel.add(this.generateButton);
      this.generateButton.setText("Generate");
      this.generateButton.setMnemonic(71);
      this.generateButton
        .addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doGenerate(e);
			}
        	
        });
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    this.viewTextArea.setEnabled(enabled);
    this.genPackageList.setEnabled(enabled);
    this.updateTextField.setEnabled(enabled);
    this.generateButton.setEnabled(enabled);
  }

  public void requestFocus() {
    super.requestFocus();
    this.viewTextArea.requestFocus();
  }

  public boolean init() {
    new Thread(){
    	public void run(){
    		try
    	    {
//    	      UpdateGeneratorPanel.b(this.a);
//    	      UpdateGeneratorPanel.a(this.a, new UpdateGenerator());
    		  updateGenerator = new UpdateGenerator();
    	    } catch (Exception a) {
    	    	logger.error("Update Generator init error", a);
    	    }
    	}
    }.start();
    return true;
  }

  private void getPackageList() {
		Object[] projectPackageNamesArray = SpringUtil.getProjectPackageNames().toArray();
	    for (int a = 0; a < projectPackageNamesArray.length; a++) {
	    	projectPackageNamesArray[a] = (projectPackageNamesArray[a] + ".update");
	    }
	    this.genPackageList.setModel(new DefaultComboBoxModel(projectPackageNamesArray));
	  }

  private void setParameters(){
	    Vector parametersTableVector = this.parametersTableModel.getDataVector();
	    for(int t=0; t < parametersTableVector.size(); t++){
	    	Vector parameter = (Vector)parametersTableVector.get(t);
	    	this.parametersMap.put((String)parameter.get(0), (String)parameter.get(1));
	    }

	    this.parametersTableModel.setRowCount(0);
	    String viewText = this.viewTextArea.getText();
	    if ((viewText == null) || (viewText.trim().length() == 0)) {
	      return;
	    }

	    viewText = SqlUtil.convertParametersToJavaStyle(viewText);
	    this.viewTextArea.setText(viewText);

    Map sqlParameter = SqlUtil.getSqlParameters(viewText);

    Iterator localIterator2 = sqlParameter.values().iterator();
    while (localIterator2.hasNext()) { 
    	String iterator2Next = (String)localIterator2.next();
      this.parametersTableModel.addRow(new Object[] { iterator2Next, "String", this.parametersMap.get(iterator2Next) });
    }
  }

  private void doGenerate(ActionEvent evt)
  {
    if (this.updateGenerator == null)
      return;
    Object selectPackage = this.genPackageList.getSelectedItem();
    if (selectPackage == null) {
		UIUtils.showInformation("info", "请选择或输入Package name!");
		return;
	}
    final String genPackage = selectPackage.toString().trim();// b
	if (genPackage.length() == 0) {
		UIUtils.showInformation("info", "请选择或输入Package name!");
		return;
	}
	final String genQueryText = this.updateTextField.getText().trim();
    if ((genQueryText== null) || (genQueryText.length() == 0)){
    	UIUtils.showInformation("info", "请选择或输入Query Text!");
    	return;
    }
    final String viewText = this.viewTextArea.getText();
    if ((viewText == null) || (viewText.trim().length() == 0)) {
    	UIUtils.showInformation("info", "请选择或输入viewText!");
    	return;
    }
    setEnabled(false);
    setParameters();
    new Thread() {
		public void run() {
			try
		    {
			  updateGenerator.reset();
			  updateGenerator.generateUpdate(genPackage, genQueryText, viewText, parametersMap);
		    } catch (Exception ex) {
		    	logger.error("Update Generator error", ex);
		      UIUtils.showError("Update Generator error", ex);
		    }
		    int a;
		    if ((a = updateGenerator.getCount()) > 0)
		    {
		    	logger.info(a + " file(s) generated. Refresh the project.");
		      UIUtils.showInformation("Info", a + " file(s) generated. \nRefresh the project.");
		    }
		    setEnabled(true);
		  }
	}.start();
  }

  private void a(ComponentEvent evt) {
	  setParameters();
  }
}
