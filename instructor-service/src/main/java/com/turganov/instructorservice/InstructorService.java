package com.turganov.instructorservice;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors();

    Instructor saveInstructor(Instructor instructor);

    Instructor editInstructor(Instructor instructor);

    boolean deleteInstructor(Long id);

    Instructor findInstructorById(Long id);
}
