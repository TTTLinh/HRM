package org.example.hrm.service;

import org.example.hrm.entity.Employee;
import org.example.hrm.iservice.IEmployeeService;
import org.example.hrm.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository iEmployeeRP;

    @Override
    public List<Employee> findAll() {
        return iEmployeeRP.findAll();
    }


    @Override
    public Optional<Employee> findById(Integer integer) {
        return iEmployeeRP.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        iEmployeeRP.deleteById(integer);
    }


    @Override
    public void updateEmployee(String userName, LocalDate dateOfBirth, String address, Boolean gender, String phone, String email, Integer departmentId, Integer positionId, Integer id) {
        iEmployeeRP.updateEmployee(userName, dateOfBirth, address, gender, phone, email, departmentId, positionId, id);
    }

    @Override
    public void insetEmployee(String userName, LocalDate dateOfBirth, String address, Boolean gender, String phone, String email, Integer departmentId, Integer positionId, LocalDate joiningDate) {
        iEmployeeRP.insetEmployee(userName, dateOfBirth, address, gender, phone, email, departmentId, positionId, joiningDate);
    }


    @Override
    public void updateEmployeeByDepartmentNull(Integer departmentId) {
        iEmployeeRP.updateEmployeeByDepartmentNull(departmentId);
    }




    @Override
    public void updateEmployeeByPositionsNull(Integer positionsId) {
        iEmployeeRP.updateEmployeeByPositionsNull(positionsId);
    }

    @Override
    public Long countEmployees() {
        return iEmployeeRP.countEmployees();
    }


    @Override
    public boolean existsByPhone(String phone) {
        return iEmployeeRP.existsByPhone(phone);
    }

    @Override
    public boolean existsByEmail(String email) {
        return iEmployeeRP.existsByEmail(email);
    }


    @Override
    public boolean existsByPhone1(String phone) {
        return iEmployeeRP.existsByPhone1(phone);
    }


    @Override
    public boolean existsByEmail1(String email) {
        return iEmployeeRP.existsByEmail1(email);
    }
}
