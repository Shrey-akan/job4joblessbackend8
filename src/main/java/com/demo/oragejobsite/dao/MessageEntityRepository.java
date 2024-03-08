package com.demo.oragejobsite.dao;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.SendMessage;
@Repository
public interface MessageEntityRepository extends MongoRepository<SendMessage, String> {

    // You can add custom queries here if needed

}
