package org.leo.serialize.compare;

import java.util.ArrayList;
import java.util.List;

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
import org.msgpack.MessagePack;

public class Runner {
	
	private int iterations = 500;
	
	private StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Runner runner = new Runner();
		runner.run(1000);		
	}
	
	public void run(int iterations) throws Exception{
		setIterations(iterations);
		/**
		testJava();
		*/
		
		testDubbo();
		/**
		testHessian2();
		testMsgpack();
		testFastJson();
		testCodehausJackson();
		testProtobuf();
		testAvroSpecific();	
		*/	
		printResult();
	}
	
	
	public void testJava() throws Exception{
		AddressBook object = getAddressBook();
		JavaSerializer<AddressBook> serializer = new JavaSerializer<AddressBook>();		
		run(serializer, object);
	}
	
	public void testDubbo() throws Exception{
		AddressBook object = getAddressBook();
		DubboSerializer<AddressBook> serializer = new DubboSerializer<AddressBook>(AddressBook.class);		
		run(serializer, object);
	}
	
	public void testHessian2() throws Exception{
		AddressBook object = getAddressBook();
		Hessian2Serializer<AddressBook> serializer = new Hessian2Serializer<AddressBook>();		
		run(serializer, object);
	}
	
	public void testMsgpack() throws Exception{
		AddressBook object = getAddressBook();
		
		MessagePack msgPack = new MessagePack(); 
		msgPack.register(PhoneType.class);
		msgPack.register(PhoneNumber.class);
		msgPack.register(Person.class);
		msgPack.register(AddressBook.class);
		
		MsgpackSerializer<AddressBook> serializer = new MsgpackSerializer<AddressBook>(msgPack,AddressBook.class);		
		run(serializer, object);
	}
	
	public void testFastJson() throws Exception{
		AddressBook object = getAddressBook();
		FastJsonSerializer<AddressBook> serializer = new FastJsonSerializer<AddressBook>(AddressBook.class);		
		run(serializer, object);
	}
		
	public void testCodehausJackson() throws Exception{
		AddressBook object = getAddressBook();
		CodehausJacksonSerializer<AddressBook> serializer = new CodehausJacksonSerializer<AddressBook>(AddressBook.class);		
		run(serializer, object);
	}
	
	public void testProtobuf() throws Exception{
		org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook object = getProtobufAddressBook();
		ProtobufSerializer serializer = new ProtobufSerializer();		
		run(serializer, object);
	}
	
	public void testAvroSpecific() throws Exception{
		org.leo.serialize.avro.domain.AddressBook object = getAddressBook(1);
		AvroSpecificSerializer<org.leo.serialize.avro.domain.AddressBook> serializer = new AvroSpecificSerializer<org.leo.serialize.avro.domain.AddressBook>(org.leo.serialize.avro.domain.AddressBook.class);		
		run(serializer, object);
	}
	
	public <T> void run(Serializer<T> serializer,T object) throws Exception{
		TestCase<T> testCase = new TestCase<T>(serializer);
		double stime = testCase.serialize(object, iterations);
		double dtime = testCase.deserialize(object, iterations);
		int size = testCase.size(object);
		result.append(serializer.getName() + "\t" + stime + "\t" + dtime + "\t" + size + "\n");
	}
	
	public void printResult(){
		System.out.println(result.toString());
	}
	
	public AddressBook getAddressBook() {
		PhoneNumber pn1 = new PhoneNumber();
		pn1.setNumber("100000000000001");
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
	
	public org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook getProtobufAddressBook() {				
		
		org.leo.serialize.protobuf.domain.NewAddressBookProtos.PhoneNumber.Builder pn1 = org.leo.serialize.protobuf.domain.NewAddressBookProtos.PhoneNumber.newBuilder();
		pn1.setNumber("100000000000001");
		pn1.setType(org.leo.serialize.protobuf.domain.NewAddressBookProtos.PhoneType.HOME);
		org.leo.serialize.protobuf.domain.NewAddressBookProtos.PhoneNumber.Builder pn2 = org.leo.serialize.protobuf.domain.NewAddressBookProtos.PhoneNumber.newBuilder();
		pn2.setNumber("100000000000002");
		pn2.setType(org.leo.serialize.protobuf.domain.NewAddressBookProtos.PhoneType.WORK);

		org.leo.serialize.protobuf.domain.NewAddressBookProtos.Person.Builder p1 = org.leo.serialize.protobuf.domain.NewAddressBookProtos.Person.newBuilder();
		p1.setName("leo.li");
		p1.setId(100001);
		p1.setEmail("leo@leo.com");
		p1.addPhone(pn1);
		p1.addPhone(pn2);
				
		org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook.Builder ab = org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook.newBuilder();
		ab.addPerson(p1);
		
		return ab.build();
	}
	
	
	public org.leo.serialize.avro.domain.AddressBook getAddressBook(int index) {
		int maxLen = 3;
		int difLen = maxLen - String.valueOf(index).length();
		StringBuilder number = new StringBuilder("100000000000");
		for(int i = 0 ; i < difLen; i++){
			number.append("0");
		}
		number.append(String.valueOf(index));
		
		org.leo.serialize.avro.domain.PhoneNumber pn1 = new org.leo.serialize.avro.domain.PhoneNumber();
		pn1.setNumber(number.toString());
		pn1.setType(org.leo.serialize.avro.domain.PhoneType.HOME);
		org.leo.serialize.avro.domain.PhoneNumber pn2 = new org.leo.serialize.avro.domain.PhoneNumber();
		pn2.setNumber("100000000000002");
		pn2.setType(org.leo.serialize.avro.domain.PhoneType.WORK);

		List<org.leo.serialize.avro.domain.PhoneNumber> phone = new ArrayList<org.leo.serialize.avro.domain.PhoneNumber>();
		phone.add(pn1);
		phone.add(pn2);

		org.leo.serialize.avro.domain.Person p1 = new org.leo.serialize.avro.domain.Person();
		p1.setName("leo.li");
		p1.setId(100001);
		p1.setEmail("leo@leo.com");
		p1.setPhone(phone);

		List<org.leo.serialize.avro.domain.Person> personList = new ArrayList<org.leo.serialize.avro.domain.Person>();
		personList.add(p1);
		org.leo.serialize.avro.domain.AddressBook ab = new org.leo.serialize.avro.domain.AddressBook();
		ab.setPerson(personList);

		return ab;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	

}
