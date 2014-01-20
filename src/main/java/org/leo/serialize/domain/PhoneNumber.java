package org.leo.serialize.domain;

import java.io.Serializable;

public class PhoneNumber implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8793163588059321528L;
	
	private String number;
	
	private PhoneType type;
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

}
