package com.example.studentcoursesservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "course", url = "http://localhost:4952") // Подставьте корректный порт вашего микросервиса Course
public interface CourseClient {

    @GetMapping("/courses")
    ResponseEntity<List<Course>> getAllCourses();

    @GetMapping("/courses/{id}")
    ResponseEntity<Course> getCourseById(@PathVariable Long id);
}
