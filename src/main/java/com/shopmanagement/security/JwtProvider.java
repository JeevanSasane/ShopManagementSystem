package com.shopmanagement.security;

import com.shopmanagement.user.dto.LoginResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class JwtProvider {

    private Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${service.his.jwtExpiration}")
    private long expiration = 86400000L;

    public String generateToken(LoginResponseDto users) {
        System.out.println("users--"+users);
        Map<String, Object> claims = new HashMap<>();
        claims.put("status", users.getIsActive());
        claims.put("username", users.getUserName());
        claims.put("userId", users.getUId());
        claims.put("created", this.generateCurrentDate());
        return this.generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(jwtSecret)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Long userId;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            userId = Long.parseLong(claims.get("userId") + "");
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    public Long getEmployeeFromToken(String token) {
        Long empId;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            empId = Long.parseLong(claims.get("empId")+"");
        } catch (Exception e) {
            empId = null;
        }
        return empId;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration);
    }

    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            System.out.println(" Token expired ");
        } catch (SignatureException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            System.out.println(" Some other exception in JWT parsing ");
        }
        return null;
    }
}
