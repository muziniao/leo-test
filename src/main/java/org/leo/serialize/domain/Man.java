package org.leo.serialize.domain;

import java.io.Serializable;

public class Man extends People implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4634741880940129060L;

	public static String SEX = "M";
	
	public transient String stran = "ggg";
	
	
	private String name;
	
	private int age;
	
	//private String id;
	
	public Man(){
	}
	
	public Man(String name, Integer age){
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
