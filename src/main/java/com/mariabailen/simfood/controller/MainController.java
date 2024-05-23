package com.mariabailen.simfood.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String home(Model model) {
        //model.addAttribute("receipts", )
        model.addAttribute("appName", appName);
        return "home";
    }
    
}
