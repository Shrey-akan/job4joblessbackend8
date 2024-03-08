package com.demo.oragejobsite.controller;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.oragejobsite.dao.RefreshTokenRepository;
import com.demo.oragejobsite.entity.RefreshToken;
import com.demo.oragejobsite.entity.SubAdminDetails;
import com.demo.oragejobsite.service.SubAdminDetailsService;
import com.demo.oragejobsite.util.TokenProvider;





@RestController
@RequestMapping("/subadmindetails")
@CrossOrigin(origins = "${myapp.url}")
public class SubAdminDetController {


    @Autowired
    private SubAdminDetailsService subAdminDetailsService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    
    @Autowired
    public SubAdminDetController(TokenProvider tokenProvider,
            RefreshTokenRepository refreshTokenRepository) {
       
        this.tokenProvider = tokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
    }
    	
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] hashedPasswordBytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPasswordBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    
    
    @CrossOrigin(origins = "${myapp.url}")
    @PostMapping("/subadminLoginCheck")
    public ResponseEntity<?> subadminLoginCheck(@RequestBody SubAdminDetails subAdmin, HttpServletResponse response) {
        try {
            String subAdminMail = subAdmin.getSubadminmail();
            String subAdminPass = subAdmin.getSubadminpassword();
            subAdminPass = hashPassword(subAdminPass);

            SubAdminDetails authenticatedSubAdmin = subAdminDetailsService.authenticateSubAdmin(subAdminMail, subAdminPass);

            if (authenticatedSubAdmin != null) {
                Cookie subAdminCookie = new Cookie("subadmin", subAdminMail);
                subAdminCookie.setMaxAge(3600);
                subAdminCookie.setPath("/");
                response.addCookie(subAdminCookie);

                String refreshToken = tokenProvider.generateRefreshToken(subAdminMail, authenticatedSubAdmin.getSubadminid());

                RefreshToken refreshTokenEntity = new RefreshToken();
                refreshTokenEntity.setTokenId(refreshToken);
                refreshTokenEntity.setUsername(authenticatedSubAdmin.getSubadminid());
                refreshTokenEntity.setExpiryDate(tokenProvider.getExpirationDateFromRefreshToken(refreshToken));
                refreshTokenRepository.save(refreshTokenEntity);

                String accessToken = tokenProvider.generateAccessToken(authenticatedSubAdmin.getSubadminid());

                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("accessToken", accessToken);
                responseBody.put("refreshToken", refreshToken);
                responseBody.put("subadminid", authenticatedSubAdmin.getSubadminid());

                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid subadmin credentials");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accessing data");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<Object> addSubAdmin(@RequestBody SubAdminDetails subAdminDetails) {
        String hashedPassword = hashPassword(subAdminDetails.getSubadminpassword());
        subAdminDetails.setSubadminpassword(hashedPassword);
        return subAdminDetailsService.addSubAdmin(subAdminDetails);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllSubAdmins() {
        return subAdminDetailsService.getAllSubAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSubAdminById(@PathVariable String id) {
        return subAdminDetailsService.getSubAdminById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSubAdmin(@PathVariable String id, @RequestBody SubAdminDetails subAdminDetails) {
        return subAdminDetailsService.updateSubAdmin(id, subAdminDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubAdmin(@PathVariable String id) {
        return subAdminDetailsService.deleteSubAdmin(id);
    }
}
