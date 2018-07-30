package com.loit.tools.testclient;

import com.loit.core.commom.SysConfig;
import com.loit.core.security.AcegiDefaultUserDetailsService;
import com.loit.core.security.SessionContext;
import com.loit.core.spring.CommonManager;
import com.loit.core.spring.SpringContext;
import com.loit.core.utils.JSONDataUtil;
import com.loit.core.web.file.FileToDownload;
import com.loit.tools.generator.CrudGenerator;
import com.loit.tools.utils.UIUtils;
import com.loit.tools.workspace.BasePanel;
import com.mchange.v2.resourcepool.ResourcePool.Manager;
import org.apache.commons.io.IOUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import org.apache.commons.lang.exception.ExceptionUtils;

public class TestClientPanel extends BasePanel {

	protected final Log log = LogFactory.getLog(getClass());
	private JLabel userLabel;// k
	private JTextField userInput;// J
	private JLabel localeLabel;// j
	private JComboBox localeItems;// I
	private JButton loginButton;// i
	private JPanel mainPanel;// H
	private JTextArea resultInput;// h
	private JTextArea paramInput;// G
	private JScrollPane resultScroll;// g
	private JScrollPane paramScroll;// F
	private JPanel resultPanel;// f
	private JPanel paramPanel;// E
	private JTabbedPane workTabs;// e
	private JPanel workPanel;// D
	private JPanel controlPanel;// d
	private JButton invokeButton;// C
	private JButton formatButton;// c
	private Class<? extends CommonManager> managerClass;// B
	private Method method;// b
	private String[] args;// A;
	private File file = new File(SysConfig.USER_HOME_DIR + "/." + SysConfig.PROJECT_NAME + "/ToolsLoginUser");// a

	public TestClientPanel() {
		b();
	}

	private void b() {
		try {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(517, 223));

			this.mainPanel = new JPanel();
			add(this.mainPanel, "Center");
			this.mainPanel.setLayout(new BorderLayout());

			this.controlPanel = new JPanel();
			FlowLayout d;
			(d = new FlowLayout()).setAlignment(0);
			this.controlPanel.setLayout(d);
			this.mainPanel.add(this.controlPanel, "North");

			this.invokeButton = new JButton();
			this.controlPanel.add(this.invokeButton);
			this.invokeButton.setText("Invoke");
			this.invokeButton.setMnemonic(73);
			this.invokeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent evt) {
					invokeMethod(evt);
				}

			});

			this.formatButton = new JButton();
			this.controlPanel.add(this.formatButton);
			this.formatButton.setText("Format");
			this.formatButton.setMnemonic(70);
			this.formatButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent evt) {
					format(evt);
				}

			});

			this.userLabel = new JLabel("User:");
			this.controlPanel.add(this.userLabel);

			this.userInput = new JTextField();
			this.controlPanel.add(this.userInput);
			this.userInput.setText("admin");
			this.userInput.setColumns(10);

			this.loginButton = new JButton();
			this.controlPanel.add(this.loginButton);
			this.loginButton.setText("Login");
			this.loginButton.setMnemonic(76);
			this.loginButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent evt) {
					login(evt);
				}

			});

			this.localeLabel = new JLabel();
			this.controlPanel.add(this.localeLabel);
			this.localeLabel.setText("Locale:");

			this.localeItems = new JComboBox();
			this.controlPanel.add(this.localeItems);
			this.localeItems.setPreferredSize(new Dimension(100, 20));

			this.workPanel = new JPanel();
			this.workPanel.setLayout(new BorderLayout());
			this.mainPanel.add(this.workPanel, "Center");

			this.workTabs = new JTabbedPane();
			this.workPanel.add(this.workTabs, "Center");

			this.paramPanel = new JPanel();
			this.workTabs.addTab("Parameters", null, this.paramPanel, null);
			this.paramPanel.setLayout(new BorderLayout());

			this.paramScroll = new JScrollPane();
			this.paramPanel.add(this.paramScroll, "Center");

			this.paramInput = new JTextArea();
			this.paramInput.setTabSize(2);
			this.paramScroll.setViewportView(this.paramInput);

			this.resultPanel = new JPanel();
			BorderLayout a = new BorderLayout();
			this.workTabs.addTab("Result", null, this.resultPanel, null);
			this.resultPanel.setLayout(a);

			this.resultScroll = new JScrollPane();
			this.resultPanel.add(this.resultScroll, "Center");

			this.resultInput = new JTextArea();
			this.resultInput.setTabSize(2);
			this.resultScroll.setViewportView(this.resultInput);
		} catch (Exception f) {
			f.printStackTrace();
		}
	}

	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		this.invokeButton.setEnabled(enabled);
		if (enabled)
			this.invokeButton.requestFocus();
		else
			this.paramInput.requestFocus();
	}

	public void requestFocus() {
		super.requestFocus();
		this.invokeButton.requestFocus();
	}

	public void setParameters(Object[] parameters) {
		if ((parameters.length == 3) && ((parameters[0] instanceof Class)) && ((parameters[1] instanceof Method))
				&& ((parameters[2] instanceof String[]))) {
			super.setParameters(parameters);
			this.managerClass = ((Class) parameters[0]);
			this.method = ((Method) parameters[1]);
			this.args = ((String[]) parameters[2]);
		} else {
			throw new IllegalArgumentException("Parameters for TestClientPanel must be of type Object[]{Class<?>, Method, String[]}");
		}
	}

	public boolean init() {
		new Thread() {
			public void run() {
				try {

					if (file.exists()) {
						userInput.setText(FileUtils.readFileToString(file));
					}

					Map d = new TreeMap();
					Locale[] arrayOfLocale = Locale.getAvailableLocales();
					for (int i = 0; i < arrayOfLocale.length; i++) {
						Locale b = arrayOfLocale[i];
						d.put(b.toString(), b);
					}
					ComboBoxModel a = new DefaultComboBoxModel(d.values().toArray());
					localeItems.setModel(a);
					localeItems.setSelectedItem(Locale.getDefault());

				} catch (Exception c) {
					log.error("Test client init error", c);
					UIUtils.showError("Test client init error", c);
				}
			}

		}.start();
		return true;
	}

	public boolean refresh() {
		try {
			String b = JSONDataUtil.buildParametersJSONString(this.managerClass, this.method, this.args);

			this.paramInput.setText(b);
			this.paramInput.setCaretPosition(0);
			this.resultInput.setText(null);
			this.resultInput.setCaretPosition(0);
			this.workTabs.setSelectedIndex(0);
		} catch (Exception a) {
			this.log.error("Build request xml error", a);
			UIUtils.showError("Build request xml error", a);
		}
		return true;
	}

	private void login() throws Exception {
		String username = this.userInput.getText();
		UserDetails loginUser = ((UserDetailsService) SpringContext.getBeanOfType(AcegiDefaultUserDetailsService.class)).loadUserByUsername(username);
		Authentication auth = new PreAuthenticatedAuthenticationToken(loginUser, null);

		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		this.log.info("Login as user " + username);

		FileUtils.writeStringToFile(file, username);
	}

	private void format() throws Exception {
		try {
			this.paramInput.setText(JSONDataUtil.format(this.paramInput.getText()));
			this.paramInput.setCaretPosition(0);
		} catch (JSONException c) {
			try {
				String b;
				int a = Integer.valueOf((b = c.getMessage()).substring(b.lastIndexOf(" ") + 1)).intValue();
				this.paramInput.select(a - 1, a);
				this.paramInput.requestFocus();
			} catch (Exception localException) {
			}
			throw c;
		}
	}

	private void invokeMethod(ActionEvent evt) {
		setEnabled(false);
		new Thread() {
			public void run() {
				try {

					LocaleContextHolder.setLocale((Locale) localeItems.getSelectedItem());

					CommonManager manager = (CommonManager) SpringContext.getBeanOfType(managerClass);

					Object[] f = JSONDataUtil.parseParametersJSONString(managerClass, method, paramInput.getText());
					Object result = method.invoke(manager, f);// e
					if (result instanceof FileToDownload) {
						FileToDownload downFile = (FileToDownload) result;
						String fileName = System.getProperty("java.io.tmpdir") + "/" + downFile.getFileName();
						FileOutputStream a = new FileOutputStream(fileName);
						IOUtils.copy(downFile.getContent(), a);
						a.close();
						Runtime.getRuntime().exec("cmd /E:ON /c start " + fileName);
					}

					String resultJsonString = JSONDataUtil.buildJSONString("result", result, true);

					resultInput.setText(resultJsonString);
					workTabs.setSelectedIndex(1);
				} catch (InvocationTargetException h) {
					Throwable g = ExceptionUtils.getRootCause(h);
					log.error("Invoke error", g);
					UIUtils.showError("Invoke error", g);
				} catch (Exception i) {
					log.error("Test Client error", i);
					UIUtils.showError("Test Client error", i);
				}
				setEnabled(true);
			}
		}.start();
	}

	private void format(ActionEvent evt) {
		try {
			format();
		} catch (Exception a) {
			this.log.error("Test Client error", a);
			UIUtils.showError("Test Client error", a);
		}
	}

	private void login(ActionEvent evt) {
		try {
			login();
		} catch (Exception a) {
			this.log.error("Login error", a);
			UIUtils.showError("Login error", a);
		}
	}
}
