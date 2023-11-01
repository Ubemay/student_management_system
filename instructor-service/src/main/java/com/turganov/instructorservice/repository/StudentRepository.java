package com.turganov.instructorservice.repository;

import com.turganov.student_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByFirstname(String name);


    List<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    List<Student> findByAgeGreaterThanEqual(Integer minAge);

    List<Student> findByAgeLessThanEqual(Integer maxAge);

}

