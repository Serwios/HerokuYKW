package com.dzeru.springloginformandoauth2tutorial.controllers;

import com.dzeru.springloginformandoauth2tutorial.entities.Post;
import com.dzeru.springloginformandoauth2tutorial.entities.User;
import com.dzeru.springloginformandoauth2tutorial.repos.PostRepo;
import com.dzeru.springloginformandoauth2tutorial.services.UserService;
import com.dzeru.springloginformandoauth2tutorial.services.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PostMakerController {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UtilityService utilityService;

    public static List<Post> posts = new ArrayList<>();

    @GetMapping("/prj/delete")
    public String deleteAllPosts(Principal principal, Model model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("name", EnterController.userName);

        if (user.getName().equals("Serwios")) {
            postRepo.deleteAll();

            return "prj";
        }

        return "prj";
    }

    @PostMapping("/prj")
    public String makePost(String title, String content,
                           Principal principal, Model model) {

        model.addAttribute("name", EnterController.userName);

        if (title.length() > 30) {
            model.addAttribute("text", "Title size > 30");
        } else if (content.trim().length() < 10) {
            model.addAttribute("text", "Your content < 10 symbols");
        } else if (content.trim().length() > 1000) {
            model.addAttribute("text", "Your content > 1000 symbols");
        } else {
            Post post = new Post();
            User user = (User) userService.loadUserByUsername(principal.getName());

            post.setContent(content);
            post.setAuthor(user.getName());
            post.setParagraph(title);
            post.setDate(utilityService.findCurrentDate());

            postRepo.saveAndFlush(post);
            posts = postRepo.findAll();
            Collections.reverse(posts);

            model.addAttribute("posts", posts);
            return "prj";
        }

        return "prj";
    }
}
