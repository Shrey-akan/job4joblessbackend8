package com.demo.oragejobsite.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.AdminDao;
import com.demo.oragejobsite.dao.EmployerDao;
import com.demo.oragejobsite.dao.RefreshTokenRepository;
import com.demo.oragejobsite.dao.UserDao;
import com.demo.oragejobsite.entity.Admin;
import com.demo.oragejobsite.entity.Employer;
import com.demo.oragejobsite.entity.User;
import com.demo.oragejobsite.util.TokenProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "${myapp.url}")
public class AccessTokenController {
	
	@Value("${jwt.secret}")
	private String jwtSecretValue;
	  @Autowired
	    private UserDao userDao; 
	    @Autowired
	    private AdminDao adminDao;
	    @Autowired
	    private EmployerDao employerDao; 
	    @Autowired
	    private RefreshTokenRepository refreshTokenRepository;

	private TokenProvider tokenProvider;

	public AccessTokenController(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	 
	@CrossOrigin(origins = "${myapp.url}")
	@PostMapping("/checkAccessTokenValidity")
	public ResponseEntity<String> checkAccessTokenValidity(@RequestBody Map<String, String> requestMap) {
	    try {
	        String accessToken = requestMap.get("accessToken");
	        if (tokenProvider.isAccessTokenValid(accessToken)) {
	        	return ResponseEntity.ok().body("{\"status\":\"Access token is valid.\"}");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access token is invalid or has expired.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception for debugging
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
	    }
	}

	
	@CrossOrigin(origins = "${myapp.url}")
	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> requestMap) {
	    try { 
	        String refreshToken = requestMap.get("refreshToken");
	        System.out.println("Received Refresh Token: " + refreshToken);
	        if (refreshToken == null || refreshToken.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
	        }
	        if (tokenProvider.isRefreshTokenValid(refreshToken)) {
	        	 System.out.println("Received Refresh Token: " + tokenProvider.isRefreshTokenValid(refreshToken));
	        	 String[] userData = tokenProvider.validateAndExtractUsernameAndUidFromRefreshToken(refreshToken);
	            System.out.println("Received Refresh Token: " + userData);
	            if (userData != null && userData.length == 2) {
	            	 String username = userData[0];
	            	    String uid = userData[1];
	            	   System.out.println("Received Refresh Token: " + uid);
	            	Optional<User> userOptional = userDao.findByUid(uid);
	            	Optional<Employer> employerOptional = employerDao.findByEmpid(uid);
	            	Optional<Admin> adminOptional = adminDao.findByAdminid(uid);
	            	   System.out.println("Received Refresh Token: " + userOptional);
	            	   System.out.println("Received Refresh Token: " + employerOptional);
	            	   System.out.println("Received Refresh Token: " + adminOptional);
                    if (userOptional.isPresent()) {    
                    	User user = userOptional.get();
                        String newAccessToken = tokenProvider.generateAccessToken(user.getUid());
                        System.out.println("Received Refresh Token: " + newAccessToken);
                        Map<String, Object> responseBody = new HashMap<>();
                        responseBody.put("accessToken", newAccessToken);
                        responseBody.put("role", "user");
                        responseBody.put("uid", user.getUid());
                        return ResponseEntity.ok(responseBody);
                    } else if (employerOptional.isPresent()) {
                    	Employer employer = employerOptional.get();
                        String newAccessToken = tokenProvider.generateAccessToken(employer.getEmpid());
                        System.out.println("Received Refresh Token: " + newAccessToken);
                        Map<String, Object> responseBody = new HashMap<>();
                        responseBody.put("accessToken", newAccessToken);
                        responseBody.put("role", "employer");
                        responseBody.put("empid", employer.getEmpid());
                        return ResponseEntity.ok(responseBody);
                    }             
                    else if (adminOptional.isPresent()) {
                        Admin admin = adminOptional.get();
                        String newAccessToken = tokenProvider.generateAccessToken(admin.getAdminId());
                        System.out.println("Received Refresh Token: " + newAccessToken);
                        Map<String, Object> responseBody = new HashMap<>();
                        responseBody.put("accessToken", newAccessToken);
                        responseBody.put("role", "admin");
                        responseBody.put("adminid", admin.getAdminId());
                        return ResponseEntity.ok(responseBody);
                    }
                }
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

}
