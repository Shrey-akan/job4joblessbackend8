package com.demo.oragejobsite.service;

import java.util.List;

import com.demo.oragejobsite.entity.QuizQuestion;

public interface QuizQuestionService {
    List<QuizQuestion> getAllQuizQuestions();
    QuizQuestion getQuizQuestionById(Long id);
    QuizQuestion saveQuizQuestion(QuizQuestion quizQuestion);
    void deleteQuizQuestion(Long id);
}