package com.example.studentcoursesservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseResponse {

    private Long id;
    private Student student;
    private Course course;
    private Instructor instructor;

}
