package com.example.studentcoursesservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "instructor", url = "http://localhost:4955")
public interface InstructorClient {

    @GetMapping("/instructors")
    ResponseEntity<List<Instructor>> getAllInstructors();

    @GetMapping("/instructors/{id}")
    ResponseEntity<Instructor> getInstructorById(@PathVariable Long id);

}
