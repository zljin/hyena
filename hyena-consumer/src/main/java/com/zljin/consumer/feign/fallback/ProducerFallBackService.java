package com.zljin.consumer.feign.fallback;


import com.zljin.consumer.feign.ProducerFeignService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProducerFallBackService implements ProducerFeignService {

    @Override
    public ResponseEntity<String> getFood(String which) {
        return ResponseEntity.ok("nothing");
    }
}
