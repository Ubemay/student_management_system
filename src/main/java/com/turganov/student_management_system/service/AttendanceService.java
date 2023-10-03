package com.turganov.student_management_system.service;

import com.turganov.student_management_system.entity.Attendance;
import java.util.List;

public interface AttendanceService {
    List<Attendance> getAllAttendances();

    Attendance saveAttendance(Attendance attendance);

    Attendance editAttendance(Attendance attendance);

    boolean deleteAttendance(Long id);

    Attendance getAttendanceById(Long id);
}
