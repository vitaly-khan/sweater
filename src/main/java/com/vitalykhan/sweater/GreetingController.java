package com.vitalykhan.sweater;

import com.vitalykhan.sweater.domain.Message;
import com.vitalykhan.sweater.repository.MessageRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    private MessageRepository repository;

    public GreetingController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = repository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Map<String, Object> model) {
        Message message = new Message(text, tag);
        repository.save(message);
        Iterable<Message> messages = repository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (Strings.isBlank(filter)) {
            messages = repository.findAll();
        } else {
            messages = repository.findByTag(filter);
        }
        model.put("messages", messages);
        return "main";
    }
}