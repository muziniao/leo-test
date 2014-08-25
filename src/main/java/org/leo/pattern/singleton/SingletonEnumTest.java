package org.leo.pattern.singleton;

public class SingletonEnumTest {

	public static void main(String[] args) {
		SingletonEnum instance01=SingletonEnum.INSTANCE01;
        instance01.setName("terje");
        System.out.println(instance01.getName());
        
        SingletonEnum instance02=SingletonEnum.INSTANCE01;
        System.out.println(instance02.getName());
        
        SingletonEnum instance03=SingletonEnum.INSTANCE02;
        instance03.setName("liu");
        System.out.println(instance03.getName());
        
        SingletonEnum instance04=SingletonEnum.INSTANCE02;
        System.out.println(instance04.getName());

	}

}
