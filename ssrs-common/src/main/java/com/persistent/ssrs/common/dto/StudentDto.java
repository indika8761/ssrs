package com.persistent.ssrs.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import com.persistent.ssrs.common.constent.ValidationMessage;
import com.persistent.ssrs.common.enums.Gender;

public class StudentDto implements Serializable {

	private static final long serialVersionUID = -4721976775486325772L;

	private String id;

	@NonNull
	@NotBlank(message = ValidationMessage.NAME_VALIDATION)
	private String firstName;

	private String lastName;

	private Gender gender;

	private Date birthDay;
	
	private String port;
	
	private List<EnrollmentDto> enrollments;

	@NonNull
	@NotBlank(message = ValidationMessage.EMAIL_VALIDATION_MANDATORY)
	@Email(message = ValidationMessage.EMAIL_VALIDATION_VALID)
	private String email;
	
	

	public StudentDto() {
		super();
	}
	
	

	public StudentDto(String id, String firstName, String lastName,Gender gender, Date birthDay, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.email = email;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public List<EnrollmentDto> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<EnrollmentDto> enrollments) {
		this.enrollments = enrollments;
	}
	
}
