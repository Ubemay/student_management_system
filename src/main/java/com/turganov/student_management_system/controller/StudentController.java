package com.turganov.student_management_system.controller;

import com.turganov.student_management_system.entity.Student;
import com.turganov.student_management_system.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public ResponseEntity<List<Student>> listStudents() {
        List<Student> students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/student")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        try {
            studentService.saveStudent(student);
            return new ResponseEntity<>("успешно сохранено", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<String> editStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        try {

            Student editedStudent = studentService.editStudent(updatedStudent);

            if (editedStudent != null) {
                return new ResponseEntity<>("успешно обновлено", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Студент не найден", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {

            boolean deleted = studentService.deleteStudent(id);

            if (deleted) {
                return new ResponseEntity<>("успешно удалено", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Студент не найден", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);

        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student/name/{name}")
    public ResponseEntity<Student> findStudentByName(@PathVariable String name) {
        Student student = studentService.findStudentsByName(name);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filterByAgeRange")
    public ResponseEntity<List<Student>> filterStudentsByAgeRange(@RequestParam(required = false) Integer minAge, @RequestParam(required = false) Integer maxAge) {
        List<Student> filteredStudents = studentService.filterStudentsByAgeRange(minAge, maxAge);
        return ResponseEntity.ok(filteredStudents);
    }

    @GetMapping("/me")
    public ResponseEntity<Student> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Student student = studentService.findStudentsByName(username);

            if (student != null) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
