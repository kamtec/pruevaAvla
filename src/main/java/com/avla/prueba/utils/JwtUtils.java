package com.avla.prueba.utils;

import java.util.Date;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
	
	    private static final long EXPIRATION_TIME = 900000; 
	    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	    public String generateToken(String username) {
	        Date now = new Date();
	        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

	        Claims claims = Jwts.claims().setSubject(username);

	        return Jwts.builder()
	                .setClaims(claims)
	                .setIssuedAt(now)
	                .setExpiration(expiration)
	                .signWith(SECRET_KEY)
	                .compact();
	    }

}
