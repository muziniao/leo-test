package org.leo.ssl;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.cert.Certificate;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.junit.Test;

public class SSLSocketTest2 {

	@Test
	public void getCer() throws UnknownHostException, IOException {
		URL url = new URL("https://vip.icbc.com.cn/icbc/perbank/index.jsp");
		String host = url.getHost();  
		int port = 443;
		System.out.println(url.toString());
		System.out.println("host:" + host + ":" + port);


		SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket socket = (SSLSocket) ssf.createSocket(host, port);
		socket.startHandshake();
		SSLSession sslSession = socket.getSession();
		socket.close();
		
		Certificate[] cerArr = sslSession.getPeerCertificates();
		System.out.println(cerArr.length);
	}
}
