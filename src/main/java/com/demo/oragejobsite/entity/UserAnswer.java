package com.demo.oragejobsite.entity;


public class UserAnswer {
    private String questionId;
    private String selectedAnswer;
    private String userResponse;
	public UserAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAnswer(String questionId, String selectedAnswer, String userResponse) {
		super();
		this.questionId = questionId;
		this.selectedAnswer = selectedAnswer;
		this.userResponse = userResponse;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getSelectedAnswer() {
		return selectedAnswer;
	}
	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	public String getUserResponse() {
		return userResponse;
	}
	public void setUserResponse(String userResponse) {
		this.userResponse = userResponse;
	}
    
    
    
    
 

}

