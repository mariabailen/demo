package com.mariabailen.simfood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mariabailen.simfood.model.Chef;
import com.mariabailen.simfood.service.ChefService;

@Controller
public class ChefViewController {

    @Autowired
    ChefService chefService;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/chef")
    public String chefDetail(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("tab", "chef");
        model.addAttribute("appName", appName);
        Optional<Chef> chef = chefService.getChef(id);
        if (chef.isPresent()) {
            model.addAttribute("chef", chef.get());
        }
        return "chef";
    }

    @GetMapping("/chefs")
    public String chefList(@RequestParam(value = "searchInput", required = false) String searchInput, Model model) {
        model.addAttribute("tab", "chef");
        model.addAttribute("appName", appName);

        model.addAttribute("searchInput", searchInput);
        if (searchInput == null || searchInput.isEmpty()) {
            model.addAttribute("chefs", chefService.getAllChefs());
        } else {
            model.addAttribute("chefs", chefService.getChefFilteredByNameAndLastName(searchInput));
        }
        return "chefs";
    }

}