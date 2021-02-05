package com.persistent.serviceproxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.persistent.ssrs.common.dto.EnrollmentDto;

@FeignClient(name = "enrollment-service", fallbackFactory = EnrollmentServiceProxyFallbackFactory.class)
public interface EnrollmentServiceProxy {

	@GetMapping("/api/v1/enrollment/student/{studentId}")
	public ResponseEntity<List<EnrollmentDto>> getByStudentId(@PathVariable("studentId") String studentId);

	@GetMapping("/api/v1/enrollment/id/{studentId}/{classId}")
	public ResponseEntity<EnrollmentDto> getById(@PathVariable("studentId") String studentId,
			@PathVariable("classId") String classId);
}
