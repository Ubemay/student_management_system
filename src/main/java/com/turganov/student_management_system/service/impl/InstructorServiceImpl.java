package com.turganov.student_management_system.service.impl;

import com.turganov.student_management_system.entity.Instructor;
import com.turganov.student_management_system.repository.InstructorRepository;
import com.turganov.student_management_system.service.InstructorService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor editInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public boolean deleteInstructor(Long id) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (optionalInstructor.isPresent()) {
            instructorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }
}

