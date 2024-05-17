package org.example.hrm.service;

import org.example.hrm.entity.Department;
import org.example.hrm.iservice.IDepartmentService;
import org.example.hrm.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository iDepartmentRP;

    @Override
    public List<Department> findAll() {
        return iDepartmentRP.findAll();
    }



    @Override
    public void insertDepartment(String departmentName, String description) {
        iDepartmentRP.insertDepartment(departmentName, description);
    }

    @Override
    public Optional<Department> findById(Integer integer) {
        return iDepartmentRP.findById(integer);
    }


    @Override
    public void updateDepartmentByDepartmentId(String departmentName, String description, Integer departmentId) {
        iDepartmentRP.updateDepartmentByDepartmentId(departmentName, description, departmentId);
    }


    @Override
    public void updateDepartmentByd(String departmentName, String description, Integer departmentId) {
        iDepartmentRP.updateDepartmentByd(departmentName, description, departmentId);
    }


    @Override
    public Long countEmployees() {
        return iDepartmentRP.countEmployees();
    }


    @Override
    public boolean existsByName(String departmentName) {
        return iDepartmentRP.existsByName(departmentName);
    }

    @Override
    public boolean existsByName1(String departmentName) {
        return iDepartmentRP.existsByName1(departmentName);
    }

    @Override
    public void deleteById(Integer integer) {
        iDepartmentRP.deleteById(integer);
    }
}
