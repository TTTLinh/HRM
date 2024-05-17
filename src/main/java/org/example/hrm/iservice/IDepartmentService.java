package org.example.hrm.iservice;

import org.example.hrm.entity.Department;
import org.example.hrm.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {

    List<Department> findAll();

    void insertDepartment(String departmentName, String description);

    Optional<Department> findById(Integer integer);

    void updateDepartmentByDepartmentId(String departmentName, String description, Integer departmentId);

    void updateDepartmentByd(String departmentName, String description, Integer departmentId);

    Long countEmployees();

    boolean existsByName(String departmentName);

    boolean existsByName1(String departmentName);

    void deleteById(Integer integer);
}
