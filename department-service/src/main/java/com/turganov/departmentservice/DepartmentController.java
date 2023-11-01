package com.turganov.departmentservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> listDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/departments")
    public ResponseEntity<String> saveDepartment(@RequestBody Department department) {
        try {
            departmentService.saveDepartment(department);
            return new ResponseEntity<>("Отделение успешно сохранено", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<String> editDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        try {
            Department editedDepartment = departmentService.editDepartment(updatedDepartment);

            if (editedDepartment != null) {
                return new ResponseEntity<>("Отделение успешно обновлено", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Отделение не найдено", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        try {
            boolean deleted = departmentService.deleteDepartment(id);

            if (deleted) {
                return new ResponseEntity<>("Отделение успешно удалено", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Отделение не найдено", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable Long id) {
        Department department = departmentService.findDepartmentById(id);

        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
