package com.demo.oragejobsite.entity;

public class QuizResult {
 private double totalMarks;

 public QuizResult(double totalMarks) {
     this.totalMarks = totalMarks;
 }

public QuizResult() {
	super();
	// TODO Auto-generated constructor stub
}



public double getTotalMarks() {
	return totalMarks;
}

public void setTotalMarks(double totalMarks) {
	this.totalMarks = totalMarks;
}

 
}

