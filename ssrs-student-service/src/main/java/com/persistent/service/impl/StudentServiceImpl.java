package com.persistent.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.persistent.entity.Student;
import com.persistent.repository.StudentRepository;
import com.persistent.service.StudentService;
import com.persistent.serviceproxy.EnrollmentServiceProxy;
import com.persistent.ssrs.common.dto.EnrollmentDto;
import com.persistent.ssrs.common.dto.StudentDto;
import com.persistent.ssrs.common.service.AbstractServiceImpl;

@Service
public class StudentServiceImpl extends AbstractServiceImpl<Student, StudentDto, String> implements StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);


	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private Environment environment;

	@Autowired
	private EnrollmentServiceProxy enrollmentServiceProxy;

	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	@Override
	public MongoRepository<Student, String> getRepository() {
		return studentRepository;
	}

	public StudentServiceImpl(StudentRepository studentRepository, Environment environment,
			EnrollmentServiceProxy enrollmentServiceProxy) {
		super();
		this.studentRepository = studentRepository;
		this.environment = environment;
		this.enrollmentServiceProxy = enrollmentServiceProxy;
	}

	@Override
	public Student getEntity(StudentDto d) {
		Student student = new Student();
		student.setBirthDay(d.getBirthDay());
		student.setEmail(d.getEmail());
		student.setFirstName(d.getFirstName());
		student.setGender(d.getGender());
		student.setId(d.getId());
		student.setLastName(d.getLastName());

		return student;
	}

	@Override
	public StudentDto getDto(Student entity) {
		StudentDto studentDto = new StudentDto();
		studentDto.setBirthDay(entity.getBirthDay());
		studentDto.setEmail(entity.getEmail());
		studentDto.setFirstName(entity.getFirstName());
		studentDto.setGender(entity.getGender());
		studentDto.setId(entity.getId());
		studentDto.setLastName(entity.getLastName());
		studentDto.setPort(environment.getProperty("local.server.port"));
		return studentDto;
	}

	@Override
	public StudentDto get(String studentId) {
		ResponseEntity<List<EnrollmentDto>> responseEntity = enrollmentServiceProxy.getByStudentId(studentId);
		StudentDto studentDto = super.get(studentId);
		if (studentDto != null) {
			studentDto.setEnrollments(responseEntity.getBody());
		}
		return studentDto;
	}

	@Override
	public EnrollmentDto getEnrollmentDto(String studentId, String classId) {
		ResponseEntity<EnrollmentDto> responseEntity = enrollmentServiceProxy.getById(studentId, classId);
		return responseEntity.getBody();
	}

}
