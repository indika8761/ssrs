package com.persistent.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.persistent.entity.Enrollment;
import com.persistent.entity.pk.EnrollmentPk;
import com.persistent.repository.EnrollmentRepository;
import com.persistent.service.EnrollmentService;
import com.persistent.ssrs.common.dto.EnrollmentDto;
import com.persistent.ssrs.common.service.AbstractServiceImpl;

@Service
public class EnrollmentServiceImpl extends AbstractServiceImpl<Enrollment, EnrollmentDto, EnrollmentPk>
		implements EnrollmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentServiceImpl.class);

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	@Override
	public MongoRepository<Enrollment, EnrollmentPk> getRepository() {
		return enrollmentRepository;
	}

	@Override
	public Enrollment getEntity(EnrollmentDto d) {
		Enrollment enrollment = new Enrollment();
		enrollment.setActive(d.isActive());
		enrollment.setId(new EnrollmentPk(d.getStudentId(), d.getClassId()));
		return enrollment;
	}

	@Override
	public EnrollmentDto getDto(Enrollment entity) {
		EnrollmentDto dto = new EnrollmentDto();
		dto.setActive(entity.isActive());
		if (entity.getId() != null) {
			dto.setClassId(entity.getId().getClassId());
			dto.setStudentId(entity.getId().getStudentId());
		}
		return dto;
	}

	@Override
	public List<EnrollmentDto> getByStudentId(String studentId) {
		List<Enrollment> list=enrollmentRepository.findByStudentId(studentId);
		return super.mapTo(list);
	}

}
