package com.demo.oragejobsite.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.oragejobsite.dao.SubAdminDao;
import com.demo.oragejobsite.entity.SubAdminDetails;

import java.util.List;
import java.util.Optional;

@Service
public class SubAdminDetailsService {

    @Autowired
    private SubAdminDao subAdminDetailsDao;

    public SubAdminDetails authenticateSubAdmin(String email, String password) {
        SubAdminDetails subAdmin = subAdminDetailsDao.findBySubadminmail(email);
        
        if (subAdmin != null && subAdmin.getSubadminpassword().equals(password)) {
            return subAdmin;
        }
        
        return null;
    }
    
    public ResponseEntity<Object> addSubAdmin(SubAdminDetails subAdminDetails) {
        try {
            if (!subAdminDetails.getSubadminmail().endsWith("@gmail.com")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email must end with @gmail.com");
            }
//            if (!subAdminDetails.getSubadminpassword().matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must be at least 8 characters long and contain at least one number, one special character, and one alphabet character");
//            }
            SubAdminDetails savedSubAdminDetails = subAdminDetailsDao.save(subAdminDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSubAdminDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add SubAdmin");
        }
    }

    public ResponseEntity<Object> getAllSubAdmins() {
        try {
            List<SubAdminDetails> subAdmins = subAdminDetailsDao.findAll();
            return ResponseEntity.ok(subAdmins);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve SubAdmins");
        }
    }

    
    public ResponseEntity<Object> getSubAdminById(String id) {
        try {
            Optional<SubAdminDetails> subAdminOptional = subAdminDetailsDao.findById(id);
            if (subAdminOptional.isPresent()) {
                return ResponseEntity.ok().body(subAdminOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve SubAdmin by ID: " + id);
        }
    }


    public ResponseEntity<Object> updateSubAdmin(String id, SubAdminDetails subAdminDetails) {
        try {
            Optional<SubAdminDetails> subAdminOptional = subAdminDetailsDao.findById(id);
            if (!subAdminOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            SubAdminDetails updatedSubAdminDetails = subAdminDetailsDao.save(subAdminDetails);
            return ResponseEntity.ok(updatedSubAdminDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update SubAdmin with ID: " + id);
        }
    }

    public ResponseEntity<Object> deleteSubAdmin(String id) {
        try {
            Optional<SubAdminDetails> subAdminOptional = subAdminDetailsDao.findById(id);
            if (!subAdminOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            subAdminDetailsDao.deleteById(id);
            return ResponseEntity.ok("SubAdmin with ID " + id + " deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete SubAdmin with ID: " + id);
        }
    }
}