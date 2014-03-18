package org.leo.serialize.hessian;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;
import org.leo.serialize.domain.Man;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public class HessianSerializerTest {

	@Test
	public void serialize() throws Exception {
		Man man = new Man("leo", 18);
		man.setColor("yellow");
		//man.setId("10000");
		FileOutputStream os = new FileOutputStream("D:\\Temp\\man-hessian2.out");  
		Hessian2Output out = new Hessian2Output(os);
		out.writeObject(man);
		out.flushBuffer();
		os.close();
	}
	
	
	@Test
	public void deserialize() throws Exception {
		FileInputStream is = new FileInputStream("D:\\Temp\\man-hessian2.out");  
		Hessian2Input in = new Hessian2Input(is);
		Man man = (Man) in.readObject();
		in.close();
		//System.out.println("id=" + man.getId());
		System.out.println("name=" + man.getName() + ";age=" + man.getAge());
		System.out.println("SEX=" + Man.SEX + ";stran=" + man.stran);
		System.out.println("Color=" + man.getColor());
	}
}
