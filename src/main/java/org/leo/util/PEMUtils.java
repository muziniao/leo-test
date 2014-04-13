package org.leo.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyPair;

import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

public class PEMUtils {

	public static KeyPair readKeyPair(File pemFile, char[] password) throws IOException{
		
		PEMParser pemParser = new PEMParser(new FileReader(pemFile));
		Object object = pemParser.readObject();
		pemParser.close();
		
		PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder().build(password);
		JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
		
		KeyPair kp = null;
		if(object instanceof PEMEncryptedKeyPair){
			PEMEncryptedKeyPair pemEncryptedKeyPair = (PEMEncryptedKeyPair)object;
			PEMKeyPair pemKeyPair = pemEncryptedKeyPair.decryptKeyPair(decProv);
			kp = converter.getKeyPair(pemKeyPair);
		}else{
			kp = converter.getKeyPair((PEMKeyPair)object);
		}
		
		return kp;
	}
	
	public static KeyPair readKeyPair(String pemFile, char[] password) throws IOException{
		return readKeyPair(new File(pemFile), password);
	}
}
