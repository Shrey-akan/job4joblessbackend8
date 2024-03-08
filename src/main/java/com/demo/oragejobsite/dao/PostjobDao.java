package com.demo.oragejobsite.dao;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.PostJob;


@Repository
public interface PostjobDao extends MongoRepository<PostJob, String>{

	PostJob findByJobid(String jobid);

	List<PostJob> findByEmpid(String empid);

	List<PostJob> findByEmpidAndArchiveTrue(String empid);

	List<PostJob> findByArchiveTrue();


	
}

