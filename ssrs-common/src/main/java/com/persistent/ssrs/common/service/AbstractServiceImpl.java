package com.persistent.ssrs.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractServiceImpl<E extends Serializable, D extends Serializable, I extends Serializable> {

	public abstract Logger getLogger();

	public abstract MongoRepository<E, I> getRepository();

	public abstract E getEntity(D d);

	public abstract D getDto(E entity);

	public D save(D dto) {
		getLogger().info("Save ",dto);
		E entity = getRepository().save(getEntity(dto));
		return getDto(entity);
	}

	public D update(D dto) {
		getLogger().info("Update ",dto);
		return save(dto);
	}

	public void delete(@PathVariable I id) {
		getLogger().info("Delete ",id);
		getRepository().deleteById(id);

	}

	public List<D> get() {
		getLogger().info("Get All objects");
		List<E> entities = getRepository().findAll();
		
		return  mapTo(entities);
	}

	public D get(I id) {
		getLogger().info("Get object by id %d",id);
		Optional<E> responce = getRepository().findById(id);
		return responce.isPresent() ? getDto(responce.get()) : null;
	}
	
	public List<D> mapTo(List<E> list){
		List<D> dtos = list.stream().map(e -> getDto(e)).collect(Collectors.toList());
		return dtos;
	}

}
