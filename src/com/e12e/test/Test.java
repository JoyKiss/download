package com.e12e.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
	public static void main(String[] args) {

		try {
			Connection con = Jsoup.connect("http://music.163.com/song/59879");
			Document doc = con.get();
			System.out.println(doc.html());
			Elements eles = doc.select("p.des.s-fc4 span").select("a");
			for (Element ele : eles) {
				System.out.println(ele.text() + ":" + ele.attr("href").replace("/artist?id=", ""));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
