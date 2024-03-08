package com.demo.oragejobsite.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class RefreshTokenUtil {

    @Value("${jwt.refreshSecret}")
    private String refreshSecret;

    @Value("${jwt.refreshExpirationMillis}")
    private long refreshExpirationMillis;

    public String generateRefreshToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + refreshExpirationMillis);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, refreshSecret)
                .compact();
    }

    public boolean validateRefreshToken(String refreshToken, String username) {
        final String tokenUsername = extractUsername(refreshToken);
        return (tokenUsername.equals(username) && !isTokenExpired(refreshToken));
    }

    public String extractUsername(String refreshToken) {
        return extractClaim(refreshToken, Claims::getSubject);
    }

    private Date extractExpirationDate(String refreshToken) {
        return extractClaim(refreshToken, Claims::getExpiration);
    }

    private <T> T extractClaim(String refreshToken, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(refreshSecret).parseClaimsJws(refreshToken).getBody();
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String refreshToken) {
        return extractExpirationDate(refreshToken).before(new Date());
    }
}
