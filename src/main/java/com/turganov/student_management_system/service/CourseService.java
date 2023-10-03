package com.turganov.student_management_system.service;

import com.turganov.student_management_system.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course saveCourse(Course course);

    boolean deleteCourse(Long id);

    Course editCourse(Course updatedCourse);
}
