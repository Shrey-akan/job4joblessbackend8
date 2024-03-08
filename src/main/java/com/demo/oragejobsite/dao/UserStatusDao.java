package com.demo.oragejobsite.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.UserStatus;

@Repository
public interface UserStatusDao extends MongoRepository<UserStatus, String>{

	UserStatus findByJuid(String juid);

	UserStatus findByUidAndJuid(String uid, String juid);

	List<UserStatus> findByUid(String uid);

	List<UserStatus> findByUidAndApplystatus(String uid, boolean viewcheck);

}
