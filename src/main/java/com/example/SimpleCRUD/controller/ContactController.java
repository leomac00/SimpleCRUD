package com.example.SimpleCRUD.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {
    @GetMapping
    public String test(){
        return "yay";
    }
}
