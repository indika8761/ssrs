package com.persistent.service.impl;

import com.persistent.ssrs.common.dto.ClassDto;
import com.persistent.ssrs.common.service.AbstractServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.persistent.entity.Class;
import com.persistent.repository.ClassRepository;
import com.persistent.service.ClassService;

@Service
public class ClassServiceImpl extends AbstractServiceImpl<Class, ClassDto, String> implements ClassService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Autowired
	private ClassRepository classRepository;

	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	@Override
	public MongoRepository<Class, String> getRepository() {
		return classRepository;
	}

	@Override
	public Class getEntity(ClassDto d) {
		Class classEntity = new Class();
		classEntity.setId(d.getId());
		classEntity.setName(d.getName());
		classEntity.setDescription(d.getDescription());
		return classEntity;
	}

	@Override
	public ClassDto getDto(Class entity) {
		ClassDto dto = new ClassDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		return dto;
	}

}
