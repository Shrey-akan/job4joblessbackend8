package com.demo.oragejobsite.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.User;
@Repository
public interface UserDao extends MongoRepository<User, String>{
	 Optional<User> findByUid(String uid);
	User findByUserName(String userName);
}


