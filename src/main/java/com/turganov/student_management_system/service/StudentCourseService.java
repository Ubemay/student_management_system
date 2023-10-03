package com.turganov.student_management_system.service;

import com.turganov.student_management_system.entity.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    List<StudentCourse> getAllStudentCourses();

    StudentCourse saveStudentCourse(StudentCourse studentCourse);

    StudentCourse editStudentCourse(StudentCourse studentCourse);

    boolean deleteStudentCourse(Long id);

    StudentCourse getStudentCourseById(Long id);
}
