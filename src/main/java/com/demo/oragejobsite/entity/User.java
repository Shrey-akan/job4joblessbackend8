package com.demo.oragejobsite.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
@Id
private String uid;
private String userName;
   private String userFirstName;
   private String userLastName;
   private String userPassword;
   private String userdesignation;
   private String websiteuser;
   private String userphone;
   private String usercountry;
   private String userstate;
   private String usercity;
   private boolean verified;

   private String summary;

   private String userlinkden;
   private String usergithub;
   private String otherturluser;
   private boolean accdeactivate;
   
   
   
public User() {
	super();
}



public User(String uid, String userName, String userFirstName, String userLastName, String userPassword,
		String userdesignation, String websiteuser, String userphone, String usercountry, String userstate,
		String usercity, boolean verified, String summary, String userlinkden, String usergithub, String otherturluser,
		boolean accdeactivate) {
	super();
	this.uid = uid;
	this.userName = userName;
	this.userFirstName = userFirstName;
	this.userLastName = userLastName;
	this.userPassword = userPassword;
	this.userdesignation = userdesignation;
	this.websiteuser = websiteuser;
	this.userphone = userphone;
	this.usercountry = usercountry;
	this.userstate = userstate;
	this.usercity = usercity;
	this.verified = verified;
	
	this.summary = summary;
	this.userlinkden = userlinkden;
	this.usergithub = usergithub;
	this.otherturluser = otherturluser;
	
	this.accdeactivate = accdeactivate;
}



public String getUid() {
	return uid;
}



public void setUid(String uid) {
	this.uid = uid;
}



public String getUserName() {
	return userName;
}



public void setUserName(String userName) {
	this.userName = userName;
}



public String getUserFirstName() {
	return userFirstName;
}



public void setUserFirstName(String userFirstName) {
	this.userFirstName = userFirstName;
}



public String getUserLastName() {
	return userLastName;
}



public void setUserLastName(String userLastName) {
	this.userLastName = userLastName;
}



public String getUserPassword() {
	return userPassword;
}



public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
}



public String getUserdesignation() {
	return userdesignation;
}



public void setUserdesignation(String userdesignation) {
	this.userdesignation = userdesignation;
}



public String getWebsiteuser() {
	return websiteuser;
}



public void setWebsiteuser(String websiteuser) {
	this.websiteuser = websiteuser;
}



public String getUserphone() {
	return userphone;
}



public void setUserphone(String userphone) {
	this.userphone = userphone;
}



public String getUsercountry() {
	return usercountry;
}



public void setUsercountry(String usercountry) {
	this.usercountry = usercountry;
}



public String getUserstate() {
	return userstate;
}



public void setUserstate(String userstate) {
	this.userstate = userstate;
}



public String getUsercity() {
	return usercity;
}



public void setUsercity(String usercity) {
	this.usercity = usercity;
}



public boolean isVerified() {
	return verified;
}



public void setVerified(boolean verified) {
	this.verified = verified;
}



public String getSummary() {
	return summary;
}



public void setSummary(String summary) {
	this.summary = summary;
}



public String getUserlinkden() {
	return userlinkden;
}



public void setUserlinkden(String userlinkden) {
	this.userlinkden = userlinkden;
}



public String getUsergithub() {
	return usergithub;
}



public void setUsergithub(String usergithub) {
	this.usergithub = usergithub;
}



public String getOtherturluser() {
	return otherturluser;
}



public void setOtherturluser(String otherturluser) {
	this.otherturluser = otherturluser;
}



public boolean isAccdeactivate() {
	return accdeactivate;
}



public void setAccdeactivate(boolean accdeactivate) {
	this.accdeactivate = accdeactivate;
}








}

