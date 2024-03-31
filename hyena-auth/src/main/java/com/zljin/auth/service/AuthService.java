package com.zljin.auth.service;

import com.zljin.auth.entity.SysUser;
import com.zljin.auth.mapper.SysUserMapper;
import com.zljin.common.core.constant.CommonConstants;
import com.zljin.common.core.util.SnowFlakeUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthService {

    SysUserMapper sysUserMapper;

    AuthenticationManager authenticationManager;

    UserService userDetailsService;

    public AuthService(SysUserMapper sysUserMapper, AuthenticationManager authenticationManager, UserService userDetailsService) {
        this.sysUserMapper = sysUserMapper;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public boolean register(SysUser user) {
        user.setUserId(SnowFlakeUtil.getInstance().nextId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            sysUserMapper.insert(user);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
        return true;
    }

    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);

        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return generateToken(userDetails);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, CommonConstants.SECRET)
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + CommonConstants.EXPIRATION_TIME * 1000);
    }
}
