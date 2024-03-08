package com.demo.oragejobsite.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "resume_upload")
public class ResumeUpload {
    @Id
    private String id;
    private String uid;
    private String fileName;

	public ResumeUpload(String id, String uid, String fileName) {
		super();
		this.id = id;
		this.uid = uid;
		this.fileName = fileName;
	}

	public ResumeUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    // Other fields, getters, and setters
    
    
    
}

