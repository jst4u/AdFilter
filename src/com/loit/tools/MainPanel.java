package com.loit.tools;

import com.loit.core.commom.SysConfig;
import com.loit.core.spring.SpringContext;
import com.loit.tools.utils.ConnectionUtils;
import com.loit.tools.workspace.BasePanel;
import com.loit.tools.workspace.TextAreaPrintStream;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.File;
import java.util.List;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class MainPanel extends BasePanel {
	private JPanel toolsMainPanel;
	private JPanel toolsMainTopLeftNorth;
	private JPanel toolsMainTopLeft;
	private JButton toolsRefreshButton;
	private JPanel toolsMainTopRight;
	private JSplitPane toolsMainSplit;
	private JSplitPane toolsMainTopSplit;
	private JScrollPane toolsMenuPane;
	private JScrollPane toolsMainBottomSplit;
	private JTextArea logInfo;
	private MainMenu mainMenu;

	private Log logger = LogFactory.getLog(MainPanel.class);

	public MainPanel() {
		startPanel();
	}

	private void startPanel() {
		try {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(900, 650));
			setTitle("Frame Tools - "+SysConfig.PROJECT_NAME);
			addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent evt) {
					toolsMainSplit.setDividerLocation(getHeight() - 200);
				}
			});

			this.toolsMainPanel = new JPanel();
			this.toolsMainPanel.setLayout(new BorderLayout());
			this.toolsMainPanel.setBorder(BorderFactory.createTitledBorder("main"));
			add(this.toolsMainPanel, "Center");

			this.toolsMainSplit = new JSplitPane();

			this.toolsMainTopSplit = new JSplitPane();
			this.toolsMainSplit.add(this.toolsMainTopSplit, "top");
			this.toolsMainTopSplit.setDividerLocation(300);
			this.toolsMainTopSplit.setBorder(BorderFactory.createTitledBorder("topsplit"));
			

			this.toolsMainTopRight = new JPanel();
			this.toolsMainTopRight.setLayout(new BorderLayout());
			this.toolsMainTopSplit.add(this.toolsMainTopRight, "right");
			this.toolsMainTopRight.setBorder(BorderFactory.createTitledBorder("topright"));
			
			
			this.toolsMainTopLeft = new JPanel();
			this.toolsMainTopSplit.add(this.toolsMainTopLeft, "left");
			this.toolsMainTopLeft.setLayout(new BorderLayout());
			this.toolsMainTopLeft.setBorder(BorderFactory.createTitledBorder("topleft"));
			
			
			this.toolsMainTopLeftNorth = new JPanel();
			 FlowLayout a;
			      (a = new FlowLayout())
			        .setAlignment(0);
			this.toolsMainTopLeftNorth.setLayout(new FlowLayout(0));
			this.toolsMainTopLeft.add(this.toolsMainTopLeftNorth, "North");
			this.toolsMainTopLeftNorth.setBorder(BorderFactory.createTitledBorder("topleftNorth"));
			
			this.toolsRefreshButton = new JButton();
			this.toolsRefreshButton.setText("Refresh");
			this.toolsMainTopRight.add(this.toolsRefreshButton);
			this.toolsMainTopLeftNorth.add(this.toolsRefreshButton);
			
			this.toolsRefreshButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							try {
								synchronized (MainPanel.class) {
									logger.info("Restarting Spring ApplicationContext...");
									ConfigurableApplicationContext context = (ConfigurableApplicationContext) SpringContext
											.getApplicationContext();
									SpringContext.setApplicationContext(null);
									context.refresh();
									SpringContext.setApplicationContext(context);
									logger.info("Spring ApplicationContext restarted");
									mainMenu.saveTreePath();
									mainMenu.refreshPanels();
									mainMenu.refresh();
									mainMenu.loadTreePath();
								}
							} catch (Exception c) {
								c.printStackTrace();
							}
						}
					}.start();
				}
			});

			this.toolsMenuPane = new JScrollPane();
			this.toolsMainTopLeft.add(this.toolsMenuPane, "Center");
			this.toolsMenuPane.setBorder(BorderFactory.createTitledBorder("menu"));
			
			this.mainMenu = new MainMenu();
			this.toolsMenuPane.setViewportView(this.mainMenu);
			
			this.toolsMainPanel.add(this.toolsMainSplit, "Center");
			this.toolsMainSplit.setOrientation(0);

			this.toolsMainBottomSplit = new JScrollPane();
			this.toolsMainSplit.add(this.toolsMainBottomSplit, "bottom");
			this.toolsMainBottomSplit.setBorder(BorderFactory.createTitledBorder("bottomSplit"));
			
			this.logInfo = new JTextArea();
			this.toolsMainBottomSplit.setViewportView(this.logInfo);
			this.logInfo.setEditable(false);
			this.logInfo.setTabSize(4);
		} catch (Exception e) {
			e.printStackTrace();
System.out.println(e);
		}
	}

	public boolean init() {
		TextAreaPrintStream textPrint = new TextAreaPrintStream(this.logInfo);
		System.setOut(textPrint);
		System.setErr(textPrint);

		
		this.mainMenu.setOperatePanel(this.toolsMainTopRight);
		this.mainMenu.setPathFileName(SysConfig.USER_HOME_DIR + "/." + SysConfig.PROJECT_NAME + "/ToolsMainMenuPath");
		this.mainMenu.requestFocus();

		new Thread() {
			public void run() {
				Log logger = LogFactory.getLog(MainPanel.class);
				
				logger.info("Initializing Spring ApplicationContext...");
				String paramValue = null;
				try {
					File webFile = new File(SysConfig.WEB_DIR + "/WEB-INF/web.xml");
					SAXBuilder xmlBuilder = new SAXBuilder();
					Namespace namespace = Namespace.getNamespace("http://java.sun.com/xml/ns/j2ee");
					List<Element> paramList = xmlBuilder.build(webFile).getRootElement().getChildren("context-param", namespace);
					for (Element param : paramList) {
						paramValue = param.getChildTextTrim("param-value", namespace);
					}
				} catch (Exception g) {
					logger.error("Error getting contextConfigLocation", g);
					System.out.println(g);
					
				}

				if (paramValue == null || paramValue.trim().length() == 0) {
					logger.error("contextConfigLocation not found, config it in web.xml first!!!");
					
				}

				ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext(StringUtils.tokenizeToStringArray(paramValue, ",; \t\n"));
				springContext.registerShutdownHook();

				logger.info("Spring ApplicationContext initialized");
				
			}
		}.start();

		
		new Thread(){
			public void run(){
				
				try
				    {
				      mainMenu.loadTreePath();
				    } catch (Exception a) {
				      a.printStackTrace();
				    }
			}
		}.start();

		return true;
	}

	public boolean close() {
		try {
			this.mainMenu.saveTreePath();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

}
