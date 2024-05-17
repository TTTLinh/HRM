package org.example.hrm.validation;

import org.example.hrm.dto.EmployeeDTO;
import org.example.hrm.iservice.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class CreatEmployeeValidator implements Validator {

    @Autowired
    private IEmployeeService iEmployee;
    String regex = "^(032|033|034|035|036|037|038|039|070|079|077|076|078|083|084|085|081|082|05[0-9]|056|058|059)\\d{7}$";


    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        EmployeeDTO employeeDTO = (EmployeeDTO) target;
        LocalDate currentDate = LocalDate.now();

        if (employeeDTO.getUserName() == null || employeeDTO.getUserName().strip().isEmpty()) {
            errors.rejectValue("userName", "NameRequired", "Không được để trống!!!");
        } else if (!employeeDTO.getUserName().matches("^[a-zA-Z\\p{L} ]+$")) {
            errors.rejectValue("userName", "InvalidCharacters", "Không chứa kí tự đặc biệt và số!!!");
        } else if (employeeDTO.getUserName().length() < 6) {
            errors.rejectValue("userName", "NameNotLength", "Không được ít hơn 6 kí tự!!!");
        } else if (employeeDTO.getUserName().split(" ").length < 2) {
            errors.rejectValue("userName", "FullNameRequired", "Vui lòng nhập đầy đủ họ và tên!!!");
        }

        if (employeeDTO.getDateOfBirth() == null || employeeDTO.getDateOfBirth().strip().isEmpty()) {
            errors.rejectValue("dateOfBirth", "Required", "Không được để trống !!!");
        } else {
            try {
                LocalDate dateOfBirth = LocalDate.parse(employeeDTO.getDateOfBirth());
                int yearOfBirth = dateOfBirth.getYear();
                int currentYear = LocalDate.now().getYear();

                // Kiểm tra năm của ngày sinh
                if (yearOfBirth < 1900 || yearOfBirth >= currentYear) {
                    errors.rejectValue("dateOfBirth", "Invalid", "Ngày sinh không hợp lệ!!!");
                }
            } catch (DateTimeParseException e) {
                errors.rejectValue("dateOfBirth", "Invalid", "Định dạng ngày sinh không hợp lệ!!!");
            }
        }

        if (employeeDTO.getGender() == null){
            errors.rejectValue("gender", "Required", "Không được để trống !!!");
        }

        if (employeeDTO.getPhone() == null || employeeDTO.getPhone().trim().isEmpty()) {
            errors.rejectValue("phone", "PhoneRequire", "Không được để trống");
        } else {
            if (!employeeDTO.getPhone().matches(regex)) {
                errors.rejectValue("phone", "PhoneFormat", "Số điện thoại sai!!");
            } else if(iEmployee.existsByPhone(employeeDTO.getPhone())){
                errors.rejectValue("phone", "Phone", "Số điện thoại đã tồn tại!!");
            }
        }


        if (employeeDTO.getAddress() == null || employeeDTO.getAddress().trim().isEmpty()) {
            errors.rejectValue("address", "AddressRequired", "Không được để trống!!!");
        } else {
            if (!employeeDTO.getAddress().matches("^[a-zA-Z\\p{L}0-9 ]+$")) {
                errors.rejectValue("address", "InvalidCharacters", "Nhập sai địa chỉ!");
            } else if (employeeDTO.getAddress().length() < 3) {
                errors.rejectValue("address", "Invalid", "Địa chỉ không được ít hơn 3 kí tự!!");
            }
        }

        if (employeeDTO.getEmail() == null || employeeDTO.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "EmailRequire", "Không được để trống");
        } else {
            if (!employeeDTO.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                errors.rejectValue("email", "EmailFormat", "Nhập email không đúng!!");
            }else if (iEmployee.existsByEmail(employeeDTO.getEmail())){
                errors.rejectValue("email", "Email", "Nhập email đã ồn tại!!");
            }
        }

        if (employeeDTO.getDepartmentId() == null){
            errors.rejectValue("departmentId", "Require", "Không được để trống");
        }

        if (employeeDTO.getPositionsId() == null){
            errors.rejectValue("positionsId","Require", "Không được để trống");
        }
    }
}
