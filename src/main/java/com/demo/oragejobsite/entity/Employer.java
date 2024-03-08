package com.demo.oragejobsite.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employer")
public class Employer {
	@Id
	private String empid;
	private String empfname;
	private String emplname;
	private String empcompany;
	private String empmailid;
	private String emppass;
	private Long empphone;
	private String empcountry;
	private String empstate;
	private String empcity;
	private String descriptionemp;
	   private boolean verifiedemp;
	   private String websiteUrl;
	    private String designation;
	private String emplinkden;
	private String empotherurl;
	private boolean accempldeactivate;
	public Employer() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public Employer(String empid, String empfname, String emplname, String empcompany, String empmailid, String emppass,
			Long empphone, String empcountry, String empstate, String empcity, String descriptionemp,
			boolean verifiedemp, String websiteUrl, String designation, String emplinkden, String empotherurl,
			boolean accempldeactivate) {
		super();
		this.empid = empid;
		this.empfname = empfname;
		this.emplname = emplname;
		this.empcompany = empcompany;
		this.empmailid = empmailid;
		this.emppass = emppass;
		this.empphone = empphone;
		this.empcountry = empcountry;
		this.empstate = empstate;
		this.empcity = empcity;
		this.descriptionemp = descriptionemp;
		this.verifiedemp = verifiedemp;
		this.websiteUrl = websiteUrl;
		this.designation = designation;
		this.emplinkden = emplinkden;
		this.empotherurl = empotherurl;
		this.accempldeactivate = accempldeactivate;
	}





	public String getEmpid() {
		return empid;
	}





	public void setEmpid(String empid) {
		this.empid = empid;
	}





	public String getEmpfname() {
		return empfname;
	}





	public void setEmpfname(String empfname) {
		this.empfname = empfname;
	}





	public String getEmplname() {
		return emplname;
	}





	public void setEmplname(String emplname) {
		this.emplname = emplname;
	}





	public String getEmpcompany() {
		return empcompany;
	}





	public void setEmpcompany(String empcompany) {
		this.empcompany = empcompany;
	}





	public String getEmpmailid() {
		return empmailid;
	}





	public void setEmpmailid(String empmailid) {
		this.empmailid = empmailid;
	}





	public String getEmppass() {
		return emppass;
	}





	public void setEmppass(String emppass) {
		this.emppass = emppass;
	}





	public Long getEmpphone() {
		return empphone;
	}





	public void setEmpphone(Long empphone) {
		this.empphone = empphone;
	}





	public String getEmpcountry() {
		return empcountry;
	}





	public void setEmpcountry(String empcountry) {
		this.empcountry = empcountry;
	}





	public String getEmpstate() {
		return empstate;
	}





	public void setEmpstate(String empstate) {
		this.empstate = empstate;
	}





	public String getEmpcity() {
		return empcity;
	}





	public void setEmpcity(String empcity) {
		this.empcity = empcity;
	}





	public String getDescriptionemp() {
		return descriptionemp;
	}





	public void setDescriptionemp(String descriptionemp) {
		this.descriptionemp = descriptionemp;
	}





	public boolean isVerifiedemp() {
		return verifiedemp;
	}





	public void setVerifiedemp(boolean verifiedemp) {
		this.verifiedemp = verifiedemp;
	}





	public String getWebsiteUrl() {
		return websiteUrl;
	}





	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}





	public String getDesignation() {
		return designation;
	}





	public void setDesignation(String designation) {
		this.designation = designation;
	}





	public String getEmplinkden() {
		return emplinkden;
	}





	public void setEmplinkden(String emplinkden) {
		this.emplinkden = emplinkden;
	}





	public String getEmpotherurl() {
		return empotherurl;
	}





	public void setEmpotherurl(String empotherurl) {
		this.empotherurl = empotherurl;
	}





	public boolean isAccempldeactivate() {
		return accempldeactivate;
	}





	public void setAccempldeactivate(boolean accempldeactivate) {
		this.accempldeactivate = accempldeactivate;
	}




	
}

