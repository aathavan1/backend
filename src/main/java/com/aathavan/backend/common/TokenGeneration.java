package com.aathavan.backend.common;

import com.aathavan.backend.model.ReturnStatus;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGeneration {

    private String securityKey = "AathavanDivya0907200413122003AathavanDivya0907200413122003";

    public String generateToken(String userId) throws Exception {
        return Jwts.builder().setSubject(userId).issuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 10000))
                .signWith(SignatureAlgorithm.HS256, securityKey).compact();
    }

    public ReturnStatus checkToken(Object authToken) throws Exception {
        try {
            if (authToken == null)
                throw new Exception("No Auth Token Generated Till now ");
            Jwts.parser().setSigningKey(securityKey).build().parseClaimsJws(String.valueOf(authToken));
            return new ReturnStatus(true, "Authentication Sucessful");
        } catch (Exception e) {
            return new ReturnStatus(false, "Authentication Failed", e.getMessage());
        }
    }
}
