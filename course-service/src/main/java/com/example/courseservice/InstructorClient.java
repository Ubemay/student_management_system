package com.example.courseservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "instructor-client", url = "http://localhost:4955")
@CrossOrigin
public interface InstructorClient {

    @GetMapping("/instructors")
    ResponseEntity<List<Instructor>> getAllInstructors();

    @GetMapping("/instructors/{id}")
    ResponseEntity<Instructor> getInstructorsById(@PathVariable Long id);

}
