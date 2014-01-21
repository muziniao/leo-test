package org.leo.serialize.compare.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerializer<T> extends AbstractSerializer<T> {
	
	@Override
	public String getName() {
		return "java";
	}
	
	@Override
	public byte[] serialize(T object) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(object);
		os.close();
		return os.toByteArray();
	}

	
	@Override
	public T deserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		ObjectInputStream in = new ObjectInputStream(is);
		@SuppressWarnings("unchecked")
		T v = (T) in.readObject();
		return v;
	}
}
