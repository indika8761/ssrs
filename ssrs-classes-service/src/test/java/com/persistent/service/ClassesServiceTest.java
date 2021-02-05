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

import com.persistent.entity.Class;
import com.persistent.repository.ClassRepository;
import com.persistent.service.impl.ClassServiceImpl;
import com.persistent.ssrs.common.dto.ClassDto;

@RunWith(EasyMockRunner.class)
public class ClassesServiceTest {

	@TestSubject
	private ClassServiceImpl service = new ClassServiceImpl();

	@Mock
	private ClassRepository classRepositoryMock;



	
	private Class class1 = new Class("CL0001","Maths","");
	private Class class2 = new Class("CL0002","Sceince","");

	ClassDto classDto1 = new ClassDto("CL0001","Maths","");

	@Before
	public void setUp() {



		expect(classRepositoryMock.findById("CL0001")).andReturn(Optional.of(class1));
		expect(classRepositoryMock.findById("CL0002")).andReturn(Optional.of(class2));
		expect(classRepositoryMock.findById("CL0003")).andReturn((Optional.<Class>empty()));

		List<Class> classes = new ArrayList<>();

		classes.add(class1);
		classes.add(class2);

		expect(classRepositoryMock.findAll()).andReturn(classes);

		expect(classRepositoryMock.save(class1)).andReturn(class1);
		classRepositoryMock.deleteById("CL0001");

		replay(classRepositoryMock);
	}

	@Test
	public void testSave() {

		ClassDto classDto = service.save(classDto1);
		assertEquals(classDto.getId(), classDto1.getId());
	}

	@Test
	public void testUpdate() {

		ClassDto classDto  = service.update(classDto1);
		assertEquals(classDto.getId(), classDto1.getId());
	}

	@Test
	public void testDelete() {

		service.delete("CL0001");

	}

	@Test
	public void testGetById() {

		ClassDto classDto = service.get("CL0001");
		assertNotNull(classDto);
	}

	@Test
	public void testGetByIdNotFound() {

		ClassDto classDto = service.get("CL0003");
		assertNull(classDto);
	}

	@Test
	public void testGet() {

		List<ClassDto> classDtos = service.get();
		assertNotNull(classDtos);
	}

	@Test
	public void testGetEntity() {
		ClassDto classDto = new ClassDto();
		Class classEntity = service.getEntity(classDto);
		assertNotNull(classEntity);
	}

	@Test
	public void testGetDto() {
		Class classEntity = new Class();
		ClassDto classDto = service.getDto(classEntity);
		assertNotNull(classDto);
	}

	@Test
	public void testGetLogger() {
		Logger logger = service.getLogger();
		assertNotNull(logger);
	}

	@Test
	public void testStudentEqule() {
		assertFalse(class1.equals(class2));
	}

	@Test
	public void testStudentHashCode() {
		assertNotEquals(class1.hashCode(), class2.hashCode());
	}

}
