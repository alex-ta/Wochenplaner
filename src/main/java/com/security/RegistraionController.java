package com.security;

import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.data.wrapper.SecurityUserWrapper;

@Controller
public class RegistraionController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Collection<String> roles = new LinkedList<>();
		for (GrantedAuthority role : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			roles.add(role.getAuthority());
		}
		model.addAttribute("username", name);
		roles.stream().forEach(System.out::println);
		System.out.println(roles.contains("ROLE_ADMIN"));
		model.addAttribute("roles", roles);
		return "index";

	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String printUsers(ModelMap model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		model.addAttribute("username", name);
		return "users";
	}
	

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new SecurityUserWrapper());
		return "registration";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

}