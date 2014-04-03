package org.leo.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class CertificateUtils {

	public static void storeToJks(String host, int port, String keystorePath,String storepass) throws Exception{
		X509Certificate[] chain = gainCertificateChain(host, port);
		String alias = host;
		KeyStoreUtils.store(chain, keystorePath, alias, storepass);
	}
	
	public static void writeToFile(String host, int port, String saveDir, boolean isBase64) throws Exception{
		X509Certificate[] chain = gainCertificateChain(host, port);
		int len = chain.length;
		for(int i = 0 ; i < len ; i++){
			String filePath = saveDir + host + "." + StringUtils.leftPad(String.valueOf(i), 2, "0");
			if(isBase64){
				filePath += ".base64";
            }
			filePath += ".cer";
            writeToFile(chain[i], filePath, isBase64);
		}
	}
	
	public static void writeToFile(X509Certificate x509Cert, String filePath, boolean isBase64) throws Exception{
		FileOutputStream out = null;
		try {                        
            out = new FileOutputStream(filePath);
            if(isBase64){
            	StringBuilder cert = new StringBuilder();
            	cert.append("-----BEGIN CERTIFICATE-----\n");
            	cert.append(new String(Base64.encodeBase64Chunked(x509Cert.getEncoded()), "utf-8"));
            	cert.append("-----END CERTIFICATE-----\n");
            	out.write(cert.toString().getBytes("utf-8"));
            }else{
            	out.write(x509Cert.getEncoded());
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            try {
                if(out != null) {
                    out.close();
                }
                out = null;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	public static X509Certificate[] gainCertificateChain(String host, int port) throws Exception{
		SavingTrustManager tm = new SavingTrustManager();
		SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, new TrustManager[] {tm}, null);
        
        // 尝试使用socket对目标主机进行通信
        SSLSocketFactory factory = context.getSocketFactory();        
        SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
        socket.setSoTimeout(1000);
        try {
            // 如果直接通信没问题的话,就不会报错,也不必获取证书
            // 如果报错的话,很有可能没有证书
            socket.startHandshake();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(socket != null) {
                try {
                    socket.close();
                } catch(Exception e) {}        
                socket = null;
            }
        }

        X509Certificate[] chain = tm.getChain();
        return chain;
	}
	
	public static X509Certificate readFromBase64String(String base64String) throws Exception{
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");				
		ByteArrayInputStream inStream = new ByteArrayInputStream(base64String.getBytes("UTF-8"));  
        X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(inStream);
        return certificate;  
	}

	public static X509Certificate getCertificate(String certPath) throws Exception {  
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");  
        FileInputStream inputStream = new FileInputStream(certPath);  
        X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate (inputStream);
        return certificate;  
    }  
	
	private static class SavingTrustManager implements X509TrustManager {
        private X509Certificate[] chain;

        public SavingTrustManager() {
        }
                
        public X509Certificate[] getChain() {
            return chain;
        }
        
        public X509Certificate[] getAcceptedIssuers() {
            throw new UnsupportedOperationException();
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            throw new UnsupportedOperationException();
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            this.chain = chain;
        }
    }
}
