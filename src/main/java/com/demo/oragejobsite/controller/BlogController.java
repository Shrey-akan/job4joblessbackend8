package com.demo.oragejobsite.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import com.demo.oragejobsite.entity.Blogs;
import com.demo.oragejobsite.service.BlogService;

@CrossOrigin(origins = "${myapp.url}")
@RestController
public class BlogController {

	
	 private BlogService blogService;

	    @Autowired
	    public BlogController(BlogService blogService) {
	        this.blogService = blogService;
	    }
   

	@CrossOrigin(origins = "${myapp.url}")
    @PostMapping("/createBlog")
    public ResponseEntity<?> createBlog(@RequestBody Blogs blog) {
		Blogs savedBlog = blogService.createBlog(blog);
		return ResponseEntity.ok(savedBlog);
    }

}

