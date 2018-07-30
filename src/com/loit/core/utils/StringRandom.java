package com.loit.core.utils;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * 
 * <p>
 * Title: StringRandom.java
 * </p>
 * <p>
 * Description: 获取指定取值字符集区间、指定长度的随机字符串
 * </p>
 * 使用方式：<br />
 * StringRandom gen = new StringRandom();	<br />
 *	gen.setCharset("a-zA-Z0-9");//设置随机字符区间	<br />
 *	gen.setLength(4);//设置随机字符长度	<br />
 *	System.out.println(gen.getRandom());//取得随机结果<br />
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * 
 */
public class StringRandom {

	private Integer length = new Integer(8);

	private String randomstr;

	private boolean allchars = false;//取值区间为所有的字符

	private HashMap hmap;

	private ArrayList lower = null; // 选择随机字符串的字符上限区间

	private ArrayList upper = null; // 选择随机字符串的字符下限区间

	private char[] single = null;

	private int singlecount = 0;

	private boolean singles = false;

	private String algorithm = null;

	private String provider = null;

	private boolean secure = false; // 是否使用SecureRandom

	private Random random = null;

	private SecureRandom secrandom = null;

	/**
	 * 生成用于产生随机字符串的对象
	 * 
	 * @throws Exception void
	 * 
	 */
	private final void generateRandomObject() throws Exception {
		// 检查对象是否为SecureRandom类型的对象
		if (secure) {
			try {
				// 取得SecureRandom类型的对象实例
				if (provider != null)
					// search for algorithm in package provider
					random = SecureRandom.getInstance(algorithm, provider);
				else
					random = SecureRandom.getInstance(algorithm);
			} catch (NoSuchAlgorithmException ne) {
				throw new Exception(ne.getMessage());
			} catch (NoSuchProviderException pe) {
				throw new Exception(pe.getMessage());
			}
		} else
			random = new Random();
	}

	/**
	 * 
	 * 生成随机字符串 
	 * void
	 * 
	 */
	private final void generaterandom() {
		if (allchars) {
			for (int i = 0; i < length.intValue(); i++)
				randomstr = randomstr
						+ new Character(
								(char) ((int) 34 + ((int) (getFloat() * 93))))
								.toString();
		} else if (singles) {
			// check if there are single chars to be included
			if (upper.size() == 3) {
				// check for the number of ranges max 3 uppercase lowercase
				// digits

				// build the random string
				for (int i = 0; i < length.intValue(); i++) {
					// you have four groups to choose a random number from, to
					// make
					// the choice a little more random select a number out of
					// 100

					// get a random number even or odd
					if (((int) (getFloat() * 100)) % 2 == 0) {

						// the number was even get another number even or odd
						if (((int) (getFloat() * 100)) % 2 == 0)
							// choose a random char from the single char group
							randomstr = randomstr + randomSingle().toString();
						else
							// get a random char from the first range
							randomstr = randomstr
									+ randomChar((Character) lower.get(2),
											(Character) upper.get(2))
											.toString();
					} else {
						// the number was odd

						if (((int) (getFloat() * 100)) % 2 == 0)
							// choose a random char from the second range
							randomstr = randomstr
									+ randomChar((Character) lower.get(1),
											(Character) upper.get(1))
											.toString();
						else
							// choose a random char from the third range
							randomstr = randomstr
									+ randomChar((Character) lower.get(0),
											(Character) upper.get(0))
											.toString();
					}
				}
			} else if (upper.size() == 2) {
				// single chars are to be included choose a random char from
				// two different ranges

				// build the random char from single chars and two ranges
				for (int i = 0; i < length.intValue(); i++) {
					// select the single chars or a range to get each random
					// char
					// from

					if (((int) (getFloat() * 100)) % 2 == 0) {

						// get random char from the single chars
						randomstr = randomstr + randomSingle().toString();
					} else if (((int) (getFloat() * 100)) % 2 == 0) {

						// get the random char from the first range
						randomstr = randomstr
								+ randomChar((Character) lower.get(1),
										(Character) upper.get(1)).toString();
					} else {

						// get the random char from the second range
						randomstr = randomstr
								+ randomChar((Character) lower.get(0),
										(Character) upper.get(0)).toString();
					}
				}
			} else if (upper.size() == 1) {

				// build the random string from single chars and one range
				for (int i = 0; i < length.intValue(); i++) {
					if (((int) getFloat() * 100) % 2 == 0)
						// get a random single char
						randomstr = randomstr + randomSingle().toString();
					else
						// get a random char from the range
						randomstr = randomstr
								+ randomChar((Character) lower.get(0),
										(Character) upper.get(0)).toString();
				}
			} else {
				// build the rand string from single chars
				for (int i = 0; i < length.intValue(); i++)
					randomstr = randomstr + randomSingle().toString();
			}
		} else {

			// no single chars are to be included in the random string
			if (upper.size() == 3) {

				// build random strng from three ranges
				for (int i = 0; i < length.intValue(); i++) {

					if (((int) (getFloat() * 100)) % 2 == 0) {

						// get random char from first range
						randomstr = randomstr
								+ randomChar((Character) lower.get(2),
										(Character) upper.get(2)).toString();
					} else if (((int) (getFloat() * 100)) % 2 == 0) {

						// get random char form second range
						randomstr = randomstr
								+ randomChar((Character) lower.get(1),
										(Character) upper.get(1)).toString();
					} else {

						// get random char from third range
						randomstr = randomstr
								+ randomChar((Character) lower.get(0),
										(Character) upper.get(0)).toString();
					}
				}
			} else if (upper.size() == 2) {

				// build random string from two ranges
				for (int i = 0; i < length.intValue(); i++) {
					if (((int) (getFloat() * 100)) % 2 == 0)
						// get random char from first range
						randomstr = randomstr
								+ randomChar((Character) lower.get(1),
										(Character) upper.get(1)).toString();
					else
						// get random char from second range
						randomstr = randomstr
								+ randomChar((Character) lower.get(0),
										(Character) upper.get(0)).toString();
				}
			} else

				// build random string
				for (int i = 0; i < length.intValue(); i++)
					// get random char from only range
					randomstr = randomstr
							+ randomChar((Character) lower.get(0),
									(Character) upper.get(0)).toString();
		}
	}

	/**
	 * 
	 * 从单一字符的集合中，生成一个随机字符
	 * 
	 * @return Character 一个从字符集合中随机选择的字符
	 * 
	 */
	private final Character randomSingle() {
		return (new Character(single[(int) ((getFloat() * singlecount) - 1)]));
	}

	/**
	 * 
	 * 从字符区间随机取得一个字符
	 * 
	 * @param lower
	 *            获得字符的下边界
	 * @param upper
	 *            获得字符的上边界
	 * @return Character 区间内的随机字符
	 * 
	 */
	private final Character randomChar(Character lower, Character upper) {
		int tempval;
		char low = lower.charValue();
		char up = upper.charValue();

		// get a random number in the range lowlow - lowup
		tempval = (int) ((int) low + (getFloat() * ((int) (up - low))));

		// return the random char
		return (new Character((char) tempval));
	}

	/**
	 * 
	 * 取得随机字符串
	 * 
	 * @return String 生成的随机字符串
	 * 
	 */
	public String getRandom() throws Exception{
		generateRandomObject();	//生成随机对象
		randomstr = "";
		generaterandom(); // generate the first random string
		if (hmap != null) {
			while (hmap.containsKey(randomstr)) {
				// random string has already been created generate a different
				// one
				generaterandom();
			}
			hmap.put(randomstr, null); // add the new random string
		}
		return randomstr;
	}

	/**
	 * 
	 * 设置选择随机字符串的字符区间
	 * 
	 * @param low
	 *            下限区间
	 * @param up
	 *            上限区间 void
	 * 
	 */
	public final void setRanges(ArrayList low, ArrayList up) {
		lower = low;
		upper = up;
	}

	/**
	 * 
	 * 用于检查随机字符串的唯一性
	 * @param map
	 * void
	 *
	 */
	public final void setHmap(HashMap map) {
		hmap = map;
	}

	/**
	 * 
	 * 设置随机字符串的长度
	 * 
	 * @param value
	 *            void
	 * 
	 */
	public final void setLength(String value) {
		length = new Integer(value);
	}

	/**
	 * 
	 * 设置随机字符串的长度
	 * 
	 * @param count
	 *            void
	 * 
	 */
	public void setLength(int count) {
		length = new Integer(count);
	}

	/**
	 * 
	 * 设置算法名称
	 * 
	 * @param value 用于SecureRandom对象的算法名称
	 * void
	 * 
	 */
	public final void setAlgorithm(String value) {
		algorithm = value;
		secure = true; // a SecureRandom object is to be used
	}

	/**
	 * 
	 * 设置提供者名称，即算法所在的包名
	 * @param value
	 * void
	 *
	 */
	public final void setProvider(String value) {
		provider = value;
	}

	/**
	 * set the allchars flag
	 * 
	 * @param value
	 *            boolean value of the allchars flag
	 * 
	 */
	public final void setAllchars(boolean value) {
		allchars = value;
	}

	/**
	 * 设置单字符的集合，用于选择来创建随机字符
	 * 
	 * @param chars
	 *            字符集数组
	 * @param value
	 *            字符个数 void
	 * 
	 */
	public final void setSingle(char[] chars, int value) {
		single = chars; // 字符集数组
		singlecount = value; // 字符个数
		singles = true; // 设置单字符集被使用标识
	}

	/**
	 * 取得随机浮点数
	 * 
	 * @return float
	 * 
	 */
	private final float getFloat() {
		// 判断随机对象的类型
		if (random == null) {
			return secrandom.nextFloat();
		}else {
			return random.nextFloat();
		}
	}

	/**
	 * 
	 * 设置用于随机的字符集，如：a-zA-Z0-9，以min-max最小值最大值成对方式设置，最多三个区间 
	 * @param value
	 * void
	 *
	 */
	public final void setCharset(String value) {
		// values tells the method whether or not to check for single chars
		boolean more = true;

		// create the arraylists to hold the upper and lower bounds for the char
		// ranges
		lower = new ArrayList(3);
		upper = new ArrayList(3);

		// user has chosen to use all possible characters in the random string
		if (value.compareToIgnoreCase("all") == 0) {
			allchars = true; // set allchars flag
			// all chars are to be used so there are no single chars to sort
			// through
			more = false;
		} else if ((value.charAt(1) == '-') && (value.charAt(0) != '\\')) {
			// run through the ranges at most 3
			while (more && (value.charAt(1) == '-')) {
				// check to make sure that the dash is not the single char
				if (value.charAt(0) == '\\') {
					break;
				}else {
					// add upper and lower ranges to there list
					lower.add(new Character(value.charAt(0)));
					upper.add(new Character(value.charAt(2)));
				}

				// check to see if there is more to the charset
				if (value.length() <= 3){
					more = false;
				}else {
					// create a new string so that the next range if there is
					// one
					// starts it
					value = value.substring(3);
				}
			}
		}
		// if more = false there are no single chars in the charset
		if (more) {
			single = new char[30]; // create single
			// create a set of tokens from the string of single chars
			StringTokenizer tokens = new StringTokenizer(value);
			while (tokens.hasMoreTokens()) {
				// get the next token from the string
				String token = tokens.nextToken();
				if (token.length() > 1) {
					// char is a - add it to the list
					single[singlecount++] = '-';
				}
				// add the current char to the list
				single[singlecount++] = token.charAt(0);
			}
		}
		//如果区间和单字符集合都没有设置，则默认为a-zA-Z0-9表示的3个区间
		if ((lower == null) && (single == null)) {
			setCharset("a-zA-Z0-9");
		}
	}
}
