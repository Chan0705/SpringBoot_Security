package com.security.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessErrorController {

    @GetMapping("/noneaccess")
    public String noneAccess() {

        return "error/noneAccess";

    }
}
