package org.leo.http.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Test;

public class HttpClientsTest {

	@Test
	public void test() throws Exception{
		
		String jksPath = "D:/temp/cer/leotest.jks";
		char[] jksPassword = "123456".toCharArray();
		
		System.out.println(KeyStore.getDefaultType());
		KeyStore jks = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream instream = new FileInputStream(new File(jksPath));
		try {
			jks.load(instream, jksPassword);
		} finally {
			instream.close();
		}
		
		SSLContextBuilder scb = SSLContexts.custom();
		scb.useSSL();
		//scb.loadKeyMaterial(jks, jksPassword);
		scb.loadTrustMaterial(jks);
		/**
		scb.loadTrustMaterial(jks, new TrustStrategy(){
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException{
				return true;				
			}
		});
		*/
		
		SSLContext sslcontext = scb.build();
		sslcontext = SSLUtils.getMySSLContext("C001");	

		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslcontext,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		//httpClientBuilder.setSSLSocketFactory(sslSocketFactory);//优先以SSLSocketFactory为准
		//httpClientBuilder.setSslcontext(SSLUtils.getMySSLContext("C002"));//当SSLSocketFactory为空再考虑Sslcontext
		//httpClientBuilder.setMaxConnTotal(10000);
		//httpClientBuilder.setMaxConnPerRoute(100);
		
		final PoolingHttpClientConnectionManager poolingmgr = new PoolingHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslSocketFactory)
                    .build());
		poolingmgr.setMaxTotal(1000);
		poolingmgr.setDefaultMaxPerRoute(100);
		//poolingmgr.setMaxPerRoute(new HttpRoute(new HttpHost(hostname, port)), 10);
		httpClientBuilder.setConnectionManager(poolingmgr);
		
		CloseableHttpClient httpclient = httpClientBuilder.build();
		
		HttpGet httpPost = new HttpGet("https://www.google.com.hk/");

		System.out.println("executing request" + httpPost.getRequestLine());

		HttpsURLConnection.setDefaultSSLSocketFactory(SSLUtils.getMySSLSocketFactory("T02"));	
		SSLUtils.repeatInitSSLContext("T0001");
		
		CloseableHttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = getEntity(response);
		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());
		if (entity != null) {
			System.out.println("Response content length: "
					+ entity.getContentLength());
		}

		//EntityUtils.consume(entity);
		//String responseText = EntityUtils.toString(entity, "utf-8");
		//System.out.println(responseText);
	}
	
	private HttpEntity getEntity(HttpResponse response) {
		HttpEntity entity = response.getEntity();
		Header header = entity.getContentEncoding();
		if (header != null) {
			for (HeaderElement element : header.getElements()) {
				if (element.getName().toLowerCase().indexOf("gzip") != -1) {
					entity = new GzipDecompressingEntity(entity);
					break;
				}
			}
		}
		return entity;
	}
}
