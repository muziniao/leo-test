package org.leo.http.ssl;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class SSLUtils {

	public static void repeatInitSSLContext(String trustManagerName) throws Exception{
		
		TrustManager[] tmArr = {
				new MyX509TrustManager(trustManagerName)
		};
		SSLContext ctx = SSLContext.getInstance("SSL");
	    ctx.init(null, tmArr, new SecureRandom());
	}
	
	public static SSLSocketFactory getMySSLSocketFactory(String trustManagerName) throws Exception{		
	    SSLContext ctx = getMySSLContext(trustManagerName);
	    SSLSocketFactory sf = ctx.getSocketFactory();
		return sf;
	}
	
	public static SSLContext getMySSLContext(String trustManagerName) throws Exception{		
	    TrustManager[] tmArr = {
	    	new MyX509TrustManager(trustManagerName)
	    };
	    SSLContext ctx = SSLContext.getInstance("SSL");
	    ctx.init(null, tmArr, new SecureRandom());
		return ctx;
	}
	
	public static SSLSocketFactory getSSLSocketFactory(String password, String keystorepath, String trustKeystorepath)throws Exception{
		KeyManager[] keyManagers = null;
		if(keystorepath != null){
			//初始化密钥库
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
			KeyStore keystore = getKeyStore(keystorepath,password);
			keyManagerFactory.init(keystore,password.toCharArray());
			keyManagers = keyManagerFactory.getKeyManagers();
		}
		
		TrustManager[] trustManagers = null;
		if(trustKeystorepath != null){
			//初始化信任库
			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
			KeyStore trustkeystore = getKeyStore(trustKeystorepath, password);
			trustManagerFactory.init(trustkeystore);
			trustManagers = trustManagerFactory.getTrustManagers();
		}
		
		//初始化ssL上下文
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(keyManagers, trustManagers, null);
		SSLSocketFactory sf = ctx.getSocketFactory();

		return sf;
	}
			
	public static KeyStore getKeyStore(String keystorePath, String password) throws Exception{
		//获得密钥库文件输入流
		FileInputStream is = new FileInputStream(keystorePath);
		//实例化密钥库
		KeyStore ks = KeyStore.getInstance("JKS");
		//加载密钥库
		ks.load(is,password.toCharArray());
		//关闭流
		is.close();
		return ks;
	}
	

	public static class MyX509TrustManager implements X509TrustManager{
		
		private String name = "default";
		
		public MyX509TrustManager(){
			
		}
		
		public MyX509TrustManager(String name){
			this.name = name;
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			System.out.println("checkClientTrusted--------------------------" + name);			
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			System.out.println("checkServerTrusted--------------------------" + name);	
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			System.out.println("getAcceptedIssuers--------------------------" + name);	
			return null;
		}
		
	}
}
