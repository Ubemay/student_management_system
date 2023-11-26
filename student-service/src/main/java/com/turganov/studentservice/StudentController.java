package com.turganov.studentservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class StudentController {

    private final StudentService studentService;
    private final GroupClient groupClient;

    public StudentController(StudentService studentService, GroupClient groupClient) {
        this.studentService = studentService;
        this.groupClient = groupClient;
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> listStudents() {
        List<Student> students = studentService.getAllStudent();
        List<StudentDTO> responseList = new ArrayList<>();

        for (Student student : students) {
            StudentDTO response = new StudentDTO();
            response.setId(student.getId());
            response.setAge(student.getAge());
            response.setEmail(student.getEmail());
            response.setFirstname(student.getFirstname());
            response.setLastname(student.getLastname());
            response.setPassword(student.getPassword());

            ResponseEntity <Group> groupResponse = groupClient.getGroupsById(student.getGroupId());
            if (groupResponse.getStatusCode() == HttpStatus.OK) {
                response.setGroup(groupResponse.getBody());
            }

            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
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
    public ResponseEntity<List<Student>> findStudentsByName(@PathVariable String name) {
        List<Student> students = studentService.findStudentsByName(name);
        if (!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filterByAgeRange")
    public ResponseEntity<List<Student>> filterStudentsByAgeRange(@RequestParam(required = false) Integer minAge, @RequestParam(required = false) Integer maxAge) {
        List<Student> filteredStudents = studentService.filterStudentsByAgeRange(minAge, maxAge);
        return ResponseEntity.ok(filteredStudents);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/student/group/{groupId}")
    public ResponseEntity<?> getStudentByGroup(@PathVariable Long groupId) {
        List<Student> students = studentService.getStudentByGroup(groupId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/student/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        Student student = studentService.findStudentByEmail(email);

        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
