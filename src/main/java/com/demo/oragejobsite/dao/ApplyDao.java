package com.demo.oragejobsite.dao;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.ApplyJob;

@Repository
public interface ApplyDao extends MongoRepository<ApplyJob, String>{
	 // Define a custom method to find an ApplyJob entity by juid
    ApplyJob findByJuid(String juid);

    List<ApplyJob> findByEmpidAndJobid(String empid, String jobid);

	List<ApplyJob> findByEmpid(String empid);

	List<ApplyJob> findByUid(String uid);

}

