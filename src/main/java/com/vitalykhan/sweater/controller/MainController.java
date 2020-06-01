package com.vitalykhan.sweater.controller;

import com.vitalykhan.sweater.domain.Message;
import com.vitalykhan.sweater.domain.User;
import com.vitalykhan.sweater.repository.MessageRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private MessageRepository repository;

    public MainController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages;
        if (Strings.isBlank(filter)) {
            messages = repository.findAll();
        } else {
            messages = repository.findByTag(filter);
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        repository.save(message);
        Iterable<Message> messages = repository.findAll();
        model.put("messages", messages);
        return "redirect:/main";
    }
}