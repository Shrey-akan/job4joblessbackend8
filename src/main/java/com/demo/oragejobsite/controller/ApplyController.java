package com.demo.oragejobsite.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.ApplicantsCountDao;
import com.demo.oragejobsite.dao.ApplyDao;
import com.demo.oragejobsite.dao.UserStatusDao;
import com.demo.oragejobsite.entity.ApplicantsCount;
import com.demo.oragejobsite.entity.ApplyJob;
import com.demo.oragejobsite.entity.UserStatus;

@CrossOrigin(origins = "${myapp.url}")
@RestController
public class ApplyController {
	@Autowired
	private ApplyDao apd;
	@Autowired
    private ApplicantsCountDao applicantsCountRepository;
	@Autowired
	private UserStatusDao userstatusdao;
	
	@Autowired
	private UserStatusDao userstatdao;
	@CrossOrigin(origins = "${myapp.url}")
    @PostMapping("/insertapplyjob")
    public ResponseEntity<?> insertapplyjob(@RequestBody ApplyJob applyjob) {
        try {
            applyjob.setProfileupdate("Waiting");
            System.out.println("ApplyJob object before saving: " + applyjob.getProfileupdate());
            ApplyJob savedApplyJob = apd.save(applyjob);
            // Update ApplicantsCount based on jobid
            String jobid = applyjob.getJobid();
            ApplicantsCount applicantsCount = getApplicantsCountByJobId(jobid);
            if (applicantsCount == null) {
                // If no entry exists for the jobid, create a new one
                applicantsCount = new ApplicantsCount();
                applicantsCount.setJobid(jobid);
                applicantsCount.setEmpid(applyjob.getEmpid());
                applicantsCount.setUid(applyjob.getUid());
                applicantsCount.setJuid(applyjob.getJuid());
                applicantsCount.setApplicants(1); 
                System.out.println(applicantsCount.getApplicants()+" "+applicantsCount.getJobid());
            } else {
            	int currentApplicants = applicantsCount.getApplicants();
            	currentApplicants++;
            	applicantsCount.setApplicants(currentApplicants);
            }
            applicantsCountRepository.save(applicantsCount);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedApplyJob);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
        }
    }

    // Helper method to retrieve ApplicantsCount by jobid
    private ApplicantsCount getApplicantsCountByJobId(String jobid) {
        return applicantsCountRepository.findByJobid(jobid);
    }
	
	
	@CrossOrigin(origins = "${myapp.url}")
	@GetMapping("/fetchapplyform")
	public ResponseEntity<?> fetchapplyform(@RequestParam(required = false) String uid) {
	    try {
	        List<ApplyJob> applyJobs;

	        if (uid != null && !uid.isEmpty()) {
	            applyJobs = apd.findByUid(uid);
	        } else {
	            applyJobs = apd.findAll();
	        }
	        return ResponseEntity.ok(applyJobs);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database error occurred: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred while processing your request: " + e.getMessage());
	    }
	}
	
	@CrossOrigin(origins = "${myapp.url}")
	@GetMapping("/notificationforuser")
	public ResponseEntity<?> notificationforuser(@RequestParam(required = false) String uid) {
	    try {
	        List<ApplyJob> applyJobs;

	        if (uid != null && !uid.isEmpty()) {
	            applyJobs = apd.findByUid(uid);
	            System.out.println(applyJobs);
	            // Get all UserStatus entries for the given uid
	            List<UserStatus> userStatusList = userstatdao.findByUid(uid);
	            System.out.println(userStatusList);

	            Iterator<ApplyJob> iterator = applyJobs.iterator();
	            while (iterator.hasNext()) {
	                ApplyJob applyJob = iterator.next();
	                boolean foundMatchingUserStatus = false;
	                for (UserStatus userStatus : userStatusList) {
	                    if (uid.equals(userStatus.getUid()) &&
	                        applyJob.getUid().equals(userStatus.getUid()) &&
	                        applyJob.getJuid().equals(userStatus.getJuid()) &&  
	                        userStatus.getViewcheck() != null && 
	                        userStatus.getViewcheck()) {

	                        System.out.println(uid.equals(userStatus.getUid()));
	                        System.out.println(applyJob.getUid().equals(userStatus.getUid()));
	                        System.out.println(applyJob.getJuid().equals(userStatus.getJuid()));
	                        System.out.println(userStatus.getViewcheck());

	                        applyJob.setUserStatus(true);
	                        System.out.println("check");
	                        foundMatchingUserStatus = true;
	                        break;
	                    }
	                }

	                if (!foundMatchingUserStatus) {
	                    applyJob.setUserStatus(false);
	                }
	                if (applyJob.isNotifydelete()) {
	                    iterator.remove(); // Safely remove the element using iterator
	                }
	            }
	        } else {
	            applyJobs = apd.findAll();
	        }
	        return ResponseEntity.ok(applyJobs);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Database error occurred: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred while processing your request: " + e.getMessage());
	    }
	}
	@CrossOrigin(origins = "${myapp.url}")
	@PostMapping("/updateProfileUpdate")
	public ResponseEntity<?> updateProfileUpdate(@RequestBody ApplyJob applyJob) {
	    try {
	        ApplyJob existingApplyJob = apd.findByJuid(applyJob.getJuid());
	        if (existingApplyJob != null) {
	            existingApplyJob.setProfileupdate(applyJob.getProfileupdate());
	            existingApplyJob.setNotifydelete(false);
	            ApplyJob updatedApplyJob = apd.save(existingApplyJob);	            
	            String jobid = applyJob.getJobid();
	            UserStatus userstat = userstatusdao.findByJuid(applyJob.getJuid());
	            if(userstat == null) {
	            	  userstat = new UserStatus();
	                  userstat.setJuid(applyJob.getJuid());
	                  userstat.setJobid(applyJob.getJobid());
	                  userstat.setUid(existingApplyJob.getUid());
	                  userstat.setEmpid(existingApplyJob.getJobid());
	                  userstat.setApplystatus(applyJob.getProfileupdate());
	                  userstat.setViewcheck(true);
	                  userstatusdao.save(userstat);
	            	
	            }else {
	            	   userstat.setApplystatus(applyJob.getProfileupdate());
	            	   userstat.setViewcheck(true);
	                   userstatusdao.save(userstat);
	            }
	            ApplicantsCount applicantsCount = getApplicantsCountByJobId(jobid);
	            if (applicantsCount == null) {
	                return ResponseEntity.ok(updatedApplyJob);
	            } else {
	                int currentApplicants = applicantsCount.getApplicants();
	                if (currentApplicants > 0) {
	                    currentApplicants--;
	                    applicantsCount.setApplicants(currentApplicants);
	                    applicantsCountRepository.save(applicantsCount);
	                }
	            }
	            return ResponseEntity.ok(updatedApplyJob);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ApplyJob not found for UID: " + applyJob.getJuid());
	        }
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	    }
	}
	
	@CrossOrigin(origins = "${myapp.url}")
	@GetMapping("/fetchapplyformbyjobid")
	public ResponseEntity<?> fetchApplyFormByJobId(
	        @RequestParam(name = "empid") String empid,
	        @RequestParam(name = "jobid") String jobid
	) {
	    try {
	        List<ApplyJob> applyJobs = apd.findByEmpidAndJobid(empid, jobid);
	        return ResponseEntity.ok(applyJobs);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	    }
	}

	
//	  public ResponseEntity<?> notifyEmployer(@RequestParam(name = "empid") String empid) {
//        try {
//            List<ApplyJob> applyJobs = apd.findByEmpid(empid);
//            Map<String, Long> jobidUidCountMap = applyJobs.stream()
//                    .collect(Collectors.groupingBy(ApplyJob::getJobid, Collectors.mapping(ApplyJob::getUid, Collectors.collectingAndThen(Collectors.toSet(), set -> (long) set.size()))));
//
//            return ResponseEntity.ok(jobidUidCountMap);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
//        }
//    }

	@CrossOrigin(origins = "${myapp.url}")
	@GetMapping("/notifyEmployer")
	public ResponseEntity<?> notifyEmployer(@RequestParam(name = "empid") String empid) {
	    try {
	        // Fetch all apply jobs for the given employer
	        List<ApplyJob> applyJobs = apd.findByEmpid(empid);

	        // Group apply jobs by jobid and count waiting applications
	        Map<String, Long> jobidWaitingCountMap = applyJobs.stream()
	                .filter(applyJob -> "Waiting".equals(applyJob.getProfileupdate()))
	                .collect(Collectors.groupingBy(
	                        ApplyJob::getJobid,
	                        Collectors.counting()
	                ));
	        
	        List<ApplyJob> waitingApplications = applyJobs.stream()
	                .filter(applyJob -> "Waiting".equals(applyJob.getProfileupdate()))
	                .collect(Collectors.toList());

	        // Count the number of waiting applications
	        long waitingApplicationsCount = waitingApplications.size();

	        Map<String, Object> responseMap = new HashMap<>();
	        responseMap.put("jobidWaitingCountMap", jobidWaitingCountMap);
	        responseMap.put("waitingApplicationsCount", waitingApplicationsCount);

	        return ResponseEntity.ok(responseMap);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	    }
	}




}