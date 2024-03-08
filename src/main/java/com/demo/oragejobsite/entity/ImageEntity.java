package com.demo.oragejobsite.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "imageentity")
public class ImageEntity {
    @Id
    private String id;
    private String imageUrl;
    private String uid;
	public ImageEntity(String id, String imageUrl, String uid) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.uid = uid;
	}

	public ImageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

    
    
    
    // Constructors, getters, and setters
}

