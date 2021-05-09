package com.dzeru.springloginformandoauth2tutorial.controllers;

import com.dzeru.springloginformandoauth2tutorial.entities.Post;
import com.dzeru.springloginformandoauth2tutorial.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class PostMakerController {
    @Autowired
    private PostRepo postRepo;

    public static List<Post> posts = new ArrayList<>();

    @PostMapping("/prj")
    public String makePost(String title, String content,
                           Principal principal, Model model) {

        int wordsLength = content.split(" ").length;

        if (title.length() > 30) {
            model.addAttribute("text", "Title size > 30");
        } else if (wordsLength < 30) {
            model.addAttribute("text", "Your content < 30 words");
        } else if (wordsLength > 100) {
            model.addAttribute("text", "Your content > 100 words");
        } else {
            Post post = new Post();
            post.setContent(content);
            post.setAuthor(principal.getName());
            post.setParagraph(title);

            postRepo.save(post);
            posts = postRepo.findAll();

            model.addAttribute("posts", posts);

            return "prj";
        }

        return "prj";
    }
}
