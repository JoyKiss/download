package com.e12e.main;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.e12e.utils.FileUtils;

public class Demo {
	public static void main(String[] args) throws IOException {
		getTag();
	}

	private static void getTag() {
		List<String> lines = FileUtils.readFileByLines("D:\\dmp\\workspace\\download\\conf\\", "duowan.txt");
		for (String line : lines) {
			Connection con = Jsoup.connect(line);
			Document doc;
			try {
				doc = con.post();

				String keywords = doc.select("meta[name='keywords']").attr("content");
				System.out.println(line + "\t" + keywords);
				if ("".equals(keywords)) {
					FileUtils.add2File("E:\\down\\notag.txt", line);
				} else {
					for (String tag : keywords.split(",")) {
						if (!"".equals(tag)) {
							FileUtils.add2File("E:\\down\\" + tag + ".txt", line);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				FileUtils.add2File("E:\\down\\exception.txt", line);
				continue;
			}
		}
	}
}
