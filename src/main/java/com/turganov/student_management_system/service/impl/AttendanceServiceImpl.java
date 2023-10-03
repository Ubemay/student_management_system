package com.turganov.student_management_system.service.impl;

import com.turganov.student_management_system.entity.Attendance;
import com.turganov.student_management_system.repository.AttendanceRepository;
import com.turganov.student_management_system.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance editAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public boolean deleteAttendance(Long id) {
        Optional<Attendance> attendanceOptional = attendanceRepository.findById(id);
        if (attendanceOptional.isPresent()) {
            attendanceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }
}
