package org.example.hrm.controlller;

import org.example.hrm.dto.EmployeeDTO;
import org.example.hrm.entity.Department;
import org.example.hrm.entity.Employee;
import org.example.hrm.entity.Positions;
import org.example.hrm.iservice.IDepartmentService;
import org.example.hrm.iservice.IEmployeeService;
import org.example.hrm.iservice.IPositionService;
import org.example.hrm.validation.CreatEmployeeValidator;
import org.example.hrm.validation.EditEmployeeValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployee;

    @Autowired
    private IDepartmentService iDepartment;

    @Autowired
    private IPositionService iPosition;

    @Autowired
    private CreatEmployeeValidator creatEmployeeValidator;

    @Autowired
    private EditEmployeeValidator editEmployeeValidator;

    @GetMapping("")
    public String view(Model model) {
        List<Employee> list = iEmployee.findAll();
        model.addAttribute("listEmployee", list);
        return "employee/employee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employeeOptional = iEmployee.findById(id);
        Employee entity = employeeOptional.get();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(entity, employeeDTO);
        Positions positions=entity.getPositions();
        Department department = entity.getDepartment();
        if (department != null) {
            employeeDTO.setDepartmentId(department.getDepartmentId());
        }
        if (positions !=null){
            employeeDTO.setPositionsId(positions.getPositionsId());
        }
        List<Department> departmentList = iDepartment.findAll();
        List<Positions> positionList = iPosition.findAll();
        model.addAttribute("employee", employeeDTO);
        model.addAttribute("departmentEntity", departmentList);
        model.addAttribute("positionEntity", positionList);
        return "employee/edit_employee";
    }


    @GetMapping("/viewFormCreatEmployee")
    public String viewForm(Model model){
        List<Department> department=iDepartment.findAll();
        List<Positions> positions=iPosition.findAll();
        EmployeeDTO employeeDTO=new EmployeeDTO();
        LocalDate currentDate = LocalDate.now();
        employeeDTO.setJoiningDate(String.valueOf(currentDate));
        model.addAttribute("departmentEntity", department);
        model.addAttribute("positionEntity", positions);
        model.addAttribute("employee", employeeDTO);
        return "employee/creat_employee";
    }

//    @GetMapping("/getPositions")
//    @ResponseBody
//    public List<Positions> getPositions(@RequestParam("departmentId") Integer departmentId) {
//        // Lấy danh sách các chức vụ tương ứng với departmentId từ cơ sở dữ liệu hoặc nguồn dữ liệu của bạn
//        List<Positions> positions = iPosition.fillAllByDepartmentId(departmentId);
//        return positions;
//    }

    @PostMapping("/creatEmployee")
    public String creatEmployee(Model model,
                                @Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
                                BindingResult result, RedirectAttributes redirectAttributes){
        creatEmployeeValidator.validate(employeeDTO, result);
        List<Department> department=iDepartment.findAll();
        List<Positions> positions=iPosition.findAll();
        if (result.hasErrors()){
            model.addAttribute("departmentEntity", department);
            model.addAttribute("positionEntity", positions);
        return"employee/creat_employee";
        }
        LocalDate dateOfBirth = LocalDate.parse(employeeDTO.getDateOfBirth());
        LocalDate joiningDate =LocalDate.parse(employeeDTO.getJoiningDate());
        iEmployee.insetEmployee(employeeDTO.getUserName(), dateOfBirth, employeeDTO.getAddress(),employeeDTO.getGender(), employeeDTO.getPhone(), employeeDTO.getEmail(), employeeDTO.getDepartmentId(), employeeDTO.getPositionsId(), joiningDate);
        redirectAttributes.addFlashAttribute("msg","Thêm hồ sơ nhân viên thành công");
        return "redirect:/admin/employee";
    }

    @PostMapping("/updateEmployee")
    public String saveEmployee(Model model,
             @Valid @ModelAttribute("employee") EmployeeDTO  employeeDTO,
                               BindingResult result, RedirectAttributes redirectAttributes) {
        editEmployeeValidator.validate(employeeDTO, result);
        List<Department> department=iDepartment.findAll();
        List<Positions> positions=iPosition.findAll();
        if (result.hasErrors()) {
            model.addAttribute("departmentEntity", department);
            model.addAttribute("positionEntity", positions);
            return "employee/edit_employee";
        }
        LocalDate dateOfBirth= LocalDate.parse(employeeDTO.getDateOfBirth());
        iEmployee.updateEmployee(employeeDTO.getUserName(),dateOfBirth, employeeDTO.getAddress(), employeeDTO.getGender(), employeeDTO.getPhone(), employeeDTO.getEmail(),employeeDTO.getDepartmentId(), employeeDTO.getPositionsId(), employeeDTO.getId());

        redirectAttributes.addFlashAttribute("msg","Chỉnh sửa hồ sơ thành công");
        return "redirect:/admin/employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes){
            iEmployee.deleteById(id);
        redirectAttributes.addFlashAttribute("msg","Hồ sơ nhân viên đã bị xóa");
        return "redirect:/admin/employee";
    }

    @GetMapping("/detail/{id}")
    public String detailEmployee(@PathVariable("id") Integer id, Model model){
        Optional<Employee> employee=iEmployee.findById(id);
        model.addAttribute("employee", employee);
        return "employee/detail_employee";
    }

}
