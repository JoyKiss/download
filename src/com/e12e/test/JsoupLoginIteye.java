package com.e12e.test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * 使用Jsoup模拟登陆Iteye
 * 
 * 
 * 大体思路如下:
 * 
 * 第一次请求登陆页面，获取页面信息，包含表单信息，和cookie（这个很重要），拿不到，会模拟登陆不上
 * 
 * 
 * 第二次登陆，设置用户名，密码，把第一次的cooking，放进去，即可
 * 
 * 怎么确定是否登陆成功？
 * 
 * 登陆后，打印页面，会看见欢迎xxx，即可证明
 * 
 * 
 * @date 2014年6月27日
 * @author qindongliang
 * 
 * 
 * **/
public class JsoupLoginIteye {

	public static void main(String[] args) throws Exception {

		JsoupLoginIteye jli = new JsoupLoginIteye();
		jli.login("2321202886@qq.com", "958685878jayxk");// 输入Iteye的用户名，和密码

	}

	/**
	 * 模拟登陆Iteye
	 * 
	 * @param userName 用户名
	 * @param pwd 密码
	 * 
	 * **/
	public void login(String userName, String pwd) throws Exception {
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
		// // 第一次请求
		// Connection con =
		// Jsoup.connect("http://www.imooc.com/user/newlogin");// 获取连接
		// con.header("User-Agent",
		// "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101
		// Firefox/29.0");// 配置模拟浏览器
		// Response rs = con.execute();// 获取响应
		// String form = "<form id=\"signup-form\" autocomplete=\"off\">\n <p
		// class=\"rlf-tip-globle color-red\" id=\"signin-globle-error\"></p>\n
		// <div class=\"rlf-group pr\">\n <input type=\"text\" value=\"\"
		// maxlength=\"37\" name=\"email\"
		// data-validate=\"require-mobile-phone\" autocomplete=\"off\"
		// class=\"xa-emailOrPhone ipt ipt-email js-own-name\"
		// placeholder=\"请输入登录邮箱/手机号\"/>\n <p class=\"rlf-tip-wrap errorHint
		// color-red\" data-error-hint=\"请输入正确的邮箱或手机号\"></p>\n </div>\n <div
		// class=\"rlf-group pr\">\n <input type=\"password\" name=\"password\"
		// data-validate=\"require-password\" class=\"ipt ipt-pwd
		// js-loginPassword js-pass-pwd\" placeholder=\"6-16位密码，区分大小写，不能用空格\"
		// maxlength=\"16\" autocomplete=\"off\"/>\n <p class=\"rlf-tip-wrap
		// errorHint color-red \"
		// data-error-hint=\"请输入6-16位密码，区分大小写，不能使用空格！\"></p>\n </div>\n \n <div
		// class=\"rlf-group clearfix form-control js-verify-row\">\n <input
		// type=\"text\" name=\"verify\" class=\"ipt ipt-verify l\"
		// data-validate=\"require-string\" data-callback=\"checkverity\"
		// maxlength=\"4\" data-minLength=\"4\" placeholder=\"请输入验证码\">\n <a
		// href=\"javascript:void(0)\" hidefocus=\"true\"
		// class=\"verify-img-wrap js-verify-refresh\"></a>\n <a
		// href=\"javascript:void(0)\" hidefocus=\"true\" class=\"icon-refresh
		// js-verify-refresh\"></a>\n <p class=\"rlf-tip-wrap errorHint
		// color-red\" data-error-hint=\"请输入正确验证码\"></p>\n </div>\n <div
		// class=\"rlf-group rlf-appendix form-control clearfix\">\n <label
		// for=\"auto-signin\" class=\"rlf-autoin l\" hidefocus=\"true\"><input
		// type=\"checkbox\" checked=\"checked\" class=\"auto-cbx\"
		// id=\"auto-signin\">下次自动登录</label>\n <a href=\"/user/newforgot\"
		// class=\"rlf-forget r\" target=\"_blank\" hidefocus=\"true\">忘记密码
		// </a>\n </div>\n <div class=\"rlf-group clearfix\">\n <input
		// type=\"button\" value=\"登录\" hidefocus=\"true\" class=\"btn-red
		// btn-full xa-login\"/>\n </div>\n </form>\n";
		// Document d1 = Jsoup.parse(form);// 转换为Dom树
		// List<Element> et = d1.select("#signup-form");//
		// 获取form表单，可以通过查看页面源码代码得知
		//
		// // 获取，cooking和表单属性，下面map存放post时的数据
		// Map<String, String> datas = new HashMap<>();
		// for (Element e : et.get(0).getAllElements()) {
		// if (e.attr("name").equals("email")) {
		// e.attr("value", userName);// 设置用户名
		// }
		//
		// if (e.attr("name").equals("password")) {
		// e.attr("value", pwd); // 设置用户密码
		// }
		//
		// if (e.attr("name").length() > 0) {// 排除空值表单属性
		// datas.put(e.attr("name"), e.attr("value"));
		// }
		// }

		/**
		* 第二次请求，post表单数据，以及cookie信息
		* 
		* **/
		Connection con2 = Jsoup.connect("http://m.imooc.com/video/782");
		con2.header("User-Agent",
				"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Mobile Safari/537.36");
		// 设置cookie和post上面的map数据
		Response login = con2.ignoreContentType(true).method(Method.POST)
				.cookies(loginPageCookies).execute();
		String pattern = "http://v1.mukewang.com/.*?mp4";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(login.body());
		if (m.find()) {
			System.out.println("Found value: " + m.group());
		}
		// System.out.println(login.body().indexOf(""));
		// Document doc = Jsoup.connect("http://m.imooc.com/video/782").get();
		// System.out.println(doc.toString());

	}

}