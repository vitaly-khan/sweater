package com.vitalykhan.sweater.controller;

import com.vitalykhan.sweater.domain.Role;
import com.vitalykhan.sweater.domain.User;
import com.vitalykhan.sweater.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.EnumSet;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserRepository repository;

    public RegistrationController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String register() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = repository.findByUsername(user.getUsername());
        if (null != userFromDb) {
            model.put("message", "User exists already!");
            return "registration";
        }
        user.setRoles(EnumSet.of(Role.USER));

        repository.save(user);

        return "redirect:/login";

    }
}
