package com.zljin.consumer.controller;

import com.zljin.consumer.feign.ProducerFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Resource
    ProducerFeignService producerFeignService;

    @GetMapping("/eat/{which}")
    public ResponseEntity<String> consumerFood(@PathVariable("which") String which) {
        return producerFeignService.getFood(which);
    }
}
