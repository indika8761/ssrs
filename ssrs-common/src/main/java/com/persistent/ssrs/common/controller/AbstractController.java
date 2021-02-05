package com.persistent.ssrs.common.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.persistent.ssrs.common.service.AbstractService;

public abstract class AbstractController<D extends Serializable, I extends Serializable> {

	public abstract Logger getLogger();

	public abstract AbstractService<D, I> getAbstractService();

	@PostMapping
	public ResponseEntity<D> save(@Valid @RequestBody D dto) throws Exception {
		getLogger().info("Save ", dto);
		D responce = getAbstractService().save(dto);
		return new ResponseEntity<>(responce, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<D> update(@Valid @RequestBody D dto) throws Exception {
		getLogger().info("Update ", dto);
		D responce = getAbstractService().update(dto);
		return new ResponseEntity<>(responce, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable I id) throws Exception {
		getLogger().info("Delete ", id);
		getAbstractService().delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<D>> get() {
		getLogger().info("Get All objects");
		List<D> responce = getAbstractService().get();
		return new ResponseEntity<>(responce, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<D> get(@PathVariable("id") I id) {
		getLogger().info("Get object by id %d", id);
		D responce = getAbstractService().get(id);
		return new ResponseEntity<>(responce, HttpStatus.OK);
	}

}
