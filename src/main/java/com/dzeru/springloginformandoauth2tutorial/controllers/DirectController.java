package com.dzeru.springloginformandoauth2tutorial.controllers;

import com.dzeru.springloginformandoauth2tutorial.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class DirectController {
    @GetMapping("/prj")
    public String openPrj() {
        return "prj";
    }

    @GetMapping("/lib")
    public String openLib() {

        return "lib";
    }

    @GetMapping("/personal")
    public String openPersonalCabinet() {
        return "personal";
    }
}