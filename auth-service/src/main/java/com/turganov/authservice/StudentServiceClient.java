package com.turganov.authservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "student-service-auth", url = "http://localhost:4950")
public interface StudentServiceClient {

    @GetMapping("/student/email/{email}")
    Student getStudentByEmail(@PathVariable String email);

    @PostMapping(value = "/student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Student saveStudent(@RequestBody Student student);
}

