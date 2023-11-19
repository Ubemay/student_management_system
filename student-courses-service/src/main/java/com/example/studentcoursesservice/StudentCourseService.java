package com.example.studentcoursesservice;

import java.util.List;

public interface StudentCourseService {
    List<StudentCourse> getAllStudentCourses();

    StudentCourse saveStudentCourse(StudentCourse studentCourse);

    StudentCourse editStudentCourse(StudentCourse studentCourse);

    boolean deleteStudentCourse(Long id);

    StudentCourse getStudentCourseById(Long id);
}
