package com.example.studentcoursesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StudentCoursesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentCoursesServiceApplication.class, args);
    }

}
