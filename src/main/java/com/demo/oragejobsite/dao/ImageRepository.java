package com.demo.oragejobsite.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.ImageEntity;

@Repository
public interface ImageRepository extends MongoRepository<ImageEntity, String> {
    List<ImageEntity> findByUid(String uid);
}

