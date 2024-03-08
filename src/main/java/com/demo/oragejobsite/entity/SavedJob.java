package com.demo.oragejobsite.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saved_job_post")
public class SavedJob {
	@Id
    private String saveId;
	private String uid;
	private String jobid;
	@DBRef
	 private PostJob postJob;
	private Boolean saveStatus;
	public SavedJob() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SavedJob(String saveId, String uid, String jobid, PostJob postJob, Boolean saveStatus) {
		super();
		this.saveId = saveId;
		this.uid = uid;
		this.jobid = jobid;
		this.postJob = postJob;
		this.saveStatus = saveStatus;
	}
	public String getSaveId() {
		return saveId;
	}
	public void setSaveId(String saveId) {
		this.saveId = saveId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public PostJob getPostJob() {
		return postJob;
	}
	public void setPostJob(PostJob postJob) {
		this.postJob = postJob;
	}
	public Boolean getSaveStatus() {
		return saveStatus;
	}
	public void setSaveStatus(Boolean saveStatus) {
		this.saveStatus = saveStatus;
	}

	
	
}
