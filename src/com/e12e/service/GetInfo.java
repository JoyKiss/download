package com.e12e.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class GetInfo {
	public static void doGetInfo(int classNo, String className)
			throws IOException {
		File file = new File("./download/" + className + "/course_info.txt");

		file.createNewFile();

		FileWriter txtWriter = new FileWriter(file);

		Document doc = Jsoup.connect("http://www.imooc.com/view/" + classNo)
				.timeout(10000).get();
		String title = doc.select("h2").html();
		txtWriter.write("【课程】：" + title + "\r\n\r\n");

		String author = doc.select("span.tit a").html();
		txtWriter.write("【讲师】：" + author + "\r\n");

		String time = ((Element) doc.select(".static-item").get(2))
				.select("span.meta-value").text();
		txtWriter.write("【时长】：" + time + "\r\n");

		String hard = ((Element) doc.select(".static-item").get(1))
				.select("span.meta-value").text();
		txtWriter.write("【难度】：" + hard + "\r\n\r\n\r\n");

		String intruc = doc.select(".auto-wrap").html();
		txtWriter.write("【课程介绍】：\r\n" + intruc + "\r\n\r\n\r\n");

		String know = doc.select(".course-info-tip .first dd").html();
		txtWriter.write("【课程须知】：\r\n" + know + "\r\n\r\n\r\n");

		String what = doc.select(".course-info-tip dd").last().html();
		txtWriter.write("【老师告诉你能学到什么？】\r\n" + what + "\r\n\r\n\r\n");

		txtWriter.write("【课程提纲】：\r\n\r\n");
		Elements chapters = doc.select(".chapter h3 strong");
		for (Element chapter : chapters) {
			String chaptername = ((TextNode) chapter.textNodes().get(1)).text()
					.trim();
			txtWriter.write(chaptername + "\r\n");
			String chapterdesc = chapter.select(".chapter-content").text()
					.trim();
			txtWriter.write(chapterdesc + "\r\n\r\n");
		}

		txtWriter.close();
	}
}