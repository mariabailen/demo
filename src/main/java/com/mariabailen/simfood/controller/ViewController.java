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
        model.addAttribute("appName", appName);
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/chef")
    public String chefDetail(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("appName", appName);
        Optional<Chef> chef = chefService.getChef(id);
        if (chef.isPresent()) {
            model.addAttribute("chef", chef.get());
        }
        return "chef";
    }

    @GetMapping("/chefs")
    public String chefList(Model model) {
        model.addAttribute("appName", appName);
        java.util.List<Chef> chefs = chefService.getAllChefs();
        model.addAttribute("chefs", chefs);
        return "chefs";
    }


    @GetMapping("/receipt")
    public String receiptDetail(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("appName", appName);
        Optional<Receipt> receipt = receiptService.getReceipt(id);
        if (receipt.isPresent()) {
            model.addAttribute("receipt", receipt.get());
        }
        return "receipt";
    }
  


}
