package org.example.hrm.validation;


import org.example.hrm.dto.EmployeeDTO;
import org.example.hrm.entity.Department;
import org.example.hrm.iservice.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreatDepartmentValidator  implements Validator {

    @Autowired
    private IDepartmentService iDepartment;

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Department department=(Department) target;
        if (department.getDepartmentName() == null || department.getDepartmentName().strip().isEmpty()){
            errors.rejectValue("departmentName", "NameRequired", "Không được để trống!!!");
        }else if (iDepartment.existsByName(department.getDepartmentName())){
            errors.rejectValue("departmentName", "Name", "Phòng ban đã tồn tại!!!");
        }

    }
}