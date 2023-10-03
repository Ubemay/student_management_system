package com.turganov.student_management_system.controller;

import com.turganov.student_management_system.entity.StudentCourse;
import com.turganov.student_management_system.service.StudentCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StudentCourseController {

    private StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @GetMapping("/student-courses")
    public ResponseEntity<List<StudentCourse>> listStudentCourses() {
        List<StudentCourse> studentCourses = studentCourseService.getAllStudentCourses();
        return ResponseEntity.ok(studentCourses);
    }

    @PostMapping("/student-courses")
    public ResponseEntity<String> saveStudentCourse(@RequestBody StudentCourse studentCourse) {
        try {
            studentCourseService.saveStudentCourse(studentCourse);
            return new ResponseEntity<>("Студентский курс успешно сохранен", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/student-courses/{id}")
    public ResponseEntity<String> editStudentCourse(@PathVariable Long id, @RequestBody StudentCourse updatedStudentCourse) {
        try {
            StudentCourse editedStudentCourse = studentCourseService.editStudentCourse(updatedStudentCourse);

            if (editedStudentCourse != null) {
                return new ResponseEntity<>("Студентский курс успешно обновлен", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Студентский курс не найден", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/student-courses/{id}")
    public ResponseEntity<String> deleteStudentCourse(@PathVariable Long id) {
        try {
            boolean deleted = studentCourseService.deleteStudentCourse(id);

            if (deleted) {
                return new ResponseEntity<>("Студентский курс успешно удален", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Студентский курс не найден", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student-courses/{id}")
    public ResponseEntity<StudentCourse> findStudentCourseById(@PathVariable Long id) {
        StudentCourse studentCourse = studentCourseService.getStudentCourseById(id);

        if (studentCourse != null) {
            return new ResponseEntity<>(studentCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
