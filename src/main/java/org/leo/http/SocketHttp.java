package org.leo.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class SocketHttp {

	/**
	 * 
	 * 
	 * @param args
	 * 
	 * @author leo.li Modify Time Dec 19, 2013 5:30:05 PM
	 */
	public static void main(String[] args) {
		httpGet();
	}

	public static void httpGet() {
		try {
			// URL u=new
			// URL("http://news.baidu.com/n?cmd=7&loc=4075&name=yantai&tn=rss");
			URL u = new URL("http://news.csdn.net/rss_news.html");
			String host = u.getHost();
			String path = u.getPath();
			String param = u.getQuery();
			
			System.out.println("host----" + host);
			System.out.println("path----" + path);
			System.out.println("param----" + param);

			try {
				Socket s = new Socket(host, 80);
				OutputStream os = s.getOutputStream();

				PrintWriter osOut = new PrintWriter(os, true);
				BufferedReader osIn = new BufferedReader(new InputStreamReader(
						s.getInputStream(), Charset.forName("UTF-8")));

				// add request param
				osOut.println("GET " + path + "?" + param + " HTTP/1.1");
				osOut.println("Host: " + host + "");
				osOut.println("Accept-Language: en-us,en;q=0.5");
				osOut.println("Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
				osOut.println("Accept-Encoding: gzip,deflate");
				osOut.println("Connection: Close");
				osOut.println();

				// get response
				StringBuilder sb = new StringBuilder();
				String line = osIn.readLine();
				while (line != null) {
					sb.append(line + "\n");
					line = osIn.readLine();
				}
				s.close();
				System.out.println(sb.toString());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
