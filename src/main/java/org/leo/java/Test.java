package org.leo.java;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Animal felid = new Felid();
		Animal cat = new Cat();

		System.out.println(felid instanceof Animal);
		System.out.println(cat instanceof Animal);
		System.out.println(cat instanceof Cat);
		System.out.println("------------------------------------");
		System.out.println(Animal.class.isInstance(felid));
		System.out.println(Animal.class.isInstance(cat));
		System.out.println(Cat.class.isInstance(cat));		
		System.out.println("------------------------------------");
		System.out.println(Felid.class.isAssignableFrom(Animal.class));
		System.out.println(Cat.class.isAssignableFrom(Animal.class));
		System.out.println(Cat.class.isAssignableFrom(Felid.class));
		System.out.println("------------------------------------");
		System.out.println(Animal.class.isAssignableFrom(Felid.class));
		System.out.println(Animal.class.isAssignableFrom(Cat.class));
		System.out.println(Felid.class.isAssignableFrom(Cat.class));
		System.out.println("------------------------------------");
		System.out.println(DC.class.isAssignableFrom(Felid.class));
		System.out.println(DC.class.isAssignableFrom(Cat.class));
		System.out.println("------------------------------------");
		
		
		/**
		instanceof运算符 只被用于对象引用变量，检查左边的被测试对象 是不是 右边类或接口的 实例化。如果被测对象是null值，则测试结果总是false。 
		形象地：自身实例或子类实例 instanceof 自身类  返回true 
		例： 
		*/
		String s1 = new String("javaisland"); 
		System.out.println(s1 instanceof String); //true 

		/**
		Class类的isInstance(Object obj)方法，obj是被测试的对象，如果obj是调用这个方法的class或接口 的实例，则返回true。这个方法是instanceof运算符的动态等价。 
		形象地：自身类.class.isInstance(自身实例或子类实例)  返回true 
		例：
		*/
		Object s2 = new String("javaisland"); 
		System.out.println(String.class.isInstance(s2)); //true 

		/**
		Class类的isAssignableFrom(Class cls)方法，如果调用这个方法的class或接口 与 参数cls表示的类或接口相同，或者是参数cls表示的类或接口的父类，则返回true。 
		形象地：自身类.class.isAssignableFrom(自身类或子类.class)  返回true 
		例：		
		*/
		System.out.println(ArrayList.class.isAssignableFrom(Object.class));  //false 
		System.out.println(Object.class.isAssignableFrom(ArrayList.class));  //true

	}

}
