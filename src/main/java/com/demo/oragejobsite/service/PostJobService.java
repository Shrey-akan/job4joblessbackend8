package com.demo.oragejobsite.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.oragejobsite.dao.PostjobDao;
import com.demo.oragejobsite.entity.PostJob;

@Service
public class PostJobService {

    @Autowired
    private PostjobDao postJobRepository;

    public Optional<PostJob> getPostJobById(String jobId) {
        return postJobRepository.findById(jobId);
    }

    // Other methods...
}