package org.leo.serialize.compare.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonSerializer<T> extends AbstractSerializer<T> {

	private Class<T> clazz;
	
	public FastJsonSerializer(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public String getName() {
		return "fastjson";
	}
	
	@Override
	public byte[] serialize(T object) throws Exception {
		 return JSON.toJSONBytes(object, SerializerFeature.WriteEnumUsingToString,SerializerFeature.DisableCircularReferenceDetect);
	}

	@Override
	public T deserialize(byte[] bytes) throws Exception {
		@SuppressWarnings("unchecked")
		T v = (T) JSON.parseObject(bytes, clazz, Feature.DisableCircularReferenceDetect);
		return v;
	}

}
