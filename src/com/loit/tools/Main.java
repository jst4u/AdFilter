package com.loit.tools;

import com.loit.tools.workspace.MainFrame;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());

			new MainFrame(new MainPanel());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
