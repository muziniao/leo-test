package org.leo.security;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class Base64Test {

	//@Test
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
	
	//@Test
	public void testDecode() throws IOException{
		String readFilePath = "d:/temp/base64.txt";
		String writeFilePath = "d:/temp/decodeBase64-2.txt";
		String base64Str = FileUtils.readFileToString(new File(readFilePath), "gbk");
		//System.out.println(base64Str);
		String txt = new String(Base64.decodeBase64(base64Str), "utf-8");
		//txt = URLDecoder.decode(txt, "utf-8");
		FileUtils.writeStringToFile(new File(writeFilePath), txt);
		System.out.println(txt);
	}
	
	@Test
	public void url() throws UnsupportedEncodingException, DecoderException, EncoderException{
		String enc = "utf-8";
		String hz = "测试";
		String udhz = URLEncoder.encode(hz, enc);
		System.out.println(udhz);
		//URLCodec
		System.out.println(URLDecoder.decode(udhz, "utf-8"));
		

		System.out.println(new String(URLCodec.decodeUrl(udhz.getBytes(enc)), enc));
		URLCodec urlCodec = new URLCodec(enc);
		System.out.println(urlCodec.decode(udhz));
		System.out.println(urlCodec.encode(hz));
		
	}
}
