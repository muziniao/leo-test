package org.leo.serialize;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;
import org.leo.serialize.avro.domain.AddressBook;
import org.leo.serialize.avro.domain.Person;
import org.leo.serialize.avro.domain.PhoneNumber;
import org.leo.serialize.avro.domain.PhoneType;

public class AvroSerializationTest {
	
	public AddressBook getAddressBook() {
		return getAddressBook(1);
	}

	public AddressBook getAddressBook(int index) {
		int maxLen = 3;
		int difLen = maxLen - String.valueOf(index).length();
		StringBuilder number = new StringBuilder("100000000000");
		for(int i = 0 ; i < difLen; i++){
			number.append("0");
		}
		number.append(String.valueOf(index));
		
		PhoneNumber pn1 = new PhoneNumber();
		pn1.setNumber(number.toString());
		pn1.setType(PhoneType.HOME);
		PhoneNumber pn2 = new PhoneNumber();
		pn2.setNumber("100000000000002");
		pn2.setType(PhoneType.WORK);

		List<PhoneNumber> phone = new ArrayList<PhoneNumber>();
		phone.add(pn1);
		phone.add(pn2);

		Person p1 = new Person();
		p1.setName("leo.li");
		p1.setId(100001);
		p1.setEmail("leo@leo.com");
		p1.setPhone(phone);

		List<Person> personList = new ArrayList<Person>();
		personList.add(p1);
		AddressBook ab = new AddressBook();
		ab.setPerson(personList);

		return ab;
	}
	
	public GenericRecord forward(AddressBook ab) {
		GenericRecord abr = new GenericData.Record(AddressBook.SCHEMA$);
		GenericData.Array<GenericRecord> personList = new GenericData.Array<GenericRecord>(ab.person.size(), AddressBook.SCHEMA$.getField("person").schema());
		for (Person person : ab.person) {
			personList.add(forwardPerson(person));
		}
		abr.put("person", personList);

		return abr;
	}
	
	public GenericRecord forwardPerson(Person person) {
		GenericRecord personr = new GenericData.Record(Person.SCHEMA$);
		personr.put("name", person.name);
		personr.put("id", person.id);
		personr.put("email", person.email);
		
		GenericData.Array<GenericRecord> phoneList = new GenericData.Array<GenericRecord>(person.phone.size(), Person.SCHEMA$.getField("phone").schema());
		for (PhoneNumber phone : person.phone) {
			phoneList.add(forwardPhoneNumber(phone));
		}
		personr.put("phone", phoneList);
		return personr;
	}
	
	public GenericRecord forwardPhoneNumber(PhoneNumber phone) {
		GenericRecord phoner = new GenericData.Record(PhoneNumber.SCHEMA$);
		phoner.put("number", phone.number);
		phoner.put("type", phone.type);

		return phoner;
	}
	
	public GenericRecord forwardPhoneType(PhoneType type) {
		GenericRecord typer = new GenericData.Record(PhoneType.SCHEMA$);
		return typer;
	}
	
	//@Test
	public void specific() throws IOException {
		AddressBook bean = getAddressBook();
		int len = 0;
		DatumWriter<AddressBook> datumWriter = new SpecificDatumWriter<AddressBook>(AddressBook.class);	
		DatumReader<AddressBook> datumReader = new SpecificDatumReader<AddressBook>(AddressBook.class);
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();		
			DataFileWriter<AddressBook> dataFileWriter = new DataFileWriter<AddressBook>(datumWriter);
			dataFileWriter.create(bean.getSchema(), os);
			dataFileWriter.append(bean);
			dataFileWriter.close();
			os.close();
			if (i == 0)
				len = os.toByteArray().length;
			
			DataFileReader<AddressBook> dataFileReader = new DataFileReader<AddressBook>(new SeekableByteArrayInput(os.toByteArray()),datumReader);
			AddressBook addressBook = dataFileReader.next();
			assertEquals(addressBook.getClass(), AddressBook.class);
			//if (i == 0) 
			//System.out.println(addressBook.toString());
		}
		System.out.println("avro-specific\t\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
		
	}
	
	//@Test
	public void specific2() throws IOException {
		List<AddressBook> beanList = new ArrayList<AddressBook>();
		for(int i = 1 ; i <= 500 ; i++){
			beanList.add(getAddressBook(i));
		}
		int len = 0;
		long now = System.currentTimeMillis();
		DatumWriter<AddressBook> datumWriter = new SpecificDatumWriter<AddressBook>(AddressBook.class);	
		DatumReader<AddressBook> datumReader = new SpecificDatumReader<AddressBook>(AddressBook.class);
		
		int i = 0;
		for (AddressBook bean : beanList) {
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();		
			DataFileWriter<AddressBook> dataFileWriter = new DataFileWriter<AddressBook>(datumWriter);
			dataFileWriter.create(bean.getSchema(), os);
			dataFileWriter.append(bean);
			dataFileWriter.close();
			os.close();
			if (i == 0){
				len = os.toByteArray().length;
				i++;
			}
				
			
			DataFileReader<AddressBook> dataFileReader = new DataFileReader<AddressBook>(new SeekableByteArrayInput(os.toByteArray()),datumReader);
			AddressBook addressBook = dataFileReader.next();
			assertEquals(addressBook.getClass(), AddressBook.class);
			//if (i == 0) 
			//System.out.println(addressBook.toString());
		}
		System.out.println("avro-specific2\t\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
		
	}
	
	//@Test
	public void specific3() throws IOException {
		List<AddressBook> beanList = new ArrayList<AddressBook>();
		for(int i = 1 ; i <= 500 ; i++){
			beanList.add(getAddressBook(i));
		}
		DecoderFactory decoderFactory = DecoderFactory.get();
	    EncoderFactory encoderFactory = EncoderFactory.get();
		DatumWriter<AddressBook> datumWriter = new SpecificDatumWriter<AddressBook>(AddressBook.class);	
		DatumReader<AddressBook> datumReader = new SpecificDatumReader<AddressBook>(AddressBook.class);
	    
		int len = 0;
		long now = System.currentTimeMillis();
		
		int i = 0;
		for (AddressBook bean : beanList) {			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			BinaryEncoder encoder = encoderFactory.binaryEncoder(os, null);
			datumWriter.write(bean, encoder);
            encoder.flush();
			os.close();
			if (i == 0){
				len = os.toByteArray().length;
				i++;
			}
			
			BinaryDecoder decoder = decoderFactory.binaryDecoder(new SeekableByteArrayInput(os.toByteArray()), null);
			AddressBook addressBook = datumReader.read(null, decoder);
			assertEquals(addressBook.getClass(), AddressBook.class);
			//if (i == 0) 
			//System.out.println(addressBook.toString());
		}
		System.out.println("avro-specific3\t\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
		
	}
	
	@Test
	public void generic()throws IOException {
		List<GenericRecord> beanList = new ArrayList<GenericRecord>();
		for(int i = 1 ; i <= 500 ; i++){
			beanList.add(forward(getAddressBook(i)));
		}
	    EncoderFactory encoderFactory = EncoderFactory.get();
		DecoderFactory decoderFactory = DecoderFactory.get();
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(AddressBook.SCHEMA$);	
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(AddressBook.SCHEMA$);
	    
		int len = 0;
		long now = System.currentTimeMillis();
		
		int i = 0;
		for (GenericRecord bean : beanList) {			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			BinaryEncoder encoder = encoderFactory.binaryEncoder(os, null);
			datumWriter.write(bean, encoder);
            encoder.flush();
			os.close();
			if (i == 0){
				len = os.toByteArray().length;
				i++;
			}
			
			BinaryDecoder decoder = decoderFactory.binaryDecoder(new SeekableByteArrayInput(os.toByteArray()), null);
			GenericRecord addressBook = datumReader.read(null, decoder);
			//assertEquals(addressBook.getClass(), GenericRecord.class);
			//if (i == 0) 
			//System.out.println(addressBook.toString());
		}
		System.out.println("avro-generic\t\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
		
	}
}
