package com.e12e.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.e12e.utils.FileUtils;

public class Music163Menue {
	public static void main(String[] args) {
		getMenue("http://music.163.com/song/", 1425, 10000000);
	}

	/**
	 * 获取网易云音乐单,并写入文件
	 * 
	 * @param baseurl
	 *            base网址
	 * @param statr
	 *            开始index
	 * @param end
	 *            结束index
	 */
	public static void getMenue(String baseurl, int statr, int end) {
		List<String> writeTmp = new ArrayList<String>();
		for (int i = statr; i < end; i++) {
			String url = baseurl + i;
			System.out.println(url);
			Connection con = Jsoup.connect(url);
			Document doc;
			try {
				doc = con.get();
				// System.out.println(doc.html());
				if (doc.select(".n-for404").size() == 0) {

					// // 音乐名称
					// Elements eles = doc.select(".tit .f-ff2");
					// 音乐标题
					Elements eles = doc.select("title");
					for (Element ele : eles) {
						String title = ele.text();
						String[] songInfo = title.split(" - ");
						String info = "";
						if (songInfo.length == 3) {
							info = songInfo[0] + "\t" + songInfo[1] + "\t" + url;
						} else {
							info = title + "\t" + url;
						}
						System.out.println(info);
						writeTmp.add(info);
						if (writeTmp.size() > 1000) {
							FileUtils.add2File("E:\\Music163.txt", writeTmp);
							writeTmp.clear();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			if (writeTmp.size() > 0) {
				FileUtils.add2File("E:\\Music163.txt", writeTmp);
			}
		}
	}

	/**
	 * 根据url查找背景图片下载地址
	 * 
	 * @param url
	 * @return
	 */
	public static String getMusicPic(String url) {
		String result = "";
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			Elements eles = doc.select(".j-img");
			for (Element ele : eles) {
				result = ele.attr("data-src");
				return result;
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 根据Document过滤背景图片下载地址
	 * 
	 * @param doc
	 * @return
	 */
	public static String getMusicPic(Document doc) {
		String result = "";
		Elements eles = doc.select(".j-img");
		for (Element ele : eles) {
			result = ele.attr("data-src");
			return result;
		}

		return result;
	}

}
