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
		List<AddressBook> addressBookList = new ArrayList<AddressBook>();		
		for(int k = 10000; k < 12000; k++){
			List<Person> personList = new ArrayList<Person>();
			for(int i = 1000; i < 1005 ; i++){
				PhoneNumber pn1 = new PhoneNumber();
				pn1.setNumber(k + "01" + i);//11
				pn1.setType(PhoneType.HOME);//4
				PhoneNumber pn2 = new PhoneNumber();
				pn2.setNumber(k + "02" + i);//11
				pn2.setType(PhoneType.WORK);//4

				List<PhoneNumber> phone = new ArrayList<PhoneNumber>();
				phone.add(pn1);
				phone.add(pn2);

				Person person = new Person();
				person.setName("P" + k + "" + i);//10
				person.setId(i);//4
				person.setEmail("E0" + i);//6
				person.setPhone(phone);
				
				personList.add(person);
			}			
			AddressBook ab = new AddressBook();
			ab.setPerson(personList);
			addressBookList.add(ab);
		}				

		byte[] bytes = JsonUtils.writeObjectToBytes(addressBookList);
		FileOutputStream os = new FileOutputStream("D:\\Temp\\test-data-addressBook-byte-250.out");  
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
