package com.turganov.groupservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Long student_id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer age;
    private Long groupId;

}
