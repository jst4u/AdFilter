package com.loit.apps.project.palace.socket.protocal;

/**
 * 电池传输协议
 * 
 * @author sunsw
 *
 */
public class BatProtocalPack {
	/**
	 * 开始标志
	 */
	public static short STARTER = 0x575A;
	/**
	 * 报文
	 */
	private String content;
	/**
	 * 报文长度
	 */
	public static int length = 28;

	public BatProtocalPack(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return this.content;
	}

}
