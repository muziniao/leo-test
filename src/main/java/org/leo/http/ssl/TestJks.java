package org.leo.http.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;

public class TestJks {
	
	public static void main(String[] args) throws Exception {
		testJks();
	}
	
	public static void testJks() throws Exception{
		String jksPath = "D:/temp/cer/wx-20150324.jks";
		char[] jksPassword = "gewarawx0324".toCharArray();
		
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
	}
}
