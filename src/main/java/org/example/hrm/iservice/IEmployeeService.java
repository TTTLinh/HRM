package org.example.hrm.iservice;

import org.example.hrm.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> findAll();

    Optional<Employee> findById(Integer integer);


    void deleteById(Integer integer);


    void updateEmployee(String userName, LocalDate dateOfBirth, String address, Boolean gender, String phone, String email, Integer departmentId, Integer positionId, Integer id);

    void insetEmployee(String userName, LocalDate dateOfBirth, String address, Boolean gender, String phone, String email, Integer departmentId, Integer positionId, LocalDate joiningDate);

    void updateEmployeeByDepartmentNull(Integer departmentId);

    void updateEmployeeByPositionsNull(Integer positionsId);

    Long countEmployees();

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByPhone1(String phone);

    boolean existsByEmail1(String email);
}
