package com.turganov.student_management_system.service.impl;

import com.turganov.student_management_system.entity.Course;
import com.turganov.student_management_system.repository.CourseRepository;
import com.turganov.student_management_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.orElse(null);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public boolean deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return false;
    }

    @Override
    public Course editCourse(Course updatedCourse) {
        return courseRepository.save(updatedCourse);
    }
}
