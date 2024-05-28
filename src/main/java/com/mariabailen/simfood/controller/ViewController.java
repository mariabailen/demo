package com.mariabailen.simfood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mariabailen.simfood.model.Chef;
import com.mariabailen.simfood.model.Receipt;
import com.mariabailen.simfood.service.ChefService;
import com.mariabailen.simfood.service.ReceiptService;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    ChefService chefService;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping(path = {"/", "/home"})
    public String home(@RequestParam(value = "searchInput", required = false) String searchInput, Model model) {
        model.addAttribute("tab", "home");
        model.addAttribute("searchInput", searchInput);
        if (searchInput == null || searchInput.isEmpty()) {
            model.addAttribute("receipts", receiptService.getReceipts());
        } else {
            model.addAttribute("receipts", receiptService.getReceiptsByNameOrDesc(searchInput));
        }
        model.addAttribute("appName", appName);
        return "home";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("tab", "login");
        model.addAttribute("appName", appName);
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


}
