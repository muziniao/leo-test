package org.leo.serialize.fastjson;

import com.alibaba.fastjson.serializer.*;
import com.alibaba.fastjson.util.ASMUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import org.leo.serialize.domain.Student;

public class Serializer_1 implements ObjectSerializer {
	public Serializer_1() {
		name_asm_fieldType = ASMUtils.getMethodType(Student.class, "getName");
		age_asm_fieldType = ASMUtils.getMethodType(Student.class, "getAge");
	}

	public void write(JSONSerializer jsonserializer, Object obj, Object obj1,
			Type type) throws IOException {
		SerializeWriter serializewriter = jsonserializer.getWriter();
		if (serializewriter.isEnabled(SerializerFeature.SortField)) {
			write1(jsonserializer, obj, obj1, type);
			return;
		}
		Student student = (Student) obj;
		if (serializewriter.isEnabled(SerializerFeature.PrettyFormat)) {
			if (nature == null)
				nature = new JavaBeanSerializer(Student.class);
			nature.write(jsonserializer, obj, obj1, type);
			return;
		}
		if (jsonserializer.containsReference(obj)) {
			if (nature == null)
				nature = new JavaBeanSerializer(Student.class);
			nature.writeReference(jsonserializer, obj);
			return;
		}
		if (jsonserializer.isWriteAsArray(obj, type)) {
			writeAsArray(jsonserializer, obj, obj1, type);
			return;
		}
		com.alibaba.fastjson.serializer.SerialContext serialcontext = jsonserializer
				.getContext();
		jsonserializer.setContext(serialcontext, obj, obj1);
		char c;
		if (jsonserializer.isWriteClassName(type, obj)
				&& type != obj.getClass()) {
			serializewriter
					.write("{\"@type\":\"org.leo.serialize.domain.Student\"");
			c = ',';
		} else {
			c = '{';
		}
		c = FilterUtils.writeBefore(jsonserializer, obj, c);
		String s = "name";
		if (FilterUtils.applyName(jsonserializer, obj, s)) {
			String s2 = student.getName();
			if (FilterUtils.apply(jsonserializer, obj, s, s2)) {
				s = FilterUtils.processKey(jsonserializer, obj, s, s2);
				String s1;
				Object obj2 = FilterUtils.processValue(jsonserializer, obj, s,
						s1 = s2);
				if (s1 != obj2) {
					if (obj2 == null) {
						if (serializewriter
								.isEnabled(SerializerFeature.WriteMapNullValue)) {
							serializewriter.writeFieldNullString(c, s);
							c = ',';
						}
					} else {
						serializewriter.write(c);
						serializewriter.writeFieldName(s);
						jsonserializer.writeWithFieldName(obj2, s,
								name_asm_fieldType);
						c = ',';
					}
				} else if (s2 == null) {
					if (serializewriter
							.isEnabled(SerializerFeature.WriteMapNullValue)) {
						serializewriter.writeFieldNullString(c, s);
						c = ',';
					}
				} else {
					serializewriter.writeFieldValue(c, s, s2);
					c = ',';
				}
			}
		}
		s = "age";
		if (FilterUtils.applyName(jsonserializer, obj, s)) {
			Integer integer1 = student.getAge();
			if (FilterUtils.apply(jsonserializer, obj, s, integer1)) {
				s = FilterUtils.processKey(jsonserializer, obj, s, integer1);
				Integer integer;
				Object obj3 = FilterUtils.processValue(jsonserializer, obj, s,
						integer = integer1);
				if (integer != obj3) {
					if (obj3 == null) {
						if (serializewriter
								.isEnabled(SerializerFeature.WriteMapNullValue)) {
							serializewriter.writeFieldNullNumber(c, s);
							c = ',';
						}
					} else {
						serializewriter.write(c);
						serializewriter.writeFieldName(s);
						jsonserializer.writeWithFieldName(obj3, s,
								age_asm_fieldType);
						c = ',';
					}
				} else if (obj3 == null) {
					if (serializewriter
							.isEnabled(SerializerFeature.WriteMapNullValue)) {
						serializewriter.writeFieldNullNumber(c, s);
						c = ',';
					}
				} else {
					serializewriter.write(c);
					serializewriter.writeFieldName(s);
					jsonserializer.writeWithFieldName(obj3, s,
							age_asm_fieldType);
					c = ',';
				}
			}
		}
		c = FilterUtils.writeAfter(jsonserializer, obj, c);
		if (c == '{')
			serializewriter.write('{');
		serializewriter.write('}');
		jsonserializer.setContext(serialcontext);
	}

	public void write1(JSONSerializer jsonserializer, Object obj, Object obj1,
			Type type) throws IOException {
		SerializeWriter serializewriter = jsonserializer.getWriter();
		Student student = (Student) obj;
		if (serializewriter.isEnabled(SerializerFeature.PrettyFormat)) {
			if (nature == null)
				nature = new JavaBeanSerializer(Student.class);
			nature.write(jsonserializer, obj, obj1, type);
			return;
		}
		if (jsonserializer.containsReference(obj)) {
			if (nature == null)
				nature = new JavaBeanSerializer(Student.class);
			nature.writeReference(jsonserializer, obj);
			return;
		}
		if (jsonserializer.isWriteAsArray(obj, type)) {
			writeAsArray(jsonserializer, obj, obj1, type);
			return;
		}
		com.alibaba.fastjson.serializer.SerialContext serialcontext = jsonserializer
				.getContext();
		jsonserializer.setContext(serialcontext, obj, obj1);
		char c;
		if (jsonserializer.isWriteClassName(type, obj)
				&& type != obj.getClass()) {
			serializewriter
					.write("{\"@type\":\"org.leo.serialize.domain.Student\"");
			c = ',';
		} else {
			c = '{';
		}
		c = FilterUtils.writeBefore(jsonserializer, obj, c);
		String s = "age";
		if (FilterUtils.applyName(jsonserializer, obj, s)) {
			Integer integer1 = student.getAge();
			if (FilterUtils.apply(jsonserializer, obj, s, integer1)) {
				s = FilterUtils.processKey(jsonserializer, obj, s, integer1);
				Integer integer;
				Object obj2 = FilterUtils.processValue(jsonserializer, obj, s,
						integer = integer1);
				if (integer != obj2) {
					if (obj2 == null) {
						if (serializewriter
								.isEnabled(SerializerFeature.WriteMapNullValue)) {
							serializewriter.writeFieldNullNumber(c, s);
							c = ',';
						}
					} else {
						serializewriter.write(c);
						serializewriter.writeFieldName(s);
						jsonserializer.writeWithFieldName(obj2, s,
								age_asm_fieldType);
						c = ',';
					}
				} else if (obj2 == null) {
					if (serializewriter
							.isEnabled(SerializerFeature.WriteMapNullValue)) {
						serializewriter.writeFieldNullNumber(c, s);
						c = ',';
					}
				} else {
					serializewriter.write(c);
					serializewriter.writeFieldName(s);
					jsonserializer.writeWithFieldName(obj2, s,
							age_asm_fieldType);
					c = ',';
				}
			}
		}
		s = "name";
		if (FilterUtils.applyName(jsonserializer, obj, s)) {
			String s2 = student.getName();
			if (FilterUtils.apply(jsonserializer, obj, s, s2)) {
				s = FilterUtils.processKey(jsonserializer, obj, s, s2);
				String s1;
				Object obj3 = FilterUtils.processValue(jsonserializer, obj, s,
						s1 = s2);
				if (s1 != obj3) {
					if (obj3 == null) {
						if (serializewriter
								.isEnabled(SerializerFeature.WriteMapNullValue)) {
							serializewriter.writeFieldNullString(c, s);
							c = ',';
						}
					} else {
						serializewriter.write(c);
						serializewriter.writeFieldName(s);
						jsonserializer.writeWithFieldName(obj3, s,
								name_asm_fieldType);
						c = ',';
					}
				} else if (s2 == null) {
					if (serializewriter
							.isEnabled(SerializerFeature.WriteMapNullValue)) {
						serializewriter.writeFieldNullString(c, s);
						c = ',';
					}
				} else {
					serializewriter.writeFieldValue(c, s, s2);
					c = ',';
				}
			}
		}
		c = FilterUtils.writeAfter(jsonserializer, obj, c);
		if (c == '{')
			serializewriter.write('{');
		serializewriter.write('}');
		jsonserializer.setContext(serialcontext);
	}

	public void writeAsArray(JSONSerializer jsonserializer, Object obj,
			Object obj1, Type type) throws IOException {
		SerializeWriter serializewriter = jsonserializer.getWriter();
		Student student = (Student) obj;
		serializewriter.write('[');
		String s = "age";
		jsonserializer.writeWithFieldName(student.getAge(), s,
				age_asm_fieldType);
		serializewriter.write(',');
		s = "name";
		serializewriter.writeString(student.getName(), ']');
	}

	private JavaBeanSerializer nature;
	public Type name_asm_fieldPrefix;
	public Type name_asm_fieldType;
	public Type age_asm_fieldPrefix;
	public Type age_asm_fieldType;
}
