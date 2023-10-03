package com.turganov.student_management_system.repository;

import com.turganov.student_management_system.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
}
