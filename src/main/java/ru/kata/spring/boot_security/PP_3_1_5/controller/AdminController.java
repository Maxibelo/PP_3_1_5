package ru.kata.spring.boot_security.PP_3_1_5.controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.PP_3_1_5.model.User;
import ru.kata.spring.boot_security.PP_3_1_5.repository.RoleRepository;
import ru.kata.spring.boot_security.PP_3_1_5.service.RolesService;
import ru.kata.spring.boot_security.PP_3_1_5.service.UserService;

import javax.validation.Valid;

@Controller
public class AdminController {
    private final RolesService rolesService;
    private final UserService userService;

    @Autowired
    public AdminController(RolesService rolesService, UserService userService) {
        this.rolesService = rolesService;
        this.userService = userService;
    }

    @GetMapping("/admin")

    public String index(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("authUser", user);
        model.addAttribute("users", userService.index());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", rolesService.getRoles());
        return "admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin";
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "/admin";

        userService.save(user);
        return "redirect:/admin";
    }
}


