package com.turganov.groupservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "student", url = "http://localhost:4950")
@CrossOrigin
public interface StudentClient {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/student/group/{groupId}")
    ResponseEntity<List<Student>> getStudentByGroup(@PathVariable Long groupId);

}
