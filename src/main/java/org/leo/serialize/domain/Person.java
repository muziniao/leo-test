package org.leo.serialize.domain;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5395864635814629093L;

	private String name;
	
	private int id;
	
	private String email;
	
	private List<PhoneNumber> phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PhoneNumber> getPhone() {
		return phone;
	}

	public void setPhone(List<PhoneNumber> phone) {
		this.phone = phone;
	}
	
	
}
