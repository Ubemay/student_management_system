package com.turganov.student_management_system.service;

import com.turganov.student_management_system.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudent();

    Student saveStudent(Student employee);

    Student editStudent(Student employee);

    boolean deleteStudent(Long id);

    Student findStudentById(Long id);

    Student findStudentsByName(String name);

    List<Student> filterStudentsByAgeRange(Integer minAge, Integer maxAge);
}
