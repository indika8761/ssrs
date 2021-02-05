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

import com.persistent.service.ClassService;
import com.persistent.ssrs.common.dto.ClassDto;

@RunWith(EasyMockRunner.class)
public class ClassesControllerTest {

	@TestSubject
	private ClassController classController = new ClassController();

	@Mock
	private ClassService classServiceMock;

	private ClassDto classDto1 = new ClassDto("CL0001","Maths","");
	private ClassDto classDto2 = new ClassDto("CL0002","Sceince","");


	@Before
	public void setUp() {

		expect(classServiceMock.get("SUD0001")).andReturn(classDto1);
		expect(classServiceMock.get("SUD0003")).andReturn(null);

		List<ClassDto> classDtos = new ArrayList<>();

		classDtos.add(classDto1);
		classDtos.add(classDto2);

		expect(classServiceMock.get()).andReturn(classDtos);

		expect(classServiceMock.save(classDto1)).andReturn(classDto1);
		expect(classServiceMock.update(classDto1)).andReturn(classDto1);
		classServiceMock.delete("SUD0001");

		replay(classServiceMock);

	}

	@Test
	public void testSave() throws Exception {

		ResponseEntity<ClassDto> classDto = classController.save(classDto1);
		assertEquals(classDto.getBody().getId(), classDto1.getId());
	}

	@Test
	public void testUpdate() throws Exception {

		ResponseEntity<ClassDto> classDto = classController.update(classDto1);
		assertEquals(classDto.getBody().getId(), classDto1.getId());
	}

	@Test
	public void testDelete() throws Exception {

		ResponseEntity<?> student = classController.delete("SUD0001");
		assertNull(student.getBody());

	}

	@Test
	public void testGetById() {

		ResponseEntity<ClassDto> responseEntity = classController.get("SUD0001");
		assertNotNull(responseEntity.getBody());
	}

	@Test
	public void testGet() {

		ResponseEntity<List<ClassDto>> responseEntity = classController.get();
		assertNotNull(responseEntity.getBody());
	}

}
