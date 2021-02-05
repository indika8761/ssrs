package com.persistent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.persistent.entity.Student;

public interface StudentRepository extends MongoRepository<Student, String> {

}
