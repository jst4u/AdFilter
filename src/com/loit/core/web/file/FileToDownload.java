package com.loit.core.web.file;

import java.io.InputStream;

import com.loit.core.hibernate.model.BaseObject;

public class FileToDownload extends BaseObject {
	private String b;
	private String A;
	private InputStream a;

	public String getFileName() {
		return this.b;
	}

	public void setFileName(String fileName) {
		this.b = fileName;
	}

	public String getContentType() {
		return this.A;
	}

	public void setContentType(String contentType) {
		this.A = contentType;
	}

	public InputStream getContent() {
		return this.a;
	}

	public void setContent(InputStream content) {
		this.a = content;
	}
}
