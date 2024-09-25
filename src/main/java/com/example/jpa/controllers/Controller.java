package com.example.jpa.controllers;

import com.example.jpa.models.MessageRequest;
import com.example.jpa.models.MessageResponse;
import com.example.jpa.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private MessageService messageService;
    private String name = "default user";

    @GetMapping("/")
    public String init(Model model) {
        model.addAttribute("name", "name");
        return "home";
    }

    @GetMapping("/home")
    public String initHome() {
        return "home";
    }

    @PostMapping("/")
    public String form(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("messages", messageService.findAll());
        model.addAttribute("request", new MessageRequest());
        this.name = name;
        return "messageweb";
    }

    @GetMapping("/messageweb")
    public String initMessageweb(@ModelAttribute MessageRequest request, @ModelAttribute String name, Model model) {
        model.addAttribute("name", this.name);
        model.addAttribute("messages", messageService.findAll());
        model.addAttribute("request", new MessageRequest());
        return "messageweb";
    }

    @PostMapping("/messageweb")
    public String createMessage(@ModelAttribute MessageRequest request, @ModelAttribute String name, Model model) {
        model.addAttribute("name", this.name);
        model.addAttribute("messages", messageService.findAll());
        model.addAttribute("request", new MessageRequest());
        request.setSenderName(this.name);
        messageService.createMessage(request);
        return "messageweb";
    }

    @GetMapping("/filteredmessages/{name}")
    public String getMessagesBySenderName(@PathVariable String name, Model model) {
        model.addAttribute("messages", messageService.findBySenderName(name));
        return "filteredmessages";
    }
}
