package org.leo.serialize.compare.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.alibaba.dubbo.common.serialize.support.dubbo.Builder;

public class DubboSerializer<T> extends AbstractSerializer<T> {
	
	private Class<T> clazz;

	private Builder<T> builder;
	
	public DubboSerializer(Class<T> clazz) {
		this.clazz = clazz;
		this.builder = Builder.register(this.clazz);
	}
	
	@Override
	public String getName() {
		return "dubbo";
	}
			
	@Override
	public byte[] serialize(T object) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		builder.writeTo(object, os);
		os.close();
		return os.toByteArray();
	}

	@Override
	public T deserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		T v = builder.parseFrom(is);
		return v;
	}

}
