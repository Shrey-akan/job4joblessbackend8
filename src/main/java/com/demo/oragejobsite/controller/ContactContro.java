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

import com.demo.oragejobsite.dao.Contactfrontdao;

import com.demo.oragejobsite.entity.DirectConntact;

@CrossOrigin(origins = "${myapp.url}")
@RestController
public class ContactContro {
	@Autowired
	private Contactfrontdao contatfront;
	
	@CrossOrigin(origins = "${myapp.url}")
	@PostMapping("/insertfrontform")
	public ResponseEntity<Boolean> insertfrontform(@RequestBody DirectConntact contact) {
	    try {
	    	DirectConntact savedContact = contatfront.save(contact);
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
	@GetMapping("/fetchcontactfront")
	public ResponseEntity<List<DirectConntact>> fetchcontactfront() {
	    try {
	        List<DirectConntact> contacts = contatfront.findAll();
	        return ResponseEntity.ok(contacts);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
}
