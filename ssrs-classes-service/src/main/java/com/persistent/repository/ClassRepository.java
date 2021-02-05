package com.persistent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.persistent.entity.Class;

public interface ClassRepository extends MongoRepository<Class, String> {

}
