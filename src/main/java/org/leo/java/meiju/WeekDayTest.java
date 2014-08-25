package org.leo.java.meiju;

import org.junit.Test;

public class WeekDayTest {

	//@Test
	public void test1() {
		WeekDay day = WeekDay.MON;
		System.out.println(day); // 自动重载了toString()方法
		System.out.println(day.name());// 输出枚举对象的名字
		System.out.println(day.ordinal());// 输出在枚举中的顺序基于0
		System.out.println(WeekDay.valueOf("SAT").toString()); // 通过传递一个参数字符串来构造一个对象静态方法
		// 我们在网页提交数据的时候,提交的是字符串我们可以在本地
		System.out.println(WeekDay.values().length);// 静态方法获得一个枚举的长度
	}
	
	@Test
	public void test2() {
		WeekDay2 day = WeekDay2.MON;
		System.out.println(day); // 自动重载了toString()方法
		System.out.println(day.name());// 输出枚举对象的名字
		System.out.println(day.ordinal());// 输出在枚举中的顺序基于0
		System.out.println(WeekDay.valueOf("SAT").toString()); // 通过传递一个参数字符串来构造一个对象静态方法
		// 我们在网页提交数据的时候,提交的是字符串我们可以在本地
		System.out.println(WeekDay.values().length);// 静态方法获得一个枚举的长度
	}
}
