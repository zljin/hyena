package com.zljin.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class HyenaAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(HyenaAuthApplication.class, args);
    }

}
