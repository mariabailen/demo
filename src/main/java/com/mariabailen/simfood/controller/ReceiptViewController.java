package com.mariabailen.simfood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mariabailen.simfood.model.Receipt;
import com.mariabailen.simfood.service.ReceiptService;


@Controller
public class ReceiptViewController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    ReceiptService receiptService;

    @GetMapping(path = { "/", "/home" })
    public String home(@RequestParam(value = "searchInput", required = false) String searchInput, Model model,
            @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("tab", "home");
        model.addAttribute("searchInput", searchInput);
        model.addAttribute("user", currentUser);
        if (currentUser != null) {
            model.addAttribute("isAdmin", currentUser.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN")));
        }

        if (searchInput == null || searchInput.isEmpty()) {
            model.addAttribute("receipts", receiptService.getReceipts());
        } else {
            model.addAttribute("receipts", receiptService.getReceiptsByName(searchInput));
        }
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/receipt")
    public String receiptDetail(@RequestParam(value = "id", required = true) Long id, Model model,
            @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("tab", "home");
        model.addAttribute("appName", appName);
        model.addAttribute("user", currentUser);
        if (currentUser != null) {
            model.addAttribute("isAdmin", currentUser.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN")));
        } else {
            model.addAttribute("isAdmin", false);
        }
        Optional<Receipt> receipt = receiptService.getReceipt(id);
        if (receipt.isPresent()) {
            model.addAttribute("receipt", receipt.get());
        }
        return "receipt";
    }

    @PostMapping("/add-ingredient")
    public String addIngredient(@RequestParam Long receiptId, @RequestParam String name, @RequestParam String quantity,
            Model model, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("tab", "home");
        model.addAttribute("appName", appName);
        model.addAttribute("user", currentUser);

        Optional<Receipt> receipt = receiptService.addIngredient(receiptId, name, quantity);

        if (receipt.isPresent()) {
            model.addAttribute("receipt", receipt.get());
        }
        redirectAttributes.addAttribute("id", receiptId);
        return "redirect:receipt";
    }

    @PostMapping("/edit-ingredient")
    public String editIngredient(@RequestParam Long id, @RequestParam String name,
            @RequestParam String quantity, Model model, RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("tab", "home");
        model.addAttribute("appName", appName);
        model.addAttribute("user", currentUser);
        if (currentUser != null) {
            model.addAttribute("isAdmin", currentUser.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN")));
        } else {
            model.addAttribute("isAdmin", false);
        }

        Optional<Receipt> receipt = receiptService.editIngredient(id, name, quantity);

        if (receipt.isPresent()) {
            model.addAttribute("receipt", receipt.get());
        }
        redirectAttributes.addAttribute("id", receipt.get().getId());
        return "redirect:receipt";
    }

    @PostMapping("/remove-receipt")
    public String removeReceipt(@RequestParam Long id, Model model, RedirectAttributes redirectAttributes,
    @AuthenticationPrincipal UserDetails currentUser) {
        receiptService.removeReceipt(id);
        return "redirect:home";
    }

}
