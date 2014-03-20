package org.leo.serialize.avro;

import java.io.File;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;
import org.leo.serialize.avro.domain.Man;

public class AvroSerializerTest {
	

	@Test
	public void genericSerialize() throws Exception {
		String avscFilePath = SpecificTest.class.getClassLoader().getResource("avro/man.avsc").getFile();		
		Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse(new File(avscFilePath));

		GenericRecord man = new GenericData.Record(schema);
		man.put("name", "leo");
		man.put("age", 18);
		man.put("color", "yellow");
		
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
		dataFileWriter.create(schema, new File("D:\\Temp\\man-avro-generic.out"));
		dataFileWriter.append(man);
		dataFileWriter.close();
	}
	
	
	@Test
	public void genericDeserialize() throws Exception {
		String avscFilePath = SpecificTest.class.getClassLoader().getResource("avro/man.avsc").getFile();
		Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse(new File(avscFilePath));
		
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File("D:\\Temp\\man-avro-generic.out"), datumReader);
		GenericRecord man = null;
		while (dataFileReader.hasNext()) {
			man = dataFileReader.next(man);
			System.out.println(man.get("name"));
			System.out.println(man.get("age"));
			System.out.println(man.get("color"));
		}
	}
	
	@Test
	public void specificSerialize() throws Exception {
		Man man = new Man();
		man.setName("leo");
		man.setAge(18);
		man.setColor("yellow");
				
		DatumWriter<Man> userDatumWriter = new SpecificDatumWriter<Man>(Man.class);
		DataFileWriter<Man> dataFileWriter = new DataFileWriter<Man>(userDatumWriter);
		dataFileWriter.create(man.getSchema(), new File("D:\\Temp\\man-avro-specific.out"));
		dataFileWriter.append(man);
		man.setName("leo-01");
		dataFileWriter.append(man);
		man.setName("leo-02");
		dataFileWriter.append(man);
		man.setName("leo-03");
		dataFileWriter.append(man);
		dataFileWriter.close();
	}
	
	@Test
	public void specificDeserialize() throws Exception {
		DatumReader<Man> userDatumReader = new SpecificDatumReader<Man>(Man.class);
		DataFileReader<Man> dataFileReader = new DataFileReader<Man>(new File("D:\\Temp\\man-avro-specific.out"),userDatumReader);
		Man man = null;
		while (dataFileReader.hasNext()) {
			man = dataFileReader.next(man);
			System.out.println("name=" + man.getName() + ";age=" + man.getAge());
			System.out.println("color=" + man.getColor());
		}
	}
	
	@Test
	public void specificSerialize2() throws Exception {
		Man man = new Man();
		man.setName("leo");
		man.setAge(18);
		man.setColor("yellow");
		
		for(int i = 0 ; i < 5 ; i++){
			DatumWriter<Man> userDatumWriter = new SpecificDatumWriter<Man>(Man.class);
			DataFileWriter<Man> dataFileWriter = new DataFileWriter<Man>(userDatumWriter);
			dataFileWriter.create(man.getSchema(), new File("D:\\Temp\\man-avro-specific-" + i + ".out"));
			man.setName("leo-" + i);
			dataFileWriter.append(man);
			dataFileWriter.close();
		}
	}
}
