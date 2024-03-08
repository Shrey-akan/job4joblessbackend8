package com.demo.oragejobsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.oragejobsite.dao.BlogDao;
import com.demo.oragejobsite.entity.Blogs;

@Service
public class BlogService {
	  private BlogDao blogRepository;

	    @Autowired
	    public BlogService(BlogDao blogRepository) {
	        this.blogRepository = blogRepository;
	    }

	    public Blogs createBlog(Blogs blog) {
	        // Add your business logic here if needed
	        return blogRepository.save(blog);
	    }
}
