package org.example.hrm.controlller;


import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Trả về tên của trang đăng nhập thực tế, ví dụ: "login.html" hoặc "login.jsp"
    }

    @PostMapping("/login")
    public String loginSubmit(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Đăng nhập thành công, chuyển hướng đến trang dashboard
            return "redirect:/admin/employee";
        } catch (AuthenticationException e) {
            // Đăng nhập thất bại, chuyển hướng đến trang đăng nhập với thông báo lỗi
            return "redirect:/login?error";
        }
    }

    @GetMapping("/403")
    public ModelAndView authorizaFalse() {
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login"; // Chuyển hướng người dùng đến trang đăng nhập với thông báo logout
    }



}
