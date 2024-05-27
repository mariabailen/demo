package com.mariabailen.simfood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mariabailen.simfood.model.Receipt;
import com.mariabailen.simfood.service.ReceiptService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.unbescape.html.HtmlEscape;

@Controller
public class ViewController {

    @Autowired
    ReceiptService receiptService;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping(path = {"/", "/home"})
    public String home(Model model) {
        model.addAttribute("receipts", receiptService.getReceipts());
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

    @GetMapping("/receipt")
    public String receiptDetail(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("appName", appName);
        Optional<Receipt> receipt = receiptService.getReceipt(id);
        if (receipt.isPresent()) {
            model.addAttribute("receipt", receipt.get());
        }
        return "receipt";
    }

    @RequestMapping("/search")
    public String searchList(@RequestParam(value = "searchInput", required = false) String searchInput, Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("searchInput", searchInput);
        if (searchInput == null || searchInput.isEmpty()) {
            model.addAttribute("receipts", receiptService.getReceipts());
        } else {
            model.addAttribute("receipts", receiptService.getReceiptsByNameOrDesc(searchInput));
        }

        return "search";
    }
    


}
