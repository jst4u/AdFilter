package com.loit.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class FileUtil {

	public static void main(String[] arg) {

//		File tmp = FileUtil.changeFileCode(new File("D:\\workevn\\eclipsework\\projects\\lyframetmp\\src\\com\\asiainfo\\tld\\AIBaseTag.java"), "UTF-8");
//		tmp.renameTo(new File("D:\\workevn\\eclipsework\\projects\\lyframetmp\\src\\com\\asiainfo\\tld\\AIBaseTag.java"));
		
		File folder = new File("C:\\Documents and Settings\\Administrator\\workspace\\temp\\src\\com\\asiainfo\\biframe\\chart");
		File resultFolder = new File("c:\\bbb");
		FileUtil.deleteAll(resultFolder);
		resultFolder.mkdirs();
		HashSet<String> filter = new HashSet<String>();
		filter.add("java");
		FileUtil.changeFileCodeAll(folder, resultFolder, "UTF-8", filter);

	}

	/**
	 *  判断字符流的编码是否是UTF8
	 * @param InputStream
	 * @return
	 */
	public static boolean isStreamUTF8(InputStream is){
		
		boolean isUTF8 = false;
		
		try {

			int read = -1;
			is.reset();
			while ((read = is.read()) != -1) {
				
				if (read >= 0xF0){
					break;
				}else if (0x80 <= read && read <= 0xBF){ // 单独出现BF以下的，也算是GBK
					break;
				}else if (0xC0 <= read && read <= 0xDF) {// 双字节 (0xC0 - 0xDF)
					read = is.read();
					if (0x80 <= read && read <= 0xBF){ // (0x80 - 0xBF),也可能在GB编码内,不确定继续判断
						continue;
					}else{
						break;
					}
				} else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
					read = is.read();                    
					if (0x80 <= read && read <= 0xBF) {   // 第二个字节判断
						read = is.read();
						if (0x80 <= read && read <= 0xBF) {  // 第三个字节判断
							isUTF8 =  true;
							break;
						} else{
							break;
						}
					} else{
						break;
					}
				}//end if
			}//end while
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isUTF8;
	}
	
	public static String getFileCode(File file) {
		
		InputStream is = null;
		String code = null;
		try {
			is = new BufferedInputStream( new FileInputStream( file ) );
			byte[] first3Bytes = new byte[3];
			is.mark(0);
			int read = is.read(first3Bytes, 0, 3);
			
			if(read != -1){
				return null;
			}
			
			if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				code = "UTF-16LE";
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				code = "UTF-16BE";
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				
				code = "UTF-8";
			}else if(FileUtil.isStreamUTF8(is)){
				
				code = "UTF-8";
			}
			
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}   
		
		return code;
	}
	
	public static void deleteAll(File f){
		if(!f.exists()){
			return;
		}
		if(f.isFile()){
			f.delete();
		}else{
			File[] files = f.listFiles();
			for(int i=0;i<files.length;i++){
				deleteAll(files[i]);
			}
		}
		
	}

	public static String getFileSuffix(File f){
		
		String name = f.getName();
		
		return name.substring(name.lastIndexOf(".")+1);
	}
	
	public static void changeFileCodeAll(File folder,File resultFolder,String codeName,HashSet<String> filter){
		
		if(!resultFolder.exists()){
			resultFolder.mkdirs();
		}
		
		String currentFolder = resultFolder.getAbsolutePath();
		
		FileUtil.changeFileCodeFolder(folder, codeName, filter, currentFolder);
		
		
	}
	
	private static void changeFileCodeFolder(File folder,String codeName,HashSet<String> filter,String currentFolder){
		
		File[] files = folder.listFiles();
		String type = null;
		File tmpFile = null;
		String fileName = null;
		try{
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					type = FileUtil.getFileSuffix(files[i]).toLowerCase();
					if(filter.contains(type) || filter.contains("*")){
						fileName = currentFolder+File.separator+files[i].getName();
						tmpFile = new File(fileName);
						tmpFile.createNewFile();
						FileUtil.changeFileCode(files[i],tmpFile, codeName);
					}
				} else {
					
					String currentFolderChildFolder = currentFolder+ File.separator+files[i].getName();
					File newChildFolder = new File(currentFolderChildFolder);
					if(!newChildFolder.exists()){
						newChildFolder.mkdirs();
					}
					FileUtil.changeFileCodeFolder(files[i],codeName,filter,currentFolderChildFolder);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		;
	}
	
	
	
	public static void changeFileCode(File source,File target, String codeName){
		
		try {
			if(null == source || !source.exists() || !source.isFile()){
				return;
			}
//			read
			String fileCode = FileUtil.getFileCode(source);
			fileCode = null == fileCode?"GBK":fileCode;
			InputStreamReader read = new InputStreamReader (new FileInputStream(source),fileCode);
			BufferedReader br = new BufferedReader(read);
			String line = null;
			
//			write
			OutputStream out =  new FileOutputStream(target,true);
			OutputStreamWriter osw = new OutputStreamWriter (out,codeName);	
			
			while((line = br.readLine()) != null){
				osw.write(line);
				osw.write("\r\n");
			}
			osw.close();
			out.close();
			br.close();
			read.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
