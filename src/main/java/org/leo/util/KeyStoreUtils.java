package org.leo.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

public class KeyStoreUtils {

	/**
	 * 获取KeyStore
	 * 
	 * @param keystorePath
	 * @param password
	 * @return
	 * @throws Exception
	 *
	 * @author leo.li
	 * Modify Time 2014年4月3日 下午2:39:30
	 */
	public static KeyStore getKeyStore(String keystorePath, String password) throws Exception{
		//获得密钥库文件输入流
		FileInputStream is = null;
		try{
			is = new FileInputStream(keystorePath);
			//实例化密钥库
			KeyStore ks = KeyStore.getInstance("JKS");
			//加载密钥库
			ks.load(is,password.toCharArray());
			return ks;
		} catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally{
			//关闭流
			try {
				if(is != null) is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 保存KeyStore
	 * 
	 * @param ks
	 * @param password
	 * @param keystorePath
	 *
	 * @author leo.li
	 * Modify Time 2014年4月3日 下午2:46:48
	 * @throws Exception 
	 */
	public static void store(KeyStore ks, String keystorePath, String storepass) throws Exception{
		FileOutputStream out = null;
		try {                      
            out = new FileOutputStream(keystorePath);
            ks.store(out, storepass.toCharArray());            
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
	
	/**
	 * 将证书保存进jsk
	 * 
	 * @param chain
	 * @param keystorePath
	 * @param alias
	 * @param storepass
	 * @throws Exception
	 *
	 * @author leo.li
	 * Modify Time 2014年4月3日 下午3:34:23
	 */
	public static void store(X509Certificate[] chain, String keystorePath, String alias, String storepass) throws Exception{
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(null, null);
		int len = chain.length;
		for(int i = 0 ; i < len ; i++){
            String aliasName = alias + (i > 0 ? i + "" : "");
            ks.setCertificateEntry(aliasName, chain[i]);
		}
		store(ks, keystorePath, storepass);
	}
}
