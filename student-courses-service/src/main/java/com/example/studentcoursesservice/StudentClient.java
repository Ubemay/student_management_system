package com.example.studentcoursesservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "student", url = "http://localhost:4950") // Замените на корректный порт вашего микросервиса Student
public interface StudentClient {

    @GetMapping("/student")
    ResponseEntity<List<Student>> getAllStudents();

    @GetMapping("/student/{id}")
    ResponseEntity<Student> getStudentById(@PathVariable Long id);
}

