package com.zljin.consumer.feign;

import com.zljin.consumer.feign.fallback.ProducerFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "hyena-producer",fallback = ProducerFallBackService.class)
public interface ProducerFeignService {

    @RequestMapping("producer/food/{which}")
    ResponseEntity<String> getFood(@PathVariable("which") String which);
}
