package com.example.studentcoursesservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}