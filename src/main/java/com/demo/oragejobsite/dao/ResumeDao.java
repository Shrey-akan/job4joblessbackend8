package com.demo.oragejobsite.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.ResumeBuilder;

@Repository
public interface ResumeDao extends MongoRepository<ResumeBuilder, String>{

}
