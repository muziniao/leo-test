package org.leo.serialize.compare.serializer;

import com.alibaba.fastjson.JSON;
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
		byte[] bytes = JSON.toJSONBytes(object, SerializerFeature.WriteEnumUsingToString,SerializerFeature.DisableCircularReferenceDetect);
		//byte[] bytes = JSON.toJSONBytes(object);
		//byte[] bytes = JSON.toJSONString(object).getBytes();
		//byte[] bytes = null;
		//JSON.toJSONString(object);
		return bytes;
	}

	@Override
	public T deserialize(byte[] bytes) throws Exception {
		//T v = JSON.parseObject(bytes, clazz, Feature.DisableCircularReferenceDetect);
		T v = JSON.parseObject(bytes, clazz);
		//T v = JSON.parseObject(new String(bytes), clazz);
		//T v = JSON.parseObject("{\"person\":[{\"email\":\"leo@leo.com\",\"id\":100001,\"name\":\"leo.li\",\"phone\":[{\"number\":\"100000000000001\",\"type\":\"HOME\"},{\"number\":\"100000000000002\",\"type\":\"WORK\"}]}]}", clazz);
		return v;
	}

}
