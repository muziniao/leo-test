package org.leo.serialize.domain;

import java.io.Serializable;
import java.util.List;

public class AddressBook implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3102939402203785171L;
	private List<Person> person;

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}
}
