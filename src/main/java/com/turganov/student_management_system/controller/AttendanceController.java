package com.turganov.student_management_system.controller;

import com.turganov.student_management_system.entity.Attendance;
import com.turganov.student_management_system.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AttendanceController {

    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/attendances")
    public ResponseEntity<List<Attendance>> listAttendances() {
        List<Attendance> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
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
