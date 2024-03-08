package com.demo.oragejobsite.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "notification")
public class Notification {
	@Id
	private String nid;
	private String nhead;
	private String nsubhead;
	private String ndescription;
	private String notisend;
	private String notifyuid;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date sendTime = new Date();
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notification(String nid, String nhead, String nsubhead, String ndescription, String notisend,
			String notifyuid, Date sendTime) {
		super();
		this.nid = nid;
		this.nhead = nhead;
		this.nsubhead = nsubhead;
		this.ndescription = ndescription;
		this.notisend = notisend;
		this.notifyuid = notifyuid;
		this.sendTime = sendTime;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getNhead() {
		return nhead;
	}
	public void setNhead(String nhead) {
		this.nhead = nhead;
	}
	public String getNsubhead() {
		return nsubhead;
	}
	public void setNsubhead(String nsubhead) {
		this.nsubhead = nsubhead;
	}
	public String getNdescription() {
		return ndescription;
	}
	public void setNdescription(String ndescription) {
		this.ndescription = ndescription;
	}
	public String getNotisend() {
		return notisend;
	}
	public void setNotisend(String notisend) {
		this.notisend = notisend;
	}
	public String getNotifyuid() {
		return notifyuid;
	}
	public void setNotifyuid(String notifyuid) {
		this.notifyuid = notifyuid;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
    
    
}

