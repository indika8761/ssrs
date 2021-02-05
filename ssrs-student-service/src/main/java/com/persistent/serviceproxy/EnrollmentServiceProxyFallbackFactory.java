package com.persistent.serviceproxy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.persistent.ssrs.common.dto.EnrollmentDto;

import feign.hystrix.FallbackFactory;

@Component
public class EnrollmentServiceProxyFallbackFactory implements FallbackFactory<EnrollmentServiceProxy> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentServiceProxyFallbackFactory.class);

	@Override
	public EnrollmentServiceProxy create(Throwable cause) {
		return new EnrollmentServiceProxy() {
			@Override
			public ResponseEntity<List<EnrollmentDto>> getByStudentId(String studentId) {
				LOGGER.warn("Can not reach enrollment service.");
				List<EnrollmentDto> dtos = new ArrayList<>();
				return new ResponseEntity<>(dtos, HttpStatus.OK);
			}

			@Override
			public ResponseEntity<EnrollmentDto> getById(String studentId, String classId) {
				LOGGER.warn("Can not reach enrollment service.");
				EnrollmentDto dto = new EnrollmentDto();
				return new ResponseEntity<>(dto, HttpStatus.OK);
			}
		};
	}

}
