package com.turganov.student_management_system.service.impl;

import com.turganov.student_management_system.entity.Student;
import com.turganov.student_management_system.repository.StudentRepository;
import com.turganov.student_management_system.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return false;
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return studentRepository.findAllByFirstname(name);
    }

    @Override
    public List<Student> filterStudentsByAgeRange(Integer minAge, Integer maxAge) {
        if (minAge != null && maxAge != null) {
            return studentRepository.findByAgeBetween(minAge, maxAge);
        } else if (minAge != null) {
            return studentRepository.findByAgeGreaterThanEqual(minAge);
        } else if (maxAge != null) {
            return studentRepository.findByAgeLessThanEqual(maxAge);
        } else {
            return studentRepository.findAll();
        }
    }

}
