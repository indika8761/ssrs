package com.persistent.tests.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.persistent.entity.Student;
import com.persistent.repository.StudentRepository;
import com.persistent.service.impl.StudentServiceImpl;
import com.persistent.serviceproxy.EnrollmentServiceProxy;
import com.persistent.ssrs.common.dto.EnrollmentDto;
import com.persistent.ssrs.common.dto.StudentDto;
import com.persistent.ssrs.common.enums.Gender;

//@RunWith(EasyMockRunner.class)
public class StudentServiceTestCase {

	@TestSubject
	private StudentServiceImpl service;

	private StudentRepository studentRepository;

	private EnrollmentServiceProxy enrollmentServiceProxy;

	private Environment environment;

	private ZoneId defaultZoneId = ZoneId.systemDefault();
	private LocalDate localDate1 = LocalDate.of(1987, Month.JUNE, 1);
	private Student student1 = new Student("SUD0001", "Indika", "Gunathilaka", Gender.MEAL,
			Date.from(localDate1.atStartOfDay(defaultZoneId).toInstant()), "indika@gmil.com");
	private LocalDate localDate2 = LocalDate.of(1991, Month.JUNE, 1);
	private Student student2 = new Student("SUD0002", "Nuwan", "Gunathilaka", Gender.MEAL,
			Date.from(localDate2.atStartOfDay(defaultZoneId).toInstant()), "nuwan@gmil.com");

	StudentDto studentDto1 = new StudentDto("SUD0001", "Indika", "Gunathilaka", Gender.MEAL,
			Date.from(localDate1.atStartOfDay(defaultZoneId).toInstant()), "indika@gmil.com");

	@Before
	public void setUp() {

		studentRepository = createMock(StudentRepository.class);
		enrollmentServiceProxy = createMock(EnrollmentServiceProxy.class);
		environment = createMock(Environment.class);

		service = new StudentServiceImpl(studentRepository, environment, enrollmentServiceProxy);

		expect(studentRepository.findById("SUD0001")).andReturn(Optional.of(student1));
		expect(studentRepository.findById("SUD0002")).andReturn(Optional.of(student2));
		expect(studentRepository.findById("SUD0003")).andReturn((Optional.<Student>empty()));

		List<Student> students = new ArrayList<>();

		students.add(student1);
		students.add(student2);

		expect(studentRepository.findAll()).andReturn(students);

		expect(studentRepository.save(student1)).andReturn(student1);
		studentRepository.deleteById("SUD0001");

		replay(studentRepository);
		
		List<EnrollmentDto> body= new ArrayList<>();
		EnrollmentDto enrollmentDto1 = new EnrollmentDto("SUD0001", "CL0001", true);
		EnrollmentDto enrollmentDto2 = new EnrollmentDto("SUD0001", "CL0002", true);
		body.add(enrollmentDto1);
		body.add(enrollmentDto2);
		ResponseEntity<List<EnrollmentDto>> responseEntity =new ResponseEntity<List<EnrollmentDto>>(body,HttpStatus.OK);

		expect(enrollmentServiceProxy.getByStudentId("SUD0001")).andReturn(responseEntity);
		responseEntity = new ResponseEntity<List<EnrollmentDto>>(new ArrayList<>(), HttpStatus.OK);
		expect(enrollmentServiceProxy.getByStudentId("SUD0003")).andReturn(responseEntity);
		replay(enrollmentServiceProxy);

		expect(environment.getProperty("local.server.port")).andReturn("8080");
		expect(environment.getProperty("local.server.port")).andReturn("8080");
		replay(environment);
	}

	@Test
	public void testSave() {

		StudentDto student = service.save(studentDto1);
		assertEquals(student.getId(), studentDto1.getId());
	}

	@Test
	public void testUpdate() {

		StudentDto student = service.update(studentDto1);
		assertEquals(student.getId(), studentDto1.getId());
	}

	@Test
	public void testDelete() {

		service.delete("SUD0001");

	}

	@Test
	public void testGetById() {

		StudentDto studentDto = service.get("SUD0001");
		assertNotNull(studentDto);
	}

	@Test
	public void testGetByIdNotFound() {

		StudentDto studentDto = service.get("SUD0003");
		assertNull(studentDto);
	}

	@Test
	public void testGet() {

		List<StudentDto> studentDtos = service.get();
		assertNotNull(studentDtos);
	}

	@Test
	public void testGetEntity() {
		StudentDto studentDto = new StudentDto();
		Student student = service.getEntity(studentDto);
		assertNotNull(student);
	}

	@Test
	public void testGetDto() {
		Student student = new Student();
		StudentDto studentDto = service.getDto(student);
		assertNotNull(studentDto);
	}

	@Test
	public void testGetLogger() {
		Logger logger = service.getLogger();
		assertNotNull(logger);
	}

	@Test
	public void testStudentEqule() {
		assertNotEquals(student1, student2);
	}

	@Test
	public void testStudentHashCode() {
		assertNotEquals(student1.hashCode(), student2.hashCode());
	}

}
