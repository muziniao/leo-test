package org.leo.serialize.compare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.leo.serialize.compare.serializer.AvroSpecificSerializer;
import org.leo.serialize.compare.serializer.CodehausJacksonSerializer;
import org.leo.serialize.compare.serializer.DubboSerializer;
import org.leo.serialize.compare.serializer.FastJsonSerializer;
import org.leo.serialize.compare.serializer.Hessian2Serializer;
import org.leo.serialize.compare.serializer.JavaSerializer;
import org.leo.serialize.compare.serializer.MsgpackSerializer;
import org.leo.serialize.compare.serializer.ProtobufSerializer;
import org.leo.serialize.compare.serializer.Serializer;
import org.leo.serialize.domain.AddressBook;
import org.leo.serialize.domain.Person;
import org.leo.serialize.domain.PhoneNumber;
import org.leo.serialize.domain.PhoneType;
import org.leo.util.JsonUtils;
import org.msgpack.MessagePack;

public class Runner2 {
	
	private int iterations = 500;
	
	private List<AddressBook> addressBookList;
	
	private StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Runner2 runner = new Runner2();
		runner.run(30000);		
	}
	
	public void run(int iterations) throws Exception{
		setIterations(iterations);
		loadData();
		Thread.sleep(2000);
		/**
		testJava();	
		
		testHessian2();*/			
		
		testDubbo();
		/**
		testCodehausJackson();
		
		testFastJson();
		
		testProtobuf();		
		
		testAvroSpecific();		
		
		testMsgpack();	
		*/	
		printResult();
	}
	
	
	public void testJava() throws Exception{
		JavaSerializer<AddressBook> serializer = new JavaSerializer<AddressBook>();		
		run(serializer, addressBookList);
	}
	
	public void testDubbo() throws Exception{
		DubboSerializer<AddressBook> serializer = new DubboSerializer<AddressBook>(AddressBook.class);		
		run(serializer, addressBookList);
	}
	
	public void testHessian2() throws Exception{
		Hessian2Serializer<AddressBook> serializer = new Hessian2Serializer<AddressBook>();		
		run(serializer, addressBookList);
	}
	
	public void testMsgpack() throws Exception{		
		MessagePack msgPack = new MessagePack(); 
		msgPack.register(PhoneType.class);
		msgPack.register(PhoneNumber.class);
		msgPack.register(Person.class);
		msgPack.register(AddressBook.class);
		
		MsgpackSerializer<AddressBook> serializer = new MsgpackSerializer<AddressBook>(msgPack,AddressBook.class);		
		run(serializer, addressBookList);
	}
	
	public void testFastJson() throws Exception{
		FastJsonSerializer<AddressBook> serializer = new FastJsonSerializer<AddressBook>(AddressBook.class);		
		run(serializer, addressBookList);
	}
		
	public void testCodehausJackson() throws Exception{
		CodehausJacksonSerializer<AddressBook> serializer = new CodehausJacksonSerializer<AddressBook>(AddressBook.class);		
		run(serializer, addressBookList);
	}
	
	public void testProtobuf() throws Exception{
		List<org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook> protobufAddressBookList = getProtobufAddressBookList();
		ProtobufSerializer serializer = new ProtobufSerializer();		
		run(serializer, protobufAddressBookList);
	}
	
	public void testAvroSpecific() throws Exception{
		List<org.leo.serialize.avro.domain.AddressBook> avroAddressBookList = getAvroAddressBookList();
		AvroSpecificSerializer<org.leo.serialize.avro.domain.AddressBook> serializer = new AvroSpecificSerializer<org.leo.serialize.avro.domain.AddressBook>(org.leo.serialize.avro.domain.AddressBook.class);		
		run(serializer, avroAddressBookList);
	}
	
	public <T> void run(Serializer<T> serializer,List<T> objectList) throws Exception{
		TestCase2<T> testCase = new TestCase2<T>(serializer, objectList, iterations);
		double stime = testCase.serialize();
		double dtime = testCase.deserialize();
		int size = testCase.size();
		result.append(serializer.getName() + "\t" + stime + "\t" + dtime + "\t" + size + "\n");
	}
	
	public void printResult(){
		System.out.println(result.toString());
	}
		
	public List<org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook> getProtobufAddressBookList() {	
		
		List<org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook> protobufAddressBookList = new ArrayList<org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook>();
		for(int i = 0 ; i < iterations; i++){
			org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook.Builder protobufAddressBook = org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook.newBuilder();
						
			AddressBook ab = addressBookList.get(i);			
			List<Person> personList = ab.getPerson(); 
			for(Person person : personList){
				org.leo.serialize.protobuf.domain.NewAddressBookProtos.Person.Builder pbPerson = org.leo.serialize.protobuf.domain.NewAddressBookProtos.Person.newBuilder();
				pbPerson.setName(person.getName());
				pbPerson.setId(person.getId());
				pbPerson.setEmail(person.getEmail());	
				
				List<PhoneNumber> phoneList = person.getPhone();
				for(PhoneNumber phone : phoneList){
					org.leo.serialize.protobuf.domain.NewAddressBookProtos.PhoneNumber.Builder pn1 = org.leo.serialize.protobuf.domain.NewAddressBookProtos.PhoneNumber.newBuilder();
					pn1.setNumber(phone.getNumber());
					pn1.setType(org.leo.serialize.protobuf.domain.NewAddressBookProtos.PhoneType.HOME);
					pbPerson.addPhone(pn1);
				}
				protobufAddressBook.addPerson(pbPerson);
			}
			protobufAddressBookList.add(protobufAddressBook.build());
		}		
		return protobufAddressBookList;
	}
	
	
	public List<org.leo.serialize.avro.domain.AddressBook> getAvroAddressBookList() {
		List<org.leo.serialize.avro.domain.AddressBook> avroAddressBookList = new ArrayList<org.leo.serialize.avro.domain.AddressBook>();
		for(int i = 0 ; i < iterations; i++){						
			AddressBook ab = addressBookList.get(i);			
			List<Person> personList = ab.getPerson(); 
			
			List<org.leo.serialize.avro.domain.Person> avroPersonList = new ArrayList<org.leo.serialize.avro.domain.Person>();
			for(Person person : personList){
				org.leo.serialize.avro.domain.Person avroPerson = new org.leo.serialize.avro.domain.Person();
				avroPerson.setName(person.getName());
				avroPerson.setId(person.getId());
				avroPerson.setEmail(person.getEmail());	
				
				List<org.leo.serialize.avro.domain.PhoneNumber> avroPhoneList = new ArrayList<org.leo.serialize.avro.domain.PhoneNumber>();
				List<PhoneNumber> phoneList = person.getPhone();
				for(PhoneNumber phone : phoneList){
					org.leo.serialize.avro.domain.PhoneNumber pn1 = new org.leo.serialize.avro.domain.PhoneNumber();
					pn1.setNumber(phone.getNumber());
					pn1.setType(org.leo.serialize.avro.domain.PhoneType.HOME);
					avroPhoneList.add(pn1);
				}
				avroPerson.setPhone(avroPhoneList);
				avroPersonList.add(avroPerson);
			}
			org.leo.serialize.avro.domain.AddressBook avroAddressBook = new org.leo.serialize.avro.domain.AddressBook();
			avroAddressBook.setPerson(avroPersonList);
			avroAddressBookList.add(avroAddressBook);
		}		
		return avroAddressBookList;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	
	public void loadData() throws IOException{
		String json = FileUtils.readFileToString(new File("D:\\Temp\\test-data-addressBook-byte-500-3W.out"));
		this.addressBookList = JsonUtils.readJsonToObjectList(AddressBook.class, json);
	}
	

}
