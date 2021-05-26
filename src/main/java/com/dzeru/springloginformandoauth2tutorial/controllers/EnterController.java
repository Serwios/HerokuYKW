package com.dzeru.springloginformandoauth2tutorial.controllers;

import com.dzeru.springloginformandoauth2tutorial.entities.User;
import com.dzeru.springloginformandoauth2tutorial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class EnterController {
	@Autowired
	private UserService userService;
	public static String userName;

	@GetMapping("/ykw")
	public String notes(Principal principal, Model model) {
		User user = (User) userService.loadUserByUsername(principal.getName());
		userName = user.getName();
		model.addAttribute("name", userName);
	    return "ykw";
	}
}
