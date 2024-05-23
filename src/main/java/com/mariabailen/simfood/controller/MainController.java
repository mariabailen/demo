package com.mariabailen.simfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mariabailen.simfood.service.ReceiptService;


@Controller
public class MainController {

    @Autowired
    ReceiptService receiptService;
    
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("receipts", receiptService.getReceipts());
        model.addAttribute("appName", appName);
        return "home";
    }
    
}
