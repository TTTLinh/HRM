package org.example.hrm.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import org.example.hrm.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO department(department_name,description)"+"VALUE(?1,?2)",nativeQuery = true)
    void insertDepartment(String departmentName, String description);

    @Transactional
    @Modifying
    @Query(value = "UPDATE department SET department_name = ?1, description = ?2 WHERE department_id = ?3", nativeQuery = true)
    void updateDepartmentByDepartmentId(String departmentName, String description, Integer departmentId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Department d SET d.departmentName = ?1, d.description = ?2 WHERE d.departmentId = ?3")
    void updateDepartmentByd(String departmentName, String description, Integer departmentId);

    @Query("SELECT COUNT(d) FROM Department d")
    Long countEmployees();

    @Query(value="SELECT COUNT(d) > 0 FROM Department d WHERE d.departmentName = ?1")
    boolean existsByName(String departmentName);

    @Query(value="SELECT COUNT(d) > 1 FROM Department d WHERE d.departmentName = ?1")
    boolean existsByName1(String departmentName);
}
