package com.mariabailen.simfood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mariabailen.simfood.model.Receipt;
import com.mariabailen.simfood.service.ReceiptService;
import org.springframework.web.bind.annotation.RequestParam;




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

    @GetMapping("/login")
    public String getMethodName(Model model) {
        model.addAttribute("appName", appName);
        return "login";
    }

    @GetMapping("/receipt")
    public String getMethodName(@RequestParam(value="id", required = true) Long id, Model model) {
        model.addAttribute("appName", appName);
        Optional<Receipt> receipt = receiptService.getReceipt(id);
        if (receipt.isPresent()) {
            model.addAttribute("receipt", receipt.get());
        }
        return "receipt";
    }    
    
}
