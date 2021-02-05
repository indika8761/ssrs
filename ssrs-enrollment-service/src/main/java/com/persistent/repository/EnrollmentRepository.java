package com.persistent.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.persistent.entity.Enrollment;
import com.persistent.entity.pk.EnrollmentPk;

public interface EnrollmentRepository extends MongoRepository<Enrollment, EnrollmentPk> {

	@Query("{'_id.studentId': ?0}")
	List<Enrollment> findByStudentId(String studentId);

}
