package com.e12e.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	public static void main(String[] args) {
		// try {
		//
		// String content = "This is the content to write into file";
		//
		// File file = new File("./补档.txt");
		//
		// // if file doesnt exists, then create it
		// if (!file.exists()) {
		// file.createNewFile();
		// }
		//
		// FileWriter fw = new FileWriter(file.getAbsoluteFile());
		// BufferedWriter bw = new BufferedWriter(fw);
		// bw.write(content);
		// bw.close();
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		List<String> list = new ArrayList<String>();
		list.add("666");
		add2File("补档.txt", list);
	}

	/**
	 * 写内容到文件中
	 * 
	 * @param filePath
	 *            文件路径
	 * @param addStr
	 *            写入文件内容List
	 */
	public static void add2File(String filePath, List<String> addStr) {
		try {

			String content = "This is the content to write into file";

			File file = new File(filePath);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (String str : addStr) {
				bw.write(str);
				bw.newLine();
			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写一行内容到文件中
	 * 
	 * @param filePath
	 * @param addStr
	 */
	public static void add2File(String filePath, String addStr) {
		List<String> addList = new ArrayList<String>();
		addList.add(addStr);
		add2File(filePath, addList);
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static List<String> readFileByLines(String filePath, String fileName) {
		File file = new File(filePath, fileName);

		List<String> tempStringList = new ArrayList<String>();
		if (!file.exists()) {
			return tempStringList;
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;

			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				tempStringList.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		return tempStringList;
	}
}