package com.persistent.ssrs.common.service;

import java.io.Serializable;
import java.util.List;

public interface AbstractService<D extends Serializable, I extends Serializable> {

	public D save(D entity);

	public D update(D entity);

	public void delete(I id);

	public D get(I id);

	public List<D> get();

}
