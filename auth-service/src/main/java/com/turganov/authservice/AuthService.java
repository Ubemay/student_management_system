package com.turganov.authservice;

import com.turganov.authservice.Student;
import com.turganov.authservice.StudentServiceClient;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final StudentServiceClient studentServiceClient;

    public AuthService(StudentServiceClient studentServiceClient) {
        this.studentServiceClient = studentServiceClient;
    }

    public boolean loginUser(String email, String password) {
        Student student = studentServiceClient.getStudentByEmail(email);
        return student != null && student.getPassword().equals(password);
    }

    public void registerUser(RegisterRequest registerRequest) {
        Student newStudent = new Student();
        newStudent.setEmail(registerRequest.getEmail());
        newStudent.setPassword(registerRequest.getPassword());
        newStudent.setFirstname(registerRequest.getFirstname());
        newStudent.setLastname(registerRequest.getLastname());
        newStudent.setAge(registerRequest.getAge());
        newStudent.setGroupId(registerRequest.getGroupId());

        studentServiceClient.saveStudent(newStudent);
    }
}
