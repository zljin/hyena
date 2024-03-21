package com.zljin.producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("producer")
public class ProducerController {

    @GetMapping("/food/{which}")
    public ResponseEntity<String> getFood(@PathVariable("which") String which) {
        return ResponseEntity.ok(which + ":" + new Random().nextInt(10));
    }
}
