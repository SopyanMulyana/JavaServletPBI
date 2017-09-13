package com.example.pbiservlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String login() {
		return "index";
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("username") String username, String password) {
        if (username.toLowerCase().equals("admin") && password.equals("admin")) {
        	return new ModelAndView("redirect:/users");
        }
        else {
        	return new ModelAndView("redirect:/");
        }
    }
}
