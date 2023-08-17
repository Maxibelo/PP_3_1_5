
package ru.kata.spring.boot_security.PP_3_1_5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.PP_3_1_5.model.User;
import ru.kata.spring.boot_security.PP_3_1_5.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUserInfo(Model model, @AuthenticationPrincipal User user) {
      
        model.addAttribute("user", user);
        return "user";
    }
}
