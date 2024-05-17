package org.example.hrm.repository;

import org.springframework.transaction.annotation.Transactional;

import org.example.hrm.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

@Transactional
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO employee (user_name, date_of_birth, address, gender, phone, email, department_id, positions_id, joining_date)" +
            "VALUE(?1,?2,?3,?4,?5,?6,?7,?8,?9)", nativeQuery = true)
    void insetEmployee(String userName, LocalDate dateOfBirth, String address, Boolean gender, String phone, String email, Integer departmentId, Integer positionId, LocalDate joiningDate);


    @Transactional
    @Modifying
    @Query(value = "UPDATE employee SET user_name = ?1, date_of_birth = ?2, address = ?3, gender = ?4, phone = ?5, email = ?6, department_id = ?7, positions_id=?8 WHERE id = ?9", nativeQuery = true)
    void updateEmployee(String userName, LocalDate dateOfBirth, String address, Boolean gender, String phone, String email, Integer departmentId, Integer positionId, Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employee SET department_id = null WHERE department_id = ?1", nativeQuery = true)
    void updateEmployeeByDepartmentNull(Integer departmentId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employee SET positions_id = null WHERE positions_id = ?1", nativeQuery = true)
    void updateEmployeeByPositionsNull(Integer positionsId);

    @Query("SELECT COUNT(e) FROM Employee e")
    Long countEmployees();

    @Query(value="SELECT COUNT(e) > 0 FROM Employee e WHERE e.phone = ?1")
    boolean existsByPhone(String phone);

    @Query(value="SELECT COUNT(e) > 0 FROM Employee e WHERE e.email = ?1")
    boolean existsByEmail(String email);

    @Query(value="SELECT COUNT(e) > 1 FROM Employee e WHERE e.phone = ?1")
    boolean existsByPhone1(String phone);

    @Query(value="SELECT COUNT(e) > 1 FROM Employee e WHERE e.email = ?1")
    boolean existsByEmail1(String email);

}
