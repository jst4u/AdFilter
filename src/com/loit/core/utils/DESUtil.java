package com.loit.core.utils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加解密工具
 * 提供DES、DESede、Blowfish三种方式
 * @author niujl
 */
public class DESUtil {

	private static final String KEY_DES = "86CE8373"+"8A43D6A1";// 密钥
	private static final String KEY_DESede = "B5584A5D9"+"B61C23BE52CA1"+"168C9110894C4F"+"E9ABC8E9F251";// 密钥
	private static final String KEY_Blowfish = "3CCD64E"+"0D027A569011"+"3B34AC9BC5B38";// 密钥

	private DESUtil() {
	}

	public static enum CryptType {
		DES, DESede, Blowfish
	}
	/**
	 * 加密文本内容：s
	 * 使用默认算法：DESede
	 * 使用默认密钥
	 */
	public static String encrypt(String s) {
		return DESUtil.encrypt(s, DESUtil.CryptType.DESede);
	}
	/**
	 * 解密文本内容：s
	 * 使用默认算法：DESede
	 * 使用默认密钥
	 */
	public static String decrypt(String s) {

		return DESUtil.decrypt(s, DESUtil.CryptType.DESede);
	}
	/**
	 * 加密文本内容：s
	 * 指定加密算法：DESUtil.CryptType
	 * 使用默认密钥
	 */
	public static String encrypt(String s, CryptType type) {
		
		return DESUtil.encrypt(s, type, DESUtil.getKey(type));
	}
	/**
	 * 解密文本内容：s
	 * 指定解密算法：DESUtil.CryptType
	 * 使用默认密钥
	 */
	public static String decrypt(String s, CryptType type) {
		return DESUtil.decrypt(s, type, DESUtil.getKey(type));
	}
	/**
	 * 加密文本内容：s
	 * 指定加密算法：DESUtil.CryptType
	 * 指定加密密钥：keyHex
	 * 根据指定算法不同，所需要指定的密钥也不同
	 */
	public static String encrypt(String s,CryptType type,String keyHex) {
		try {
			// DESede为加密算法
			Cipher cipher = Cipher.getInstance(DESUtil.getCryptType(type));

			// 密钥
			byte[] keyByte = DESUtil.hex2byte(keyHex);

			// 根据密钥生成密锁
			SecretKey key = new SecretKeySpec(keyByte, DESUtil.getCryptType(type));
			// 加密算法容器初始化
			cipher.init(Cipher.ENCRYPT_MODE, key);

			// -----------------随机密钥
			// KeyGenerator kg=KeyGenerator.getInstance(getCryptType(type));//
			// SecretKey key = kg.generateKey();
			// cipher.init(Cipher.ENCRYPT_MODE, key);
			// -----------------随机密钥

			// 加密
			byte[] enByte = cipher.doFinal(s.getBytes());
			// 返回16进制加密结果
			return DESUtil.byte2hex(enByte);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed to encrypt!");
		}
		return null;
	}
	/**
	 * 解密文本内容：s
	 * 指定解密算法：DESUtil.CryptType
	 * 指定解密密钥：keyHex
	 * 根据指定算法不同，所需要指定的密钥也不同
	 */
	public static String decrypt(String s,CryptType type,String keyHex) {

		try {
			// DESede为加密算法
			Cipher cipher = Cipher.getInstance(DESUtil.getCryptType(type));
			// 密钥
			byte[] keyByte = DESUtil.hex2byte(keyHex);
			// 根据密钥生成密锁
			SecretKey key = new SecretKeySpec(keyByte, DESUtil.getCryptType(type));
			// 解密算法容器初始化
			cipher.init(Cipher.DECRYPT_MODE, key);

			System.out.println("dekey:"
					+ new String(DESUtil.byte2hex(key.getEncoded())));

			// 解密
			byte[] ss = DESUtil.hex2byte(s);
			System.out.println("ss:" + DESUtil.byte2hex(ss));

			byte[] enByte = cipher.doFinal(ss);

			return new String(enByte);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed to decrypt!");
		}
		return null;
	}
	

	private static String getCryptType(CryptType type) {

		switch (type) {
		case DES:
			return "DESUtil";
		case DESede:
			return "DESede";
		case Blowfish:
			return "Blowfish";
		}
		return "";
	}

	private static String getKey(CryptType type) {
		switch (type) {
		case DES:
			return KEY_DES;
		case DESede:
			return KEY_DESede;
		case Blowfish:
			return KEY_Blowfish;
		}
		return "";
	}

	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int i = 0; i < b.length; i++) {

			stmp = Integer.toHexString(b[i] & 0xFF);
			if (stmp.length() == 1) {
				hs += "0" + stmp;
			} else {
				hs += stmp;
			}
		}
		return hs.toUpperCase();
	}

	private static byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	public static void main(String arg[]) {
		System.out.println(DESUtil.encrypt("牛家乐", DESUtil.CryptType.DESede));
		System.out.println("-----------------");
		System.out.println(DESUtil.decrypt("825687BB7FC6E2ED", DESUtil.CryptType.DESede));
		
		System.out.println(DESUtil.encrypt("牛家乐", DESUtil.CryptType.Blowfish,"7A0C010697EFF688327543FB1B135425"));
		System.out.println("-----------------");
		System.out.println(DESUtil.decrypt("EC10997D7EAC1B81", DESUtil.CryptType.Blowfish,"7A0C010697EFF688327543FB1B135425"));
		
		
		try {
			KeyGenerator kg=KeyGenerator.getInstance(DESUtil.getCryptType(DESUtil.CryptType.Blowfish));
			System.out.println(DESUtil.byte2hex(kg.generateKey().getEncoded()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
