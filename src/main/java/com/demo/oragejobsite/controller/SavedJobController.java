package com.demo.oragejobsite.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.entity.SavedJob;

import com.demo.oragejobsite.service.SavedJobService;
import com.demo.oragejobsite.service.SavedJobServiceImpl;


@CrossOrigin(origins = "${myapp.url}")
@RestController
public class SavedJobController {

	

	 
	    @Autowired
	    private SavedJobServiceImpl savedJobServiceimpl;

	    
	    @PutMapping("/update-status")
	    public ResponseEntity<SavedJob> updateSavedJobStatus(
	            @RequestParam String jobid,
	            @RequestParam String uid
	    ) {
	        try {
	            SavedJob savedJob = savedJobServiceimpl.updateSavedJobStatus(jobid, uid);
	            System.out.println("checking the value"+savedJob);
	            return new ResponseEntity<>(savedJob, HttpStatus.OK);
	        } catch (Exception e) {
	        	 e.printStackTrace();
	        	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    
	  



}
