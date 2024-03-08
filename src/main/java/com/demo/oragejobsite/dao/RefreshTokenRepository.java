package com.demo.oragejobsite.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    RefreshToken findByTokenId(String tokenId);
}
