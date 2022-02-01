package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/users/")
public class UserController {

    @PostMapping("/register")
    public String registerUser(){
        return "calling registerUser";
    }
}
