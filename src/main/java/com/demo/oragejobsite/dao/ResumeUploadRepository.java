package com.demo.oragejobsite.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.ResumeUpload;
@Repository
public interface ResumeUploadRepository extends MongoRepository<ResumeUpload, String> {
    // Add custom queries or methods if needed
	   ResumeUpload findByUid(String uid);
}

