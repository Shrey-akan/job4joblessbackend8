package com.demo.oragejobsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.oragejobsite.dao.AdminDao;
import com.demo.oragejobsite.entity.Admin;

@Service
public class AdminService {

    private AdminDao adminRepository;

    @Autowired
    public AdminService(AdminDao adminRepository) {
        this.adminRepository = adminRepository;
    }
    
    public Admin authenticateAdmin(String adminMail, String adminPass) {
        Admin admin = adminRepository.findByAdminMail(adminMail);
        
        if (admin != null && admin.getAdminPass().equals(adminPass)) {
            return admin;
        }
        
        return null;
    }
}
