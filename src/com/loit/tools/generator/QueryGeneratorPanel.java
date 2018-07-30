package com.loit.tools.generator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.loit.core.utils.SpringUtil;
import com.loit.core.utils.SqlUtil;
import com.loit.tools.utils.ConnectionUtils;
import com.loit.tools.utils.UIUtils;
import com.loit.tools.workspace.BasePanel;

public class QueryGeneratorPanel extends BasePanel
{
  private static final long serialVersionUID = 265280396368300462L;
  protected final Log logger = LogFactory.getLog(getClass());
  private JTable parametersTable;
  private JScrollPane parametersJScrollPane;
  private JPanel parametersPanel;
  private JTabbedPane centerTabbedPane;
  private JPanel genQueryPanel;
  private JTextField genQueryTextField;
  private JLabel genQueryLabel;
  private JTextArea viewTextArea;
  private JScrollPane sqlScrollPane;
  private JPanel sqlPanel;
  private JPanel southPanel;
  private JButton generateButton;
  private JPanel southgenPanel;
  private JPanel layoutPannelCenter;
  private JComboBox genPackageList;
  private JLabel genPackageLabel;
  private JPanel genPackagePanel;
  private DefaultTableModel parametersTableModel = new DefaultTableModel(new String[][] { { "loading..." } }, new String[] { "Parameter Name", "Type", "Dynamic"  });
  private QueryGenerator queryGenerator;
  private Map<String, String> parametersMap1 = new HashMap();
  private Map<String, Boolean> parametersMap2 = new HashMap();

  public QueryGeneratorPanel()
  {
    b();
  }

  private void b() {
    try {
      setLayout(new BorderLayout());
	  setPreferredSize(new Dimension(640, 480));
      setTitle("Query Generator");
      
      this.layoutPannelCenter = new JPanel();
      add(this.layoutPannelCenter, "Center");
      this.layoutPannelCenter.setLayout(new BorderLayout());

      this.centerTabbedPane = new JTabbedPane();
      this.layoutPannelCenter.add(this.centerTabbedPane, "Center");

      this.sqlPanel = new JPanel();
      this.centerTabbedPane.addTab("Sql", null, this.sqlPanel, null);
      this.sqlPanel.setLayout(new BorderLayout());

      this.sqlScrollPane = new JScrollPane();
      this.sqlPanel.add(this.sqlScrollPane, "Center");

      this.viewTextArea = new JTextArea();
      this.sqlScrollPane.setViewportView(this.viewTextArea);
//      this.viewTextArea.setText("select t.c1, t.c2\n  from table1 t\n where t.c1 = :p1\n   and ##CONDITIONS##");
      this.viewTextArea.setText("select new_tablecol from new_table \n where new_tablecol = :c1\n");

      this.parametersPanel = new JPanel();
      this.centerTabbedPane.addTab("Parameters", null, this.parametersPanel, null);
      this.parametersPanel.setLayout(new BorderLayout());
      this.parametersPanel.addComponentListener(new ComponentAdapter() {
        	public void componentShown(ComponentEvent evt)
        	  {
        		setParameters(evt);
        	  }
		});

      this.parametersJScrollPane = new JScrollPane();
      this.parametersPanel.add(this.parametersJScrollPane, "Center");

      this.parametersTable = new JTable();
      this.parametersJScrollPane.setViewportView(this.parametersTable);
      this.parametersTable.setModel(this.parametersTableModel);
      this.parametersTable.getColumnModel().getColumn(1).setCellEditor(
        new DefaultCellEditor(
        new JComboBox(new String[] { "String", "Integer", "Long", "Double", "Date", 
        "String[]", "Integer[]", "Long[]", "Double[]", "Date[]" })));
      this.parametersTable.getModel().addTableModelListener(new TableModelListener() {
    	  public void tableChanged(TableModelEvent evt)
    	  {
    		  setViewTextArea(evt);
    	  }
		});

      this.southPanel = new JPanel();
      this.layoutPannelCenter.add(this.southPanel, "South");
      this.southPanel.setLayout(new BoxLayout(this.southPanel, 1));

      this.genPackagePanel = new JPanel();
      this.southPanel.add(this.genPackagePanel);
      FlowLayout genPackageList;
      (genPackageList = new FlowLayout()).setAlignment(0);
      this.genPackagePanel.setLayout(genPackageList);

      this.genPackageLabel = new JLabel();
      this.genPackagePanel.add(this.genPackageLabel);
      this.genPackageLabel.setText("Package name:");
      this.genPackageLabel.setPreferredSize(new Dimension(99, 15));
      this.genPackageLabel.setHorizontalAlignment(11);

      this.genPackageList = new JComboBox();
      this.genPackagePanel.add(this.genPackageList);
      this.genPackageList.setModel(new DefaultComboBoxModel());
      this.genPackageList.setEditable(true);
      this.genPackageList.setPreferredSize(new Dimension(300, 20));

      this.genQueryPanel = new JPanel();
      FlowLayout c;
      (c = new FlowLayout()).setAlignment(0);
      this.southPanel.add(this.genQueryPanel);
      this.genQueryPanel.setLayout(c);

      this.genQueryLabel = new JLabel();
      this.genQueryPanel.add(this.genQueryLabel);
      this.genQueryLabel.setText("Query name:");
      this.genQueryLabel.setPreferredSize(new Dimension(99, 15));
      this.genQueryLabel.setHorizontalAlignment(11);

      this.genQueryTextField = new JTextField();
      this.genQueryPanel.add(this.genQueryTextField);
      this.genQueryTextField.setText("XxxQuery");
      this.genQueryTextField.setPreferredSize(new Dimension(300, 20));

      this.southgenPanel = new JPanel();
      FlowLayout b = new FlowLayout();
      b.setAlignment(0);
      this.southPanel.add(this.southgenPanel);
      this.southgenPanel.setLayout(b);

      this.southgenPanel.add(Box.createRigidArea(new Dimension(99, 15)));

      this.generateButton = new JButton();
      this.southgenPanel.add(this.generateButton);
      this.generateButton.setText("Generate");
      this.generateButton.setMnemonic(71);
      this.generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doGenerate(arg0);
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
    this.genQueryTextField.setEnabled(enabled);
    this.generateButton.setEnabled(enabled);
  }

  public void requestFocus() {
    super.requestFocus();
    this.viewTextArea.requestFocus();
  }

  public boolean init() {
	  new Thread() {

		public void run()
		  {
		    try
		    {
//		      QueryGeneratorPanel.B(this.a);
//		      QueryGeneratorPanel.a(this.a, new QueryGenerator());
		    	queryGenerator = new QueryGenerator();
		    	getPackageList();
//		    	setViewTextArea();
//		    	searchTable();
		    } catch (Exception a) {
		    	logger.error("Query Generator init error", a);
		    }
		  }
		}.start();
    return true;
  }


private void getPackageList() {
	Object[] projectPackageNamesArray = SpringUtil.getProjectPackageNames().toArray();
    for (int a = 0; a < projectPackageNamesArray.length; a++) {
    	projectPackageNamesArray[a] = (projectPackageNamesArray[a] + ".query");
    }
    this.genPackageList.setModel(new DefaultComboBoxModel(projectPackageNamesArray));
  }
  
//设置参数
  private void setParameters(){
    Vector parametersTableVector = this.parametersTableModel.getDataVector();
    for(int t=0; t < parametersTableVector.size(); t++){
    	Vector parameter = (Vector)parametersTableVector.get(t);
    	this.parametersMap1.put((String)parameter.get(0), (String)parameter.get(1));
    	this.parametersMap2.put((String)parameter.get(0), (Boolean)parameter.get(2));
    }

    this.parametersTableModel.setRowCount(0);
    String viewText = this.viewTextArea.getText();
    if ((viewText == null) || (viewText.trim().length() == 0)) {
      return;
    }

    viewText = SqlUtil.convertParametersToJavaStyle(viewText);
    this.viewTextArea.setText(viewText);

    String[] viewTextSplit = viewText.replace("\r", "").split("\n");
    String[] arrayString;
    int n = (arrayString = viewTextSplit).length; 
    Iterator tmpTernaryOp;
	for ( int m = 0; m < n; m++) { 
	  String arrayOfString = arrayString[m];
      boolean isMatches = arrayOfString.trim().matches("<<.*>>");
      Iterator localIterator3 = SqlUtil.getSqlParameters(arrayOfString).values().iterator();
      for (tmpTernaryOp = localIterator3; localIterator3.hasNext(); ) { 
    	  String iterator3Next = (String)localIterator3.next();
        this.parametersMap2.put(iterator3Next, Boolean.valueOf(isMatches));
      }
    }

//    TreeMap sqlParameter = SqlUtil.getSqlParameters(viewText);
	Map sqlParameter = SqlUtil.getSqlParameters(viewText);

    Iterator localIterator2 = sqlParameter.values().iterator();
    while (localIterator2.hasNext()) { 
    	String iterator2Next = (String)localIterator2.next();
      this.parametersTableModel.addRow(new Object[] { iterator2Next, "String", this.parametersMap2.get(iterator2Next) });
    }
  }

  private void doGenerate(ActionEvent evt)
  {
    if (this.queryGenerator == null)
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
    final String genQueryText = this.genQueryTextField.getText().trim();
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
			  queryGenerator.reset();
			  queryGenerator.generateQuery(genPackage, genQueryText, viewText, parametersMap1, parametersMap2);
		    } catch (Exception ex) {
		    	logger.error("Query Generator error", ex);
		      UIUtils.showError("Query Generator error", ex);
		    }
		    int a;
		    if ((a = queryGenerator.getCount()) > 0)
		    {
		    	logger.info(a + " file(s) generated. Refresh the project.");
		      UIUtils.showInformation("Info", a + " file(s) generated. \nRefresh the project.");
		    }
		    setEnabled(true);
		  }
	}.start();
  }

  private void setParameters(ComponentEvent evt) {
	  setParameters();
  }
  

  private void setViewTextArea(TableModelEvent evt)
  {
    if (evt.getColumn() != 2) {
      return;
    }
    int firstRow = evt.getFirstRow();
    TableModel tableModelSource = (TableModel)evt.getSource();
    String sourceValueAt = (String)tableModelSource.getValueAt(firstRow, 0);
    boolean hasBooleanValue = ((Boolean)tableModelSource.getValueAt(firstRow, 2)).booleanValue();
    this.parametersMap2.put(sourceValueAt, Boolean.valueOf(hasBooleanValue));

    String viewText = this.viewTextArea.getText();
    String[] viewTextSplit = viewText.replace("\r", "").split("\n");
    
    for (int i = 0; i < viewTextSplit.length; i++) {
      String text = viewTextSplit[i];
      boolean hasNextIterator = false;
      Iterator localIterator = SqlUtil.getSqlParameters(text).values().iterator(); 
      Iterator tmpTernaryOp;
	for (tmpTernaryOp = localIterator; localIterator.hasNext(); ) { 
		String localIteratorNext = (String)localIterator.next();
        if (((Boolean)this.parametersMap2.get(localIteratorNext)).booleanValue()) {
        	hasNextIterator = true;
          break;
        }
      }
      boolean isMatches = text.trim().matches("<<.*>>");
      if ((hasNextIterator) && (!isMatches)) {
        String textTrim = text.trim();
        int textIndexOf = text.indexOf(textTrim);
        String textSubstring = text.substring(0, textIndexOf);
        
        for (int a = 0; a < 2; a++) {
          if (textSubstring.endsWith(" ")) {
        	  textSubstring = textSubstring.substring(0, textSubstring.length() - 1);
          }
        }
        text = textSubstring + "<<" + textTrim + ">>";
      }
      if ((!hasNextIterator) && (isMatches)) {
        text = text.replace("<<", "  ").replace(">>", "");
      }
      viewTextSplit[i] = text;
    }
    viewText = StringUtils.join(viewTextSplit, "\r\n");
    this.viewTextArea.setText(viewText);
  }
}