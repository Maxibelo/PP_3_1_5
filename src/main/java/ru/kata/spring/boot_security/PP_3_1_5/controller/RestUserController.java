package ru.kata.spring.boot_security.PP_3_1_5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/active")
public class RestUserController {

    @GetMapping("")
    public ResponseEntity<UserDetails> showUser(@AuthenticationPrincipal User user) {

        return ResponseEntity.ok(user);
    }

}