package com.demo.oragejobsite.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.QuizQuestion;

@Repository
public interface QuizQuestionRepository extends MongoRepository<QuizQuestion, String>{

	List<QuizQuestion> findByJobid(String jobid);
	 boolean existsByJobid(String jobid);
}	

