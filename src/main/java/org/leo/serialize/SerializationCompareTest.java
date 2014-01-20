package org.leo.serialize;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.leo.serialize.domain.AddressBook;
import org.leo.serialize.domain.Person;
import org.leo.serialize.domain.PhoneNumber;
import org.leo.serialize.domain.PhoneType;
import org.leo.util.JsonUtils;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;

import com.alibaba.dubbo.common.serialize.support.dubbo.Builder;
import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public class SerializationCompareTest {

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

	@Test
	public void testJavaOutputPerm() throws Exception {
		AddressBook bean = getAddressBook();
		int len = 0;
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(os);
			out.writeObject(bean);
			os.close();
			if (i == 0)
				len = os.toByteArray().length;
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			ObjectInputStream in = new ObjectInputStream(is);
			assertEquals(in.readObject().getClass(), AddressBook.class);
		}
		System.out.println("java\t\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
	}

	@Test
	public void testBuilderPerm() throws Exception {
		Builder<AddressBook> bb = Builder.register(AddressBook.class);
		AddressBook bean = getAddressBook();
		int len = 0;
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			bb.writeTo(bean, os);
			os.close();
			if (i == 0)
				len = os.toByteArray().length;

			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			AddressBook b = bb.parseFrom(is);
			assertEquals(b.getClass(), AddressBook.class);
		}
		System.out.println("Builder\t\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
	}

	@Test
	public void testH2oPerm() throws Exception {
		AddressBook bean = getAddressBook();
		int len = 0;
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			Hessian2Output out = new Hessian2Output(os);
			out.writeObject(bean);
			out.flushBuffer();
			os.close();
			if (i == 0)
				len = os.toByteArray().length;
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			Hessian2Input in = new Hessian2Input(is);
			assertEquals(in.readObject().getClass(), AddressBook.class);
		}
		System.out.println("Hessian2\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
	}
	
	@Test
	public void testMsgpackPerm() throws Exception {
		MessagePack msgPack = new MessagePack(); 
		msgPack.register(PhoneType.class);
		msgPack.register(PhoneNumber.class);
		msgPack.register(Person.class);
		msgPack.register(AddressBook.class);
		
		AddressBook bean = getAddressBook();
		int len = 0;
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
			Packer packer = msgPack.createPacker(os); 
			packer.write(bean);  
			os.close();
			if (i == 0)
				len = os.toByteArray().length;
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());	
			AddressBook ab = msgPack.read(is, AddressBook.class);
			//AddressBook ab = msgPack.read(os.toByteArray(), AddressBook.class);
			assertEquals(ab.getClass(), AddressBook.class);
			//System.out.println(ab.getPerson().get(0).getName() + i);
		}
		System.out.println("Msgpack\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
	}
	
	@Test
	public void testFastJsonPerm() throws Exception {
		AddressBook bean = getAddressBook();
		String jsonString = JSON.toJSONString(bean);
		System.out.println(jsonString);
		AddressBook ab = JSON.parseObject(jsonString, AddressBook.class);
		System.out.println(ab.getPerson().get(0).getEmail());
		
		int len = 0;
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			String ss = JSON.toJSONString(bean);
			if (i == 0) len = ss.getBytes().length;

			AddressBook addressBook = JSON.parseObject(ss, AddressBook.class);
			
			assertEquals(addressBook.getClass(), AddressBook.class);
		}
		System.out.println("FastJson\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
	}
	
	@Test
	public void testJsonUtilsPerm() throws Exception {
		AddressBook bean = getAddressBook();
		String jsonString = JsonUtils.writeObjectToJson(bean);
		System.out.println(jsonString);
		AddressBook ab = JsonUtils.readJsonToObject(AddressBook.class, jsonString);
		System.out.println(ab.getPerson().get(0).getEmail());
		
		int len = 0;
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			String ss = JsonUtils.writeObjectToJson(bean);
			if (i == 0) len = ss.getBytes().length;

			AddressBook addressBook = JsonUtils.readJsonToObject(AddressBook.class, jsonString);
			
			assertEquals(addressBook.getClass(), AddressBook.class);
		}
		System.out.println("JsonUtils\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
	}
}
