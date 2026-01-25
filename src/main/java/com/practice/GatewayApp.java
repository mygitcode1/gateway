package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class GatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Points to the Order service (Port 8083)
    @Value("${order.url:http://localhost:8083}")
    private String orderUrl;

    @GetMapping("/shop/{id}")
    public String shop(@PathVariable String id) {
        try {
            // The Gateway calls the Order Service
            String result = restTemplate().getForObject(orderUrl + "/place-order/" + id, String.class);
            return "--- Welcome to the Gateway ---\n" + result;
        } catch (Exception e) {
            return "Gateway Error: Order Service is unreachable at " + orderUrl;
        }
    }
}
