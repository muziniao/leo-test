package org.leo.serialize.dubbo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.leo.serialize.domain.Student;

import com.alibaba.dubbo.common.serialize.support.dubbo.Builder;

public class DubboTest {

	/**
	 * 
	 * @param args
	 *
	 * @author leo.li
	 * Modify Time Jan 8, 2014 10:49:19 AM
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Student sd = new Student();
		sd.setName("leo");
		sd.setAge(10);
		
		Builder<Student> bb = Builder.register(Student.class);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		bb.writeTo(sd, os);
		os.close();
		System.out.println(os.toByteArray().length);
	}

}
