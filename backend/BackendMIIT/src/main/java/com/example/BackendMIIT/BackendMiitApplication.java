package com.example.BackendMIIT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BackendMiitApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendMiitApplication.class, args);
    }
}
