package ru.kata.spring.boot_security.PP_3_1_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.PP_3_1_3.model.User;
import ru.kata.spring.boot_security.PP_3_1_3.repository.RoleRepository;
import ru.kata.spring.boot_security.PP_3_1_3.service.RolesService;
import ru.kata.spring.boot_security.PP_3_1_3.service.UserService;

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
        //this.userValidator = userValidator;
    }

    @GetMapping("/admin")

    public String index(Model model) {
        model.addAttribute("users", userService.index());

        return "usersList";
    }

    @GetMapping("/admin/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "show";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String showEditUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        model.addAttribute("allRoles", rolesService.getRoles());
        return "edit";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "edit";
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/new")
    public String showPageCreatingUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", rolesService.getRoles());
        return "new";
    }

    @PostMapping("/admin/user")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {

        //userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "/new";

        userService.save(user);
        return "redirect:/admin";
    }
}
