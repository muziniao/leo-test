package org.leo.serialize.protobuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;
import org.leo.serialize.protobuf.domain.ManProtos.Man;

public class ProtobufSerializerTest {

	//@Test
	public void serialize() throws Exception {
		Man.Builder man = Man.newBuilder();
		//Man man = new Man("leo", 18);
		man.setName("leo");
		man.setAge(18);
		//man.setColor("yellow");
		//man.setId(10000);
		FileOutputStream os = new FileOutputStream("D:\\Temp\\man-protobuf.out");  
		man.build().writeTo(os);
		os.close();
	}
	
	
	@Test
	public void deserialize() throws Exception {
		FileInputStream is = new FileInputStream("D:\\Temp\\man-protobuf.out");  
		Man man = Man.parseFrom(is);
		is.close();
		
		//System.out.println("id=" + people.getId());
		System.out.println("name=" + man.getName() + ";age=" + man.getAge());
		//System.out.println("SEX=" + Man.SEX + ";stran=" + man.stran);
		//System.out.println("Color=" + man.getColor());
	}
}
