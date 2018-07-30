package com.loit.tools.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JOptionPane;

public class UIUtils {
	private static Component window;

	public static void setCurrentWindow(Component currentWindow) {
		window = currentWindow;
	}

	public static Component getCurrentWindow() {
		return window;
	}

	public static void showInformation(String title, String message) {
		JOptionPane.showMessageDialog(window, message, title, 1);
	}

	public static void showError(String title, String message) {
		JOptionPane.showMessageDialog(window, message, title, 0);
	}

	public static void showError(String title, Throwable throwable) {
		JOptionPane.showMessageDialog(window, throwable, title, 0);
	}

	public static int showConfirmYesNo(String title, String message) {
		return JOptionPane.showConfirmDialog(window, message, title, 0);
	}

	public static int showConfirmYesNoCancel(String title, String message) {
		return JOptionPane.showConfirmDialog(window, message, title, 1);
	}

	public static void locateInScreenCenter(Window window) {
		Dimension b = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension a = window.getSize();
		window.setLocation((b.width - a.width) / 2, (b.height - a.height) / 2);
	}

	public static void locateInOwnerCenter(Window window) {
		Window c;
		if ((c = window.getOwner()) == null) {
			locateInScreenCenter(window);
		} else {
			Dimension b = c.getSize();
			Dimension a = window.getSize();
			window.setLocation(c.getX() + (b.width - a.width) / 2, c.getY() + (b.height - a.height) / 2);
		}
	}
}