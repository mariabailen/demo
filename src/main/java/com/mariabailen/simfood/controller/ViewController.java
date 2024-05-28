package com.mariabailen.simfood.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ViewController {


    @Value("${spring.application.name}")
    String appName;


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
