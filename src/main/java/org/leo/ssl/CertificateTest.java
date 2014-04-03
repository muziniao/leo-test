package org.leo.ssl;

import java.io.File;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.leo.http.ssl.HttpsURLConnectionTestUtils;
import org.leo.util.CertificateUtils;

public class CertificateTest {
	
	@Test
	public void testWriteToFile() throws Exception{
		String saveDir = "D:\\temp\\certest\\";
		String host = "www.google.com.hk";
		int port = 443;
		CertificateUtils.writeToFile(host, port, saveDir, false);
	}
	
	//@Test
	public void testWriteToFile2() throws Exception{
		String saveDir = "D:\\temp\\certest\\";
		String host = "www.google.com.hk";
		int port = 443;
		CertificateUtils.writeToFile(host, port, saveDir, true);
	}
	
	
	//@Test
	public void testStoreToJksAndUseIt() throws Exception{
		String url = "https://www.google.com.hk/";
		String host = "www.google.com.hk";
		int port = 443;

		String keystorePath = "D:/temp/certest/google-test01.jks";
		String storepass = "12345678";

		CertificateUtils.storeToJks(host, port, keystorePath, storepass);
		
		HttpsURLConnectionTestUtils.get(storepass, null, keystorePath, url, true, "Big5");
	}
	
	//@Test
	public void testStoreToJks() throws Exception{
		String host = "www.google.com.hk";
		int port = 443;

		String keystorePath = "D:/temp/certest/google-test01.jks";
		String storepass = "12345678";
		CertificateUtils.storeToJks(host, port, keystorePath, storepass);
	}
	
	//@Test
	public void testJks() throws Exception{
		String url = "https://www.google.com.hk/";
		String keystorePath = "D:/temp/certest/google-base64.jks";
		String storepass = "12345678";
	
		HttpsURLConnectionTestUtils.get(storepass, null, keystorePath, url, true, "Big5");
	}
	
	//@Test
	public void testGetCertificate() throws Exception{
		String certPath = "D:/temp/certest/www.google.com.hk.00.cer";
		//String certPath = "D:/temp/certest/www.google.com.hk.00.base64.cer";
		X509Certificate cer = CertificateUtils.getCertificate(certPath);
		printCertificate(cer);		
	}
	
	//@Test
	public void testReadFromBase64String() throws Exception{
		String certPath = "D:/temp/certest/www.google.com.hk.00.base64.cer";
		String base64String = FileUtils.readFileToString(new File(certPath), "utf-8");
		System.out.println(base64String);
		 
		X509Certificate cer = CertificateUtils.readFromBase64String(base64String);
		printCertificate(cer);		
	}
	
	public void printCertificate(X509Certificate cer) throws CertificateExpiredException, CertificateNotYetValidException{
		System.out.println("NotBefore is " + DateFormatUtils.format(cer.getNotBefore(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println("NotAfter is " + DateFormatUtils.format(cer.getNotAfter(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println("SigAlgName is " + cer.getSigAlgName());
		System.out.println("dn is " + cer.getIssuerDN().getName());
		//System.out.println(cer);

		cer.checkValidity();
	}
	
}
