package org.leo.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Java在线自动获取并安装证书工具类
 * 
 * @author leo.li
 * Modify Time 2014年4月3日 上午10:28:34
 */
public class CertManager {

    public static void main(String[] args) {
        try {
            trustCert("D:\\temp\\certest\\", "www.google.com.hk", 443, "12345678");//changeit
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param dir        证书所在路径
     * @param host        主机地址
     * @param port        端口
     * @param password    证书密码
     * @throws Exception
     */
    public static void trustCert(String dir, String host, int port, String password) throws Exception {
        // 如果证书颂给的名称与所通信的域名不一致的话，那么需要重写校验方法
        HostnameVerifier hv = new HostnameVerifier() {
            @Override
            public boolean verify(String urlHostName, SSLSession session) {
                return urlHostName.equals(session.getPeerHost());
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);

        // 信任管理器工厂
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        File file = new File(dir + host + ".jks");
        //File file = new File(dir + "leotest.jks");
        file = makeSureFile(file);
        KeyStore ks = getKeyStore(file, password);
        tmf.init(ks);
        
        SSLContext context = SSLContext.getInstance("SSL");
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[] { tm }, null);
        
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
            X509Certificate[] chain = tm.getChain();
            if(chain != null) {
                System.out.println("服务器返回：" + chain.length + " 个证书");
                OutputStream out = null;
                for(int i = 0; i < chain.length; i++) {
                    try {                        
                        X509Certificate x509Cert = chain[i];
                        String alias = host + (i > 0 ? i + "" : "");
                        ks.setCertificateEntry(alias, x509Cert);
                        
                        String certFile = dir + alias + ".jks";
                        out = new FileOutputStream(certFile);
                        ks.store(out, password.toCharArray());
                        out.close();
                        
                        System.setProperty("javax.net.ssl.trustStore", certFile);
                        System.out.println("第" + (i + 1) + "个证书安装成功");
                    } catch(Exception e) {
                        e.printStackTrace();
                        continue;
                    } finally {
                        try {
                            if(out != null) {
                                out.close();
                            }
                            out = null;
                        } catch(Exception e) {}
                    }
                }
            }
        }
    }
    
    /**
     * 确保文件存在
     * @param file
     * @return
     */
    private static File makeSureFile(File file) {
        if (file.isFile() == false) {
        	//char SEP = File.separatorChar;
        	//String jksPaht = System.getProperty("java.home") + SEP + "lib"    + SEP + "security";
            String jksPaht = "D:\\temp\\certest";
            File dir = new File(jksPaht);
            file = new File(dir, file.getName());
            if (file.isFile() == false) {
            	//file = new File(dir, "cacerts");
            	file = new File(dir, "leotest.jks");
            }
        }
        System.out.println(file.getPath());
        return file;
    }
    
    /**
     * 获取keystore
     * @param file
     * @param password
     * @return
     * @throws Exception
     */
    private static KeyStore getKeyStore(File file, String password) throws Exception {
        InputStream in = new FileInputStream(file);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] passphrase = password.toCharArray();
        ks.load(in, passphrase);
        in.close();
        return ks;
    }
    
    public static class SavingTrustManager implements X509TrustManager {
        private final X509TrustManager tm;
        private X509Certificate[] chain;

        public SavingTrustManager(X509TrustManager tm) {
            this.tm = tm;
        }
        
        public X509TrustManager getTM() {
            return tm;
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
            tm.checkServerTrusted(chain, authType);
        }
    }
}
