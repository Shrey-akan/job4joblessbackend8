package com.demo.oragejobsite.dao;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.Employer;


@Repository
public interface EmployerDao extends MongoRepository<Employer, String>{

	Optional<Employer> findByEmpid(String empid);
	Employer findByEmpmailid(String empmailid);
}
