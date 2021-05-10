package com.dzeru.springloginformandoauth2tutorial.controllers;

import com.dzeru.springloginformandoauth2tutorial.entities.Post;
import com.dzeru.springloginformandoauth2tutorial.entities.User;
import com.dzeru.springloginformandoauth2tutorial.repos.PostRepo;
import com.dzeru.springloginformandoauth2tutorial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    public static List<Post> posts = new ArrayList<>();

    @PostMapping("/prj")
    public String makePost(String title, String content,
                           Principal principal, Model model) {


        if (title.length() > 30) {
            model.addAttribute("text", "Title size > 30");
        } else if (content.length() < 50) {
            model.addAttribute("text", "Your content < 50 symbols");
        } else if (content.length() > 500) {
            model.addAttribute("text", "Your content > 500 symbols");
        } else {
            Post post = new Post();
            User user = (User) userService.loadUserByUsername(principal.getName());

            post.setContent(content);
            post.setAuthor(user.getName());
            post.setParagraph(title);

            postRepo.saveAndFlush(post);
            posts = postRepo.findAll();
            Collections.reverse(posts);

            model.addAttribute("posts", posts);
            model.addAttribute("name", EnterController.userName);
            return "prj";
        }

        return "prj";
    }
}
