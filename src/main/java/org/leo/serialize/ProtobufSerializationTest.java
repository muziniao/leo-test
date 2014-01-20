package org.leo.serialize;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.leo.serialize.protobuf.domain.AddressBookProtos.AddressBook;
import org.leo.serialize.protobuf.domain.AddressBookProtos.Person;


public class ProtobufSerializationTest {

	public AddressBook getAddressBook() {				
		
		Person.PhoneNumber.Builder pn1 = Person.PhoneNumber.newBuilder();
		pn1.setNumber("100000000000001");
		pn1.setType(Person.PhoneType.HOME);
		Person.PhoneNumber.Builder pn2 = Person.PhoneNumber.newBuilder();
		pn2.setNumber("100000000000002");
		pn2.setType(Person.PhoneType.WORK);

		Person.Builder p1 = Person.newBuilder();
		p1.setName("leo.li");
		p1.setId(100001);
		p1.setEmail("leo@leo.com");
		p1.addPhone(pn1);
		p1.addPhone(pn2);
				
		AddressBook.Builder ab = AddressBook.newBuilder();
		ab.addPerson(p1);
		
		return ab.build();
	}
	
	@Test
	public void testProtobufPerm() throws Exception {
		AddressBook bean = getAddressBook();
		int len = 0;
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			bean.writeTo(os);
			os.close();
			if (i == 0)
				len = os.toByteArray().length;
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			AddressBook ab = AddressBook.parseFrom(is);
			assertEquals(ab.getClass(), AddressBook.class);
			//System.out.println(ab.getPersonList().get(0).getName() + i);
		}
		System.out.println("Protobuf\twrite and parse 500 times in "
				+ (System.currentTimeMillis() - now) + "ms, size " + len);
	}
}
