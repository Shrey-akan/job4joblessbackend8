package com.demo.oragejobsite.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "resumebuilder")
public class ResumeBuilder {
	@Id
	private String rid;
	private String rname;
	private String rmail;
	private Long rphone;
	private String experience;
	private String skills;
	private String projectlink;
	private String description;
	

	public ResumeBuilder(String rid, String rname, String rmail, Long rphone, String experience, String skills,
			String projectlink, String description) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.rmail = rmail;
		this.rphone = rphone;
		this.experience = experience;
		this.skills = skills;
		this.projectlink = projectlink;
		this.description = description;
		
	}
	public ResumeBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRmail() {
		return rmail;
	}
	public void setRmail(String rmail) {
		this.rmail = rmail;
	}
	public Long getRphone() {
		return rphone;
	}
	public void setRphone(Long rphone) {
		this.rphone = rphone;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getProjectlink() {
		return projectlink;
	}
	public void setProjectlink(String projectlink) {
		this.projectlink = projectlink;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	
	
}
