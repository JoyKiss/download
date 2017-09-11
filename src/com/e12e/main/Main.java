package com.e12e.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.e12e.service.DownloadFile;
import com.e12e.service.GetAttachFile;
import com.e12e.service.GetInfo;
import com.e12e.service.GetInput;
import com.e12e.service.GetList;
import com.e12e.utils.FileUtils;

public class Main {
	public static void main(String[] args) throws JSONException {
		Document doc = null;
		while (true) {
			int curruntCount = 0;
			boolean flag = true;
			int classNo = GetInput.getInputClassNo();
			try {
				doc = Jsoup.connect("http://www.imooc.com/learn/" + classNo)
						.get();
			} catch (IOException e) {
				System.out.println("获取课程信息时网络异常！可以稍后重试~\n");
				continue;
			}

			String title = doc.getElementsByTag("h2").html();
			title = title.replaceAll("[\\\\/:\\*\\?\"<>\\|]", "#");
			String savePath = "./download/" + title + "/";
			File file = new File(savePath);

			Elements videos = doc.select("ul.video a");
			if ((title.equals("")) && (videos.size() == 0)) {
				System.out.println("抱歉，没有该课程！\n");
			} else {
				int count = 0;
				for (Element video : videos) {
					String[] videoNos = video.attr("href").split("/");

					if (videoNos[1].equals("video")) {
						count++;
					}
				}
				System.out.print("\n要下载的课程标题为【" + title + "】，");
				System.out.println("共 " + videos.size() + " 节课程，其中视频课程有 "
						+ count + " 节\n");

				int videoDef = GetInput.getInputVideoDef();

				System.out.println("\n正在下载，请耐心等待…\n");

				for (Element video : videos) {
					curruntCount++;
					String[] videoNos = video.attr("href").split("/");
					try {
						GetAttachFile.doGetFile(videoNos[2], title);
					} catch (IOException e) {
						System.out.println("下载课程资料附件时出现异常！\n");
					}

					if (flag) {
						file.mkdirs();
						try {
							GetInfo.doGetInfo(classNo, title);

							System.out.println("课程信息 course_info.txt 生成成功！");
						} catch (Exception e2) {
							e2.printStackTrace();
							System.out.println("生成course_info.txt时出现异常！");
						}

						try {
							GetList.doGetList(videos, savePath);
							System.out.println("课程列表 course_list.html 生成成功！");
						} catch (Exception e1) {
							System.out.println("生成course_list.html时出现异常！");
						}
						flag = false;
					}

					if (videoNos[1].equals("video")) {
						String videoName = ((TextNode) video.textNodes().get(1))
								.text()
								.substring(0,
										((TextNode) video.textNodes().get(1))
												.text().length() - 8)
								.trim();
						videoName = videoName
								.replaceAll("[\\\\/:\\*\\?\"<>\\|]", "#");
						String videoNo = videoNos[2];
						Document jsonDoc = null;
						try {
							jsonDoc = Jsoup
									.connect(
											"http://www.imooc.com/course/ajaxmediainfo/?mid="
													+ videoNo + "&mode=flash")
									.timeout(10000).get();
						} catch (IOException e) {

							System.out.println("【" + curruntCount + "】"
									+ videoName + "\t网络异常，地址获取失败！");
							continue;
						}

						String jsonData = jsonDoc.text();
						JSONObject jsonObject = new JSONObject(jsonData);
						if ("false".equals(jsonObject.optJSONObject("data")
								.optJSONObject("result").get("mpath")
								.toString())) {
							List<String> list = new ArrayList<String>();
							list.add("http://www.imooc.com/learn/" + classNo
									+ "   课程名称:" + title + " 章节名:" + videoName);
							FileUtils.add2File("补档", list);
							continue;
						}
						JSONArray mpath = jsonObject.optJSONObject("data")
								.optJSONObject("result").optJSONArray("mpath");
						String downloadPath = mpath.getString(videoDef).trim();
						try {
							DownloadFile.downLoadFromUrl(downloadPath,
									videoName + ".mp4", savePath);
							System.out.println("【" + curruntCount + "】"
									+ videoName + " \t下载成功！");
						} catch (IOException e) {
							System.out.println("【" + curruntCount + "】：\t"
									+ videoName + " \t网络异常，下载失败！");
						}
					}
				}

				System.out.println("\n【" + title + "】课程的下载任务已完成！！！\n已下载到该程序所在目"
						+ "录download文件夹下。\n慕课网视频批量下载工具 v1.6  By Coande"
						+ "\n----------------------------------------------------------\n");
			}
		}
	}
}