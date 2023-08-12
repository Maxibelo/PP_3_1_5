package ru.kata.spring.boot_security.PP_3_1_4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.PP_3_1_4.model.User;
import ru.kata.spring.boot_security.PP_3_1_4.repository.RoleRepository;
import ru.kata.spring.boot_security.PP_3_1_4.service.RolesService;
import ru.kata.spring.boot_security.PP_3_1_4.service.UserService;

import javax.validation.Valid;

@Controller
public class AdminController {
    private final RolesService rolesService;
    private final UserService userService;
    private final RoleRepository roleRepository;


    @Autowired
    public AdminController(RolesService rolesService, UserService userService, RoleRepository roleRepository) {
        this.rolesService = rolesService;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin")

    public String index(Model model) {

        model.addAttribute("authUser", userService.showOne());
        model.addAttribute("users", userService.index());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", rolesService.getRoles());

        return "admin";
    }
    @GetMapping("/adminUser")
    public String adminUser(Model model) {
        User user = userService.showOne();
        model.addAttribute("user", user);
        return "adminUser";
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
