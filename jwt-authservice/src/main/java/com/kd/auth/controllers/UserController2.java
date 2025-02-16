package com.kd.auth.controllers;

import com.kd.auth.data.RegisterUserData;
import com.kd.auth.data.UserData;
import com.kd.auth.entities.User;
import com.kd.auth.services.UserService;
import com.kd.auth.responses.LoginResponse;
import com.kd.auth.services.AuthenticationService;
import com.kd.auth.services.JwtService;
import com.kd.auth.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@RequestMapping("/find")
@RestController
public class UserController2 {

    private final UserService userService;

    public UserController2(UserService userService) {

        this.userService = userService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}