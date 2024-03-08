package com.demo.oragejobsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.entity.RefreshTokenRequest;
import com.demo.oragejobsite.service.AccessTokenResponse;
import com.demo.oragejobsite.util.JwtTokenUtil;
import com.demo.oragejobsite.util.RefreshTokenUtil;
@CrossOrigin(origins = "${myapp.url}")
@RestController
public class AuthController {

    @Autowired
    private RefreshTokenUtil refreshTokenUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @CrossOrigin(origins = "${myapp.url}")
    @PostMapping("/api/refreshToken")
    public ResponseEntity<AccessTokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        String username = refreshTokenUtil.extractUsername(refreshToken);

        if (refreshTokenUtil.validateRefreshToken(refreshToken, username)) {
            String newAccessToken = jwtTokenUtil.generateToken(username);
            AccessTokenResponse response = new AccessTokenResponse(newAccessToken);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build(); // Invalid refresh token
        }
    }
}
