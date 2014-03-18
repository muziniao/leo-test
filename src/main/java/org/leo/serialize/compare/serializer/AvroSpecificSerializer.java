package org.leo.serialize.compare.serializer;

import java.io.ByteArrayOutputStream;

import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class AvroSpecificSerializer<T> extends AbstractSerializer<T> {

	private DecoderFactory decoderFactory = DecoderFactory.get();
	private EncoderFactory encoderFactory = EncoderFactory.get();
	
	private Class<T> clazz;
	
	private  DatumWriter<T> datumWriter;	
	private  DatumReader<T> datumReader;
	
	public AvroSpecificSerializer(Class<T> clazz){
		this.clazz = clazz;
		this.datumWriter = new SpecificDatumWriter<T>(this.clazz);	
		this.datumReader = new SpecificDatumReader<T>(this.clazz);
	}
	
	@Override
	public String getName() {
		return "avro-specific";
	}
	
	@Override
	public byte[] serialize(T object) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BinaryEncoder encoder = encoderFactory.binaryEncoder(os, null);
		datumWriter.write(object, encoder);
        encoder.flush();
		os.close();
		return os.toByteArray();
	}

	@Override
	public T deserialize(byte[] bytes) throws Exception {
		BinaryDecoder decoder = decoderFactory.binaryDecoder(new SeekableByteArrayInput(bytes), null);
		T v = datumReader.read(null, decoder);
		return v;
	}

}
