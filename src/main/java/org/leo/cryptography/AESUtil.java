package org.leo.cryptography;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESUtil {
	

	public static final String KEY_GENERATION_ALG = "PBKDF2WithHmacSHA1";
	public static final int HASH_ITERATIONS = 10000;
	public static final int KEY_LENGTH = 256;
	public static byte[] salt = { 1, 3, 9, 6, 9, 4, 4, 4, 0, 2, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF };

	/**
	 * 密钥算法
	 */
	public static final String KEY_ALGORITHM = "AES";
		
	/**
	 * 加密/解密算法/工作模式/填充方式
	 */	
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
		
	/**
	 * 转换密钥
	 * @param key 二进制密钥
	 * @return key 密钥
	 * 
	 */	
	public static Key toKey(String password) throws Exception{
		/**
		PBEKeySpec keyspec = new PBEKeySpec(password.toCharArray(), salt, HASH_ITERATIONS, KEY_LENGTH);
		
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM, "BC");
		//生成秘密密钥
		return keyFactory.generateSecret(keyspec);
		*/
		
		PBEKeySpec myKeyspec = new PBEKeySpec(password.toCharArray(), salt, HASH_ITERATIONS, KEY_LENGTH);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(KEY_GENERATION_ALG, "BC");
		SecretKey sk = keyfactory.generateSecret(myKeyspec);
		byte[] skAsByteArray = sk.getEncoded();
		SecretKeySpec skforAES = new SecretKeySpec(skAsByteArray, "AES");
		System.out.println(Hex.encodeHexString(skforAES.getEncoded()).length());
		return skforAES;
	}

	/**
	 * 解密
	 * @param data 待解密数据
	 * @param key 密钥
	 * @return byte[] 解密数据
	 */	
	public static byte[] decrypt(byte[] data, String password)throws Exception{
		//还原密钥
		Key k = toKey(password);
		/**
		 * 实例化
		 * 使用PKCS7Padding填充方式，按如下代码实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
		//初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		//执行操作
		return cipher.doFinal(data);
	}
	
	/**
	 * 加密
	 * @param data 待加密数据
	 * @param key 密钥
	 * @return byte[] 加密数据
	 */	
	public static byte[] encrypt(byte[] data, String password) throws Exception{
		//还原密钥
		Key k = toKey(password);
		/**
		 * 实例化
		 * 使用PKCS7Padding填充方式，按如下代码实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM ,"BC");
		//初始化，设置为解密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		//执行操作
		return cipher.doFinal(data);
	}
	
	/**
	 * 生成密钥
	 * 
	 * @return byte[] 二进制密钥
	 */	
	public static byte[] initKey() throws Exception{
		/**
		 * 实例化
		 * 使用128位或192位长度密钥
		 * KeyGenerator.getInstance(KEY_ALGORITHM,"BC");
		 */
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		/**
		 * 初始化
		 *使用128位或192位长度密钥，按如下代码实现
		 *kg.init(128);
		 *kg.init(192);
		 */
		kg.init(256);
		//生成秘密密钥
		SecretKey secretKey = kg.generateKey();
		//获得密钥的二进制编码形式
		return secretKey.getEncoded();
	}


	public static void main(String[] args) throws UnsupportedEncodingException, Exception {

		Security.addProvider(new BouncyCastleProvider());
		/***/
		byte[] en = AESUtil.encrypt("abc".getBytes("utf-8"), "123456");
		System.out.println(Base64.encodeBase64String(en));
		
		//System.out.println(Hex.encodeHexString(initKey()));
	}
	
}
