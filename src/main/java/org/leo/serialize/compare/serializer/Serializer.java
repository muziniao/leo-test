package org.leo.serialize.compare.serializer;

public interface Serializer<T> {
	
	public abstract String getName();

	public abstract byte[] serialize(T object) throws Exception;
	
	public abstract T deserialize(byte[] bytes) throws Exception;

}
