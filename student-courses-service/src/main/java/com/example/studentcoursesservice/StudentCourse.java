package com.example.studentcoursesservice;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students_courses")
@Data
@NoArgsConstructor
@Getter
@Setter
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_course_id")
    private Long id;

    @JoinColumn(name = "student_id")
    private Long studentId;

    @JoinColumn(name = "course_id")
    private Long courseId;

    @JoinColumn(name = "instructor_id")
    private Long instructorId;

}
