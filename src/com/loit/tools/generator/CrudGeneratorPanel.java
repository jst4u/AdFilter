package com.loit.tools.generator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.loit.core.utils.SpringUtil;
import com.loit.tools.utils.ConnectionUtils;
import com.loit.tools.utils.UIUtils;
import com.loit.tools.workspace.BasePanel;

public class CrudGeneratorPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 265280396368300462L;
	protected final Log logger = LogFactory.getLog(getClass());
	private JPanel searchPanel;
	private JCheckBox genModelCheck;
	private JPanel layoutGenSouth;
	private JTable searchResultTable;
	private JButton searchButton;
	private JTextField searchInputText;
	private JLabel searchLabel;
	private JPanel serachPanel;
	private JButton genButton;
	private JScrollPane searchResultScroll;
	private JCheckBox genFieldCheck;
	private JCheckBox genManagerCheck;
	private JPanel genButtonPanel;
	private JPanel layoutPannelCenter;// e
	private JLabel genTypeLabel;
	private JComboBox genPackageList;
	private JLabel genPackageLabel;
	private JCheckBox genJspCheck;
	private JPanel genCheckPanel;
	private JPanel genPanel;
	private DefaultTableModel searchResultTableColumn = new DefaultTableModel(new String[][] { { "loading..." } }, new String[] { "Table Name", "Table Type", "Remarks" });
	private CrudGenerator crudGenerator;

	public CrudGeneratorPanel() {
		super();
		b();
	}

	private void b() {
		try {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(640, 480));
			setTitle("CRUD Generator");

			this.layoutPannelCenter = new JPanel();
			BorderLayout h = new BorderLayout();
			add(this.layoutPannelCenter, "Center");
			this.layoutPannelCenter.setLayout(h);

			this.searchPanel = new JPanel();
			this.layoutPannelCenter.add(this.searchPanel, "Center");
			BorderLayout g = new BorderLayout();
			this.searchPanel.setLayout(g);
			this.searchPanel.setBorder(BorderFactory.createTitledBorder("Tables"));

			this.searchResultScroll = new JScrollPane();
			this.searchPanel.add(this.searchResultScroll);
			this.searchResultScroll.setEnabled(false);

			this.searchResultTable = new JTable();
			this.searchResultScroll.setViewportView(this.searchResultTable);
			this.searchResultTable.setModel(this.searchResultTableColumn);

			this.serachPanel = new JPanel();
			this.searchPanel.add(this.serachPanel, "North");
			FlowLayout d;
			(d = new FlowLayout()).setAlignment(0);
			this.serachPanel.setLayout(d);

			this.searchLabel = new JLabel();
			this.serachPanel.add(this.searchLabel);
			this.searchLabel.setText("Find:");

			this.searchInputText = new JTextField();
			this.serachPanel.add(this.searchInputText);
			this.searchInputText.setPreferredSize(new Dimension(300, 20));
			this.searchInputText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// ((CrudGeneratorPanel)(arg0.getSource())).b(arg0);
				}
			});

			this.searchButton = new JButton();
			this.serachPanel.add(this.searchButton);
			this.searchButton.setText(">>");
			this.searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						searchTable();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e);
						UIUtils.showError("CRUD Generator init error", e);
					}
					// ((CrudGeneratorPanel)(arg0.getSource())).A(arg0);
				}
			});

			this.layoutGenSouth = new JPanel();
			this.layoutPannelCenter.add(this.layoutGenSouth, "South");
			BoxLayout f = new BoxLayout(this.layoutGenSouth, 1);
			this.layoutGenSouth.setLayout(f);

			this.genPanel = new JPanel();
			this.layoutGenSouth.add(this.genPanel);
			FlowLayout e;
			(e = new FlowLayout()).setAlignment(0);
			this.genPanel.setLayout(e);

			this.genPackageLabel = new JLabel();
			this.genPanel.add(this.genPackageLabel);
			this.genPackageLabel.setText("Package name:");
			this.genPackageLabel.setPreferredSize(new Dimension(99, 15));
			this.genPackageLabel.setHorizontalAlignment(11);

			ComboBoxModel a = new DefaultComboBoxModel();
			this.genPackageList = new JComboBox();
			this.genPanel.add(this.genPackageList);
			this.genPackageList.setModel(a);
			this.genPackageList.setEditable(true);
			this.genPackageList.setPreferredSize(new Dimension(300, 20));

			this.genCheckPanel = new JPanel();
			this.layoutGenSouth.add(this.genCheckPanel);
			this.genCheckPanel.setLayout(new FlowLayout(0));

			this.genTypeLabel = new JLabel();
			this.genCheckPanel.add(this.genTypeLabel);
			this.genTypeLabel.setText("Generate types:");
			this.genTypeLabel.setPreferredSize(new Dimension(99, 15));
			this.genTypeLabel.setHorizontalAlignment(11);

			this.genModelCheck = new JCheckBox();
			this.genCheckPanel.add(this.genModelCheck);
			this.genModelCheck.setText("Model");
			this.genModelCheck.setSelected(true);
			this.genModelCheck.setMnemonic(77);

			this.genManagerCheck = new JCheckBox();
			this.genCheckPanel.add(this.genManagerCheck);
			this.genManagerCheck.setText("Manager");
			this.genManagerCheck.setMnemonic(78);

			this.genJspCheck = new JCheckBox();
			this.genCheckPanel.add(this.genJspCheck);
			this.genJspCheck.setText("Jsp");
			this.genJspCheck.setMnemonic(74);
			this.genJspCheck.setEnabled(true);

			this.genFieldCheck = new JCheckBox();
			this.genCheckPanel.add(this.genFieldCheck);
			this.genFieldCheck.setText("FieldDef");
			this.genFieldCheck.setMnemonic(70);

			this.genButtonPanel = new JPanel();
			this.layoutGenSouth.add(this.genButtonPanel);
			this.genButtonPanel.setLayout(new FlowLayout(0));

			this.genButtonPanel.add(Box.createRigidArea(new Dimension(99, 15)));

			this.genButton = new JButton();
			this.genButtonPanel.add(this.genButton);
			this.genButton.setText("Generate");
			this.genButton.setMnemonic(71);
			this.genButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					doGenerate(arg0);
				}
			});
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		this.searchResultTable.setEnabled(enabled);
		this.searchInputText.setEnabled(enabled);
		this.serachPanel.setEnabled(enabled);
		this.genPackageList.setEnabled(enabled);
		this.genModelCheck.setEnabled(enabled);
		this.genManagerCheck.setEnabled(enabled);
		this.genFieldCheck.setEnabled(enabled);

		this.genButton.setEnabled(enabled);
	}

	public void requestFocus() {
		super.requestFocus();
		this.searchInputText.requestFocus();
	}

	public boolean init() {

		new Thread() {
			public void run() {
				try {

					// CrudGeneratorPanel(this.a);
					// CrudGeneratorPanel.d(this.a);
					// CrudGeneratorPanel.a(this.a, new
					// CrudGenerator());
					crudGenerator = new CrudGenerator();
					searchTable();
					getPackageList();
				} catch (Exception a) {
					logger.error("CRUD Generator init error", a);
				}
			}

		}.start();
		// new m(this).start();

		return true;
	}

	/**
	 * 获取package列表
	 */
	private void getPackageList() {
		Object[] projectPackageNamesArray = SpringUtil.getProjectPackageNames().toArray();
		for (int a = 0; a < projectPackageNamesArray.length; a++) {
			projectPackageNamesArray[a] = (projectPackageNamesArray[a]);
		}
		this.genPackageList.setModel(new DefaultComboBoxModel(projectPackageNamesArray));
	}

	private void searchTable() throws Exception {

		DatabaseMetaData metaData = ConnectionUtils.getConnection().getMetaData();
		this.searchResultTableColumn.setRowCount(0);
		ResultSet resultSet = null;
		try {
			if (metaData.getDriverName().contains("SQL Server")) {
				resultSet = metaData.getTables(null, "dbo", null, null);
				while (resultSet.next()) {
					this.searchResultTableColumn.addRow(new String[] { "dbo." + resultSet.getString("TABLE_NAME"), resultSet.getString("TABLE_TYPE"), resultSet.getString("REMARKS") });
				}
			} else {
				String userName = metaData.getUserName().toUpperCase();
				resultSet = metaData.getTables(null, userName, null, null);
				while (resultSet.next()) {
					this.searchResultTableColumn.addRow(new String[] { resultSet.getString("TABLE_NAME"), resultSet.getString("TABLE_TYPE"), resultSet.getString("REMARKS") });
				}
			}

		} finally {
			try {
				resultSet.close();
			} catch (Exception localException) {
			}
		}
	}

	// private void a() {
	// this.genPachageList.setModel(new
	// DefaultComboBoxModel(SpringUtil.getProjectPackageNames().toArray()));
	// }

	// private void b(ActionEvent evt) {
	// // searchTable(evt);
	// }

	// private void searchTable(ActionEvent evt) {
	// // String e;
	// if ( this.searchInputText.getText().trim().toUpperCase().length() ==
	// 0) {
	// return;
	// }
	// int d = this.searchResultTable.getSelectedRow();
	// int c;
	//
	// for(int i=0;i<this.searchResultTable.getRowCount();i++){
	//
	// }
	// for (tmpTernaryOp = (c = 0); c <
	// this.searchResultTable.getRowCount(); c++) {
	// d++;
	// if (d == this.searchResultTable.getRowCount())
	// d = 0;
	// int b;
	// for (tmpTernaryOp = (b = 0); b <
	// this.searchResultTable.getColumnCount(); b++) {
	// Object a;
	// if ((a = this.searchResultTable.getValueAt(d, b)) == null) {
	// continue;
	// }
	// if (a.toString().toUpperCase().indexOf(e) >= 0) {
	// this.searchResultTable.getSelectionModel().setSelectionInterval(d,
	// d);
	// this.searchResultTable.scrollRectToVisible(this.searchResultTable.getCellRect(d,
	// 0, true));
	// return;
	// }
	// }
	// }
	// }

	private void doGenerate(ActionEvent evt) {
		if (this.crudGenerator == null)
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
		final int[] selectCount = this.searchResultTable.getSelectedRows();// a
		if (selectCount == null || (selectCount.length == 0)) {
			UIUtils.showInformation("info", "请选择需要生成的Table对象！\n多选按住Ctrl键。");
			return;
		}
		setEnabled(false);
		// new l(this, a, b).start();//selcetCount a genPackage b
		new Thread() {
			public void run() {
				try {
					crudGenerator.reset();
					for (int i = 0; i < searchResultTable.getSelectedRows().length; i++) {
						String tableName = searchResultTable.getValueAt(selectCount[i], 0).toString();
						if (genModelCheck.isSelected()) {
							crudGenerator.generateModel(genPackage, tableName);
						}
						if (genManagerCheck.isSelected()) {
							crudGenerator.generateManager(genPackage, tableName);
						}
						if (genFieldCheck.isSelected()) {
							crudGenerator.generateFieldDef(genPackage, tableName);
						}
						if (genJspCheck.isSelected()) {
							crudGenerator.generateJsp(genPackage, tableName);
						}
					}

				} catch (Exception b) {
					logger.error("CRUD Generator error", b);
					UIUtils.showError("CRUD Generator error", b);
				}
				int genCounts = crudGenerator.getCount();
				if (genCounts > 0) {
					logger.info(genCounts + " file(s) generated. Refresh the project.");
					UIUtils.showInformation("Info", genCounts + " file(s) generated. \nRefresh the project.");
				}
				setEnabled(true);
			}
		}.start();

	}

}
