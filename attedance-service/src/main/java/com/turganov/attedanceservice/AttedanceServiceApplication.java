package com.turganov.attedanceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AttedanceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttedanceServiceApplication.class, args);
    }

}
