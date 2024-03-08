package com.demo.oragejobsite.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.SendMessage;


@Repository
public interface SendMessageDao extends MongoRepository<SendMessage, String>{

}
