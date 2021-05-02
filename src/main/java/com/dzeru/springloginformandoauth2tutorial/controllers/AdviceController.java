package com.dzeru.springloginformandoauth2tutorial.controllers;

import com.dzeru.springloginformandoauth2tutorial.entities.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AdviceController {
    @ModelAttribute("user")
    public User populateUser() {
        return new User();
    }
}
