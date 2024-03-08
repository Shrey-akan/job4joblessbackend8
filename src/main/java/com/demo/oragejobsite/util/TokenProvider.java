package com.demo.oragejobsite.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.SecureRandom;
import java.security.SignatureException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 15 * 24 * 60 * 60 * 1000; // 15 days in milliseconds
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 15 * 60 * 24 * 1000; // 15 minutes in milliseconds
    private static final String OUTPUT_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    @Value("${jwt.secret}")
    private String jwtSecretValue;

    private SecretKey jwtSecret;

    public TokenProvider(String jwtSecretValue) {
        jwtSecret = Keys.hmacShaKeyFor(jwtSecretValue.getBytes());
    }

   
    public TokenProvider() {

        this("jjbjhgbkgigcuol6354623g23c4y2t42werfd347637648c472i34723847823x4y378i78378943k4iyh23c4847y6238c4y6i");
    }
    
    
    public String generateRefreshToken(String username, String uid) {
        Date expiryDate = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME);
        String tokenId = UUID.randomUUID().toString();

        return Jwts.builder()
            .setSubject(username)
            .claim("uid", uid)
            .setExpiration(expiryDate)
            .signWith(jwtSecret, SignatureAlgorithm.HS256)
            .setId(tokenId)
            .compact();
    }

    public String[] validateAndExtractUsernameAndUidFromRefreshToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();

            String username = claims.getSubject();
            String uid = claims.get("uid", String.class); // Extract the 'uid' claim

            if (username != null && uid != null) {
                return new String[]{username, uid};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String generateAccessToken(String uid) {
        Date expiryDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME);

        return Jwts.builder()
            .setSubject(uid)
            .setExpiration(expiryDate)
            .signWith(jwtSecret, SignatureAlgorithm.HS256)
            .compact();
        
       
    }

//    public java.sql.Date getExpirationDateFromRefreshToken(String refreshToken) {
//        try {
//            Claims claims = Jwts.parserBuilder()
//                .setSigningKey(jwtSecret)
//                .build()
//                .parseClaimsJws(refreshToken)
//                .getBody();
//
//            Date expirationDate = claims.getExpiration();
//
//            if (expirationDate != null) {
//                // Convert the expiration date to a SQL Date
//                return new java.sql.Date(expirationDate.getTime());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
    
    public java.sql.Date getExpirationDateFromRefreshToken(String refreshToken) throws SQLException {
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

            Date expirationDate = claims.getExpiration();

            if (expirationDate != null) {
                // Format the expiration date to the desired output format
                String formattedDate = formatDate(expirationDate);

                // Convert the formatted date string back to a java.sql.Date object
                SimpleDateFormat sdf = new SimpleDateFormat(OUTPUT_FORMAT_PATTERN);
                java.util.Date parsedDate = sdf.parse(formattedDate);

                // Convert the parsed date to a SQL Date
                return new java.sql.Date(parsedDate.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while parsing the expiration date.");
        }

        return null;
    }

    // Method to format date to the desired output format
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(OUTPUT_FORMAT_PATTERN);
        return sdf.format(date);
    }

    public SecretKey getRefreshTokenSecret() {
        return jwtSecret;
    }

    public void setRefreshTokenSecret(SecretKey refreshTokenSecret) {
        this.jwtSecret = refreshTokenSecret;
    }
    
    
    public boolean isAccessTokenValid(String accessToken) {
        try {
            // Parse the access token
            Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(accessToken);

            // If parsing is successful, the token is valid
            return true;
        } catch (Exception e) {
            // Token is invalid or there was an error parsing it
            return false;
        }
    }
    
    public boolean isRefreshTokenValid(String refreshToken) {
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

            // Check if the token has not expired
            if (!claims.getExpiration().before(new Date())) {
                return true; // Refresh token is valid
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Refresh token is invalid
    }
    // You can add more methods or setters as needed.
}


