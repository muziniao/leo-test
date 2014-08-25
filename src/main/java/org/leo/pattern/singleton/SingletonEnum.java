package org.leo.pattern.singleton;

public enum SingletonEnum {

	INSTANCE01, INSTANCE02;

	private String name;

	public String getName(){

		return name;

	}

	public void setName(String name){

		this.name = name;

	}

}
