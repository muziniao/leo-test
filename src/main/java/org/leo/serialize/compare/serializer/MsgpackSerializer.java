package org.leo.serialize.compare.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;

public class MsgpackSerializer<T> extends AbstractSerializer<T> {
	
	private MessagePack msgPack;
	
	private Class<T> clazz;
	
	public MsgpackSerializer(MessagePack msgPack, Class<T> clazz){
		this.msgPack = msgPack;
		this.clazz = clazz;
	}
	
	@Override
	public String getName() {
		return "msgPack";
	}
	
	@Override
	public byte[] serialize(T object) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();		
		Packer packer = msgPack.createPacker(os); 
		packer.write(object);  
		os.close();
		return os.toByteArray();
	}

	@Override
	public T deserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);	
		T v = msgPack.read(is, clazz);
		return v;
	}

}
