package com.proyectoaccenture.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Devuelve el archivo login.html desde src/main/resources/templates/
    }
}