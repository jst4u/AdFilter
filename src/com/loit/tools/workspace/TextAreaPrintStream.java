package com.loit.tools.workspace;

import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TextAreaPrintStream extends PrintStream {

	private JTextArea textarea;
	private String inputString;

	public TextAreaPrintStream(JTextArea jTextArea) {
		super(System.out);
		this.textarea = jTextArea;
	}

	public void write(byte[] buf, int off, int len) {
		inputString = new String(buf, off, len);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textarea.append(inputString);
				textarea.setCaretPosition(textarea.getDocument().getLength());
			}
		});

	}

}
