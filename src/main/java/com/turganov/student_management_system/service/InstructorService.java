package com.turganov.student_management_system.service;

import com.turganov.student_management_system.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors();

    Instructor saveInstructor(Instructor instructor);

    Instructor editInstructor(Instructor instructor);

    boolean deleteInstructor(Long id);

    Instructor findInstructorById(Long id);
}
