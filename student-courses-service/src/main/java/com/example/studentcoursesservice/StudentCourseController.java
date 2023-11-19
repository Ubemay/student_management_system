package com.example.studentcoursesservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class StudentCourseController {

    private StudentCourseService studentCourseService;
    private final CourseClient courseClient;
    private final StudentClient studentClient;
    private final InstructorClient instructorClient;

    public StudentCourseController(StudentCourseService studentCourseService, CourseClient courseClient, StudentClient studentClient, InstructorClient instructorClient) {
        this.studentCourseService = studentCourseService;
        this.courseClient = courseClient;
        this.studentClient = studentClient;
        this.instructorClient = instructorClient;
    }

    @GetMapping("/student-courses")
    public ResponseEntity<List<StudentCourseResponse>> listStudentCourses() {
        List<StudentCourse> studentCourses = studentCourseService.getAllStudentCourses();
        List<StudentCourseResponse> responseList = new ArrayList<>();

        for (StudentCourse studentCourse : studentCourses) {
            StudentCourseResponse response = new StudentCourseResponse();
            response.setId(studentCourse.getId());

            ResponseEntity<Course> courseResponse = courseClient.getCourseById(studentCourse.getCourseId());
            if (courseResponse.getStatusCode() == HttpStatus.OK) {
                response.setCourse(courseResponse.getBody());
            }

            ResponseEntity<Student> studentResponse = studentClient.getStudentById(studentCourse.getStudentId());
            if (studentResponse.getStatusCode() == HttpStatus.OK) {
                response.setStudent(studentResponse.getBody());
            }

            ResponseEntity<Instructor> instructorResponse = instructorClient.getInstructorById(studentCourse.getInstructorId());
            if (instructorResponse.getStatusCode() == HttpStatus.OK) {
                response.setInstructor(instructorResponse.getBody());
            }

            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
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
    public ResponseEntity<StudentCourseResponse> findStudentCourseById(@PathVariable Long id) {
        StudentCourse studentCourse = studentCourseService.getStudentCourseById(id);

        if (studentCourse != null) {
            StudentCourseResponse response = new StudentCourseResponse();
            response.setId(studentCourse.getId());

            ResponseEntity<Course> courseResponse = courseClient.getCourseById(studentCourse.getCourseId());
            if (courseResponse.getStatusCode() == HttpStatus.OK) {
                response.setCourse(courseResponse.getBody());
            }

            ResponseEntity<Student> studentResponse = studentClient.getStudentById(studentCourse.getStudentId());
            if (studentResponse.getStatusCode() == HttpStatus.OK) {
                response.setStudent(studentResponse.getBody());
            }

            ResponseEntity<Instructor> instructorResponse = instructorClient.getInstructorById(studentCourse.getInstructorId());
            if (instructorResponse.getStatusCode() == HttpStatus.OK) {
                response.setInstructor(instructorResponse.getBody());
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
