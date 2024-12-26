package com.proyectoaccenture.demo.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor.secured;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class TestAuthController {

    @GetMapping("/get")
    public String homeGet() {
        return "Home GET";
    }
    @PostMapping("/post")
    public String homePost() {
        return "Home POST";
    }
    @PutMapping("/put")
    public String homePut() {
        return "Home PUT";
    }
    @DeleteMapping("/delete")
    public String homeDelete() {
        return "Home DELETE";
    }
    @PatchMapping("/patch")
    public String homePatch() {
        return "Home PATCH";
    }



}