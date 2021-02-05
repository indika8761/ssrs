package com.persistent.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.service.ClassService;
import com.persistent.ssrs.common.controller.AbstractController;
import com.persistent.ssrs.common.dto.ClassDto;
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
@RequestMapping(value = "api/v1/class")
@Tag(name = "Class", description = "The Class API")
public class ClassController extends AbstractController<ClassDto, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

	@Autowired
	private ClassService classService;

	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	@Override
	public AbstractService<ClassDto, String> getAbstractService() {
		return classService;
	}

	@Operation(summary = "Create a Class", tags = { "Class" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Class created successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClassDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
	@Override
	public ResponseEntity<ClassDto> save(@Valid ClassDto dto) throws Exception {
	
		return super.save(dto);
	}

	@Operation(summary = "Update a Class", tags = { "Class" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Class updated successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClassDto.class)) }),
			@ApiResponse(responseCode = "404", description = "No Class exists with given id", content = @Content) })
	@Override
	public ResponseEntity<ClassDto> update(@Valid ClassDto dto) throws Exception {
		return super.update(dto);
	}

	@Operation(summary = "Delete a Class", tags = { "Class" })
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Class deleted"),
			@ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
	@Override
	public ResponseEntity<?> delete(String id) throws Exception {
		return super.delete(id);
	}

	@Operation(summary = "Get all Class", tags = { "Class" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "found Class", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClassDto.class))) }),
			@ApiResponse(responseCode = "404", description = "No Class found", content = @Content) })
	@Override
	public ResponseEntity<List<ClassDto>> get() {
		return super.get();
	}

	@Operation(summary = "Get a Student by Class id", tags = { "Class" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "found the Class", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClassDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Class not found", content = @Content) })
	@Override
	public ResponseEntity<ClassDto> get(String id) {
		return super.get(id);
	}
	
	

}
