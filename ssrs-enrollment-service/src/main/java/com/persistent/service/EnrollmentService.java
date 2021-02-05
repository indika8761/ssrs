package com.persistent.service;

import java.util.List;

import com.persistent.entity.pk.EnrollmentPk;
import com.persistent.ssrs.common.dto.EnrollmentDto;
import com.persistent.ssrs.common.service.AbstractService;

public interface EnrollmentService extends AbstractService<EnrollmentDto, EnrollmentPk> {

	List<EnrollmentDto> getByStudentId(String studentId);

}
