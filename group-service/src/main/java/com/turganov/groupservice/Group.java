package com.turganov.groupservice;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_groups")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "faculty")
    private String faculty;

}
