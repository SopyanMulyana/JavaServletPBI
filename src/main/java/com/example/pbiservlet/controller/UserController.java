package com.example.pbiservlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.pbiservlet.repository.User;
import com.example.pbiservlet.repository.UserRepository;


@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String listPost(Model model) {
		model.addAttribute("users", repository.findAll());
		return "users/list";
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);
        return new ModelAndView("redirect:/users");
    }
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
    public String newProject() {
        return "users/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("fullname") String fullName, String email, String grade) {
        repository.save(new User(fullName, email, grade));
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("user_id") long id,
    							@RequestParam("fullname") String fullName, String email, String grade) {
        User user = repository.findOne(id);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setGrade(grade);
        repository.save(user);
        return new ModelAndView("redirect:/users");
    }
    
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id,
                       Model model) {
        User user = repository.findOne(id);
        model.addAttribute("user", user);
        return "users/edit";
    }
    
    @RequestMapping(value="/search", method = RequestMethod.POST)
	public String searchName(@RequestParam("fullname") String fullName, @RequestParam("email") String email, Model model)
	{
		model.addAttribute("users", repository.findByFullNameContainingIgnoreCaseAndEmailContainingIgnoreCase(fullName,email));
		return "users/list";
	}
    
}
