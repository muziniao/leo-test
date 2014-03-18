package org.leo.serialize.dubbo;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;
import org.leo.serialize.domain.Man;

import com.alibaba.dubbo.common.serialize.support.dubbo.Builder;

public class DubboSerializerTest {

	@Test
	public void serialize() throws Exception {
		Builder<Man> bb = Builder.register(Man.class);
		
		Man man = new Man("leo", 18);
		man.setColor("yellow");
		//man.setId("10000");
		FileOutputStream os = new FileOutputStream("D:\\Temp\\man-dubbo.out");  
		bb.writeTo(man, os);
		os.close();
	}
	
	
	@Test
	public void deserialize() throws Exception {
		Builder<Man> bb = Builder.register(Man.class);
		
		FileInputStream is = new FileInputStream("D:\\Temp\\man-dubbo.out");  
		Man man = (Man) bb.parseFrom(is);;
		is.close();
		//System.out.println("id=" + man.getId());
		System.out.println("name=" + man.getName() + ";age=" + man.getAge());
		System.out.println("SEX=" + Man.SEX + ";stran=" + man.stran);
		System.out.println("Color=" + man.getColor());
	}
}
