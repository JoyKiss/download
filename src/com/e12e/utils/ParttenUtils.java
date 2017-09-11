package com.e12e.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParttenUtils {

	/**
	 * 正则表达式匹配(匹配一次)
	 * 
	 * @param reg
	 *            正则表达式
	 * @param str
	 *            匹配的文字列
	 * @return
	 */
	public static String getPartten(String reg, String str) {
		String result = "";

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			if (!"".equals(matcher.group())) {
				result = matcher.group();
				break;
			}
		}

		return result;
	}

	/**
	 * 正则表达式匹配(匹配所有)
	 * 
	 * @param reg
	 *            正则表达式
	 * @param str
	 *            匹配的文字列
	 * @return
	 */
	public static List<String> getParttens(String reg, String str) {
		List<String> result = new ArrayList<String>();

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			result.add(matcher.group(0));
		}

		return result;
	}
}
