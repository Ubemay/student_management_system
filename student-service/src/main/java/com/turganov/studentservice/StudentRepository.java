package com.turganov.studentservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByFirstname(String name);


    List<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    List<Student> findByAgeGreaterThanEqual(Integer minAge);

    List<Student> findByAgeLessThanEqual(Integer maxAge);

    List<Student> findStudentByGroupId(Long groupId);

}
