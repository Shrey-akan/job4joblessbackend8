package com.demo.oragejobsite.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.SubAdminDetails;

@Repository
public interface SubAdminDao extends MongoRepository<SubAdminDetails, String>{

	SubAdminDetails findBySubadminmail(String email);

}
