package com.example.courseservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CourseController {

    private final CourseService courseService;
    private final InstructorClient instructorClient;

    public CourseController(CourseService courseService, InstructorClient instructorClient) {
        this.courseService = courseService;
        this.instructorClient = instructorClient;
    }

//    @GetMapping("/courses")
//    public ResponseEntity<List<Course>> listCourses() {
//        List<Course> courses = courseService.getAllCourses();
//        return ResponseEntity.ok(courses);
//    }

//    @GetMapping("/instructors")
//    public ResponseEntity<List<InstructorResponse>> listInstructors() {
//        List<Instructor> instructors = instructorService.getAllInstructors();
//        List<InstructorResponse> responseList = new ArrayList<>();
//
//        for (Instructor instructor : instructors) {
//            InstructorResponse response = new InstructorResponse();
//            response.setId(instructor.getId());
//            response.setFirstName(instructor.getFirstName());
//            response.setLastName(instructor.getLastName());
//            response.setEmail(instructor.getEmail());
//            response.setPhoneNumber(instructor.getPhoneNumber());
//
//            ResponseEntity<Department> departmentResponse = departmentClient.getDepartmentById(instructor.getDepartmentId());
//            if (departmentResponse.getStatusCode() == HttpStatus.OK) {
//                response.setDepartment(departmentResponse.getBody());
//            }
//
//            responseList.add(response);
//        }
//
//        return ResponseEntity.ok(responseList);
//    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> listCourses() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseResponse> responseList = new ArrayList<>();

        for (Course course : courses) {
            CourseResponse response = new CourseResponse();
            response.setId(course.getId());
            response.setCourseName(course.getCourseName());
            response.setDescription(course.getDescription());
            response.setEndDate(course.getEndDate());
            response.setStartDate(course.getStartDate());

            ResponseEntity <Instructor> instructorResponse = instructorClient.getInstructorsById(course.getInstructorId());
            if (instructorResponse.getStatusCode() == HttpStatus.OK) {
                response.setInstructor(instructorResponse.getBody());
            }

            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
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

