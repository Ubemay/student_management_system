package com.turganov.studentservice;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();

    Student saveStudent(Student employee);

    Student editStudent(Student employee);

    boolean deleteStudent(Long id);

    Student findStudentById(Long id);

    List<Student> findStudentsByName(String name);

    List<Student> filterStudentsByAgeRange(Integer minAge, Integer maxAge);

    List<Student> getStudentByGroup(Long groupId);

    Student findStudentByEmail(String email);

}
