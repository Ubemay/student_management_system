package com.example.studentcoursesservice;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;

    public StudentCourseServiceImpl(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public List<StudentCourse> getAllStudentCourses() {
        return studentCourseRepository.findAll();
    }

    @Override
    public StudentCourse saveStudentCourse(StudentCourse studentCourse) {
        return studentCourseRepository.save(studentCourse);
    }

    @Override
    public StudentCourse editStudentCourse(StudentCourse studentCourse) {
        return studentCourseRepository.save(studentCourse);
    }

    @Override
    public boolean deleteStudentCourse(Long id) {
        Optional<StudentCourse> studentCourseOptional = studentCourseRepository.findById(id);
        if (studentCourseOptional.isPresent()) {
            studentCourseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public StudentCourse getStudentCourseById(Long id) {
        return studentCourseRepository.findById(id).orElse(null);
    }
}
