package com.persistent.service;

import com.persistent.ssrs.common.dto.EnrollmentDto;
import com.persistent.ssrs.common.dto.StudentDto;
import com.persistent.ssrs.common.service.AbstractService;

public interface StudentService extends AbstractService<StudentDto, String> {


	EnrollmentDto getEnrollmentDto(String studentId, String classId);
}
