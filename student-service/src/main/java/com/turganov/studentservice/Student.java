package com.turganov.studentservice;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "group_id")
    private Long groupId;

}
