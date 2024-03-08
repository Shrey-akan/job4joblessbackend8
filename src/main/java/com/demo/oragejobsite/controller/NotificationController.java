 package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.NotificationDao;
import com.demo.oragejobsite.entity.Notification;

@CrossOrigin(origins = "${myapp.url}")
@RestController
public class NotificationController {
	@Autowired
	private NotificationDao nd;
	
	
	@CrossOrigin(origins = "${myapp.url}")
	@PostMapping("/insertnotification")
	public ResponseEntity<Boolean> insertnotification(@RequestBody Notification nn) {
	    try {
	        Notification savedNotification = nd.save(nn);
	        return ResponseEntity.status(HttpStatus.CREATED).body(true);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	    }
	}

	@CrossOrigin(origins = "${myapp.url}")
	@GetMapping("/fetchnotify")
	public ResponseEntity<List<Notification>> fetchnotify() {
	    try {
	        List<Notification> notifications = nd.findAll();
	        return ResponseEntity.ok(notifications);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	
}
