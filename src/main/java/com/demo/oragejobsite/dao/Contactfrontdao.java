package com.demo.oragejobsite.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.DirectConntact;

@Repository
public interface Contactfrontdao extends MongoRepository<DirectConntact, String>{

}
