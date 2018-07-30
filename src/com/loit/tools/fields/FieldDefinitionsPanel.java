package com.loit.tools.fields;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.loit.core.commom.fields.FieldDefinition;
import com.loit.core.utils.EntityUtil;
import com.loit.tools.generator.CrudGenerator;
import com.loit.tools.utils.UIUtils;
import com.loit.tools.workspace.BasePanel;

public class FieldDefinitionsPanel extends BasePanel {
	protected final Log log = LogFactory.getLog(getClass());
	private JPanel buttonPanel;
	private JPanel centerPanel;
	private JButton addButton;
	private JButton delButton;
	private JButton downButton;
	private JButton upButton;
	private JPanel mainPanel;
	private JList list;
	private JScrollPane scrollPanel;
	private FieldDefinitionEditPanel editPanel;
	private JButton saveButton;
	private JPanel fieldPanel;
	private JPanel northPanel;
	private DefaultComboBoxModel C;
	private String c;
	private File B;
	private String entityName;
	private LinkedHashMap<String, FieldDefinition> allFields;
	private int newFieldNum = 0;

	public FieldDefinitionsPanel() {
		intiPanel();
	}

	private void intiPanel() {
		try {
			setLayout(new BorderLayout());

			this.mainPanel = new JPanel();
			add(this.mainPanel, "Center");
			this.mainPanel.setLayout(new BorderLayout());

			this.northPanel = new JPanel();
			FlowLayout flo = new FlowLayout();
			flo.setAlignment(0);
			this.northPanel.setLayout(flo);
			this.mainPanel.add(this.northPanel, "North");

			this.saveButton = new JButton();
			this.northPanel.add(this.saveButton);
			this.saveButton.setText("Save");
			this.saveButton.setMnemonic(83);
			this.saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					saveButtonClick(evt);
				}
			});

			this.centerPanel = new JPanel();
			this.mainPanel.add(this.centerPanel, "Center");
			this.centerPanel.setLayout(new BorderLayout());

			this.fieldPanel = new JPanel();
			this.centerPanel.add(this.fieldPanel, "West");
			this.fieldPanel.setLayout(new BorderLayout());
			this.fieldPanel.setBorder(BorderFactory
					.createTitledBorder("Fields"));

			this.scrollPanel = new JScrollPane();
			this.fieldPanel.add(this.scrollPanel, "West");
			this.scrollPanel.setPreferredSize(new Dimension(150, 3));

			this.list = new JList();
			this.scrollPanel.setViewportView(this.list);
			this.list.setSelectionMode(0);
			this.list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					listSelection(e);
				}
			});

			this.buttonPanel = new JPanel();
			this.fieldPanel.add(this.buttonPanel, "East");
			this.buttonPanel.setLayout(null);
			this.buttonPanel.setPreferredSize(new Dimension(21, 392));

			this.addButton = new JButton();
			this.buttonPanel.add(this.addButton);
			this.addButton.setText("＋");
			this.addButton.setBorder(BorderFactory.createCompoundBorder(null,
					null));
			this.addButton.setBounds(0, 0, 21, 21);
			this.addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					addButtonClick(evt);
				}
			});

			this.delButton = new JButton();
			this.buttonPanel.add(this.delButton);
			this.delButton.setText("－");
			this.delButton.setBorder(BorderFactory.createCompoundBorder(null,
					null));
			this.delButton.setBounds(0, 21, 21, 21);
			this.delButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					delButtonClick(evt);
				}
			});

			this.upButton = new JButton();
			this.buttonPanel.add(this.upButton);
			this.upButton.setText("∧");
			this.upButton.setBorder(BorderFactory.createCompoundBorder(null,
					null));
			this.upButton.setBounds(0, 42, 21, 21);
			this.upButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					upButtonClick(evt);
				}
			});

			this.downButton = new JButton();
			this.buttonPanel.add(this.downButton);
			this.downButton.setText("∨");
			this.downButton.setBorder(BorderFactory.createCompoundBorder(null,
					null));
			this.downButton.setBounds(0, 63, 21, 21);
			this.downButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					downButtonClick(evt);
				}
			});

			this.editPanel = new FieldDefinitionEditPanel();
			this.centerPanel.add(this.editPanel, "Center");
			this.editPanel.setBorder(BorderFactory
					.createTitledBorder("Field Definition"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean init() {
		this.editPanel.init();
		return true;
	}

	public void setParameters(Object[] parameters) {
		if ((parameters.length == 2) && ((parameters[0] instanceof String))
				&& ((parameters[1] instanceof File))) {
			super.setParameters(parameters);
			this.c = ((String) parameters[0]);
			if (this.c.endsWith(".model")) {
				this.c = this.c.substring(0, this.c.length() - 6);
			}
			this.B = ((File) parameters[1]);
			String a;
			if ((a = this.B.getName()).endsWith("Fields.xml"))
				this.entityName = a.substring(0, a.length() - "Fields.xml".length());
			else
				throw new IllegalArgumentException("File must be *Fields.xml");
		} else {
			throw new IllegalArgumentException(
					"Parameters for FieldDefinitionsPanel must be of type Object[]{String, File}");
		}
	}

	public boolean refresh() {
		try {
			this.allFields = new LinkedHashMap();
			this.list.setModel(new DefaultComboBoxModel(new Object[] { "loading..." }));
			
			Document doc;
			Iterator localIterator1 = (doc = (new SAXBuilder()).build(B)).getRootElement().getChildren("bean", doc.getRootElement().getNamespace()).iterator();
			while (localIterator1.hasNext()) {
				Element e = (Element) localIterator1.next();
				if (!"fieldDefinition".equals(e.getAttributeValue("parent")))
					continue;
				String idFullName = e.getAttributeValue("id");
				if (!idFullName.startsWith(this.entityName + "."))
					continue;
				FieldDefinition field = new FieldDefinition();
				field.setBeanName(idFullName);
				String id = idFullName.substring(this.entityName.length() + 1);
				Iterator localIterator2 = e.getChildren("property",	e.getNamespace()).iterator();
				while (localIterator2.hasNext()) {
					Element element = (Element) localIterator2.next();
					if ("fieldName".equals(element.getAttributeValue("name")))
						field.setFieldName(element.getAttributeValue("value"));
					else if ("label".equals(element
							.getAttributeValue("name")))
						field.setLabel(element.getAttributeValue("value"));
					else if ("fieldType".equals(element
							.getAttributeValue("name")))
						field.setFieldType(element.getAttributeValue("value"));
					else if ("sortable".equals(element
							.getAttributeValue("name")))
						field.setSortable(Boolean.valueOf(
								element.getAttributeValue("value"))
								.booleanValue());
					else if ("nullable".equals(element
							.getAttributeValue("name")))
						field.setNullable(Boolean.valueOf(
								element.getAttributeValue("value"))
								.booleanValue());
					else if ("length".equals(element.getAttributeValue("name")))
						field.setLength(Integer.valueOf(
								element.getAttributeValue("value")).intValue());
					else if ("precision".equals(element
							.getAttributeValue("name")))
						field.setPrecision(Integer.valueOf(
								element.getAttributeValue("value")).intValue());
					else if ("scale".equals(element.getAttributeValue("name")))
						field.setScale(Integer.valueOf(
								element.getAttributeValue("value")).intValue());
					else if ("width".equals(element.getAttributeValue("name"))) {
						field.setWidth(Integer.valueOf(
								element.getAttributeValue("value")).intValue());
					}
				}
				this.allFields.put(id, field);
			}

			this.C = new DefaultComboBoxModel(this.allFields.keySet().toArray());
			this.list.setModel(this.C);
			this.list.setSelectedIndex(0);
			this.list.ensureIndexIsVisible(0);
			this.newFieldNum = 0;
		} catch (Exception f) {
			this.log.error("FieldDefinitionsPanel error", f);
			UIUtils.showError("FieldDefinitionsPanel error", f);
		}
		return true;
	}

	private void check() {
		FieldDefinition field = this.editPanel.getFieldDefinition();
		if (field != null) {
			this.editPanel.endEdit();
			String oldField = this.editPanel.getOldFieldName();
			String newField = this.editPanel.getNewFieldName();
			this.editPanel.reset();

			if (!oldField.equals(newField)) {
				this.allFields.remove(oldField);
				this.allFields.put(newField, field);

				int b = this.C.getIndexOf(oldField);
				int a = this.list.getSelectedIndex();
				this.C.removeElementAt(b);
				this.C.insertElementAt(newField, b);
				if (b == a) {
					this.list.setSelectedIndex(b);
					this.list.ensureIndexIsVisible(b);
				}
			}
		}
	}

	/**
	 * field 点击事件
	 * 
	 * @param evt
	 */
	private void listSelection(ListSelectionEvent evt) {
		if (evt.getValueIsAdjusting()) {
			check();
		} else {
			String selectedValue = (String) this.list.getSelectedValue();
			if (selectedValue != null) {
				this.editPanel.edit((FieldDefinition) this.allFields.get(selectedValue));
			}
		}
	}

	/**
	 * saveButton click事件
	 * 
	 * @param evt
	 */
	private void saveButtonClick(ActionEvent evt) {
		check();
		try {
			HashMap hm = new HashMap();
			hm.put("className", this.entityName);
			hm.put("tableName", this.entityName);
			String id = EntityUtil.getIdFieldName(EntityUtil.getEntityClass(this.entityName));
			ArrayList al = new ArrayList();
			for (int i = 0; i < this.C.getSize(); i++) {
				String fieldName = (String) this.C.getElementAt(i);
				FieldDefinition field = (FieldDefinition) this.allFields.get(fieldName);
				Map map = new HashMap();
				map.put("fieldName", fieldName);
				map.put("isPK", Boolean.valueOf((id != null) && (id.equals(fieldName))));
				map.put("label", field.getLabel());
				map.put("uiFieldType", field.getFieldType());
				map.put("sortable", field.isSortable() ? "true" : "false");
				map.put("nullable", field.isNullable() ? "true" : "false");
				map.put("length", Integer.toString(field.getLength()));
				map.put("precision", Integer.toString(field.getPrecision()));
				map.put("scale", Integer.toString(field.getScale()));
				map.put("width", Integer.toString(field.getWidth()));
				al.add(map);
			}
			hm.put("columns", al);

			CrudGenerator cg = new CrudGenerator();
			cg.setDataModel(hm);
			cg.setPromptOverwrite(false);
			cg.generateFieldDef(this.c, this.entityName);
			UIUtils.showInformation("Info", "Saved successfully.");

			// tmpTernaryOp = (saveButton = 0);
		} catch (Exception h) {
			this.log.error("FieldDefinitionsPanel error", h);
			UIUtils.showError("FieldDefinitionsPanel error", h);
		}
	}

	/**
	 * 添加点击事件
	 * 
	 * @param evt
	 */
	private void addButtonClick(ActionEvent evt) {
		check();

		String newFieldName = "new_" + this.newFieldNum++;
		while (this.allFields.containsKey(newFieldName)) {
			newFieldName = "new_" + this.newFieldNum++;
		}
		FieldDefinition newField = new FieldDefinition();
		newField.setBeanName(this.entityName + "." + newFieldName);
		newField.setFieldName(newFieldName);
		newField.setLabel(newFieldName);
		newField.setNullable(true);
		newField.setSortable(true);
		newField.setFieldType("string");
		newField.setLength(20);
		newField.setWidth(20);

		this.allFields.put(newFieldName, newField);
		int a = this.list.getSelectedIndex();
		this.C.insertElementAt(newFieldName, a + 1);
		this.list.setSelectedIndex(a + 1);

//		this.C.addElement(newFieldName);
//		this.list.setSelectedIndex(this.C.getSize() - 1);
		this.list.ensureIndexIsVisible(this.list.getSelectedIndex());
	}

	/**
	 * 删除点击事件
	 * 
	 * @param evt
	 */
	private void delButtonClick(ActionEvent evt) {
		check();
		int a;
		if ((a = this.list.getSelectedIndex()) == -1) {
			return;
		}
		this.C.removeElementAt(a);
		if (a == this.C.getSize()) {
			a--;
		}
		this.list.setSelectedIndex(a);
		this.list.ensureIndexIsVisible(a);
	}

	/**
	 * 上移点击事件
	 * 
	 * @param evt
	 */
	private void upButtonClick(ActionEvent evt) {
		check();
		int b;
		if (((b = this.list.getSelectedIndex()) == -1) || (b == 0)) {
			return;
		}
		Object a = this.C.getElementAt(b);
		this.C.removeElementAt(b);
		this.C.insertElementAt(a, b - 1);
		this.list.setSelectedIndex(b - 1);
		this.list.ensureIndexIsVisible(b - 1);
	}

	/**
	 * 下移点击事件
	 * 
	 * @param evt
	 */
	private void downButtonClick(ActionEvent evt) {
		check();
		int b;
		if (((b = this.list.getSelectedIndex()) == -1)
				|| (b == this.C.getSize() - 1)) {
			return;
		}
		Object a = this.C.getElementAt(b);
		this.C.removeElementAt(b);
		this.C.insertElementAt(a, b + 1);
		this.list.setSelectedIndex(b + 1);
		this.list.ensureIndexIsVisible(b + 1);
	}
}