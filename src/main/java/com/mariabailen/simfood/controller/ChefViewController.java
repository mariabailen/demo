package com.mariabailen.simfood.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mariabailen.simfood.model.Chef;
import com.mariabailen.simfood.service.ChefService;

@Controller
public class ChefViewController {

    @Autowired
    ChefService chefService;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/chef")
    public String chefDetail(@RequestParam(value = "id", required = true) String id, Model model,
            @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("tab", "chef");
        model.addAttribute("appName", appName);
        model.addAttribute("user", currentUser);
        if (currentUser != null) {
            model.addAttribute("isAdmin", currentUser.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN")));
        } else {
            model.addAttribute("isAdmin", false);
        }

        Optional<Chef> chef = chefService.getChef(id);
        if (chef.isPresent()) {
            model.addAttribute("chef", chef.get());
        }
        return "chef";
    }

    @GetMapping("/chefs")
    public String chefList(@RequestParam(value = "searchInput", required = false) String searchInput, Model model,
            @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("tab", "chef");
        model.addAttribute("appName", appName);
        model.addAttribute("user", currentUser);
        model.addAttribute("searchInput", searchInput);
        if (currentUser != null) {
            model.addAttribute("isAdmin", currentUser.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN")));
        } else {
            model.addAttribute("isAdmin", false);
        }
        if (searchInput == null || searchInput.isEmpty()) {
            model.addAttribute("chefs", chefService.getAllChefs());
        } else {
            model.addAttribute("chefs", chefService.getChefFilteredByNameAndLastName(searchInput));
        }
        return "chefs";
    }

    @PostMapping("/chefs")
    public String addChef(@RequestParam String name, @RequestParam String lastName, @RequestParam LocalDate birthDate,
            @RequestParam("image") MultipartFile imageFile, Model model, RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails currentUser)
            throws IOException {
        model.addAttribute("tab", "chef");
        model.addAttribute("appName", appName);
        model.addAttribute("user", currentUser);
        if (currentUser != null) {
            model.addAttribute("isAdmin", currentUser.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN")));
        } else {
            model.addAttribute("isAdmin", false);
        }
        String chefImagePath = null;
        if (!imageFile.isEmpty()) {
            // Save the image file to a local directory
            String uploadDirectory = "src/main/resources/static/images/";
            Path imagePath = Paths.get(uploadDirectory + imageFile.getOriginalFilename());
            System.out.println("Path: " + imagePath);
            Files.write(imagePath, imageFile.getBytes());
            chefImagePath = "images/" + imagePath.getFileName().toString();
            System.out.println("ImagePath: " + chefImagePath);
        }

        chefService.saveChef(name, lastName, birthDate, chefImagePath);
        return "redirect:/chefs";
    }

}
