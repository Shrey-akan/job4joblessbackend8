package com.demo.oragejobsite.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "applicants_count")
public class ApplicantsCount {
	@Id
	private String id;
	private String uid;
	private String empid;
	private String jobid;
	private String juid;
	private int applicants;
	public ApplicantsCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApplicantsCount(String id, String uid, String empid, String jobid, String juid, int applicants) {
		super();
		this.id = id;
		this.uid = uid;
		this.empid = empid;
		this.jobid = jobid;
		this.juid = juid;
		this.applicants = applicants;
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
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getJuid() {
		return juid;
	}
	public void setJuid(String juid) {
		this.juid = juid;
	}
	public int getApplicants() {
		return applicants;
	}
	public void setApplicants(int applicants) {
		this.applicants = applicants;
	}
	
	
	
}
