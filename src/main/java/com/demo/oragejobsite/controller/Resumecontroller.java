package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.ResumeDao;
import com.demo.oragejobsite.entity.ResumeBuilder;

@CrossOrigin(origins = "${myapp.url}")
@RestController
public class Resumecontroller {
	
	@Autowired
	private ResumeDao rm;
	
	@CrossOrigin(origins = "${myapp.url}")
	@PostMapping("/resumeinsert")
	public ResumeBuilder resumeinsert(@RequestBody ResumeBuilder resume)
	{
			return rm.save(resume);
		
	}
	
	@CrossOrigin(origins = "${myapp.url}")
	@GetMapping("/fetchresumedetails")
	public List<ResumeBuilder> fetchresumedetails(){
		return rm.findAll();
	}
	
	
	
	
	
}
