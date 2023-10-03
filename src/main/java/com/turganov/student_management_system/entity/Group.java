package com.turganov.student_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "student_groups")
@Data
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "faculty")
    private String faculty;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

}