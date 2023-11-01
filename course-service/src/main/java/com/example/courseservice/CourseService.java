package com.example.courseservice;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course saveCourse(Course course);

    boolean deleteCourse(Long id);

    Course editCourse(Course updatedCourse);
}
