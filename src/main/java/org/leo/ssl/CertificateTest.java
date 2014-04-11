package org.leo.ssl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.DEROctetString;
import org.junit.Test;
import org.leo.http.ssl.HttpsURLConnectionTestUtils;
import org.leo.util.CertificateUtils;

import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.PolicyConstraintsExtension;
import sun.security.x509.X509CertImpl;

public class CertificateTest {
	
	//@Test
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
	
	@Test
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
		//System.out.println(base64String);
		 
		X509Certificate cer = CertificateUtils.readFromBase64String(base64String);
		printCertificate(cer);		
	}
	
	public void printCertificate(X509Certificate cer) throws CertificateException, IOException{
		//System.out.println(cer.toString());
		System.out.println("-----end print cert---1------------------------------------------------------------------------");
		X509CertImpl impl = (X509CertImpl)cer;
		int extnum = 0;
        if (cer.getCriticalExtensionOIDs() != null) {
             for (String extOID : cer.getCriticalExtensionOIDs()) {
                 if (extnum == 0) {
                     System.out.println("Extensions: ");
                 }
                 System.out.println("#"+(++extnum)+": "+ impl.getExtension(new ObjectIdentifier(extOID)));
             }
        }
        System.out.println("-----end print cert---2------------------------------------------------------------------------");
        
		
		System.out.println("Type is \t\t:" + cer.getType());
		System.out.println("Version is \t\t:" + cer.getVersion());
		System.out.println("SerialNumber is \t:" + Hex.encodeHexString(cer.getSerialNumber().toByteArray()));
		System.out.println("SigAlgName is \t\t:" + cer.getSigAlgName());
		System.out.println("IssuerDN is \t\t:" + cer.getIssuerDN().getName());
		//System.out.println("IssuerX500Principal is \t:" + cer.getIssuerX500Principal());
		System.out.println("NotBefore is \t\t:" + DateFormatUtils.format(cer.getNotBefore(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println("NotAfter is \t\t:" + DateFormatUtils.format(cer.getNotAfter(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println("SubjectDN is \t\t:" + cer.getSubjectDN().getName());
		
		System.out.println("PublicKey is \t\t:" + Hex.encodeHexString(cer.getPublicKey().getEncoded()));
		System.out.println("ExtendedKeyUsage is \t:" + cer.getExtendedKeyUsage());
		System.out.println("SubjectAlternativeNames is \t:" + cer.getSubjectAlternativeNames());
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("CriticalExtensionOIDs is \t:" + cer.getCriticalExtensionOIDs());
		
		/**
		byte[] policyArry = cer.getExtensionValue("2.5.29.32");
		CertificatePoliciesExtension cpe = new CertificatePoliciesExtension(false,policyArry);
		System.out.println("CertificatePolicies is \t:" + cpe.toString());
		*/
		System.out.println("CertificatePolicies is \t\t:" + getExtensionValue(cer, "2.5.29.32"));
		System.out.println("CertificatePolicies is \t\t:" + impl.getExtension(new ObjectIdentifier("2.5.29.32")));
        		
		
		
		//getPolicyConstraintsExtension(cer);

		System.out.println("AuthorityKeyIdentifier is \t:" + new String(cer.getExtensionValue("2.5.29.35"), "utf-8"));
		System.out.println("AuthorityKeyIdentifier is \t:" + Hex.encodeHexString(cer.getExtensionValue("2.5.29.35")));
		
		System.out.println("KeyUsage is \t:" + cer.getExtensionValue("2.5.29.15"));
		System.out.println("PrivateKeyUsage is \t:" + cer.getExtensionValue("2.5.29.16"));
		
		
		
		
		System.out.println("Signature is \t:" + Hex.encodeHexString(cer.getSignature()));
		System.out.println("NonCriticalExtensionOIDs is \t:" + cer.getNonCriticalExtensionOIDs());
		
		System.out.println("SigAlgParams is \t:" + cer.getSigAlgParams());
		System.out.println("TBSCertificate is \t:" + Hex.encodeHexString(cer.getTBSCertificate()));

		
		
		
		System.out.println("SigAlgOID is \t\t:" + cer.getSigAlgOID());
		System.out.println("CriticalExtensionOIDs is \t:" + cer.getCriticalExtensionOIDs());

		cer.checkValidity();
	}
	
	public void getPolicyConstraintsExtension(X509Certificate cer) throws CertificateException, IOException{
		/**
		InputStream is = new ByteArrayInputStream(cer.getEncoded());
		X509CertImpl certImpl = new X509CertImpl(is);
		System.out.println("PolicyConstraints is \t:" + certImpl.getPolicyConstraintsExtension());
		*/
		
		 byte[] ext = cer.getExtensionValue("2.5.29.32");
		 System.out.println(Hex.encodeHexString(ext));
         if (ext == null) {
             return;
         }
         DerValue val = new DerValue(ext);
         byte[] data = val.getOctetString();

         PolicyConstraintsExtension policyConstraints =  new PolicyConstraintsExtension(Boolean.FALSE, data);
		 System.out.println("PolicyConstraints is \t:" + policyConstraints.getName());
		 System.out.println("PolicyConstraints is \t:" + policyConstraints.getElements());
		 System.out.println("PolicyConstraints is \t:" + policyConstraints);
	}
	
	private String getExtensionValue(X509Certificate X509Certificate, String oid) throws IOException{
	    String decoded = null;
	    byte[] extensionValue = X509Certificate.getExtensionValue(oid);

	    if (extensionValue != null){
	        ASN1Primitive derObject = toDERObject(extensionValue);
	        if (derObject instanceof DEROctetString) {
	            DEROctetString derOctetString = (DEROctetString) derObject;

	            derObject = toDERObject(derOctetString.getOctets());
	            if (derObject instanceof ASN1String) {
	                ASN1String s = (ASN1String)derObject;
	                decoded = s.getString();
	            }else if(derObject instanceof ASN1Sequence){
	            	decoded = derObject.toString();
	            }else{
	            	decoded = derObject.toString();
	            }

	        }
	    }
	    return decoded;
	}
	
	private ASN1Primitive toDERObject(byte[] data) throws IOException{
	    ByteArrayInputStream inStream = new ByteArrayInputStream(data);
	    ASN1InputStream asnInputStream = new ASN1InputStream(inStream);

	    return asnInputStream.readObject();
	}
	
}
