package org.leo.serialize;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.leo.serialize.domain.AddressBook;
import org.leo.serialize.domain.Man;
import org.leo.serialize.domain.Person;
import org.leo.serialize.domain.PhoneNumber;
import org.leo.serialize.domain.PhoneType;
import org.leo.util.JsonUtils;

public class TestData {

	public static void main(String[] args) throws Exception {
		
	}
	
	@Test
	public void createAddressBook() throws Exception{
		List<Person> personList = new ArrayList<Person>();
		for(int i = 1000; i < 1020 ; i++){
			PhoneNumber pn1 = new PhoneNumber();
			pn1.setNumber("1000001" + i);
			pn1.setType(PhoneType.HOME);
			PhoneNumber pn2 = new PhoneNumber();
			pn2.setNumber("1000002" + i);
			pn2.setType(PhoneType.WORK);

			List<PhoneNumber> phone = new ArrayList<PhoneNumber>();
			phone.add(pn1);
			phone.add(pn2);

			Person person = new Person();
			person.setName("P0" + i);
			person.setId(i);
			person.setEmail("email0" + i);
			person.setPhone(phone);
			
			personList.add(person);
		}
		
		AddressBook ab = new AddressBook();
		ab.setPerson(personList);		

		byte[] bytes = JsonUtils.writeObjectToBytes(ab);
		FileOutputStream os = new FileOutputStream("D:\\Temp\\test-data-addressBook.out");  
		os.write(bytes);
		os.close();
	}
	
	
	
	public void createMan() throws Exception{
		List<Man> manList = new ArrayList<Man>();
		for(int i = 1000; i < 1100; i++){
			Man man = new Man("M" + i, i);
			man.setColor("c");
			manList.add(man);
		}
		byte[] bytes = JsonUtils.writeObjectToBytes(manList);
		FileOutputStream os = new FileOutputStream("D:\\Temp\\test-data-man.out");  
		os.write(bytes);
		os.close();
	}

}
