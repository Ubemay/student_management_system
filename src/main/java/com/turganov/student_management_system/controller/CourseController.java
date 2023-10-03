package com.turganov.student_management_system.controller;

import com.turganov.student_management_system.entity.Course;
import com.turganov.student_management_system.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> listCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/courses")
    public ResponseEntity<String> saveCourse(@RequestBody Course course) {
        try {
            courseService.saveCourse(course);
            return new ResponseEntity<>("Курс успешно сохранен", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<String> editCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        try {
            Course editedCourse = courseService.editCourse(updatedCourse);

            if (editedCourse != null) {
                return new ResponseEntity<>("Курс успешно обновлен", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Курс не найден", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        try {
            boolean deleted = courseService.deleteCourse(id);

            if (deleted) {
                return new ResponseEntity<>("Курс успешно удален", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Курс не найден", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);

        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
