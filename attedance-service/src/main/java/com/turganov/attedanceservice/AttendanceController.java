package com.turganov.attedanceservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final CourseClient courseClient;
    private final StudentClient studentClient;

    public AttendanceController(AttendanceService attendanceService, CourseClient courseClient, StudentClient studentClient) {
        this.attendanceService = attendanceService;
        this.courseClient = courseClient;
        this.studentClient = studentClient;
    }


    @GetMapping("/attendances")
    public ResponseEntity<List<AttendanceResponse>> listAttendances() {
        List<Attendance> attendances = attendanceService.getAllAttendances();
        List<AttendanceResponse> responseList = new ArrayList<>();

        for (Attendance attendance : attendances) {
            AttendanceResponse response = new AttendanceResponse();
            response.setId(attendance.getId());
            response.setPresent(attendance.isPresent());
            response.setDate(attendance.getDate());

            ResponseEntity<Course> courseResponse = courseClient.getCourseById(attendance.getCourseId());
            if (courseResponse.getStatusCode() == HttpStatus.OK) {
                response.setCourse(courseResponse.getBody());
            }

            ResponseEntity<Student> studentResponse = studentClient.getStudentById(attendance.getStudentId());
            if (studentResponse.getStatusCode() == HttpStatus.OK) {
                response.setStudent(studentResponse.getBody());
            }

            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/attendances")
    public ResponseEntity<String> saveAttendance(@RequestBody Attendance attendance) {
        try {
            attendanceService.saveAttendance(attendance);
            return new ResponseEntity<>("Посещаемость успешно сохранена", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/attendances/{id}")
    public ResponseEntity<String> editAttendance(@PathVariable Long id, @RequestBody Attendance updatedAttendance) {
        try {
            Attendance editedAttendance = attendanceService.editAttendance(updatedAttendance);

            if (editedAttendance != null) {
                return new ResponseEntity<>("Посещаемость успешно обновлена", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Посещаемость не найдена", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/attendances/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        try {
            boolean deleted = attendanceService.deleteAttendance(id);

            if (deleted) {
                return new ResponseEntity<>("Посещаемость успешно удалена", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Посещаемость не найдена", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/attendances/{id}")
    public ResponseEntity<Attendance> findAttendanceById(@PathVariable Long id) {
        Attendance attendance = attendanceService.getAttendanceById(id);

        if (attendance != null) {
            return new ResponseEntity<>(attendance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}