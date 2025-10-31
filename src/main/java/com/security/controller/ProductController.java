package com.security.controller;


import com.security.DTO.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/valid01")
@Controller
public class ProductController {

    @GetMapping
    public String showForm(@ModelAttribute ProductDTO productDTO) {
        //product 모델을 보냄
        return "validation/viewPage01";
    }

    @PostMapping
    public String addProduct(@Valid ProductDTO productDTO,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            //에러가 있으면 상품 등록 폼 이동
            return "validation/viewPage01";
        }
        //정상이면 홈으로 이동
        return "redirect:/";
    }
}