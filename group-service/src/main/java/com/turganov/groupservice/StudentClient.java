package com.turganov.groupservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "student", url = "http://localhost:4950")
public interface StudentClient {

    @GetMapping("/student/group/{groupId}")
    ResponseEntity<List<Student>> getStudentByGroup(@PathVariable Long groupId);

}
