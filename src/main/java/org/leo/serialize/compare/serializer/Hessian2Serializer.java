package org.leo.serialize.compare.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public class Hessian2Serializer<T> extends AbstractSerializer<T> {
	
	@Override
	public String getName() {
		return "hessian2";
	}
	
	@Override
	public byte[] serialize(T object) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Hessian2Output out = new Hessian2Output(os);
		out.writeObject(object);
		out.flushBuffer();
		os.close();
		return os.toByteArray();
	}

	@Override
	public T deserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		Hessian2Input in = new Hessian2Input(is);
		@SuppressWarnings("unchecked")
		T v = (T) in.readObject();
		return v;
	}

}
