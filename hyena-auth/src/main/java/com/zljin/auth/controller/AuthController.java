package com.zljin.auth.controller;

import com.zljin.auth.entity.SysUser;
import com.zljin.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SysUser user) {
        boolean register = authService.register(user);
        return ResponseEntity.ok("register " + (register ? "success" : "fail"));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(String username, String password) {
        String loginToken = authService.login(username, password);
        return ResponseEntity.ok("Bearer " + loginToken);
    }

}
