package org.leo.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

public class HexTest {
	
	@Test
	public void test() throws UnsupportedEncodingException{
		String s = "22300d06092a864886f70d01010105000382010f00308201";
		byte[] byteArr = Hex.decode(s);
		System.out.println(new String(byteArr, "utf-8"));
		System.out.println(Base64.encodeBase64String(byteArr));
	}
}
