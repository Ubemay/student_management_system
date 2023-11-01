package com.turganov.departmentservice;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department saveDepartment(Department department);

    Department editDepartment(Department department);

    boolean deleteDepartment(Long id);

    Department findDepartmentById(Long id);
}
