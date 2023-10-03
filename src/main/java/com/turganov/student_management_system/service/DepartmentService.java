package com.turganov.student_management_system.service;

import com.turganov.student_management_system.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department saveDepartment(Department department);

    Department editDepartment(Department department);

    boolean deleteDepartment(Long id);

    Department findDepartmentById(Long id);
}
