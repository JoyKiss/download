package com.e12e.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class GetOtherVideo {
	public static String getVideoUrl(String videoNo) throws IOException {
		String url = "http://m.imooc.com/video/" + videoNo;
		String pattern = "http://.*?mp4";

		return login(url, pattern);
	}

	public static String login(String url, String pattern) throws IOException {
		String result = "";
		Map<String, String> loginPageCookies = new HashMap<String, String>();
		loginPageCookies.put("imooc_uuid",
				"6639a11e-bef1-42df-8a4f-6f5026a6f6af");
		loginPageCookies.put("imooc_isnew_ct", "1484534056");
		loginPageCookies.put("PHPSESSID", "ggq99epbm4j74h7ft4k6dbnjl6");
		loginPageCookies.put("loginstate", "1");
		loginPageCookies.put("apsid",
				"UwNGZkY2Y1ODdjZjcxZGJkODljZTYzNDRhZDQxNjUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANDQ1MDIwNQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAyMzIxMjAyODg2QHFxLmNvbQAAAAAAAAAAAAAAAAAAADJiZjZjZjE4ZWExNTI3ZGVjZWI5YmNkOTUyOGY4OGZixiMUWcYjFFk%3DMD");
		loginPageCookies.put("last_login_username", "2321202886%40qq.com");
		loginPageCookies.put("IMCDNS", "0");
		loginPageCookies.put("Hm_lvt_f0cfcccd7b1393990c78efdeebff3968",
				"1493190931,1493954173,1494485779,1494487718");
		loginPageCookies.put("Hm_lpvt_f0cfcccd7b1393990c78efdeebff3968",
				"1494492102");
		loginPageCookies.put("imooc_isnew", "2");
		loginPageCookies.put("cvde", "591412a584252-173");

		/**
		* 第二次请求，post表单数据，以及cookie信息
		* 
		* **/
		Connection con2 = Jsoup.connect(url);
		con2.header("User-Agent",
				"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Mobile Safari/537.36");
		// 设置cookie和post上面的map数据
		Response login = con2.ignoreContentType(true).method(Method.POST)
				.cookies(loginPageCookies).execute();

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(login.body());
		if (m.find()) {
			result = m.group();
		}
		return result;
	}
}
