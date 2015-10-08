package org.leo.http.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpsTest {

	/**
	 * 
	 * @param args
	 * 
	 * @author leo.li Modify Time Dec 19, 2013 2:46:16 PM
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		testIcbc();
		
		/**
		URL url = new URL("http://www.baidu.com");

		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.getOutputStream();
		*/
		
		//testHttpPost();
	}
	
	public static void testHttpPost(){
		String url = "https://corporbank.docnet.com.cn/servlet/ICBCINBSEBusinessServlet";
		HttpPost httpPost = new HttpPost(url);
		System.out.println("" + httpPost.getMethod());
	}

	public static void testIcbc() throws Exception  {
		String charset = "GBK";		
		String merReqData = "<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in>"
				+ "<orderNum>Test20150407110859</orderNum>"
				+ "<tranDate>20150407</tranDate>"
				+ "<ShopCode>1001EC23820830</ShopCode>"
				+ "<ShopAccount>1001083319004606417</ShopAccount>" + "</in></ICBCAPI>";
		
		String url = "https://corporbank3.dccnet.com.cn/servlet/ICBCINBSEBusinessServlet";
		Map<String, String> params = new HashMap<String, String>();
		params.put("APIName", "EAPI");
		params.put("APIVersion", "001.001.002.001");
		params.put("MerReqData", merReqData);
		
		String jksPath = "D:/temp/cer/icbc.test.wap0402.jks";
		char[] jksPassword = "12345678".toCharArray();
		
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
		scb.loadKeyMaterial(jks, jksPassword);
		scb.loadTrustMaterial(jks);
		/**
		scb.loadTrustMaterial(jks, new TrustStrategy(){
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException{
				return true;				
			}
		});
		*/
		
		SSLContext sslcontext = scb.build();
		/*
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);*/
		
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] {"TLSv1", "SSLv3"},
                null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();
		try {

			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //构建POST请求的表单参数 
            for(Map.Entry<String,String> entry : params.entrySet()){ 
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue())); 
            } 
            httpPost.setEntity(new UrlEncodedFormEntity(formParams,charset)); 

			System.out.println("executing request" + httpPost.getRequestLine());

			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = getEntity(response);
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: "
							+ entity.getContentLength());
				}

				String responseText = EntityUtils.toString(entity, charset);
				//EntityUtils.consume(entity);

				System.out.println(responseText);
				responseText = URLDecoder.decode(responseText,charset);
				System.out.println(responseText);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

	private static HttpEntity getEntity(HttpResponse response) {
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
