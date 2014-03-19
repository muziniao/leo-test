package org.leo.serialize.fastjson;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.leo.serialize.domain.Man;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonSerializerTest {

	@Test
	public void serialize() throws Exception {		
		Man man = new Man("leo", 18);
		man.setColor("yellow");
		//man.setId("10000");
		
		byte[] bytes = JSON.toJSONBytes(man, SerializerFeature.WriteEnumUsingToString,SerializerFeature.DisableCircularReferenceDetect);
		FileOutputStream os = new FileOutputStream("D:\\Temp\\man-fastJson.out");  
		os.write(bytes);
		os.close();
	}
	
	
	//@Test
	public void deserialize() throws Exception {		
		//FileInputStream is = new FileInputStream("D:\\Temp\\man-jackson.out"); 
		byte[] byteArr = FileUtils.readFileToByteArray(new File("D:\\Temp\\man-fastJson.out"));
		Man man = JSON.parseObject(byteArr,Man.class);
		
		System.out.println("name=" + man.getName() + ";age=" + man.getAge());
		System.out.println("SEX=" + Man.SEX + ";stran=" + man.stran);
		System.out.println("Color=" + man.getColor());
	}
}
