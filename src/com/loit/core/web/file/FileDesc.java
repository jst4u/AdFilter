package com.loit.core.web.file;

import java.util.Date;

import com.loit.core.hibernate.model.BaseObject;

public class FileDesc extends BaseObject {

	private String b;
	private long A;
	private Date a;

	public String getFileName() {
		return this.b;
	}

	public void setFileName(String fileName) {
		this.b = fileName;
	}

	public long getFileSize() {
		return this.A;
	}

	public void setFileSize(long fileSize) {
		this.A = fileSize;
	}

	public Date getCreateTime() {
		return this.a;
	}

	public void setCreateTime(Date createTime) {
		this.a = createTime;
	}

}
