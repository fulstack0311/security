package com.duynt.projectsecurity.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Thuc hien cac hoat dong cua JWT nhu tao JWT, xac thuc requst, lay ten username...
 */

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expira}")
    private long expira;

    /**
     * Get Username from token
     *
     * @return name
     */
    public String getUsernameFromToken(String token) {
        return getAllClaimFromToken(token).getSubject();
    }

    /**
     * Get Expiration date from token
     *
     * @param token code JWT
     * @return expiration date
     */
    public Date getExpirationDateFromToke(String token) {
        return  getAllClaimFromToken(token).getExpiration();
    }

    /**
     * check ì the token has expira
     *
     * @param token code JWT
     * @return false: expired
     */
    public boolean isTokenExpira(String token) {
        return getExpirationDateFromToke(token).before(new Date());
    }

    /**
     * get all infomation from token
     *
     * @param token code JWT
     * @return Claims
     */
    public Claims getAllClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Tao ma JWT
     *
     * @param userDetails doi tuong da duoc xac thuc
     * @return code JWT
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToke(claims, userDetails.getUsername());
    }

    /**
     * Qua trinh tao ma JWT
     *
     * @param claims khoi noi dung trong ma JWT
     * @param subject username
     * @return code JWT
     */
    public String doGenerateToke(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expira * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Xac nhan token, dung voi username can xac thuc, han token
     *
     * @param token code JWT
     * @param userDetails doi tuong can xac nhan
     * @return true: done
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpira(token);
    }

    /**
     * Get token From header request
     *
     * @param request httpRequest
     * @return token
     */
    public String getTokenFromHeader(HttpServletRequest request) {
        String requestHeader = request.getHeader("Authorization");
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            token = requestHeader.substring(7);
        }
        return token;
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
           // logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
           // logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
