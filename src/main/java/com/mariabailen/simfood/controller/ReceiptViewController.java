package com.mariabailen.simfood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mariabailen.simfood.model.Receipt;
import com.mariabailen.simfood.service.ReceiptService;

@Controller
public class ReceiptViewController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    ReceiptService receiptService;

    @GetMapping("/receipt")
    public String receiptDetail(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("tab", "home");
        model.addAttribute("appName", appName);
        Optional<Receipt> receipt = receiptService.getReceipt(id);
        if (receipt.isPresent()) {
            model.addAttribute("receipt", receipt.get());
        }
        return "receipt";
    }

}
