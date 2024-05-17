package org.example.hrm.controlller;


import org.example.hrm.entity.Positions;
import org.example.hrm.iservice.IEmployeeService;
import org.example.hrm.iservice.IPositionService;
import org.example.hrm.validation.CreatPositionValidator;
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
@RequestMapping("/admin/position")
public class PositionController {

    @Autowired
    private IPositionService iPosition;

    @Autowired
    private IEmployeeService iEmployee;

    @Autowired
    private CreatPositionValidator creatPositionValidator;

    @Autowired
    private EditPositionsValidator editPositionsValidator;

    @GetMapping("")
    public String viewList(Model model){

        List<Positions> positionsList=iPosition.findAll();

        model.addAttribute("position",positionsList);
        return "position/position";
    }

    @GetMapping("/viewFormCreatPosition")
    public  String viewFormCreat(Model model){
        Positions positions=new Positions();
        model.addAttribute("positions",positions);
        return "position/creat_position";
    }

    @PostMapping("/creatPosition")
    public String creat(Model model, @ModelAttribute("positions") Positions positions,
                        BindingResult result, RedirectAttributes redirectAttributes){
        creatPositionValidator.validate(positions, result);
        if (result.hasErrors()){
            return "position/creat_position";
        }
        iPosition.insertPosition(positions.getPositionsName(), positions.getDescription());
        redirectAttributes.addFlashAttribute("msg","Thêm vị trí làm việc thành công");
        return "redirect:/admin/position";
    }

    @GetMapping("/edit/{id}")
    public String viewFormEdit(Model model,
                               @PathVariable("id") Integer id){

        Optional<Positions> optionalPositions=iPosition.findById(id);
        Positions positions=optionalPositions.get();
        model.addAttribute("positions", positions);
        return "position/edit_position";
    }

    @PostMapping("/editPositions")
    public String edit(Model model,
                       @ModelAttribute("positions") Positions positions,
                       BindingResult result, RedirectAttributes redirectAttributes){
        editPositionsValidator.validate(positions, result);
        if (result.hasErrors()){
            return "position/edit_position";
        }
        iPosition.updatePositionsById(positions.getPositionsName(), positions.getDescription(), positions.getPositionsId());
       redirectAttributes.addFlashAttribute("msg","Vị trí làm việc đã được chỉnh sửa");
        return "redirect:/admin/position";
    }

    @GetMapping("/delete/{id}")
    public String delete (Model model, @PathVariable("id") Integer id,
                          RedirectAttributes redirectAttributes){
            iEmployee.updateEmployeeByPositionsNull(id);
            iPosition.deleteById(id);
        redirectAttributes.addFlashAttribute("msg","Đã xóa vị trí làm việc");
        return "redirect:/admin/position";

    }

}
