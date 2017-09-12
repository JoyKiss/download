package com.e12e.main;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.e12e.service.DownloadFile;
import com.e12e.utils.FileUtils;
import com.e12e.utils.ParttenUtils;
import com.e12e.utils.ThreadUtils;

public class DuowanPicDownLoad {
	public static void main(String[] args) throws IOException {
		// for (int i = 93020; i < 140000; i++) {
		// String url = "http://tu.duowan.com/gallery/" + i + ".html";
		// try {
		// System.out.println(i);
		// Document doc = Jsoup.connect(url).get();
		// } catch (Exception e) {
		// continue;
		// }
		//
		// System.out.println(url);
		// }
		// String videoName = videoName.replaceAll("[\\\\/:\\*\\?\"<>\\|]",
		// "#");
		List<String> lines = FileUtils.readFileByLines("E:\\", "今日囧图.txt");

		List list = ThreadUtils.listCut(lines, 300);

		ThreadUtils.createThread(list, DuowanPicDownLoad.class, "downPic");
		// for (String line : lines) {
		// line = line.replace("gallery", "scroll");
		// String no = ParttenUtils.getPartten("\\d*", line);
		// System.out.println(line);
		// run(line, "http://tu.duowan.com/", "E:\\down" + "\\" + no);
		// }
	}

	public void downPic(List<String> lines) {
		for (String line : lines) {
			line = line.replace("gallery", "scroll");
			String no = ParttenUtils.getPartten("\\d*", line);
			// System.out.println(line);
			run(line, "http://tu.duowan.com/", "E:\\down2" + "\\" + no);
		}
	}

	/**
	 * 根据多玩图片文件访问文件url过滤每一页的访问url
	 * 
	 * @param downUrl
	 *            目标主url
	 * @param baseUrl
	 *            网站的baseurl
	 * @param savePath
	 *            文件的保存路径
	 */
	public static void run(String downUrl, String baseUrl, String savePath) {
		Document doc;
		try {
			doc = Jsoup.connect(downUrl).timeout(1000000).get();

			Elements elePages = doc.select("#yw0");
			int index = 0;
			if (elePages != null && elePages.size() > 0) {
				Elements as = elePages.get(0).select("a");
				for (Element ele : as) {
					String url = baseUrl + ele.attr("href");
					index++;
					index = downByUrl(url, index, savePath);
				}
			} else {
				index = downByUrl(downUrl, index, savePath);
			}
		} catch (IOException e) {
			FileUtils.add2File("E:\\error3.txt", downUrl);
		}
	}

	/**
	 * 根据访问url过滤data-img标签的图片文件url,下载至指定文件路径下
	 * 
	 * @param url
	 *            网页链接
	 * @param index
	 *            文件保存序号(作为文件夹序号)
	 * @param savePath
	 *            目标文件保存路径
	 * @return 文件保存序列号
	 */
	public static int downByUrl(String url, int index, String savePath) {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(1000000).get();

			Elements eles = doc.select(".pic-box");
			for (Element ele : eles) {
				Elements elements = ele.select(".pic-box-item");
				String fileName = index + "_"
						+ ele.select(".comment").get(0).text().replaceAll("[\\\\/:\\*\\?\"<>\\|]", "#");
				String downLoadUrl = elements.get(0).attr("data-img");
				// System.out.println("-------------");
				// System.out.println(fileName);
				// System.out.println(downLoadUrl);
				long beginTime = new Date().getTime();
				String picFileName = fileName + getPic(downLoadUrl);
				DownloadFile.downLoadFromUrl(downLoadUrl, picFileName, savePath);
				long endTime = new Date().getTime();
				System.out.println("----------------------" + picFileName + "下载完成(" + (endTime - beginTime)
						+ "ms)----------------------");
				index++;
			}

		} catch (IOException e) {
			e.printStackTrace();
			FileUtils.add2File("E:\\error.txt", url);
			return index;
		}

		return index;
		// System.out.println(doc.toString());
	}

	/**
	 * 根据downurl获得文件的后缀名
	 * 
	 * @param imgp
	 *            down链接
	 * @return
	 */
	public static String getPic(String imgp) {
		String result = ".jpg";
		String reg = "(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(imgp);
		while (matcher.find()) {
			result = matcher.group(0);
		}

		return result;

	}

}
