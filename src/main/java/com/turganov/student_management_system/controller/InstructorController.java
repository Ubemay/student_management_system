package com.turganov.student_management_system.controller;

import com.turganov.student_management_system.entity.Instructor;
import com.turganov.student_management_system.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> listInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

    @PostMapping("/instructors")
    public ResponseEntity<String> saveInstructor(@RequestBody Instructor instructor) {
        try {
            instructorService.saveInstructor(instructor);
            return new ResponseEntity<>("Успешно сохранено", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/instructors/{id}")
    public ResponseEntity<String> editInstructor(@PathVariable Long id, @RequestBody Instructor updatedInstructor) {
        try {
            Instructor editedInstructor = instructorService.editInstructor(updatedInstructor);
            if (editedInstructor != null) {
                return new ResponseEntity<>("Успешно обновлено", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Преподаватель не найден", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/instructors/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable Long id) {
        try {
            boolean deleted = instructorService.deleteInstructor(id);
            if (deleted) {
                return new ResponseEntity<>("Успешно удалено", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Преподаватель не найден", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<Instructor> findInstructorById(@PathVariable Long id) {
        Instructor instructor = instructorService.findInstructorById(id);
        if (instructor != null) {
            return new ResponseEntity<>(instructor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
