package com.persistent.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.entity.pk.EnrollmentPk;
import com.persistent.service.EnrollmentService;
import com.persistent.ssrs.common.controller.AbstractController;
import com.persistent.ssrs.common.dto.EnrollmentDto;
import com.persistent.ssrs.common.service.AbstractService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/enrollment")
@Tag(name = "Enrollment", description = "The Enrollment API")
public class EnrollmentController extends AbstractController<EnrollmentDto, EnrollmentPk> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentController.class);

	@Autowired
	private EnrollmentService enrollmentService;

	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	@Override
	public AbstractService<EnrollmentDto, EnrollmentPk> getAbstractService() {
		return enrollmentService;
	}
	
	
	@Operation(summary = "Create a Enrollment", tags = { "Enrollment" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Enrollment created successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EnrollmentDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
	@Override
	public ResponseEntity<EnrollmentDto> save(@Valid EnrollmentDto dto) throws Exception {
		return super.save(dto);
	}

	@Operation(summary = "Update a Enrollment", tags = { "Enrollment" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Enrollment updated successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EnrollmentDto.class)) }),
			@ApiResponse(responseCode = "404", description = "No Student exists with given id", content = @Content) })
	@Override
	public ResponseEntity<EnrollmentDto> update(@Valid EnrollmentDto dto) throws Exception {
		return super.update(dto);
	}


	@Operation(summary = "Get all Enrollment", tags = { "Enrollment" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "found Enrollments", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EnrollmentDto.class))) }),
			@ApiResponse(responseCode = "404", description = "No Enrollments found", content = @Content) })
	@Override
	public ResponseEntity<List<EnrollmentDto>> get() {
		return super.get();
	}

	@Operation(summary = "Delete a Enrollment", tags = { "Enrollment" })
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Enrollment deleted"),
			@ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
	@Override
	@DeleteMapping(value = "/id/{studentId}/{classId}")
	public ResponseEntity<?> delete(EnrollmentPk id) throws Exception {
		return super.delete(id);
	}

	@Operation(summary = "Get a Enrollment by student id and class id", tags = { "Enrollment" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "found the Enrollment", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EnrollmentDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Enrollment not found", content = @Content) })
	@Override
	@GetMapping(value = "/id/{studentId}/{classId}")
	public ResponseEntity<EnrollmentDto> get(EnrollmentPk id) {
		return super.get(id);
	}
	
	@Operation(summary = "Get a Enrollment by student id", tags = { "Enrollment" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "found the Enrollment", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EnrollmentDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Enrollment not found", content = @Content) })
	@GetMapping(value = "/student/{studentId}")
	public ResponseEntity<List<EnrollmentDto>> get(@PathVariable String studentId) {
		List<EnrollmentDto> responce = enrollmentService.getByStudentId(studentId);
		return new ResponseEntity<>(responce, HttpStatus.OK);
	}

}
