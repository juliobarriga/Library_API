package com.library.libraryapi.controller;

import com.library.libraryapi.model.User;
import com.library.libraryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired public void setUserService(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User userObject){
        return userService.registerUser(userObject);
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
