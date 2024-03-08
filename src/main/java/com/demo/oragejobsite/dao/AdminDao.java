package com.demo.oragejobsite.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.Admin;


@Repository
public interface AdminDao extends MongoRepository<Admin, String>{

	Admin findByAdminMail(String adminMail);
	Optional<Admin> findByAdminid(String adminid);

}