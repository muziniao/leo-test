package org.leo.http.ssl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SocketHttps {

	/**
	 * 
	 * 
	 * @param args
	 * 
	 * @author leo.li Modify Time Dec 19, 2013 5:30:05 PM
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		testIcbc();
	}

	public static void testIcbc() throws Exception {
		String charset = "GBK";
		String merReqData = "<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in>"
				+ "<orderNum>1131219134358117</orderNum>"
				+ "<tranDate>20131219</tranDate>"
				+ "<ShopCode>1001EC23819829</ShopCode>"
				+ "<ShopAccount>1001083319004606417</ShopAccount></in></ICBCAPI>";		
		Map<String, String> params = new HashMap<String, String>();
		params.put("APIName", "EAPI");
		params.put("APIVersion", "001.001.002.001");
		params.put("MerReqData", merReqData);
		StringBuilder requestBody = new StringBuilder();
		requestBody.append(URLEncoder.encode("APIName", charset)); 
		requestBody.append("="); 
		requestBody.append(URLEncoder.encode("EAPI", charset)); 
		requestBody.append("&"); 
		requestBody.append(URLEncoder.encode("APIVersion", charset)); 
		requestBody.append("="); 
		requestBody.append(URLEncoder.encode("001.001.002.001", charset)); 
		requestBody.append("&"); 
		requestBody.append(URLEncoder.encode("MerReqData", charset)); 
		requestBody.append("="); 
		requestBody.append(URLEncoder.encode(merReqData, charset));
		
		URL url = new URL("https://corporbank.icbc.com.cn/servlet/ICBCINBSEBusinessServlet");
		String host = url.getHost();
		String path = url.getPath();
		String param = url.getQuery();
		
		System.out.println("host----" + host);
		System.out.println("path----" + path);
		System.out.println("param----" + param);
		
        int port = 443;
        String jksPath = "D:/temp/cer/icbc-remote.jks";
		char[] jksPassword = "12345678".toCharArray();
        
		SSLContext ctx = SSLContext.getInstance("SSL");
		
		System.out.println(KeyManagerFactory.getDefaultAlgorithm());
		System.out.println(TrustManagerFactory.getDefaultAlgorithm());
		
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());//SunX509
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());//PKIX SunX509
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        KeyStore tks = KeyStore.getInstance("JKS");
        
        ks.load(new FileInputStream(jksPath), jksPassword);
        tks.load(new FileInputStream(jksPath), jksPassword);
        kmf.init(ks, jksPassword);
        tmf.init(tks);
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
        SSLSocketFactory ssf = ctx.getSocketFactory();
        
        SSLSocket socket = (SSLSocket) ssf.createSocket(host, port);
        socket.startHandshake();
        
        BufferedWriter httpPostWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charset)); 
        // 发送数据头 
        httpPostWriter.write("POST " + path + " HTTP/1.0\r\n"); 
        httpPostWriter.write("Host: " + host + "\r\n"); 
        httpPostWriter.write("Content-Length: " + requestBody.length() + "\r\n"); 
        httpPostWriter.write("Content-Type: application/x-www-form-urlencoded\r\n"); 
        httpPostWriter.write("\r\n"); // 以空行作为分割 
        // 发送数据 
        httpPostWriter.write(requestBody.toString()); 
        httpPostWriter.flush(); 
        // 创建web服务器响应的数据流 
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), charset));
        StringBuilder responseText = new StringBuilder();
		String line = responseReader.readLine();
		while (line != null) {
			responseText.append(URLDecoder.decode(line,charset) + "\n");
			line = responseReader.readLine();
		}
		responseReader.close();
		System.out.println(responseText.toString());
	}
}
