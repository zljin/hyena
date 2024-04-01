package com.zljin.common.core.util;

import com.zljin.common.core.constant.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    private JwtTokenUtil() {
    }

    public static String generateToken(String genStr) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", genStr);
        claims.put("created", new Date());
        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, CommonConstants.SECRET)
                .compact();
    }

    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + CommonConstants.EXPIRATION_TIME * 1000);
    }

    public static String getSubFromToken(String token) {
        String sub;
        try {
            final Claims claims = getClaimsFromToken(token);
            sub = claims.getSubject();
        } catch (Exception e) {
            sub = null;
        }
        return sub;
    }


    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(CommonConstants.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public static Boolean validateToken(String token, String compareSub) {
        final String sub = getSubFromToken(token);
        return (
                sub.equals(compareSub)
                        && !isTokenExpired(token)
        );
    }

    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private static Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
}
