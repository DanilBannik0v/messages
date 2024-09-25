package com.example.jpa.controllers;

import com.example.jpa.models.MessageRequest;
import com.example.jpa.models.MessageResponse;
import com.example.jpa.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class RestController {
    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "Welcome";
    }

    @GetMapping(value = "/numberofmessages")
    public Integer getNumberOfMessages() {
        return messageService.findAll().size();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<MessageResponse> findAll() {
        return messageService.findAll();
    }

    @GetMapping(value = "/{messageId}", produces = APPLICATION_JSON_VALUE)
    public MessageResponse findById(@PathVariable Integer messageId) {
        return messageService.findById(messageId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public MessageResponse create(@RequestBody MessageRequest request) {
        return messageService.createMessage(request);
    }

    @PatchMapping(value = "/{messageId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public MessageResponse update(@PathVariable Integer messageId, @RequestBody MessageRequest request) {
        return messageService.update(messageId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{messageId}", produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer messageId) {
        messageService.delete(messageId);
    }
}
