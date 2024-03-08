package com.demo.oragejobsite.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.oragejobsite.dao.ResumeUploadRepository;
import com.demo.oragejobsite.entity.ResumeUpload;



@RestController
@CrossOrigin(origins = "${myapp.url}")
public class FileUploadController {

    @Autowired
    private ResumeUploadRepository resumeUploadRepository;

    
    @PostMapping("/uploadPdf")
    @CrossOrigin(origins = "${myapp.url}")
    public ResponseEntity<?> uploadPdf(@RequestParam("file") MultipartFile file, @RequestParam("uid") String uid) {
        if (!file.isEmpty()) {
            try {
                String uploadDirectory = "/root/folder_name/upload_pdf/";               
                String originalFileName = uid + ".pdf";
                File directory = new File(uploadDirectory);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                String filePath = uploadDirectory + originalFileName;
                file.transferTo(new File(filePath));          
                ResumeUpload resumeUpload = new ResumeUpload();
                resumeUpload.setUid(uid);
                resumeUpload.setFileName(originalFileName);             
                resumeUploadRepository.save(resumeUpload);
                return ResponseEntity.ok(resumeUpload);
            } catch (IOException e) {
            	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
            }
        }
        else {
        	  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume not found for the user : ");
        }
    }

    
    @GetMapping("/fetchByUid")
    @CrossOrigin(origins = "${myapp.url}")
    public ResponseEntity<ResumeUpload> fetchByUid(@RequestParam("uid") String uid) {
        try {
            ResumeUpload resumeUpload = resumeUploadRepository.findByUid(uid);
            if (resumeUpload != null) {
                return ResponseEntity.ok(resumeUpload);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/fetchAll")
    @CrossOrigin(origins = "${myapp.url}")
    public ResponseEntity<java.util.List<ResumeUpload>> fetchAll() {
        try {
            java.util.List<ResumeUpload> resumeUploads = resumeUploadRepository.findAll();
            if (!resumeUploads.isEmpty()) {
                return ResponseEntity.ok(resumeUploads);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getPdfByUid/{uid}")
    @CrossOrigin(origins = "${myapp.url}")
    public void getPdfByUid(@PathVariable("uid") String uid, HttpServletResponse response) {
        try {
            ResumeUpload resumeUpload = resumeUploadRepository.findByUid(uid);
            if (resumeUpload != null) {
                String filePath = "/root/folder_name/upload_pdf/" + uid + ".pdf";
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=" + uid + ".pdf");
                FileInputStream fileInputStream = new FileInputStream(filePath);
                IOUtils.copy(fileInputStream, response.getOutputStream());
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/getPdfByUi/{uid}")
    @CrossOrigin(origins = "${myapp.url}")
    public ResponseEntity<byte[]> getPdfByUi(@PathVariable("uid") String uid) {
        try {
            String filePath = "/root/folder_name/upload_pdf/" + uid + ".pdf";
            File pdfFile = new File(filePath);
            byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=" + uid + ".pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle errors as needed
            return ResponseEntity.notFound().build();
        }
    }
    
}
