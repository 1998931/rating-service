package com.ganesh.IdentityService.service;



import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

import org.springframework.core.codec.Decoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtService {

    private static final String SECRTE= "6F1A4B0C1E3E4F6D8B9A0C7D2E1F2A3B4C5D6E7F8A9B0C1D";
    
    // private final long expirationMillis;

    
public String generateToken(String userName){
    Map<String,Object> claims=new HashMap<>();
    return createToken(claims,userName);
}
    public String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + System.currentTimeMillis()+1000*60*30);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRTE);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            throw new RuntimeException("Invalid or expired JWT", e);
        }
    }

    public Claims getClaims(String token) {
        return null;
        
    }

    
}

