package com.demo.oragejobsite.service;

import com.demo.oragejobsite.entity.SavedJob;

public interface SavedJobService {
   
	SavedJob updateSavedJobStatus(String jobid, String uid);
}
