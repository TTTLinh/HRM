package org.example.hrm.validation;

import org.example.hrm.entity.Positions;
import org.example.hrm.iservice.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EditPositionsValidator implements Validator {
    @Autowired
    IPositionService iPosition;
    @Override
    public boolean supports(Class<?> clazz) {
        return Positions.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Positions positions = (Positions) target;
        if (positions.getPositionsName()==null ||positions.getPositionsName().trim().isEmpty()){
            errors.rejectValue("PositionsName", "NameRequired", "Không được để trống!!!");
        }else if (iPosition.existsByPositionsName1(positions.getPositionsName())){
            errors.rejectValue("PositionsName", "Name", "Chức vụ đã tồn tại!!!");
        }
    }
}
