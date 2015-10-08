package org.leo.java.meiju;

public enum WeekDay2 {
	
	MON(1), TUS(), WED(3), THI, FRI, SAT, SUN;// 可以加;也可以不加
	
	private WeekDay2() {
		System.out.println("不带参数的构造方法!");
	}

	private WeekDay2(int i) {
		System.out.println("带参数的构造方法 !" + i);
	}
}
