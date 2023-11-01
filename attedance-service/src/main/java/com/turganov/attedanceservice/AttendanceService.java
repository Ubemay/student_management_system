package com.turganov.attedanceservice;

import java.util.List;

public interface AttendanceService {
    List<Attendance> getAllAttendances();

    Attendance saveAttendance(Attendance attendance);

    Attendance editAttendance(Attendance attendance);

    boolean deleteAttendance(Long id);

    Attendance getAttendanceById(Long id);
}
