package com.example.studentcoursesservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private Long id;
    private String courseName;
    private String description;
    private Date startDate;
    private Date endDate;

}

