package com.persistent.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.persistent.entity.pk.EnrollmentPk;

@Document(collection = "enrollments")
public class Enrollment implements Serializable {

	private static final long serialVersionUID = -8392185299027193819L;

	@Id
	private EnrollmentPk id;

	private boolean active;


	public Enrollment() {
		super();
	}

	public Enrollment(EnrollmentPk id, boolean active) {
		super();
		this.id = id;
		this.active = active;
	}

	public EnrollmentPk getId() {
		return id;
	}

	public void setId(EnrollmentPk id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollment other = (Enrollment) obj;
		if (active != other.active)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
