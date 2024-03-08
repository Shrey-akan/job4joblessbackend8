package com.demo.oragejobsite.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.AdminDao;
import com.demo.oragejobsite.dao.RefreshTokenRepository;
import com.demo.oragejobsite.entity.Admin;
import com.demo.oragejobsite.entity.RefreshToken;
import com.demo.oragejobsite.service.AdminService;
import com.demo.oragejobsite.util.TokenProvider;


@RestController
@CrossOrigin(origins = "${myapp.url}")
public class AdminController {

    @Autowired
    private AdminDao admindao;
    private AdminService adminService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    
    
    @Autowired
    public AdminController(AdminService adminService, TokenProvider tokenProvider,
            RefreshTokenRepository refreshTokenRepository) {
        this.adminService = adminService;
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
    @PostMapping("/insertadmin")
    public ResponseEntity<Object> insertadmin(@RequestBody Admin admin) {
        try {
            String randomString = UUID.randomUUID().toString().replaceAll("-", "");
            admin.setAdminId(randomString);
            admin.setAdminPass(hashPassword(admin.getAdminPass()));
            Admin savedAdmin = admindao.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin successfully created");
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving admin to the database");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
    @CrossOrigin(origins = "${myapp.url}")
    @PostMapping("/adminLoginCheck")
    public ResponseEntity<?> adminLoginCheck(@RequestBody Admin admin, HttpServletResponse response) {
        try {
            String adminMail = admin.getAdminMail();
            String adminPass = admin.getAdminPass();
            adminPass = hashPassword(adminPass);

            Admin authenticatedAdmin = adminService.authenticateAdmin(adminMail, adminPass);

            if (authenticatedAdmin != null) {
                Cookie adminCookie = new Cookie("admin", adminMail);
                adminCookie.setMaxAge(3600);
                adminCookie.setPath("/");
                response.addCookie(adminCookie);

                String refreshToken = tokenProvider.generateRefreshToken(adminMail, authenticatedAdmin.getAdminId());

                RefreshToken refreshTokenEntity = new RefreshToken();
                refreshTokenEntity.setTokenId(refreshToken);
                refreshTokenEntity.setUsername(authenticatedAdmin.getAdminId());
                refreshTokenEntity.setExpiryDate(tokenProvider.getExpirationDateFromRefreshToken(refreshToken));
                refreshTokenRepository.save(refreshTokenEntity);

                String accessToken = tokenProvider.generateAccessToken(authenticatedAdmin.getAdminId());

                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("accessToken", accessToken);
                responseBody.put("refreshToken", refreshToken);
                responseBody.put("adminid", authenticatedAdmin.getAdminId());

                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin credentials");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accessing data");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }

    
    @CrossOrigin(origins = "${myapp.url}")
    @PostMapping("/adminlogout")
    public ResponseEntity<String> adminlogout(HttpServletResponse response) {
        try {
            Cookie empCookie = new Cookie("adminid", null);
            empCookie.setMaxAge(0);
            empCookie.setPath("/");
            response.addCookie(empCookie);
            Cookie accessTokenCookie = new Cookie("accessToken", null);
            accessTokenCookie.setMaxAge(0);
            accessTokenCookie.setPath("/");
            response.addCookie(accessTokenCookie);
            Cookie refreshTokenCookie = new Cookie("refreshToken", null);
            refreshTokenCookie.setMaxAge(0);
            refreshTokenCookie.setPath("/");
            response.addCookie(refreshTokenCookie);
            return ResponseEntity.ok("Logout successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during logout");
        }
    }
    @CrossOrigin(origins = "${myapp.url}")
    @GetMapping("/fetchadmin")
    public ResponseEntity<List<Admin>> fetchadmin() {
        try {
            List<Admin> admindata = admindao.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(admindata);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
