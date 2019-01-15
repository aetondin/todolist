package ie.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ie.todolist.models.Users;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView index(Users users) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
}
