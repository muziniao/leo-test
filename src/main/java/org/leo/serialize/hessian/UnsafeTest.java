package org.leo.serialize.hessian;

import sun.misc.Unsafe;  

import java.lang.reflect.Field;  

import org.junit.Test;
import org.leo.serialize.domain.Man;

public class UnsafeTest {

	@Test
	public void test() throws Exception {
		Unsafe unsafe = getUnsafeInstance();		
		
		Field SEXField = Man.class.getDeclaredField("SEX");
		long SEXOffset = unsafe.staticFieldOffset(SEXField);
		System.out.println("Location of SEX: " + SEXOffset);

		Field nameField = Man.class.getDeclaredField("name");
		long nameOffset = unsafe.objectFieldOffset(nameField);
		System.out.println("Location of name: " + nameOffset);

		Field ageField = Man.class.getDeclaredField("age");
		long ageOffset = unsafe.objectFieldOffset(ageField);
		System.out.println("Location of Orange: " + ageOffset);
		
		Man man = new Man("leo", 18);
		System.out.println("SEX is " + unsafe.getObject(man, SEXOffset));
		System.out.println("name is " + unsafe.getObject(man, nameOffset));
		System.out.println("age is " + unsafe.getInt(man, ageOffset));
		
		man = new Man("leo2", 19);
		System.out.println("SEX is " + unsafe.getObject(man, SEXOffset));
		System.out.println("name is " + unsafe.getObject(man, nameOffset));
		System.out.println("age is " + unsafe.getInt(man, ageOffset));
	}

	private Unsafe getUnsafeInstance() throws SecurityException,NoSuchFieldException, IllegalArgumentException,IllegalAccessException {
		Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafeInstance.setAccessible(true);
		return (Unsafe) theUnsafeInstance.get(Unsafe.class);
	}
}
