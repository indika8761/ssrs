package com.persistent.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.persistent.entity.Enrollment;
import com.persistent.entity.pk.EnrollmentPk;
import com.persistent.repository.EnrollmentRepository;
import com.persistent.service.impl.EnrollmentServiceImpl;
import com.persistent.ssrs.common.dto.EnrollmentDto;

@RunWith(EasyMockRunner.class)
public class EnrollmentServiceTest {

	@TestSubject
	private EnrollmentServiceImpl service = new EnrollmentServiceImpl();

	@Mock
	private EnrollmentRepository enrollmentRepositoryMock;


	EnrollmentPk pk1=new EnrollmentPk("SUD0001", "CL0001");
	EnrollmentPk pk2=new EnrollmentPk("SUD0002", "CL0001");
	EnrollmentPk pk3=new EnrollmentPk("SUD0003", "CL0001");
	private Enrollment enrollment1 = new Enrollment(pk1,true);
	private Enrollment enrollment2 = new Enrollment(pk2,true);

	EnrollmentDto enrollmentDto1 = new EnrollmentDto("SUD0001","CL0001",true);

	@Before
	public void setUp() {



		expect(enrollmentRepositoryMock.findById(pk1)).andReturn(Optional.of(enrollment1));
		expect(enrollmentRepositoryMock.findById(pk2)).andReturn(Optional.of(enrollment2));
		expect(enrollmentRepositoryMock.findById(pk3)).andReturn((Optional.<Enrollment>empty()));

		List<Enrollment> enrollments = new ArrayList<>();

		enrollments.add(enrollment1);
		enrollments.add(enrollment2);

		expect(enrollmentRepositoryMock.findAll()).andReturn(enrollments);
		
		List<Enrollment> enrollmentsByStudent = new ArrayList<>();

		enrollmentsByStudent.add(enrollment1);
		expect(enrollmentRepositoryMock.findByStudentId("SUD0001")).andReturn(enrollmentsByStudent);
		
		expect(enrollmentRepositoryMock.save(enrollment1)).andReturn(enrollment1);
		enrollmentRepositoryMock.deleteById(pk1);

		replay(enrollmentRepositoryMock);
		
	}

	@Test
	public void testSave() {

		EnrollmentDto enrollment = service.save(enrollmentDto1);
		assertEquals(enrollment.getClassId(), enrollmentDto1.getClassId());
		assertEquals(enrollment.getStudentId(), enrollmentDto1.getStudentId());
	}

	@Test
	public void testUpdate() {

		EnrollmentDto enrollment= service.update(enrollmentDto1);
		assertEquals(enrollment.getClassId(), enrollmentDto1.getClassId());
		assertEquals(enrollment.getStudentId(), enrollmentDto1.getStudentId());
	}

	@Test
	public void testDelete() {

		service.delete(pk1);

	}

	@Test
	public void testGetById() {

		EnrollmentDto enrollmentDto = service.get(pk1);
		assertNotNull(enrollmentDto);
	}

	@Test
	public void testGetByIdNotFound() {

		EnrollmentDto enrollmentDto = service.get(pk3);
		assertNull(enrollmentDto);
	}

	@Test
	public void testGet() {

		List<EnrollmentDto> enrollmentDtos = service.get();
		assertNotNull(enrollmentDtos);
	}

	@Test
	public void testGetEntity() {
		EnrollmentDto enrollmentDto = new EnrollmentDto();
		Enrollment enrollment = service.getEntity(enrollmentDto);
		assertNotNull(enrollment);
	}

	@Test
	public void testGetDto() {
		Enrollment enrollment = new Enrollment();
		EnrollmentDto enrollmentDto = service.getDto(enrollment);
		assertNotNull(enrollmentDto);
	}

	@Test
	public void testGetLogger() {
		Logger logger = service.getLogger();
		assertNotNull(logger);
	}

	@Test
	public void testStudentEqule() {
		assertFalse(enrollment1.equals(enrollment2));
	}

	@Test
	public void testStudentHashCode() {
		assertNotEquals(enrollment1.hashCode(), enrollment2.hashCode());
	}
	
	@Test
	public void testGetByStudentId() {

		List<EnrollmentDto> enrollmentDtos = service.getByStudentId("SUD0001");
		assertNotNull(enrollmentDtos);
	}

}
