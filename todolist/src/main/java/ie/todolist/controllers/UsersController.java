package ie.todolist.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.todolist.daos.UsersDAO;
import ie.todolist.models.Users;
import ie.todolist.validation.UsersValidation;

@Controller
@RequestMapping("/home")
public class UsersController {
	
	@Autowired
	private UsersDAO userDao;
	
	@Autowired
	private HomeController homeController;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsersValidation());
	}
	
	@RequestMapping(value="/enter", method= RequestMethod.POST, name="enter")
	public ModelAndView enter(@Valid Users users, BindingResult result, HttpSession session) {
		
		if(result.hasErrors()) {
			return homeController.index(users);
		}
		
		Users user = userDao.login(users);
		
		ModelAndView modelAndView = null;
		if(user != null) {
			session.setAttribute("users", user);
			modelAndView = new ModelAndView("redirect:/activities/form");
		}else {
			modelAndView = new ModelAndView("redirect:/");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/save", method= RequestMethod.POST, name="saveUser")
	public ModelAndView save(Users users, RedirectAttributes redirectAttributes) {

		userDao.save(users);
		
		redirectAttributes.addFlashAttribute("success", "User successfully registered");
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		
		return modelAndView;
	}
	
	@RequestMapping(name="userLogOff")
	public ModelAndView userLogOff(HttpSession session) {
		
		session.removeAttribute("users");
		
		return homeController.index(new Users());
	}
	
}
