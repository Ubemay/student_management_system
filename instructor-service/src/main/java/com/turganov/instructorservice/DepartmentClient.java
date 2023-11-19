package com.turganov.instructorservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "department-client", url = "http://localhost:4954")
@CrossOrigin
public interface DepartmentClient {

    @GetMapping("/departments")
    ResponseEntity<List<Department>> getAllDepartments();

    @GetMapping("/departments/{id}")
    ResponseEntity<Department> getDepartmentById(@PathVariable Long id);

}
