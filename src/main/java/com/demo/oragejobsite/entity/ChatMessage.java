package com.demo.oragejobsite.entity;

public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;

    // Additional properties
    private String messageTo;

  
    
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }



	public ChatMessage(MessageType type, String content, String sender, String messageTo) {
		super();
		this.type = type;
		this.content = content;
		this.sender = sender;
		this.messageTo = messageTo;
	
	}

	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}


	
}
