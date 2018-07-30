package com.loit.tools.fields;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.loit.core.commom.fields.FieldDefinition;
import com.loit.tools.workspace.BasePanel;

public class FieldDefinitionEditPanel extends BasePanel {
	private JLabel fieldTypeLabel;
	private JTextField entityTextField;
	private JTextField lengthTextField;
	private JTextField widthTextField;
	private JLabel codeTypeLabel;
	private JComboBox codeTypeComboBox;
	private JComboBox fieldComboBox;
	private JCheckBox sortableCheckBox;
	private JCheckBox nullabelCheckBox;
	private JTextField scaleTextField;
	private JTextField precisionTextField;
	private JLabel sortableLabel;
	private JLabel precisionLabel;
	private JLabel widthLabel;
	private JLabel scaleLabel;
	private JLabel lengthLabel;
	private JLabel label;
	private JTextField labelTextField;
	private JTextField fieldTextField;
	private JLabel entityLabel;
	private JLabel nullableLabel;
	private JLabel fieldLabel;
	private FieldDefinition fieldDefinition;
	private String oldFieldName;
	private String newFieldName;

	public FieldDefinitionEditPanel() {
		intiPanel();
	}

	private void intiPanel() {
		try {
			setPreferredSize(new Dimension(441, 420));
			setLayout(null);

			this.entityLabel = new JLabel();
			add(this.entityLabel);
			this.entityLabel.setText("Entity:");
			this.entityLabel.setHorizontalAlignment(11);
			this.entityLabel.setBounds(7, 35, 77, 28);

			this.fieldLabel = new JLabel();
			add(this.fieldLabel);
			this.fieldLabel.setText("Field:");
			this.fieldLabel.setHorizontalAlignment(11);
			this.fieldLabel.setBounds(7, 70, 77, 28);

			this.label = new JLabel();
			add(this.label);
			this.label.setText("Label:");
			this.label.setHorizontalAlignment(11);
			this.label.setBounds(7, 105, 77, 28);

			this.nullableLabel = new JLabel();
			add(this.nullableLabel);
			this.nullableLabel.setText("Nullable:");
			this.nullableLabel.setHorizontalAlignment(11);
			this.nullableLabel.setBounds(7, 140, 77, 28);

			this.sortableLabel = new JLabel();
			add(this.sortableLabel);
			this.sortableLabel.setText("Sortable:");
			this.sortableLabel.setHorizontalAlignment(11);
			this.sortableLabel.setBounds(112, 140, 77, 28);

			this.fieldTypeLabel = new JLabel();
			add(this.fieldTypeLabel);
			this.fieldTypeLabel.setText("Field Type:");
			this.fieldTypeLabel.setBounds(7, 175, 77, 28);
			this.fieldTypeLabel.setHorizontalAlignment(11);

			this.codeTypeLabel = new JLabel();
			add(this.codeTypeLabel);
			this.codeTypeLabel.setText("Code Type:");
			this.codeTypeLabel.setHorizontalAlignment(11);
			this.codeTypeLabel.setBounds(7, 210, 77, 28);

			this.lengthLabel = new JLabel();
			add(this.lengthLabel);
			this.lengthLabel.setText("Length:");
			this.lengthLabel.setHorizontalAlignment(11);
			this.lengthLabel.setBounds(7, 245, 77, 28);

			this.precisionLabel = new JLabel();
			add(this.precisionLabel);
			this.precisionLabel.setText("Precision:");
			this.precisionLabel.setHorizontalAlignment(11);
			this.precisionLabel.setBounds(7, 280, 77, 28);

			this.scaleLabel = new JLabel();
			add(this.scaleLabel);
			this.scaleLabel.setText("Scale:");
			this.scaleLabel.setHorizontalAlignment(11);
			this.scaleLabel.setBounds(175, 280, 35, 28);

			this.widthLabel = new JLabel();
			add(this.widthLabel);
			this.widthLabel.setText("Width:");
			this.widthLabel.setHorizontalAlignment(11);
			this.widthLabel.setBounds(7, 315, 77, 28);

			this.entityTextField = new JTextField();
			add(this.entityTextField);
			this.entityTextField.setBounds(91, 35, 210, 28);
			this.entityTextField.setEnabled(false);

			this.fieldTextField = new JTextField();
			add(this.fieldTextField);
			this.fieldTextField.setBounds(91, 70, 210, 28);

			this.labelTextField = new JTextField();
			add(this.labelTextField);
			this.labelTextField.setBounds(91, 105, 210, 28);

			this.lengthTextField = new JTextField();
			add(this.lengthTextField);
			this.lengthTextField.setBounds(91, 245, 210, 28);

			this.precisionTextField = new JTextField();
			add(this.precisionTextField);
			this.precisionTextField.setBounds(91, 280, 84, 28);

			this.scaleTextField = new JTextField();
			add(this.scaleTextField);
			this.scaleTextField.setBounds(217, 280, 84, 28);

			this.widthTextField = new JTextField();
			add(this.widthTextField);
			this.widthTextField.setBounds(91, 315, 210, 28);

			this.nullabelCheckBox = new JCheckBox();
			add(this.nullabelCheckBox);
			this.nullabelCheckBox.setBounds(91, 140, 28, 28);

			this.sortableCheckBox = new JCheckBox();
			add(this.sortableCheckBox);
			this.sortableCheckBox.setBounds(196, 140, 28, 28);

			this.fieldComboBox = new JComboBox();
			add(this.fieldComboBox);
			this.fieldComboBox.setBounds(91, 175, 210, 28);
			this.fieldComboBox.setEditable(true);
			this.fieldComboBox.setMaximumRowCount(10);
			this.fieldComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					a(evt);
				}
			});

			this.codeTypeComboBox = new JComboBox();
			add(this.codeTypeComboBox);
			this.codeTypeComboBox.setBounds(91, 210, 210, 28);
			this.codeTypeComboBox.setEditable(true);
			this.codeTypeComboBox.setEnabled(false);
			this.codeTypeComboBox.setMaximumRowCount(10);
		} catch (Exception a) {
			a.printStackTrace();
		}
	}

	public boolean init() {
		new Thread() {
			public void run() {
				ComboBoxModel comboModel = new DefaultComboBoxModel(new String[] {
						"string", "text", "bytes", "int", "double", "date",
						"datetime", "time", "month", "selectCode." });
				fieldComboBox.setModel(comboModel);

//				TreeSet c = new TreeSet();
//				for (Iterator localIterator = ContextUtil.getBeansOfType(SelectCodeDefinition.class).keySet()
//						.iterator(); localIterator.hasNext();) {
//					String b = (b = (String) localIterator.next())
//							.substring("selectCode.".length());
//					c.add(b);
//				}
//				c.addAll(((SelectCodeManager) ContextUtil.getBeanOfType(SelectCodeManager.class))
//						.getSystemCodeTypes());
//				ComboBoxModel a = new DefaultComboBoxModel(c.toArray());
//				a(this.a).setModel(a);
//				a(this.a).setSelectedIndex(-1);
			}
		}.start();

		return true;
	}

	public void reset() {
		this.fieldDefinition = null;
		this.oldFieldName = null;
		this.newFieldName = null;
	}

	public void edit(FieldDefinition fieldDefinition) {
		reset();
		String beanName = fieldDefinition.getBeanName();
		String entityName = beanName.substring(0, beanName.indexOf("."));
		String fieldName = fieldDefinition.getFieldName();
		this.entityTextField.setText(entityName);
		this.fieldTextField.setText(fieldName);
		this.labelTextField.setText(fieldDefinition.getLabel());
		this.nullabelCheckBox.setSelected(fieldDefinition.isNullable());
		this.sortableCheckBox.setSelected(fieldDefinition.isSortable());
		String a;
		if (((a = fieldDefinition.getFieldType()) != null)
				&& (a.startsWith("selectCode."))) {
			this.fieldComboBox.setSelectedItem("selectCode.");
			this.codeTypeComboBox.setSelectedItem(a.substring("selectCode.".length()));
		} else {
			this.fieldComboBox.setSelectedItem(a);
		}
		this.lengthTextField.setText(Integer.toString(fieldDefinition.getLength()));
		this.precisionTextField.setText(Integer.toString(fieldDefinition.getPrecision()));
		this.scaleTextField.setText(Integer.toString(fieldDefinition.getScale()));
		this.widthTextField.setText(Integer.toString(fieldDefinition.getWidth()));

		this.fieldTextField.requestFocus();

		this.fieldDefinition = fieldDefinition;
		this.oldFieldName = fieldName;
	}

	public void endEdit() {
		if (this.fieldDefinition == null) {
			return;
		}

		this.newFieldName = this.fieldTextField.getText();

		this.fieldDefinition.setBeanName(this.entityTextField.getText() + "." + this.fieldTextField.getText());
		this.fieldDefinition.setFieldName(this.fieldTextField.getText());
		this.fieldDefinition.setLabel(this.labelTextField.getText());
		this.fieldDefinition.setNullable(this.nullabelCheckBox.isSelected());
		this.fieldDefinition.setSortable(this.sortableCheckBox.isSelected());

		String itemValue = (String) this.fieldComboBox.getSelectedItem();
		if (itemValue == null) {
			itemValue = "";
		}
		if ("selectCode.".equals(itemValue))
			this.fieldDefinition.setFieldType(itemValue + this.codeTypeComboBox.getSelectedItem());
		else {
			this.fieldDefinition.setFieldType(itemValue);
		}
		if ("string".equals(itemValue)) {
			this.fieldDefinition.setLength(Integer.parseInt(this.lengthTextField.getText()));
		} else if ("int".equals(itemValue)) {
			this.fieldDefinition.setPrecision(Integer.parseInt(this.precisionTextField.getText()));
		} else if ("double".equals(itemValue)) {
			this.fieldDefinition.setPrecision(Integer.parseInt(this.precisionTextField.getText()));
			this.fieldDefinition.setScale(Integer.parseInt(this.scaleTextField.getText()));
		}
		this.fieldDefinition.setWidth(Integer.parseInt(this.widthTextField.getText()));
	}

	private void a(ActionEvent evt) {
		String itemValue = (String) this.fieldComboBox.getSelectedItem();
		if ("string".equals(itemValue)) {
			this.codeTypeComboBox.setEnabled(false);
			this.codeTypeComboBox.setSelectedIndex(-1);
			this.lengthTextField.setEditable(true);
			this.precisionTextField.setEditable(false);
			this.scaleTextField.setEditable(false);
		} else if ("int".equals(itemValue)) {
			this.codeTypeComboBox.setEnabled(false);
			this.codeTypeComboBox.setSelectedIndex(-1);
			this.lengthTextField.setEditable(false);
			this.precisionTextField.setEditable(true);
			this.scaleTextField.setEditable(false);
		} else if ("double".equals(itemValue)) {
			this.codeTypeComboBox.setEnabled(false);
			this.codeTypeComboBox.setSelectedIndex(-1);
			this.lengthTextField.setEditable(false);
			this.precisionTextField.setEditable(true);
			this.scaleTextField.setEditable(true);
		} else if (("text".equals(itemValue)) || ("bytes".equals(itemValue))
				|| ("date".equals(itemValue)) || ("datetime".equals(itemValue))
				|| ("time".equals(itemValue)) || ("month".equals(itemValue))) {
			this.codeTypeComboBox.setEnabled(false);
			this.codeTypeComboBox.setSelectedIndex(-1);
			this.lengthTextField.setEditable(false);
			this.precisionTextField.setEditable(false);
			this.scaleTextField.setEditable(false);
		} else if ("selectCode.".equals(itemValue)) {
			this.codeTypeComboBox.setEnabled(true);
			if (this.codeTypeComboBox.getModel().getSize() > 0)
				this.codeTypeComboBox.setSelectedIndex(0);
			else {
				this.codeTypeComboBox.setSelectedIndex(-1);
			}
			this.lengthTextField.setEditable(false);
			this.precisionTextField.setEditable(false);
			this.scaleTextField.setEditable(false);
		} else {
			this.codeTypeComboBox.setEnabled(false);
			this.codeTypeComboBox.setSelectedIndex(-1);
			this.lengthTextField.setEditable(false);
			this.precisionTextField.setEditable(false);
			this.scaleTextField.setEditable(false);
		}
	}

	public FieldDefinition getFieldDefinition() {
		return this.fieldDefinition;
	}

	public String getOldFieldName() {
		return this.oldFieldName;
	}

	public String getNewFieldName() {
		return this.newFieldName;
	}
}