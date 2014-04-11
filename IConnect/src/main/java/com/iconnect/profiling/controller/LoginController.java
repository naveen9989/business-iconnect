package com.iconnect.profiling.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author NaveenKumar
 *
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String login(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = auth.getName();
		String userName=email.split("@")[0];
		System.out.println(userName);
		model.addAttribute("userName", userName);
		return "home";
	}

	// @PreAuthorize(value = "/loginform")
	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform(ModelMap model) {
		return "loginform";
	}

	@RequestMapping(value = "/failure", method = RequestMethod.GET)
	public String loginFailure(ModelMap model) {
	// System.out.println(model.containsAttribute("error"));
			model.addAttribute("invalid", "Invalid userName or Password");
		
		return "loginform";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		// model.addAttribute("message", "welcome");
		return "loginform";

	}

	@RequestMapping(value = "/deniedpage", method = RequestMethod.GET)
	public String denied(Model model) {
		// model.addAttribute("message", "welcome");
		return "deniedpage";

	}

	@RequestMapping(value = "/sessiontimeout", method = RequestMethod.GET)
	public String timeOut() {
		return "sessiontimeout";
	}
}
