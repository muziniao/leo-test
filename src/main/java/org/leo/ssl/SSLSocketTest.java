package org.leo.ssl;

import java.io.FileInputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSLSocketTest {

	/**
	 * 
	 * @param args
	 *
	 * @author leo.li
	 * Modify Time Dec 19, 2013 7:20:17 PM
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void server() {
        String type = "TLS";//类型
        String keyf = "..\\srvstore";//key文件路径
        String trustf = "..\\clitrust";//信任证书库
        String pass = "123456";//密码
        int port = 8888;//端口
        try {
            //初始化上下文
            SSLContext ctx = SSLContext.getInstance(type);
            
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            
            KeyStore ks = KeyStore.getInstance("JKS");
            KeyStore tks = KeyStore.getInstance("JKS");
            
            //载入keystore
            ks.load(new FileInputStream(keyf), pass.toCharArray());
            tks.load(new FileInputStream(trustf), pass.toCharArray());
            
            kmf.init(ks, pass.toCharArray());
            tmf.init(tks);
            
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
            
            SSLServerSocket sslServerSocket = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(port);
            sslServerSocket.setNeedClientAuth(true);//客户端认证
            
            Socket socket = sslServerSocket.accept();
            //多线程处理...
            System.out.println(socket.getLocalAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void client() {
        String host = "localhost";
        int port = 8888;
        String keyf = "..\\clistore";
        String trustf = "..\\srvtrust";
        String pass = "12345678";
        
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            KeyStore ks = KeyStore.getInstance("JKS");
            KeyStore tks = KeyStore.getInstance("JKS");
            
            ks.load(new FileInputStream(keyf), pass.toCharArray());
            tks.load(new FileInputStream(trustf), pass.toCharArray());
            kmf.init(ks, pass.toCharArray());
            tmf.init(tks);
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
            SSLSocketFactory ssf = ctx.getSocketFactory();
            
            SSLSocket socket = (SSLSocket) ssf.createSocket(host, port);
            socket.startHandshake();
            //socket IO处理...

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
