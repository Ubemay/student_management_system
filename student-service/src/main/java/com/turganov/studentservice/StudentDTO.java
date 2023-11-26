package com.turganov.studentservice;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;
    private Integer age;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private Group group;


}
