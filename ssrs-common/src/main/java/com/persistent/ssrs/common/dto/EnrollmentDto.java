package com.persistent.ssrs.common.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import com.persistent.ssrs.common.constent.ValidationMessage;

public class EnrollmentDto implements Serializable {

	private static final long serialVersionUID = 1617857694747744912L;

	@NonNull
	@NotBlank(message = ValidationMessage.ENROLLMENT_VALIDATION_MANDATORY_STUDENT_ID)
	private String studentId;

	@NonNull
	@NotBlank(message = ValidationMessage.ENROLLMENT_VALIDATION_MANDATORY_CLASS_ID)
	private String classId;

	private boolean active;
	
	private String port;
	
	

	public EnrollmentDto() {
		super();
	}

	public EnrollmentDto(String studentId,String classId, boolean active) {
		super();
		this.studentId = studentId;
		this.classId = classId;
		this.active = active;
	}



	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	

}
