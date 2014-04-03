package org.leo.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Test {

	@Test
	public void test() throws UnsupportedEncodingException{
		String str = "这是科再奇上任CEO以来，首次来华参加IDF峰会。科再奇上任后即展开了对英特尔的转型工作，他在今日的主题演讲中全面阐述了这一转型的思路。他将目前英特尔的战略总结为在各类设备上提供全面的计算体验。";
		byte[] binaryData = str.getBytes("utf-8");
		System.out.println(Base64.encodeBase64URLSafeString(binaryData));
		System.out.println(Base64.encodeBase64String(binaryData));
		System.out.println(new String(Base64.encodeBase64(binaryData), "utf-8"));
		System.out.println(new String(Base64.encodeBase64(binaryData, true), "utf-8"));
		System.out.println(new String(Base64.encodeBase64(binaryData, false), "utf-8"));
		System.out.println(new String(Base64.encodeBase64Chunked(binaryData), "utf-8"));
		System.out.println(new String(Base64.encodeBase64(binaryData, false, true), "utf-8"));
		System.out.println(new String(Base64.decodeBase64(Base64.encodeBase64(binaryData)), "utf-8"));
		System.out.println(new String(Base64.decodeBase64(Base64.encodeBase64Chunked(binaryData)), "utf-8"));
	}
}
