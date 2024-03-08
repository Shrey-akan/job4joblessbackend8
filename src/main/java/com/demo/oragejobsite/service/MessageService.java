package com.demo.oragejobsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.demo.oragejobsite.dao.MessageEntityRepository;
import com.demo.oragejobsite.entity.SendMessage;
@Service
public class MessageService {
	private final MessageEntityRepository messageEntityRepository;

    @Autowired
    public MessageService(MessageEntityRepository messageEntityRepository) {
        this.messageEntityRepository = messageEntityRepository;
    }

    public SendMessage saveMessage(SendMessage chatMessage) {
        try {
            SendMessage messageEntity = new SendMessage();
            messageEntity.setMessageTo(chatMessage.getMessageTo());
            messageEntity.setMessageFrom(chatMessage.getMessageFrom());
            messageEntity.setMessage(chatMessage.getMessage());
            messageEntity.setIsSender(chatMessage.getIsSender());
            return messageEntityRepository.save(messageEntity);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Error saving message", e);
        }
    }

    public List<SendMessage> getAllMessages() {
        try {
            return messageEntityRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching messages", e);
        }
    }
	
}
