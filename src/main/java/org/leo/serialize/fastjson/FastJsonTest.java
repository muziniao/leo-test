package org.leo.serialize.fastjson;

import java.io.IOException;

import org.leo.serialize.domain.Student;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

public class FastJsonTest {

	public static void main(String[] args) throws IOException {
		Student student = new Student("leo",20);
		//String jsonString = JSON.toJSONString(student);
		String jsonString = toJSONString(student);
		System.out.println(jsonString);

	}
	
	public static String toJSONString(Student student) throws IOException{
		SerializeWriter out = new SerializeWriter();
		JSONSerializer jsonserializer = new JSONSerializer(out);
		Serializer_1 serializer = new Serializer_1();
		serializer.write(jsonserializer, student, null, null);
		return out.toString();
	}

}
