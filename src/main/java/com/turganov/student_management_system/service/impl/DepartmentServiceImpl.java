package com.turganov.student_management_system.service.impl;

import com.turganov.student_management_system.entity.Department;
import com.turganov.student_management_system.repository.DepartmentRepository;
import com.turganov.student_management_system.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department editDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public boolean deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Department findDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
