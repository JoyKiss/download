package com.e12e.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFile {
	public static void main(String[] args) throws IOException {
		downLoadFromUrl(
				"https://d11.baidupcs.com/file/16567d6afc5d0e00901c04098cf366db",
				"666.zip", "./download");
	}

	public static void downLoadFromUrl(String urlStr, String fileName,
			String savePath) throws IOException {
		File saveDir = new File(savePath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		File file = new File(saveDir + File.separator + fileName);

		if (!file.exists() || file.length() == 0) {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(10000);

			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");

			InputStream inputStream = conn.getInputStream();

			FileOutputStream fos = new FileOutputStream(file);
			byte[] temp = new byte[1024];
			int len;
			while ((len = inputStream.read(temp)) != -1) {
				fos.write(temp, 0, len);
			}

			fos.close();
			inputStream.close();
		}
	}
}