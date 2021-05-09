package com.dzeru.springloginformandoauth2tutorial.controllers;

import com.dzeru.springloginformandoauth2tutorial.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DirectController {
    @Autowired
    private PostRepo postRepo;

    @GetMapping("/prj")
    public String openPrj(Model model) {
        PostMakerController.posts = postRepo.findAll();
        model.addAttribute("posts", PostMakerController.posts);
        model.addAttribute("name", EnterController.userName);
        return "prj";
    }

    @GetMapping("/lib")
    public String openLib(Model model) {
        model.addAttribute("name", EnterController.userName);
        return "lib";
    }
}