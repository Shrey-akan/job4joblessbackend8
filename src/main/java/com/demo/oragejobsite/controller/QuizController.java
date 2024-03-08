package com.demo.oragejobsite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.QuizQuestionRepository;
import com.demo.oragejobsite.entity.QuizQuestion;



@RestController
@CrossOrigin(origins = "${myapp.url}")
public class QuizController {

    @Autowired
    private QuizQuestionRepository questionRepository;

    
    @CrossOrigin(origins = "${myapp.url}")
    @PostMapping("/add")
    public ResponseEntity<Object> addQuestion(@RequestParam String jobid, @RequestBody QuizQuestion question) {
        try {
           
            question.setJobid(jobid);

            questionRepository.save(question);
            return ResponseEntity.status(HttpStatus.OK).body(question);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add question");
        }
    }

	
    @CrossOrigin(origins = "${myapp.url}")
	@GetMapping("/fetchquestion")
	public ResponseEntity<List<QuizQuestion>> fetchquestion() {
	    try {
	        List<QuizQuestion> questions = questionRepository.findAll();
	        return ResponseEntity.ok(questions);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
    
    
    @GetMapping("/fetchquestionbyjobid")
    public ResponseEntity<List<QuizQuestion>> fetchQuestionsByJobId(@RequestParam String jobid) {
        try {
            List<QuizQuestion> questions = questionRepository.findByJobid(jobid);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/checkjobid")
    public ResponseEntity<Boolean> checkJobIdExists(@RequestParam String jobid) {
        try {
            boolean exists = questionRepository.existsByJobid(jobid);
            System.out.println("checkin hejob id "+jobid);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    @CrossOrigin(origins = "${myapp.url}")
	@PostMapping("/deletequestion")
	public ResponseEntity<String> deleteQuestionById(@RequestBody Map<String, String> request) {
	    String id = request.get("id");
	    if (id != null) {
	        try {
	            questionRepository.deleteById(id);
	            return ResponseEntity.ok("Question deleted successfully");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the question: " + e.getMessage());
	        }
	    } else {
	        return ResponseEntity.badRequest().body("Invalid request. 'id' parameter is missing or invalid.");
	    }
	}

	

}