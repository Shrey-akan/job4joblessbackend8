package com.demo.oragejobsite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.ApplyDao;
import com.demo.oragejobsite.dao.UserStatusDao;
import com.demo.oragejobsite.entity.ApplyJob;
import com.demo.oragejobsite.entity.UserStatus;


@RestController
@CrossOrigin(origins = "${myapp.url}")
public class UserStatusController {

	@Autowired
	private UserStatusDao userstatdao;
	@Autowired
	private ApplyDao applyJobRepository;
	
	   @CrossOrigin(origins = "${myapp.url}")
	   @GetMapping("/countTrueStatus")
	   public ResponseEntity<Map<String, Integer>> countTrueStatus(@RequestParam String uid) {
		    try {
		    	System.out.println("Received uid: " + uid);
		        List<UserStatus> statusList = userstatdao.findByUid(uid);
		     	System.out.println("Checking the statuslist: " + statusList);
		        System.out.println("checking the statuslist "+ statusList);
		        int trueCount = (int) statusList.stream()
		                .filter(userStatus -> Boolean.TRUE.equals(userStatus.getViewcheck()))
		                .count();
		        Map<String, Integer> response = new HashMap<>();
		        response.put("trueCount", trueCount);

		        return ResponseEntity.ok(response);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		    }
		}
	   
	   @CrossOrigin(origins = "${myapp.url}")
	   @PutMapping("/updateViewCheck")
	   public ResponseEntity<Boolean> updateViewCheck(@RequestParam String uid, @RequestParam String juid) {
		    try {
		        UserStatus userStatus = userstatdao.findByUidAndJuid(uid, juid);
		        System.out.println(userStatus);
		        if (userStatus != null) {
		            userStatus.setViewcheck(false);
		            userstatdao.save(userStatus);
		            System.out.println(userStatus.getViewcheck());
		            return ResponseEntity.ok(true); // Return true if update is successful
		        } else {
		            return ResponseEntity.ok(false); // Return false if UserStatus not found
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false); // Return false for any other error
		    }
		}
	   
	   @CrossOrigin(origins = "${myapp.url}")
	   @DeleteMapping("/deleteUserStatus")
	   public ResponseEntity<Boolean> deleteUserStatus(@RequestParam String uid, @RequestParam String juid) {
	       try {
	           UserStatus userStatus = userstatdao.findByUidAndJuid(uid, juid);
	           if (userStatus != null) {
	               userstatdao.delete(userStatus);
	               
	               
	               ApplyJob applyJob = applyJobRepository.findByJuid(juid);
	               if (applyJob != null) {
	                   applyJob.setNotifydelete(true);
	                   applyJobRepository.save(applyJob);
	               }
	               return ResponseEntity.ok(true); // Return true if deletion is successful
	           } else {
	               return ResponseEntity.ok(false); // Return false if UserStatus not found
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false); // Return false for any other error
	       }
	   }

	
}
