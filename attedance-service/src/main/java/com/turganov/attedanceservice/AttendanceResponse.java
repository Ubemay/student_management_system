package com.turganov.attedanceservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponse {

    private Long id;
    private Course course;
    private Student student;
    private Date date;
    private boolean present;

}
