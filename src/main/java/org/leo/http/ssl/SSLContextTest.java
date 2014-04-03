package org.leo.http.ssl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.junit.Test;
import org.leo.util.SSLUtils;

public class SSLContextTest {
	
	//@Test
	public void testHttp() throws Exception{
		URL url = new URL("https://www.google.com.hk/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//conn.setDoInput(true);
		//conn.setDoOutput(true);
		
		conn.connect();
		InputStream is = conn.getInputStream();
		int length = conn.getContentLength();
		//若能正常打开，ContentLength不为-1
		printResponseText(is, "utf-8");
		
		System.out.println("ResponseCode is " + conn.getResponseCode());
		System.out.println("ContentLength is " + length);
		System.out.println("ContentType is " + conn.getContentType());
		is.close();
		// 断开连接
		conn.disconnect();
	}

	@Test
	public void testHttps() throws Exception{
		SSLSocketFactory sf = SSLUtils.getSSLSocketFactory("12345678", null, "D:/temp/certest/leotest.jks");
		HttpsURLConnection.setDefaultSSLSocketFactory(sf);//以最近一次执行setDefaultSSLSocketFactory有关
		HttpsURLConnection.setDefaultSSLSocketFactory(SSLUtils.getMySSLSocketFactory("T02"));
		HttpsURLConnection.setDefaultSSLSocketFactory(SSLUtils.getMySSLSocketFactory("T03"));
		SSLUtils.repeatInitSSLContext("R01");
		
		URL url = new URL("https://www.google.com.hk/");
		//HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("User-Agent", "Mozilla/MSIE");
		//conn.setDoInput(true);
		//conn.setDoOutput(true);		
		//conn.setSSLSocketFactory(sf);
		
		conn.connect();
		InputStream is = conn.getInputStream();
		int length = conn.getContentLength();
		//若能正常打开，ContentLength不为-1
		//printResponseText(is, "Big5");
		
		System.out.println("ResponseCode is " + conn.getResponseCode());
		System.out.println("ContentLength is " + length);
		System.out.println("ContentType is " + conn.getContentType());
		System.out.println("ContentEncoding is " + conn.getContentEncoding());
		is.close();
		// 断开连接
		conn.disconnect();
	}
	
	
	private void printResponseText(InputStream is, String charset) throws Exception{
		
		BufferedReader responseReader = new BufferedReader(new InputStreamReader(is, charset));
        StringBuilder responseText = new StringBuilder();
		String line = responseReader.readLine();
		while (line != null) {
			//URLDecoder.decode(line,charset)
			responseText.append(line + "\n");
			line = responseReader.readLine();
		}
		responseReader.close();
		System.out.println(responseText.toString());
		
		/**
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        String lines;
        while ((lines = reader.readLine()) != null) {
            System.out.println(lines);
        }
        reader.close();
        */
	}
	
}
