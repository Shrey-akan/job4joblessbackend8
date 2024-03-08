package com.demo.oragejobsite.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_status")
public class UserStatus {
@Id
private String savestatusid;
private String uid;
private String empid;
private String jobid;
private String juid;
private String applystatus;
private Boolean viewcheck;
public UserStatus() {
	super();
	// TODO Auto-generated constructor stub
}
public UserStatus(String savestatusid, String uid, String empid, String jobid, String juid, String applystatus,
		Boolean viewcheck) {
	super();
	this.savestatusid = savestatusid;
	this.uid = uid;
	this.empid = empid;
	this.jobid = jobid;
	this.juid = juid;
	this.applystatus = applystatus;
	this.viewcheck = viewcheck;
}
public String getSavestatusid() {
	return savestatusid;
}
public void setSavestatusid(String savestatusid) {
	this.savestatusid = savestatusid;
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
public String getApplystatus() {
	return applystatus;
}
public void setApplystatus(String applystatus) {
	this.applystatus = applystatus;
}
public Boolean getViewcheck() {
	return viewcheck;
}
public void setViewcheck(Boolean viewcheck) {
	this.viewcheck = viewcheck;
}




}
