package org.leo.serialize.domain;

import java.io.Serializable;

public class People implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6060652797409346110L;

	
	private String color;


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}
	
}
