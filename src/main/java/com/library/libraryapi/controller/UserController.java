package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/users/")
public class UserController {

    @PostMapping("/register")
    public String registerUser(){
        return "calling registerUser";
    }

    @PostMapping("/login")
    public String loginUser(){
        return "calling loginUser";
    }

    @PutMapping("/update")
    public String updateUser(){
        return "calling updateUser";
    }
}
