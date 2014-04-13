package org.leo.cryptography;

import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.leo.util.PEMUtils;

public class PEMTest {

	private String passyword = "123456";
	
	private String pemFIlePath = "F:/cert/openssl/private/test-128-2048.key.pem";
	
	private PublicKey publicKey;
	
	private PrivateKey privateKey;
	
	private byte[] data;
	
	@Before
	public void initKeyPair(){
		Security.addProvider(new BouncyCastleProvider());
		data = "PEM".getBytes();
		
		KeyPair kp;
		try {
			kp = PEMUtils.readKeyPair(pemFIlePath, passyword.toCharArray());
			publicKey = kp.getPublic();
			System.out.println("publicKey--" + Base64.encodeBase64String(publicKey.getEncoded()));
			privateKey = kp.getPrivate();
			System.out.println("privateKey--" + Base64.encodeBase64String(privateKey.getEncoded()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	public void encryptAndDecrypt() throws Exception{
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] encrypt = cipher.doFinal(data);
		System.out.println("encrypt--" + Base64.encodeBase64String(encrypt));
		
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] decrypt = cipher.doFinal(encrypt);
		
		Assert.assertArrayEquals(data, decrypt);
	}
	
	@Test
	public void sign() throws Exception{
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(privateKey);
		signature.update(data);
		byte[] sign = signature.sign();
		System.out.println("sign--" + Base64.encodeBase64String(sign));
		
		signature.initVerify(publicKey);
		signature.update(data);
		boolean status = signature.verify(sign);
		
		Assert.assertTrue(status);
	}
}
