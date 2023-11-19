package com.turganov.instructorservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class InstructorController {

    private final InstructorService instructorService;
    private final DepartmentClient departmentClient;

    public InstructorController(InstructorService instructorService, DepartmentClient departmentClient) {
        this.instructorService = instructorService;
        this.departmentClient = departmentClient;
    }

    @GetMapping("/instructors")
    public ResponseEntity<List<InstructorResponse>> listInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        List<InstructorResponse> responseList = new ArrayList<>();

        for (Instructor instructor : instructors) {
            InstructorResponse response = new InstructorResponse();
            response.setId(instructor.getId());
            response.setFirstName(instructor.getFirstName());
            response.setLastName(instructor.getLastName());
            response.setEmail(instructor.getEmail());
            response.setPhoneNumber(instructor.getPhoneNumber());

            ResponseEntity<Department> departmentResponse = departmentClient.getDepartmentById(instructor.getDepartmentId());
            if (departmentResponse.getStatusCode() == HttpStatus.OK) {
                response.setDepartment(departmentResponse.getBody());
            }

            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
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
