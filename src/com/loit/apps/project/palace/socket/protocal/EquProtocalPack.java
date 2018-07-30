package com.loit.apps.project.palace.socket.protocal;

import com.loit.apps.project.palace.socket.BaseTool;

/**
 * 网关、中继、节点传输协议
 * 
 * @author sunsw
 *
 */
public class EquProtocalPack {
	/**
	 * 开始标志
	 */
	public static byte STARTER = 0x7E;
	/**
	 * 报文部分长度
	 */
	private short length;
	/**
	 * 报文
	 */
	private byte[] content;
	/**
	 * 校验和
	 */
	private byte sum;

	public EquProtocalPack(byte[] content) {
		this.content = content;
		this.length = (short) content.length;
		this.sum = (byte) this.getCheckSum(content);
	}

	public EquProtocalPack(byte[] content, byte sum) {
		this.content = content;
		this.length = (short) content.length;
		this.sum = sum;
	}

	/**
	 * 判断校验和是否正确
	 * 
	 * @return
	 */
	public boolean isRight() {
		return sum == this.getCheckSum(this.content);
	}

	/**
	 * 获取校验和
	 * 
	 * @param content
	 * @return
	 */
	public byte getCheckSum(byte[] content) {
		int sum = 0;
		for (int i = 0; i < content.length; i++) {
			sum += content[i];
		}
		int checkSum = 0xFF - sum % 0x100;
		return (byte)checkSum;
	}

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public byte getSum() {
		return sum;
	}

	public void setSum(byte sum) {
		this.sum = sum;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(BaseTool.getByteHexString(EquProtocalPack.STARTER) + " ");
		sb.append(BaseTool.getByteHexString((byte) (this.length >> 8)) + " ");
		sb.append(BaseTool.getByteHexString((byte) this.length) + " ");
		for (int i = 0; i < this.content.length; i++) {
			sb.append(BaseTool.getByteHexString(this.content[i]) + " ");
		}
		sb.append(BaseTool.getByteHexString(this.sum));
		return sb.toString();
	}

}
