package org.leo.serialize.compare.serializer;

import org.leo.util.JsonUtils;

public class CodehausJacksonSerializer<T> extends AbstractSerializer<T> {

	private Class<T> clazz;
	
	public CodehausJacksonSerializer(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public String getName() {
		return "jackson-codehaus";
	}
	
	@Override
	public byte[] serialize(T object) throws Exception {
		byte[] bytes = JsonUtils.writeObjectToBytes(object);
		return bytes;
	}

	@Override
	public T deserialize(byte[] bytes) throws Exception {
		T v = JsonUtils.readJsonToObject(clazz, bytes);
		return v;
	}

}
