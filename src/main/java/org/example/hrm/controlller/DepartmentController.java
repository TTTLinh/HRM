package org.example.hrm.controlller;


import org.example.hrm.entity.Department;
import org.example.hrm.entity.Employee;
import org.example.hrm.iservice.IDepartmentService;
import org.example.hrm.iservice.IEmployeeService;
import org.example.hrm.validation.CreatDepartmentValidator;
import org.example.hrm.validation.EditDepartmentValidator;
import org.example.hrm.validation.EditPositionsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService iDepartment;

    @Autowired
    private IEmployeeService iEmployee;

    @Autowired
    private CreatDepartmentValidator creatDepartmentValidator;

    @Autowired
    private EditDepartmentValidator editDepartmentValidator;
    @GetMapping("")
    public String viewDepartment(Model model){
        List<Department> list= iDepartment.findAll();
        model.addAttribute("list", list);
        return "department/department";
    }

    @GetMapping("/viewFormCreatDepartment")
    public String viewFormCreat(Model model){
        Department department=new Department();
        model.addAttribute("department", department);
        return "department/creat_department";
    }

    @PostMapping("/creatDepartment")
    public String creat(Model model,
                        @ModelAttribute("department") Department department,
                        BindingResult result, RedirectAttributes redirectAttributes){
        creatDepartmentValidator.validate(department, result);

        if(result.hasErrors()){
            return "department/creat_department";
        }

        redirectAttributes.addFlashAttribute("msg","Thêm phòng ban thành công");
        iDepartment.insertDepartment(department.getDepartmentName(),department.getDescription());
        return "redirect:/admin/department";
    }

    @GetMapping("/edit/{id}")
    public String viewFormEdit(Model model,
                       @PathVariable("id") Integer id){
        Optional<Department> departmentOptional=iDepartment.findById(id);
            Department department=departmentOptional.get();
        model.addAttribute("department",department);
        return "department/edit_department";
    }

    @PostMapping("/editDepartment")
    public String edit(Model model,
                       @ModelAttribute("department") Department department,
                       BindingResult result, RedirectAttributes redirectAttributes){
        editDepartmentValidator.validate(department, result);
        if (result.hasErrors()){
            return "department/edit_department";
        }
        iDepartment.updateDepartmentByDepartmentId(department.getDepartmentName(), department.getDescription(), department.getDepartmentId());
        redirectAttributes.addFlashAttribute("msg","Chỉnh sửa phòng ban thành công");
        return "redirect:/admin/department";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") Integer id,
                         RedirectAttributes redirectAttributes){
        iEmployee.updateEmployeeByDepartmentNull(id);
        iDepartment.deleteById(id);
        redirectAttributes.addFlashAttribute("msg","Phòng ban đã được xóa");
        return "redirect:/admin/department";
    }
}
