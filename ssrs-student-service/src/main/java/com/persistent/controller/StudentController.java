package com.persistent.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.service.StudentService;
import com.persistent.ssrs.common.controller.AbstractController;
import com.persistent.ssrs.common.dto.StudentDto;
import com.persistent.ssrs.common.service.AbstractService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/student")
@Tag(name = "Student", description = "The Student API")
public class StudentController extends AbstractController<StudentDto, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	@Override
	public AbstractService<StudentDto, String> getAbstractService() {
		return studentService;
	}

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@Operation(summary = "Create a Student", tags = { "Student" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Student created successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
	@Override
	public ResponseEntity<StudentDto> save(@Valid StudentDto dto) throws Exception {
		return super.save(dto);
	}

	@Operation(summary = "Update a Student", tags = { "Student" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student updated successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
			@ApiResponse(responseCode = "404", description = "No Student exists with given id", content = @Content) })
	@Override
	public ResponseEntity<StudentDto> update(@Valid StudentDto dto) throws Exception {
		return super.update(dto);
	}

	@Operation(summary = "Delete a Student", tags = { "Student" })
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Student deleted"),
			@ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
	@Override
	public ResponseEntity<?> delete(String id) throws Exception {
		return super.delete(id);
	}

	@Operation(summary = "Get all Student", tags = { "Student" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "found Students", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StudentDto.class))) }),
			@ApiResponse(responseCode = "404", description = "No Students found", content = @Content) })
	@Override
	public ResponseEntity<List<StudentDto>> get() {
		return super.get();
	}

	@Operation(summary = "Get a Student by Student id", tags = { "Student" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "found the Student", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Student not found", content = @Content) })
	@Override
	public ResponseEntity<StudentDto> get(String id) {
		return super.get(id);
	}

}
