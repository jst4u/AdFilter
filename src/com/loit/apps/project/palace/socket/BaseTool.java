package com.loit.apps.project.palace.socket;

public class BaseTool {
	/**
	 * byte转为16进制，取后两位
	 * 
	 * @param b
	 * @return
	 */
	public static String getByteHexString(byte b) {
		String s = "0" + Integer.toHexString(b).toUpperCase();
		return s.substring(s.length() - 2);
	}
	
	/**
	 * 16进制ascii转为string
	 * 
	 * @param hex
	 * @return
	 */
	public static String convertHexToString(String hex) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hex.length() - 1; i += 2) {
			String output = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(output, 16);
			sb.append((char) decimal);
		}
		return sb.toString();
	}
}
