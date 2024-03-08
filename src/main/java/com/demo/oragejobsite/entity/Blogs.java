package com.demo.oragejobsite.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogs")
public class Blogs {
@Id
private String blogId;
private String blogHead;
private String blogSubHead;
private String blogDescription;
private byte[] blogImg;
public Blogs(String blogId, String blogHead, String blogSubHead, String blogDescription, byte[] blogImg) {
	super();
	this.blogId = blogId;
	this.blogHead = blogHead;
	this.blogSubHead = blogSubHead;
	this.blogDescription = blogDescription;
	this.blogImg = blogImg;
}
public Blogs() {
	super();
	// TODO Auto-generated constructor stub
}
public String getBlogId() {
	return blogId;
}
public void setBlogId(String blogId) {
	this.blogId = blogId;
}
public String getBlogHead() {
	return blogHead;
}
public void setBlogHead(String blogHead) {
	this.blogHead = blogHead;
}
public String getBlogSubHead() {
	return blogSubHead;
}
public void setBlogSubHead(String blogSubHead) {
	this.blogSubHead = blogSubHead;
}
public String getBlogDescription() {
	return blogDescription;
}
public void setBlogDescription(String blogDescription) {
	this.blogDescription = blogDescription;
}
public byte[] getBlogImg() {
	return blogImg;
}
public void setBlogImg(byte[] blogImg) {
	this.blogImg = blogImg;
}


}
