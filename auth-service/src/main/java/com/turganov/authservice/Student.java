package com.turganov.authservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {
    private String firstname;
    private String lastname;
    private String email;
    private Integer age;
    private String password;
    private String groupId;
}
