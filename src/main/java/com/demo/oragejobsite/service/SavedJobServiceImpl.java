package com.demo.oragejobsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.oragejobsite.dao.PostjobDao;
import com.demo.oragejobsite.dao.SavedJobDao;
import com.demo.oragejobsite.entity.PostJob;
import com.demo.oragejobsite.entity.SavedJob;

@Service
public class SavedJobServiceImpl implements SavedJobService {

    @Autowired
    private SavedJobDao savejobdao;
    @Autowired
    private PostjobDao postjobdao;

    @Override
    public SavedJob updateSavedJobStatus(String jobid, String uid) {
        PostJob postJob = postjobdao.findByJobid(jobid);
        if (postJob != null) {
            SavedJob savedJob = savejobdao.findByJobidAndUid(jobid, uid);

            if (savedJob != null) {
                // If SavedJob exists, toggle the status between false and true
                savedJob.setSaveStatus(!savedJob.getSaveStatus());
                return savejobdao.save(savedJob);
            } else {
                // If SavedJob doesn't exist, create a new one with status true
                SavedJob newSavedJob = new SavedJob();
                newSavedJob.setJobid(jobid);
                newSavedJob.setUid(uid);
                newSavedJob.setSaveStatus(true);
                newSavedJob.setPostJob(postJob); // Link the PostJob
                return savejobdao.save(newSavedJob);
            }
        }
        return null;
    }
}