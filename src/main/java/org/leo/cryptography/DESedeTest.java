package org.leo.cryptography;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

public class DESedeTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, DecoderException {
		// TODO Auto-generated method stub

		String messagePass = DigestUtils.md5Hex(RandomStringUtils.randomAlphanumeric(10));
		System.out.println("messagePass----------------");
		System.out.println(messagePass);
		System.out.println(messagePass.getBytes().length);

		System.out.println("==========================================");
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
		keyGenerator.init(112);
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] key = secretKey.getEncoded();
		String keyHex = new String(Hex.encodeHex(key));
		System.out.println(keyHex);
		System.out.println("key bytes is " + key.length);
		System.out.println("keyHex length is " + keyHex.length());
		
		System.out.println("==========================================");
		DESedeKeySpec dks = new DESedeKeySpec(Hex.decodeHex(keyHex.toCharArray()));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		secretKey = keyFactory.generateSecret(dks);
		System.out.println(Hex.encodeHex(secretKey.getEncoded()));
		
		System.out.println("==========================================");
		String content = "12345678";
		System.out.println(content.getBytes().length);
		
		Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] secretContent = cipher.doFinal(content.getBytes());
		System.out.println(Hex.encodeHex(secretContent));
		
		cipher = Cipher.getInstance("DESede/CBC/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		secretContent = cipher.doFinal(content.getBytes());
		System.out.println(Hex.encodeHex(secretContent));
		
	}

}
