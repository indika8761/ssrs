package com.persistent.controller;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;

import com.persistent.entity.pk.EnrollmentPk;
import com.persistent.service.EnrollmentService;
import com.persistent.ssrs.common.dto.EnrollmentDto;

@RunWith(EasyMockRunner.class)
public class EnrollmentControllerTest {

	@TestSubject
	private  EnrollmentController enrollmentController = new  EnrollmentController();

	@Mock
	private EnrollmentService enrollmentServiceMock;

	
	private EnrollmentDto enrollmentDto1 = new EnrollmentDto("SUD0001","CL0001",true);

	private EnrollmentDto enrollmentDto2 = new EnrollmentDto("SUD0002","CL0001",true);

	@Before
	public void setUp() {

		expect(enrollmentServiceMock.get(new EnrollmentPk("SUD0001","CL0001"))).andReturn(enrollmentDto1);
		expect(enrollmentServiceMock.get(new EnrollmentPk("SUD0003","CL0001"))).andReturn(null);

		List<EnrollmentDto> enrollmentDtos = new ArrayList<>();

		enrollmentDtos.add(enrollmentDto1);
		enrollmentDtos.add(enrollmentDto2);

		expect(enrollmentServiceMock.get()).andReturn(enrollmentDtos);
		
		List<EnrollmentDto> enrollmentDtosByStudent = new ArrayList<>();

		enrollmentDtosByStudent.add(enrollmentDto1);
		
		expect(enrollmentServiceMock.getByStudentId("SUD0001")).andReturn(enrollmentDtosByStudent);

		expect(enrollmentServiceMock.save(enrollmentDto1)).andReturn(enrollmentDto1);
		expect(enrollmentServiceMock.update(enrollmentDto1)).andReturn(enrollmentDto1);
		enrollmentServiceMock.delete(new EnrollmentPk("SUD0001","CL0001"));

		replay(enrollmentServiceMock);

	}

	@Test
	public void testSave() throws Exception {

		ResponseEntity<EnrollmentDto> enrollment = enrollmentController.save(enrollmentDto1);
		assertEquals(enrollment.getBody().getClassId(), enrollmentDto1.getClassId());
	}

	@Test
	public void testUpdate() throws Exception {

		ResponseEntity<EnrollmentDto> enrollment = enrollmentController.update(enrollmentDto1);
		assertEquals(enrollment.getBody().getClassId(), enrollmentDto1.getClassId());
	}

	@Test
	public void testDelete() throws Exception {

		ResponseEntity<?> enrollment = enrollmentController.delete(new EnrollmentPk("SUD0001","CL0001"));
		assertNull(enrollment.getBody());

	}

	@Test
	public void testGetById() {

		ResponseEntity<EnrollmentDto> responseEntity = enrollmentController.get(new EnrollmentPk("SUD0001","CL0001"));
		assertNotNull(responseEntity.getBody());
	}

	@Test
	public void testGet() {

		ResponseEntity<List<EnrollmentDto>> responseEntity = enrollmentController.get();
		assertNotNull(responseEntity.getBody());
	}
	
	@Test
	public void testGetByStudentId() {

		ResponseEntity<List<EnrollmentDto>> responseEntity = enrollmentController.get("SUD0001");
		assertNotNull(responseEntity.getBody());
	}

}
