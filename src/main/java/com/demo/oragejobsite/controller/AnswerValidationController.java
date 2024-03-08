package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.QuizQuestionRepository;
import com.demo.oragejobsite.entity.QuizQuestion;
import com.demo.oragejobsite.entity.UserAnswer;


@RestController
@CrossOrigin(origins = "${myapp.url}")
public class AnswerValidationController {
	
	 private final QuizQuestionRepository quizQuestionRepository;

	    @Autowired
	    public AnswerValidationController(QuizQuestionRepository quizQuestionRepository) {
	        this.quizQuestionRepository = quizQuestionRepository;
	    }
	    
	@CrossOrigin(origins = "${myapp.url}")
    @PostMapping("/checkallanswer")
    public ResponseEntity<Boolean> validateAnswers(@RequestBody List<UserAnswer> userAnswers) {
        System.out.println(userAnswers.get(0).getQuestionId());
        boolean allCorrect = checkAllAnswers(userAnswers);
        return ResponseEntity.ok(allCorrect);
    }
    private boolean checkAllAnswers(List<UserAnswer> userAnswers) {
    	System.out.println("Received userAnswers: " + userAnswers);
        for (UserAnswer answer : userAnswers) {
            String questionId = answer.getQuestionId();
            String userResponse = answer.getUserResponse();
            String correctAnswer = getCorrectAnswerFromDatabase(questionId);
            if (!userResponse.equalsIgnoreCase(correctAnswer)) {
                return false;
            }
        }
        return true;
    }
    private String getCorrectAnswerFromDatabase(String questionId) {
        QuizQuestion quizQuestion = quizQuestionRepository.findById((String) questionId).orElse(null);
        return (quizQuestion != null) ? quizQuestion.getCorrectAnswer() : "";
    }
    
    
}
