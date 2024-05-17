package org.example.hrm.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class EmployeeDTO {
    private Integer id;
    private String userName;

    private Boolean gender;

    private String dateOfBirth;

    private String phone;

    private String address;

    private String email;

    private Integer departmentId;


    private Integer positionsId;

    private String joiningDate;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, String userName, Boolean gender, String dateOfBirth, String phone, String address, String email, Integer departmentId, Integer positionsId, String joiningDate) {
        this.id = id;
        this.userName = userName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.departmentId = departmentId;
        this.positionsId = positionsId;
        this.joiningDate = joiningDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getPositionsId() {
        return positionsId;
    }

    public void setPositionsId(Integer positionsId) {
        this.positionsId = positionsId;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }
}