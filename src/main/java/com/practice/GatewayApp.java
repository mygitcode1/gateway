package com.practice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class GatewayApp {
    public static void main(String[] args) { SpringApplication.run(GatewayApp.class, args); }
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/shop/{id}")
    public String shop(@PathVariable String id) {
        return restTemplate.getForObject("http://localhost:8083/place-order/" + id, String.class);
    }
}
