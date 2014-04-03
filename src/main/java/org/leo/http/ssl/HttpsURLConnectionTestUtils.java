package org.leo.http.ssl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.leo.util.SSLUtils;

public class HttpsURLConnectionTestUtils {

	public static void get(String storepass, String keystorepath, String trustKeystorepath, String httpsUrl, boolean isPrint, String charset) throws Exception{

		SSLSocketFactory sf = SSLUtils.getSSLSocketFactory(storepass, keystorepath, trustKeystorepath);
		URL url = new URL(httpsUrl);
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		conn.setRequestProperty("Charset", charset);
		conn.setRequestProperty("User-Agent", "Mozilla/MSIE");
		//conn.setDoInput(true);
		//conn.setDoOutput(true);		
		conn.setSSLSocketFactory(sf);
		
		conn.connect();
		InputStream is = conn.getInputStream();
		int length = conn.getContentLength();
		//若能正常打开，ContentLength不为-1
		if(isPrint){
			printResponseText(is, charset);
		}
		
		System.out.println("ResponseCode is " + conn.getResponseCode());
		System.out.println("ContentLength is " + length);
		System.out.println("ContentType is " + conn.getContentType());
		System.out.println("ContentEncoding is " + conn.getContentEncoding());
		is.close();
		// 断开连接
		conn.disconnect();
	}
	
	private static void printResponseText(InputStream is, String charset) throws Exception{
		
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
	}
}
