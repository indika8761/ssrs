package com.persistent.tests.controller;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.persistent.controller.StudentController;
import com.persistent.service.StudentService;
import com.persistent.ssrs.common.dto.StudentDto;
import com.persistent.ssrs.common.enums.Gender;

//@RunWith(EasyMockRunner.class)
public class StudentControllerTestCase {

	@TestSubject
	private StudentController studentController;

	// @Mock
	private StudentService studentService;

	private ZoneId defaultZoneId = ZoneId.systemDefault();
	private LocalDate localDate1 = LocalDate.of(1987, Month.JUNE, 1);
	private LocalDate localDate2 = LocalDate.of(1991, Month.JUNE, 1);
	private StudentDto studentDto1 = new StudentDto("SUD0001", "Indika", "Gunathilaka", Gender.MEAL,
			Date.from(localDate1.atStartOfDay(defaultZoneId).toInstant()), "indika@gmil.com");

	private StudentDto studentDto2 = new StudentDto("SUD0002", "Nuwan", "Gunathilaka", Gender.MEAL,
			Date.from(localDate2.atStartOfDay(defaultZoneId).toInstant()), "nuwan@gmil.com");

	@Before
	public void setUp() {

		studentService = createMock(StudentService.class);
		studentController = new StudentController(studentService);

		expect(studentService.get("SUD0001")).andReturn(studentDto1);
		expect(studentService.get("SUD0003")).andReturn(null);
		

		List<StudentDto> studentDtos = new ArrayList<>();

		studentDtos.add(studentDto1);
		studentDtos.add(studentDto2);

		expect(studentService.get()).andReturn(studentDtos);

		expect(studentService.save(studentDto1)).andReturn(studentDto1);
		expect(studentService.update(studentDto1)).andReturn(studentDto1);
		studentService.delete("SUD0001");
		replay(studentService);



	}

	@Test
	public void testSave() throws Exception {
		ResponseEntity<StudentDto> student = studentController.save(studentDto1);
		assertEquals(student.getBody().getId(), studentDto1.getId());
	}

	@Test
	public void testUpdate() throws Exception {
		ResponseEntity<StudentDto> student = studentController.update(studentDto1);
		assertEquals(student.getBody().getId(), studentDto1.getId());
	}

	@Test
	public void testDelete() throws Exception {
		ResponseEntity<?> student = studentController.delete("SUD0001");
		assertNull(student.getBody());

	}

	@Test
	public void testGetById() {
		ResponseEntity<StudentDto> responseEntity = studentController.get("SUD0001");
		assertNotNull(responseEntity.getBody());
	}

	@Test
	public void testGet() {
		ResponseEntity<List<StudentDto>> responseEntity = studentController.get();
		assertNotNull(responseEntity.getBody());
	}

}
