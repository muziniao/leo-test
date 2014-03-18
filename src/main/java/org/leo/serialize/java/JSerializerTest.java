package org.leo.serialize.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;
import org.leo.serialize.domain.Man;
import org.leo.serialize.domain.People;

public class JSerializerTest {
	
	@Test
	public void test(){
		System.out.println(String.class.getName() + "-" + String.class.getName().length());
		System.out.println(Man.class.getName() + "-" + Man.class.getName().length());
		System.out.println(People.class.getName() + "-" + People.class.getName().length());
	}

	//@Test
	public void serialize() throws Exception {
		Man man = new Man("leo", 18);
		man.setColor("yellow");
		//student.setId(10000);
		FileOutputStream os = new FileOutputStream("D:\\Temp\\man.out");  
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(man);
		os.flush();
		os.close();
		//os.toByteArray();
	}
	
	
	@Test
	public void deserialize() throws Exception {
		FileInputStream is = new FileInputStream("D:\\Temp\\man.out");  
		ObjectInputStream in = new ObjectInputStream(is);
		Man man = (Man) in.readObject();
		in.close();
		//System.out.println("id=" + people.getId());
		System.out.println("name=" + man.getName() + ";age=" + man.getAge());
		System.out.println("SEX=" + Man.SEX + ";stran=" + man.stran);
		System.out.println("Color=" + man.getColor());
	}
}
