package com.loit.tools.workspace;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import com.loit.tools.utils.UIUtils;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private BasePanel basePanel;// a

	public MainFrame(BasePanel mainPanel) {
		setTitle(mainPanel.getTitle());
		this.basePanel = mainPanel;
		initFrame();
		UIUtils.locateInScreenCenter(this);
		UIUtils.setCurrentWindow(this);
		setVisible(true);
	}

	private void initFrame() {
		try {
			setDefaultCloseOperation(2);
			
			addWindowListener(new MainFrameListener(this) {});

			getContentPane().add(this.basePanel, "Center");

			pack();
		} catch (Exception a) {
			a.printStackTrace();
		}
	}

	private void windowOpened(WindowEvent evt) {
		System.out.println("-------------------open tools ---------------------");
		this.basePanel.init();
	}

	private void windowClosed(WindowEvent evt) {
		this.basePanel.close();
		System.out.println("-------------------exit tools ---------------------");
		System.exit(0);
	}

	private class MainFrameListener extends WindowAdapter{
		MainFrame mainframe = null;
		MainFrameListener(MainFrame mainframe){
			this.mainframe = mainframe;
		}
		
		public void windowClosed(WindowEvent evt) {
			this.mainframe.windowClosed(evt);
		}

		public void windowOpened(WindowEvent evt) {
			this.mainframe.windowOpened(evt);
		}
	}
}
