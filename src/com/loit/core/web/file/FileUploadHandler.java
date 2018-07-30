package com.loit.core.web.file;

import java.io.InputStream;
import java.util.List;


public abstract interface FileUploadHandler {

	public abstract void uploadFile(String paramString1, String paramString2, String paramString3, InputStream paramInputStream);

	  public abstract List<FileDesc> listUploadedFiles(String paramString1, String paramString2);

	  public abstract void deleteUploadedFile(String paramString1, String paramString2, String paramString3);

	  public abstract FileToDownload getUploadedFile(String paramString1, String paramString2, String paramString3);
	
}
