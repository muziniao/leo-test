package org.leo.serialize.jackson;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.leo.serialize.domain.Man;
import org.leo.util.JsonUtils;

public class JacksonSerializerTest {

	@Test
	public void serialize() throws Exception {		
		Man man = new Man("leo", 18);
		man.setColor("yellow");
		//man.setId("10000");
		
		byte[] bytes = JsonUtils.writeObjectToBytes(man);
		FileOutputStream os = new FileOutputStream("D:\\Temp\\man-jackson.out");  
		os.write(bytes);
		os.close();
	}
	
	
	//@Test
	public void deserialize() throws Exception {		
		//FileInputStream is = new FileInputStream("D:\\Temp\\man-jackson.out"); 
		byte[] byteArr = FileUtils.readFileToByteArray(new File("D:\\Temp\\man-jackson.out"));
		Man man = JsonUtils.readJsonToObject(Man.class, byteArr);
		
		System.out.println("name=" + man.getName() + ";age=" + man.getAge());
		System.out.println("SEX=" + Man.SEX + ";stran=" + man.stran);
		System.out.println("Color=" + man.getColor());
	}
}
