package com.persistent.ssrs.common.dto;

import java.io.Serializable;

public class ClassDto implements Serializable {

	private static final long serialVersionUID = -2362334765704251316L;

	private String id;

	private String name;

	private String description;
	
	

	public ClassDto() {
		super();
	}
	
	

	public ClassDto(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
