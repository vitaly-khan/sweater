package com.vitalykhan.sweater.controller;

import com.vitalykhan.sweater.domain.Role;
import com.vitalykhan.sweater.domain.User;
import com.vitalykhan.sweater.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", repository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String user(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String User(@RequestParam("userId") User user,
                       @RequestParam Map<String, String> form,
                       @RequestParam String username) {

        user.setUsername(username);

        //Getting set of all possible roles as Strings
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::getAuthority)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        repository.save(user);

        return "redirect:/user";
    }
}
