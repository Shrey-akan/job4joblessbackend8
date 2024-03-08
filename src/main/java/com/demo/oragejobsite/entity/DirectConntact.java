package com.demo.oragejobsite.entity;




import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "directcontact")
public class DirectConntact {

@Id
private String contactid;
private String name;
private String email;
private String contactNumber;
private String message;
public DirectConntact(String contactid, String name, String email, String contactNumber, String message) {
	super();
	this.contactid = contactid;
	this.name = name;
	this.email = email;
	this.contactNumber = contactNumber;
	this.message = message;
}
public DirectConntact() {
	super();
	// TODO Auto-generated constructor stub
}
public String getContactid() {
	return contactid;
}
public void setContactid(String contactid) {
	this.contactid = contactid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getContactNumber() {
	return contactNumber;
}
public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}




}
