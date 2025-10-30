package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/auth")
    public String method1(){
        return "auth/viewPage";
    }

    @GetMapping("/home/main")
    public String method2(Model model){
        model.addAttribute("data", "homePage.html");
        return "auth/homePage";
    }

    
    // user 전용
    @GetMapping("/user/main")
    public String method3(Model model){
        model.addAttribute("data", "userPage.html");
        return "auth/userPage";
    }

    // admin 전용
    @GetMapping("/admin/main")
    public String method4(Model model){
        model.addAttribute("data", "adminPage.html");
        return "auth/adminPage";
    }

    // login page
    @GetMapping("/login")
    public String login(){

        return "/member/login";
    }

}
